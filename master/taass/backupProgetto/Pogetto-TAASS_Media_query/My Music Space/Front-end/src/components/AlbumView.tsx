import { useEffect, useState } from "react";
import "../css/AlbumView.css";
const imgPath = "http://localhost:8092/";

function SongElement({ object }) {
  let path = imgPath + object["imageURL"];
  return (
    <div className="songElement">
      <div className="songImg">
        <img src={path}></img>
      </div>

      <div className="info">
        <h1 className="titleSong">{object["title"]}</h1>
        <h1 className="visual">542.211</h1>
        <img className="likeButton" src="/src/img/like.png"></img>
        <h1 className="duration">{object["duration"]}</h1>
      </div>
    </div>
  );
}

function AlbumView({ handleClick, object }) {
  const [items, setItems] = useState([]);
  const [song, setSong] = useState([]);

  useEffect(() => {
    $("#AlbumView").show(0);
    document.getElementById("AlbumView")!.style.display = "block";
    document.getElementById("AlbumView")!.style.transition = "opacity 1s";

    retrieveSong();
  }, []);

  function back() {
    document.getElementById("AlbumView")!.style.opacity = "0";
    document.getElementById("AlbumView")!.style.display = "none";
    document.getElementById("MusicView")!.style.display = "block";
    document.getElementById("MusicView")!.style.transition = "opacity 1s";
    setTimeout(function () {
      document.getElementById("MusicView")!.style.opacity = "1";
    }, 50);
    $(".search").show(0);
    handleClick(false);
  }

  function retrieveSong() {
    $.ajax({
      url: "http://localhost:8092/song",
      method: "GET",
      contentType: "application/json",
      crossDomain: true,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods":
          "GET, POST, PUT, DELETE, PATCH, OPTIONS",
        "Access-Control-Allow-Headers":
          "X-Requested-With, content-type, Authorization",
      },
      success: function (data) {
        let song = [];
        for (const el in data) {
          if (data[el]["idAlbum"] == object["idAlbum"]) {
            song.push(data[el]);
          }
        }
        setSong(song);
        console.log(song);
      },
      error: function (error) {
        console.error("Error:", error);
      },
    });
  }

  function changeContainer(Clicked) {
    document.getElementById("DescriptionC")!.style.opacity = "0";
    document.getElementById("DescriptionC")!.style.display = "none";
    document.getElementById("ReviewsC")!.style.opacity = "0";
    document.getElementById("ReviewsC")!.style.display = "none";
    document.getElementById("BuyC")!.style.opacity = "0";
    document.getElementById("BuyC")!.style.display = "none";
    if (Clicked == "Descrizione") {
      document.getElementById("DescriptionC")!.style.opacity = "1";
      document.getElementById("DescriptionC")!.style.display = "block";
    }
    if (Clicked == "Recensioni") {
      document.getElementById("ReviewsC")!.style.opacity = "1";
      document.getElementById("ReviewsC")!.style.display = "block";
    }
    if (Clicked == "Acquista") {
      document.getElementById("BuyC")!.style.opacity = "1";
      document.getElementById("BuyC")!.style.display = "block";
    }
  }

  return (
    <div id="AlbumView">
      <div className="backButton">
        <button
          id="backButton"
          onClick={() => {
            back();
          }}
        >
          {" "}
          &#8592;
        </button>
      </div>
      <div id="BackgroundS">
        <h1>Album</h1>
        <h2 className="albumTitle">{object["title"]}</h2>
        <img src={"http://localhost:8092/" + object["imageURL"]}></img>
      </div>
      <div id="OperationS">
        <div id="ArtistContainer">
          <div id="DescriptionC">
            {song.map((obj, index) => (
              <SongElement key={index} object={obj} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
export default AlbumView;
