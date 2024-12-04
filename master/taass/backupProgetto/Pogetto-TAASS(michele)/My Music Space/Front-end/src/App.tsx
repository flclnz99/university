import "./App.css";
import Login from "./components/Login"
import User from "./components/User";
import Menu from "./components/Menu";
import ChatView from "./components/ChatView";
import ShopView from "./components/ShopView";
import MusicView from "./components/MusicView"
import Search from "./components/Search";

import {useState} from "react";
import {useEffect} from "react";
import MyChatView from "./components/MyChatView";
import MyLikeView from "./components/MyLikeView";
import CartView from "./components/CartView";
import Loader from "./components/Loader"
import UserView from "./components/UserView";


function App() {

  function appSelected (){
    document.getElementById("All")!.style.display = "block";
    document.getElementById("All")!.style.transition = "opacity 1s";
    document.getElementById("login")!.style.opacity = "0";
    document.getElementById("All")!.style.opacity = "1";
    document.getElementById("login")!.style.display = "none";
  }
  

  const [username, setUsername] = useState("Aldo");
  const [email, setEmail] = useState("rambaudo.aldo@gmail.com");
  const [userProfile, setUserProfile] = useState({"token": "", "email": "", "name": "", "surname": ""});

  //  Richiesta al db
  const [registeredChatRooms, setRegisteredChatRooms] = useState([]);
  const [chatLog, setChatLog] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [focus, setFocus] = useState("musicButton");
  const [isLogged, setIsLogged] = useState(false);


  useEffect(() => {
    checkToken();
  }, []);

  // Da modificare. Appena gestiamo correttamente il login
  useEffect(() => {
    //console.log(userProfile)
    if (isLogged){
      retrieveRegisteredChatRooms();
      retrieveChatLog();
    }
  }, [isLogged]);


  function checkToken(){

    const token = localStorage.getItem("token");
    const expiration = localStorage.getItem("expiration_date");
    if (token && expiration) {
      const expirationTime = new Date(expiration).getTime();
      const currentTime = new Date().getTime();

      if (currentTime >= expirationTime) {
        setIsLogged(false);
        localStorage.clear();
        window.location.reload();
      
      } else {
        setIsLogged(true);
        appSelected();
      }

    } else {
      setIsLogged(false);
    }
  }

  function handleSearchBar(query){
    setSearchQuery(query);
  }

  function handleMenuButton(button){
    if (focus != button) {handleSearchBar("");}
    setFocus(button)
  }

  function subscribe_to_chatRoom(chatRoom){
    const chatRoomNames = []
    for(const el in registeredChatRooms) chatRoomNames.push(registeredChatRooms[el])
    chatRoomNames.push(chatRoom)
    setRegisteredChatRooms(chatRoomNames)
  }

  function leave_chatRoom(chatRoom){
    let chatRoomNames = []
    for(const el in registeredChatRooms) chatRoomNames.push(registeredChatRooms[el])
    chatRoomNames = chatRoomNames.filter(function(item) {return item !== chatRoom})
    setRegisteredChatRooms(chatRoomNames)
  }

  function retrieveChatLog(){

    $.ajax({
        type:"GET",
        url: "http://localhost:8080/api/log/chat",
        data:{userEmail:userProfile['email']},
        contentType: "application/json",
        headers:{
            'token':userProfile['token'],
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, PATCH, OPTIONS',
            'Access-Control-Allow-Headers': 'X-Requested-With, content-type, Authorization'
        }

    }).then(function(data) {
        //console.log(data);
        setChatLog(data);
        console.log("Most popular chat received!")
        //console.log(data);
    });

  }

  function retrieveRegisteredChatRooms(){
    
    $.ajax({
        type:"GET",
        url: "http://localhost:8080/api/chat/preferred",
        data:{userEmail:userProfile['email']},
        contentType: "application/json",
        headers:{
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, PATCH, OPTIONS',
            'Access-Control-Allow-Headers': 'X-Requested-With, content-type, Authorization'
        }

    }).then(function(data) {
        //console.log(data);
        setRegisteredChatRooms(data);
        console.log("Registered Chatrooms retrieved!")
    });
    
  } 

  return (
      <div id="My Music Space">
        <link rel="icon" type="image/x-icon" href="./img/Logo_My%20Music%20Space_round.png"></link>
        <Login setUserProfile={setUserProfile} setIsLogged={setIsLogged}></Login>
        <div className="liquidContainer" id="liquidContainer">

          <div className="blobs">
            <div className="liquid"></div>
            <div className="blob"></div>
            <div className="blob"></div>
            <div className="blob"></div>
            <div className="blob"></div>
            <div className="blob"></div>
            <div className="blob"></div>
            <div className="blob"></div>
            <div className="blob"></div>
          </div>

          <svg xmlns="http://www.w3.org/2000/svg" version="1.1" height="0">
            <defs>
              <filter id="goog">
                <feGaussianBlur in="SourceGraphic" stdDeviation="10" result="blur" />
                <feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 18 -7" result="goo" />
                <feBlend in="SourceGraphic" in2="goo" />
              </filter>
              <filter id="goo">
                <feGaussianBlur in="SourceGraphic" stdDeviation="10" result="blur" />
                <feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 18 -7" result="goo" />
                <feBlend in="SourceGraphic" in2="goo"/>
              </filter>
            </defs>
          </svg>
        </div>

        <div id="All">
          <div className="Search-User" id="Search-User">
            <User focus={focus} ></User>
            <Search focus={focus} onClick={handleSearchBar} chatLog={chatLog}/>
          </div>
          <div className="Menu+View">
            <Menu onClick={handleMenuButton}/>
            <div className="View" id="View">
              <Loader></Loader>
              <UserView userProfile={userProfile}></UserView>
              <MyChatView></MyChatView>
              <MyLikeView userProfile={userProfile} userEmail={email} username={username} registeredChatRooms={registeredChatRooms} subscribe={subscribe_to_chatRoom} leave={leave_chatRoom}></MyLikeView>
              <ChatView userProfile={userProfile} userEmail={email} username={username} registeredChatRooms={registeredChatRooms} focus={focus} query={searchQuery} subscribe={subscribe_to_chatRoom} leave={leave_chatRoom}></ChatView>
              <ShopView userProfile={userProfile} focus={focus} query={searchQuery}></ShopView>
              <MusicView userProfile={userProfile} focus={focus} query={searchQuery}></MusicView>
              <CartView ></CartView>
            </div>
          </div>
            <div className='air air1'></div>
            <div className='air air2'></div>
            <div className='air air3'></div>
            <div className='air air4'></div>
        </div>
        
      </div>
  );
}

export default App;
