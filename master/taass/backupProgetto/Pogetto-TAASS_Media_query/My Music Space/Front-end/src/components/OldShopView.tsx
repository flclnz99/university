const ImgVinyl = "/src/img/Vinyl_icon.png"
const ImgAlbum = "/src/img/Viva la vida.jpg"
const ImgTicket = "/src/img/elodie-show-2023.jpg"
const ImgMerch ="/src/img/Cappello PTN.jpg"
import "../css/OldShopView.css"
function VinylProduct({object}){
    return(
        <div className="VinylProduct">
        <img className="Vinyl" src={ImgVinyl}></img>
        <img src={ImgAlbum}></img>
        <p>{object['name']}</p>
        </div>
);
}

function TicketProduct({object}){
    return(
        <div className="TicketProduct">
        <img src={ImgTicket} className="TicketFront"></img>
        <img src={ImgTicket} className="TicketBack"></img>
        <p>{object['name']}</p>
        </div>
);
}

function MerchProduct({object}){
    return(
        <div className="MerchProduct">
        <img src={ImgMerch} className="Merch"></img>
        <p>{object['name']}</p>
        </div>
);
}

function OldShopView() {

    // Type 1 == CD/vinili
    // Type 2 == Eventi
    // Type 3 == prodotti

    const objects = [{
        type: 1,
        name: "Vinile 1"
    },
        {
            type: 1,
            name: "Cd 1"
        },
        {
            type: 2,
            name: "Evento1"
        },
        {
            type: 2,
            name: "Evento2"
        },
        {
            type: 3,
            name: "prodotto1"
        },
        {
            type: 3,
            name: "prodotto2"
        },]


    return (
        <div className="ShopView" id = "ShopView">
    <div className="VinylRow">
        {objects.map((obj,index) => (
                obj['type'] == 1 ? (<VinylProduct key={index} object={obj} />) : null))
}
    </div>
    <div className="TicketRow">
        {objects.map((obj,index) => (
                obj['type'] == 2 ? (<TicketProduct key={index} object={obj} />) : null))
}
    </div>
    <div className="MerchRow">
        {objects.map((obj,index) => (
                obj['type'] == 3 ? (<MerchProduct key={index} object={obj} />) : null))
}

    </div>
    </div>
);
}
export default OldShopView;