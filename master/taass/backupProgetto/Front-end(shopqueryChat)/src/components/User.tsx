const imageUrl = "/src/img/user.jpg";
import "../css/User.css"
interface Props{
    name: string;
}

function User({name, focus}) {
    function selected() {
        document.getElementById("UserView")!.style.display = "block";
        document.getElementById("UserView")!.style.transition = "opacity 1s";
        setTimeout(function() {
            document.getElementById("View")!.style.zIndex="-7";
            document.getElementById("Menu")!.style.zIndex="-8";
            document.getElementById("UserView")!.style.opacity = "1";
        }, 50);
    }
  return (
    <div className={"UserDiv"}>
      <div className={"imgContainer"}>
        <img className="immagini" src={imageUrl} alt="Immagine" onClick={() => selected()}/>
      </div>
      <div className="BluBackGround"></div>
    </div>
  );
}
export default User;
