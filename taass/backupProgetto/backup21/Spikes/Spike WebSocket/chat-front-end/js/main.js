'use strict';
// serve per dichiarare tutte le variabili


// qui importiamo tutti gli elementi visivi
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var logoutButton = document.querySelector('#logout');
var generalButton = document.querySelector('#button_public');
var metalButton = document.querySelector('#button_metal');
var classicButton = document.querySelector('#button_classic');
var roomName = document.querySelector('#room_name');

// Questo è il nostro websocket, inizialmente NULL

var path = "http://localhost:8091"
var stompClient = null;
var username = null;
var actualRoom = "general"

// serve per assegnare colori agli utenti
var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];


function cleanMessages() {
    messageArea.innerHTML = ""
}

function printMessage(message){

    var messageElement = document.createElement('li');
    if(message.sender === username) {

        messageElement.classList.add('chat-message2');
        messageElement.style.marginLeft = "30%";
        messageElement.style.marginTop = "1%";
        //var avatarElement = document.createElement('i');
        //var avatarText = document.createTextNode(message.sender[0]);
        //avatarElement.appendChild(avatarText);
        //avatarElement.style['background-color'] = getAvatarColor(message.sender);
        //messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }else {


        messageElement.classList.add('chat-message');
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;

}

function retrieveMessagesList(){

    $.ajax({
        type:"GET",
        url: path + "/chat/messages",
        data: { room: actualRoom} ,
        contentType: "application/json"
    }).then(function(data) {
        //alert(data);
        let lista = data.reverse();
        for(let text in lista) {
            printMessage(lista[text]);
        }

    });
}


function changeRoom(room,event){

    if(room === actualRoom) return

    cleanMessages();
    actualRoom = room;
    roomName.innerText = "# " + room;
    disconnect();
    connect(event)
}

function changeView() {
    if(stompClient) {
        // nascondiamo la pagina di login e mostriamo quella di chat
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');
    }else{
        usernamePage.classList.remove('hidden');
        chatPage.classList.add('hidden');
    }
}


function connect(event) {
    username = document.querySelector('#name').value.trim();
    if(username) {
        // nascondiamo la pagina di login e mostriamo quella di chat
        // Qui inizia la connessione al broker STOMP


        //retrieveMessagesList();   CHECK CORS
        var socket = new SockJS(path + '/websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
        changeView()
    }
    event.preventDefault();
}


// se la connessione va bene, ci dobbiamo iscrivere al topic pubblico...
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/messages/' + actualRoom, onMessageReceived);

    // inoltriamo una richiesta al message broker per registrare l'utente username
    stompClient.send("/app/chat/" + actualRoom, {}, JSON.stringify({sender: username, type: 'JOIN'}))
    connectingElement.classList.add('hidden');
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
        cleanMessages();
        stompClient = null;
        username = null;
        console.log("Disconnected");
    }
}


function send(event) {

    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT',
            room: actualRoom,
            date: new Date().toLocaleString('en-GB')
        };

        stompClient.send("/app/chat/" + actualRoom, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}



function onMessageReceived(payload) {

    // recupero dal payload l'oggetto message
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');
    if(message.type === 'JOIN') {
        if(message.sender === username) {
            retrieveMessagesList()
        }
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';

    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        if(message.sender === username) {

            messageElement.classList.add('chat-message2');
            messageElement.style.marginLeft = "30%";
            messageElement.style.marginTop = "1%";
            //var avatarElement = document.createElement('i');
            //var avatarText = document.createTextNode(message.sender[0]);
            //avatarElement.appendChild(avatarText);
            //avatarElement.style['background-color'] = getAvatarColor(message.sender);
            //messageElement.appendChild(avatarElement);

            var usernameElement = document.createElement('span');
            var usernameText = document.createTextNode(message.sender);
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);
        }else {

            messageElement.classList.add('chat-message');

            var avatarElement = document.createElement('i');
            var avatarText = document.createTextNode(message.sender[0]);
            avatarElement.appendChild(avatarText);
            avatarElement.style['background-color'] = getAvatarColor(message.sender);

            messageElement.appendChild(avatarElement);

            var usernameElement = document.createElement('span');
            var usernameText = document.createTextNode(message.sender);
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);
        }
    }
    var textElement = document.createElement('p');

    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;


}



function onError(error) {
    connectingElement.textContent = 'ERROR! Non è stato possibile stabilire alcuna connessione con il webSocket server! ';
    connectingElement.style.color = 'red';
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}



// Quando l'utente clicca sul tasto submit della prima finestra,
// creiamo una connessione verso il websocket broker.
// Quando l'utente clicca sul tasto submit del form di chat,
// chiediamo al message broker di inviare un messaggio

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', send, true)
logoutButton.addEventListener('click', function (){disconnect(); changeView()}, true)

generalButton.addEventListener('click', function (event){changeRoom("general",event)})
metalButton.addEventListener('click', function (event){ changeRoom("metal",event)})
classicButton.addEventListener('click', function(event){ changeRoom("classic",event)})