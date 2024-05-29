const cartStatic = "/src/img/CarrelloStaico.png";
import "../css/CartView.css"

interface Props{
    productname: string;
    type:string;
    price:string;
    vendor: string;
    date:string;
}
function CartView() {
    return (
        <div className="CartView" id ="CartView">
            <img src={cartStatic}></img>
        </div>
    );
}
export default CartView;