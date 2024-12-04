const imageUrl = "/src/img/cart.jpg";
import "../css/Cart.css"

function Cart() {
    function selected() {
        document.getElementById("CartView")!.style.display = "block";
        document.getElementById("CartView")!.style.transition = "opacity 1s";
        setTimeout(function() {
            document.getElementById("View")!.style.zIndex="-7";
            document.getElementById("CartView")!.style.opacity = "1";
            document.getElementById("Menu")!.style.zIndex="-8";
        }, 50);
    }
  return (
    <div className={"cartDiv"}>
      <div className={"imgContainer"}>
          CIAOOOO
        <img className="cartImg" src={imageUrl} alt="Immagine" onClick={() => selected()} />
      </div>
    </div>
  );
}
export default Cart;
