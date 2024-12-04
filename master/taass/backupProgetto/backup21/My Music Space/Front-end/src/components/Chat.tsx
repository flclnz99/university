const ImgChat = "/src/img/chat-icon.png"
import "../css/Chat.css"

function Chat({index, name, handleClick, handleMessageChange }){


    function chatClicked(){

        document.getElementById(name).className = 'rotate-vert-center';

        setTimeout(function (){
        $(".search").fadeOut(400);
        $("#chats").fadeOut(400, function(){$(".ChatMessage").fadeIn(500);
        });
        document.getElementById(name).className = '';},400)
        handleMessageChange(name); 
        handleClick(index);
    }

    return(
        // spostato click così si clicca solo sull'immagine o il campo p... 
        <div className="Chat">
            <p onClick={() => {chatClicked()}}>{name} </p>
            <img src={ImgChat} id={name} onClick={() => {chatClicked()}}></img>
        </div>
    );
}

export default Chat;