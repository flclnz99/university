import React from "react";
import {useState} from "react";
import {useEffect} from "react";
import {Simulate} from "react-dom/test-utils";
import Chat from "./Chat";
import ChatMessage from "./ChatMessage";
import select = Simulate.select;
import "../css/MyLikeView.css"
const ImgStaticMusicLike="/src/img/MyMusicLikeStatic.PNG"

function MyLikeView({userProfile, userEmail, username,registeredChatRooms, subscribe, leave}) {

    const [isButtonClicked, setIsButtonClicked] = useState(false);
    const [message, setMessage] = useState("");

    useEffect(() => {
        setIsButtonClicked(false);
        //retrieveRegisteredChatRooms();
    },[]);


    const handleClick = (index) => { //ascolto sulla chiat cliccata e retropropago l'indice dell'array Chats
        if(index === -1){
            $(".ChatMessage").fadeOut(400);
            setIsButtonClicked(false);
            $("#MyLikeViewContainer").fadeIn(200);
        }
        else{
            setTimeout(function (){
                setIsButtonClicked(true);
                $("#MyLikeViewContainer").fadeOut(400);
            }, 190);
        }
    };

    const handleMessageChange = (newMessage) => {
        setMessage(newMessage);
    };


    function selected(id){
        if (id=="Chat"){
            document.getElementById("MyMusicLikeStatic")!.style.opacity = "0";
            document.getElementById("MyMusicLikeStatic")!.style.display = "none";
            document.getElementById("MyChatContainer")!.style.display = "block";
            document.getElementById("MyChatContainer")!.style.transition = "opacity 1s";
            document.getElementById("MyChatButton")!.style.backgroundColor = "#3E99CF";
            document.getElementById("MyMusicButton")!.style.backgroundColor = "#404040";
            setTimeout(function () {
                document.getElementById("MyChatContainer")!.style.opacity = "1";
            }, 50);

        }
        if (id=="Musica"){
            document.getElementById("MyChatContainer")!.style.opacity = "0";
            document.getElementById("MyChatContainer")!.style.display = "none";
            document.getElementById("MyMusicLikeStatic")!.style.display = "block";
            document.getElementById("MyMusicLikeStatic")!.style.transition = "opacity 1s";
            document.getElementById("MyMusicButton")!.style.backgroundColor = "#3E99CF";
            document.getElementById("MyChatButton")!.style.backgroundColor = "#404040";
            setTimeout(function () {
                document.getElementById("MyMusicLikeStatic")!.style.opacity = "1";
            }, 50);

        }

    }

    return (
        <div className="MyLikeView" id="MyLikeView">
            

            <div id = "MyLikeViewContainer">
                <h1>Preferiti</h1>
                <button id="MyChatButton"  onClick={() => {selected("Chat")}} >Chat</button>
                <button id="MyMusicButton"  onClick={() => {selected("Musica")}}>Musica</button>
                
                <div id="MyChatContainer" >
                    <div id = "myChats">
                        {registeredChatRooms.map((name,i) => (
                            <Chat key={i} index={i} name={name} handleClick={handleClick} handleMessageChange={handleMessageChange} />
                        ))}
                    </div>
                </div>
                <img id="MyMusicLikeStatic" src={ImgStaticMusicLike}></img>
            </div>
            {isButtonClicked && <ChatMessage userProfile={userProfile} userEmail = {userEmail} username = {username} active={true} message={message} handleClick={handleClick} subscribe={subscribe} leave={leave}/>}
        </div>
    );
}
export default MyLikeView;