import { useEffect } from "react";
import { useState } from "react";
import "../css/chatMessage.css";
const ImgExit = "/src/img/Exit_ChatW.png";

let stompClient = null;

function ChatMex({ object, email, username }) {
  return (
    <div className="ChatMex">
      {object["email"] == email ? (
        <div className="MessageBox MyMessageBox">
          <div className="sender">{username}</div>
          <div className="content">{object["content"]}</div>
          <div className="date">{object["date"]}</div>
        </div>
      ) : (
        <div className="MessageBox OtherMessageBox">
          <div className="sender">{object["sender"]}</div>
          <div className="content">{object["content"]}</div>
          <div className="date">{object["date"]}</div>
        </div>
      )}
    </div>
  );
}

function ChatMessage({
  userProfile,
  userEmail,
  username,
  active,
  message,
  handleClick,
  subscribe,
  leave,
}) {
  // In ascolto sulla variabile "message == nome della chatroom"
  const [state, setState] = useState(false);
  useEffect(() => {
    //console.log("In ChatMessage! ")
    //retrieveMessagesList();
    setState(active);
    connect();
  }, [message]);

  // ----------------------------------------------------
  // Dichiarazione delle variabili
  // ----------------------------------------------------

  // Type: JOIN, CHAT, LEAVE
  const [chatMessages, setChatMessages] = useState([]);
  const [status, setStatus] = useState("disconnected!");
  const [inputValue, setInputValue] = useState("");
  let actualRoom = message.replace("#", "");
  let array = [];

  function updateMessages(newMessage) {
    console.log("Aggiorno lista ", newMessage);
    setChatMessages([...array, newMessage]);
    array = [...array, newMessage];
  }

  const handleChange = (event) => {
    setInputValue(event.target.value);
  };

  function retrieveMessagesList() {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/api/chat/messages",
      data: { room: actualRoom },
      contentType: "application/json",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods":
          "GET, POST, PUT, DELETE, PATCH, OPTIONS",
        "Access-Control-Allow-Headers":
          "X-Requested-With, content-type, Authorization",
      },
    }).then(function (data) {
      let lista = data.reverse();
      array = lista;
      setChatMessages(lista);
    });
  }

  function connect() {
    setStatus("connecting...");
    let socket = new SockJS("http://localhost:8091/websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
  }

  // se la connessione va bene, ci dobbiamo iscrivere al topic pubblico...
  // inoltriamo una richiesta al message broker per registrare l'utente username
  function onConnected() {
    stompClient.subscribe("/topic/messages/" + actualRoom, onMessageReceived);
    stompClient.send(
      "/app/chat/" + actualRoom,
      {},
      JSON.stringify({
        sender: userProfile["name"],
        email: userProfile["email"],
        type: "JOIN",
      })
    );
  }

  function onError(error) {
    alert(
      "ERROR! Non è stato possibile stabilire alcuna connessione con il webSocket server! "
    );
  }

  function disconnect() {
    if (stompClient !== null) {
      stompClient.disconnect();
      stompClient = null;
      setStatus("disconnected!");
    }
  }

  function back() {
    $(".ChatMessage").fadeOut(300, function () {
      disconnect();
      handleClick(-1);
    });
  }

  function onMessageReceived(payload) {
    // recupero dal payload l'oggetto message
    let new_message = JSON.parse(payload.body);
    if (new_message.type === "JOIN") {
      if (new_message.email == userProfile["email"]) {
        setStatus("joined!");
        retrieveMessagesList();
      }

      // SEGANLARE LO STATUS DEGLI ALTRI UTENTI QUI!!!
      //status = 'joined!';
    } else if (new_message.type === "LEAVE") {
      setStatus("left");
    } else {
      updateMessages(new_message);
    }
  }

  function sendMessage(event) {
    if (event && event.key !== "Enter") {
      return;
    }
    if (inputValue != "" && stompClient) {
      let new_chatMessage = {
        sender: userProfile["name"],
        email: userProfile["email"],
        content: inputValue,
        type: "CHAT",
        room: actualRoom,
        date: new Date().toLocaleString("en-GB"),
      };

      console.log(new_chatMessage);
      stompClient.send(
        "/app/chat/" + actualRoom,
        {},
        JSON.stringify(new_chatMessage)
      );
      setInputValue("");
    }
  }

  function joinChat() {
    let chat = {
      userEmail: userProfile["email"],
      chatRoom: message,
    };

    $.ajax({
      type: "POST",
      url: "http://localhost:8080/api/chat/preferred/insert",
      data: JSON.stringify(chat),
      contentType: "application/json",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods":
          "GET, POST, PUT, DELETE, PATCH, OPTIONS",
        "Access-Control-Allow-Headers":
          "X-Requested-With, content-type, Authorization",
      },
    }).always(function (xhr) {
      if (xhr.status == 200) {
        console.log("Add chatRoom");
        // SE OK DOPO LA RICHIESTA AL SERVER
        $(".right #leaveText").fadeIn(1);
        $("#chatBar").fadeIn(1);
        $("#chatJoin").fadeOut(1);
        setState(true);
        subscribe(message);
        swal(
          "Iscrizione",
          "Ti sei iscritto alla chatRoom " +
            message +
            ". Adesso puoi iniziare a chattare con i fan!",
          "success"
        );
      } else {
        swal(
          "Iscrizione",
          "Non è stato possibile iscriverti alla chatRoom " +
            message +
            ". Riprova",
          "error"
        );
      }
    });
  }

  function leaveChat() {
    let chat = {
      userEmail: userProfile["email"],
      chatRoom: message,
    };

    $.ajax({
      type: "DELETE",
      url: "http://localhost:8080/api/chat/preferred/leave",
      data: JSON.stringify(chat),
      contentType: "application/json",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods":
          "GET, POST, PUT, DELETE, PATCH, OPTIONS",
        "Access-Control-Allow-Headers":
          "X-Requested-With, content-type, Authorization",
      },
    }).always(function (xhr) {
      if (xhr.status == 200) {
        setState(false);
        leave(message);
        swal(
          "Abbandona chatRoom",
          "Hai abbandonato la chatRoom " + message,
          "success"
        );
      } else {
        swal(
          "Abbandona",
          "Non è stato possibile abbandonare la chatRoom " +
            message +
            ". Riprova",
          "error"
        );
      }
    });
  }

  return (
    <div className="ChatMessage">
      <div className="title">
        <div className="left">
          <div className="titleSymbol">
            {" "}
            <img src="/src/img/people.png" alt="People" />
          </div>
          <div className="titleName">
            Chat Room &nbsp; <i>{message}</i> &nbsp; &nbsp;
            <span>[{status}]</span>
          </div>
        </div>

        <div className="right">
          {state && (
            <div
              id="leaveText"
              onClick={() => {
                leaveChat();
              }}
            >
              Abbandona
              <img id="ImgExit" src={ImgExit}></img>
            </div>
          )}
          <div className="backButton">
            <button
              id="backButton"
              onClick={() => {
                back();
              }}
            >
              {" "}
              &#8592;
            </button>
          </div>
        </div>
      </div>

      <div className="chatContainer">
        <div id="ChatList">
          {chatMessages.map((obj, index) => (
            <ChatMex
              key={index}
              object={obj}
              email={userProfile["email"]}
              username={userProfile["name"]}
            />
          ))}
        </div>
      </div>

      <div className="searchTerm">
        {state && (
          <div id="chatBar">
            <input
              type="text"
              id="input"
              value={inputValue}
              onChange={handleChange}
              onKeyDown={(event) => {
                sendMessage(event);
              }}
              placeholder="Scrivi un messaggio qui!"
            ></input>
            <button
              id="sendButton"
              onClick={() => {
                sendMessage();
              }}
            >
              {" "}
              &#10003;
            </button>
          </div>
        )}

        {!state && (
          <div
            id="chatJoin"
            onClick={() => {
              joinChat();
            }}
          >
            <div>Unisciti alla ChatRoom</div>
          </div>
        )}
      </div>
    </div>
  );
}
export default ChatMessage;
