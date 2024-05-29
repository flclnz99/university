import { useState } from "react";
import { useEffect } from "react";
const imagePath = "http://localhost:8080/shop-service";
import "../css/ShopView.css";
import ShopProductView from "./ShopProductView";

function selectedShop({ object }) {
  // TROVARE UN MODO PER PASSARE QUESTA PROPERIES A SHOPPRODUCTVIEW
  // forse quella componente va messa in shopView al pari di chatMessages
  $(".search").hide(0);
  document.getElementById("ShopView")!.style.opacity = "0";
  document.getElementById("ShopView")!.style.display = "none";
  //document.getElementById("ShopProductView")!.style.display = "block";
  //document.getElementById("ShopProductView")!.style.transition = "opacity 1s";
  setTimeout(function () {
    document.getElementById("ShopProductView")!.style.opacity = "1";
  }, 50);
}

function TicketProduct({ object, setShopProduct }) {
  let path = imagePath + object["imageURL"];
  path = path.split(" ").join("%20");

  function clickOnObject() {
    setShopProduct(object);
    selectedShop({ object });
  }

  return (
    <div className="TicketProduct" onClick={() => clickOnObject()}>
      <img src={path} className="TicketImg"></img>
    </div>
  );
}

function MerchProduct({ object, setShopProduct }) {
  let path = imagePath + object["imageURL"];
  path = path.split(" ").join("%20");

  function clickOnObject() {
    $("#ShopProductView").show(0);
    setShopProduct(object);
    selectedShop({ object });
  }

  return (
    <div className="MerchProduct" onClick={() => clickOnObject()}>
      <img src={path} className="Merch"></img>
    </div>
  );
}

function ShopView({ focus, query }) {
  const [items, setItems] = useState([]);
  const [threeItems, setThreeItems] = useState([]);
  const [shopProduct, setShopProduct] = useState({});
  const [buttonClicked, setButtonClicked] = useState(false);

  useEffect(() => {
    $("#ShopProductView").hide(0);
    if (focus == "shopButton") {
      if (query && query != "") {
        retrieveItems(
          { itemName: query },
          "http://localhost:8080/api/shop/items/search"
        );
      } else {
        // POSSIBILE MODIFICA: SE LA QUERY NON DA
        // RISULTATO ALLORA NON CANCELLIAMO LA PRECEDENTE VIEW. QUINDI LASCIAMO GLI OGGETTI DI PRIMA
        retrieveItems({}, "http://localhost:8080/api/shop/items");
      }
    }
  }, [focus, query]);

  useEffect(() => {
    setButtonClicked(false);
  }, []);

  function selectProduct(newObject) {
    setButtonClicked(true);
    setShopProduct(newObject);
  }

  function handleClick(param) {
    setButtonClicked(false);
  }

  function retrieveItems(params, URL) {
    $("#loaderBar").fadeIn(0);
    $.ajax({
      type: "GET",
      url: URL,
      data: params,
      contentType: "application/json",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods":
          "GET, POST, PUT, DELETE, PATCH, OPTIONS",
        "Access-Control-Allow-Headers":
          "X-Requested-With, content-type, Authorization",
      },
    }).then(function (data) {
      let products = [];
      let itemList = [];
      for (const el in data) {
        if (data[el]["type"] == "PRODUCT") {
          products.push(data[el]);
        } else {
          itemList.push(data[el]);
        }
      }

      if (products.length > 3) {
        let firstThreeItems = products.slice(0, 3);
        setThreeItems(firstThreeItems);
        products.splice(0, 3);
      }
      itemList = itemList.concat(products);
      setItems(itemList);
      $("#loaderBar").fadeOut(200);
    });

    if (items.length > 0) {
      $("#loaderBar").fadeOut(200);
    }
  }

  return (
    <div id="ShopViewContainer">
      <div id="ShopView" className="ShopView">
        <div id="inEvidenza" className="labelRow">
          In evidenza
        </div>
        <div className="MerchRow">
          {threeItems.map((obj, index) => (
            <MerchProduct
              key={index}
              object={obj}
              setShopProduct={selectProduct}
            />
          ))}
        </div>

        <div className="TicketRow">
          <div className="labelRow">Eventi</div>
          <div className="scrollBar">
            {items.map((obj, index) =>
              obj["type"] == "EVENT" ? (
                <TicketProduct
                  key={index}
                  setShopProduct={selectProduct}
                  object={obj}
                />
              ) : null
            )}
          </div>
        </div>

        <div className="TicketRow">
          <div className="labelRow">Prodotti</div>
          <div className="scrollBar">
            {items.map((obj, index) =>
              obj["type"] == "PRODUCT" ? (
                <TicketProduct
                  key={index}
                  setShopProduct={selectProduct}
                  object={obj}
                />
              ) : null
            )}
          </div>
        </div>
      </div>

      {buttonClicked && (
        <ShopProductView object={shopProduct} handleClick={handleClick} />
      )}
    </div>
  );
}
export default ShopView;
