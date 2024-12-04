const imageUrl = "/src/img/user.jpg";
const likedLogoW = "/src/img/fixedElements/likeWhite.png";
const cartLogoW = "/src/img/fixedElements/cartWhite.png";
import "../css/User.css"
interface Props{
    name: string;
}

function User({name, focus}) {
    function selected() {
        $(".search").hide(0);
        document.getElementById("musicButton")!.style.backgroundColor = "#232425";
        document.getElementById("shopButton")!.style.backgroundColor = "#232425";
        document.getElementById("chatButton")!.style.backgroundColor = "#232425";
        document.getElementById("likeButton").src = likedLogoW;
        document.getElementById("cartButton").src = cartLogoW;
        document.getElementById("MyLikeView")!.style.opacity = "0";
        document.getElementById("MyLikeView")!.style.display = "none";
        document.getElementById("CartView")!.style.display = "none";
        document.getElementById("CartView")!.style.transition = "0";
        document.getElementById("MusicView")!.style.opacity = "0";
        document.getElementById("MusicView")!.style.display = "none";
        document.getElementById("ShopView")!.style.opacity = "0";
        document.getElementById("ShopView")!.style.display = "none";
        document.getElementById("ChatView")!.style.opacity = "0";
        document.getElementById("ChatView")!.style.display = "none";
        document.getElementById("ShopViewContainer")!.style.opacity = "0";
        document.getElementById("ShopViewContainer")!.style.display = "none";
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
