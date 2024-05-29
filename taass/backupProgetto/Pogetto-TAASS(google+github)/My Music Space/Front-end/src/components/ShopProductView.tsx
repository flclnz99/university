import { useEffect } from "react";
import "../css/ShopProductView.css"
const ImgReview = "/src/img/Recensione.PNG"
const ImgReview2= "/src/img/Recensione 2.PNG"
const ImgAddNumber="/src/img/AddNumber.PNG"
const imagePath = "http://localhost:8080/shop-service"

function ShopProductView({object, handleClick}){

    useEffect(() => {
        $("#ShopProductView").show(0);
        document.getElementById("ShopProductView")!.style.display = "block";
        document.getElementById("ShopProductView")!.style.transition = "opacity 1s";
    }, []);

    function back(){
        document.getElementById("ShopProductView")!.style.opacity = "0";
        document.getElementById("ShopProductView")!.style.display = "none";
        document.getElementById("ShopView")!.style.display = "block";
        document.getElementById("ShopView")!.style.transition = "opacity 1s";
        setTimeout(function () {
            document.getElementById("ShopView")!.style.opacity = "1";
        }, 50);
        $(".search").show(0);
        handleClick(false)
    }
    function changeContainer(Clicked){
        document.getElementById("DescriptionContainer")!.style.opacity = "0";
        document.getElementById("DescriptionContainer")!.style.display = "none";
        document.getElementById("ReviewsContainer")!.style.opacity = "0";
        document.getElementById("ReviewsContainer")!.style.display = "none";
        document.getElementById("BuyContainer")!.style.opacity = "0";
        document.getElementById("BuyContainer")!.style.display = "none";
        if(Clicked == "Descrizione" ){
            document.getElementById("DescriptionContainer")!.style.opacity = "1";
            document.getElementById("DescriptionContainer")!.style.display = "block";
        }
        if(Clicked == "Recensioni" ){
            document.getElementById("ReviewsContainer")!.style.opacity = "1";
            document.getElementById("ReviewsContainer")!.style.display = "block";

        }
        if(Clicked == "Acquista" ){
            document.getElementById("BuyContainer")!.style.opacity = "1";
            document.getElementById("BuyContainer")!.style.display = "block";

        }

    }


    return(
        <div id="ShopProductView">
            <div className="backButton"><button id="backButton" onClick={() => {back()}}> &#8592;</button></div>
            <div id="BackgroundSection">
                <h1>{object['artistName']}</h1>
                <h2>{object['itemName']}</h2>
                <img src={imagePath + object['imageURL']}></img>
            </div>
            <div id="OperationSection">
                <button onClick={() => {changeContainer("Descrizione")}}>Descrizione</button>
                <button onClick={() => {changeContainer("Recensioni")}}>Recensioni</button>
                <button onClick={() => {changeContainer("Acquista")}}>Acquista</button>

                <div id = "ShopProductViewContainer">
                    <div id="DescriptionContainer">
                        {object['description']}
                    </div>
                    <div id="ReviewsContainer">
                        <img src={ImgReview}></img>
                        <img src={ImgReview2}></img>
                    </div>
                    <div id="BuyContainer">
                        <p>Prezzo: {object['price']},00 €</p>
                        <img src={ImgAddNumber}></img>
                        <br></br>
                        <button id="AddtoCart">Aggiungi al carrello</button>
                        <button id="AddtoCart">Acquista ora</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ShopProductView;