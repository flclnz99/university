import { useEffect, useState } from "react";
import "../css/SongView.css";
const imagePath = "/src/img/Viva la vida.jpg";
const imgPath = import.meta.env.VITE_REACT_APP_API_URL + "/music-service";

function SongElement1({ object, artist }) {
  let path = imgPath + object["imageURL"];
  console.log(object);
  return (
    <div className="songElement1">
      <div className="songImg1">
        <img src={path}></img>
      </div>
      <div className="songTitle1">
        <div className="infoTitle">
          <h1 className="infoSong">{object["title"]} - Singolo</h1>
        </div>
        <div className="infoTitle">
          <h1 className="infoSong">duration: {object["duration"]}</h1>
          <h1 className="infoSong">year: {object["year"]}</h1>
        </div>
      </div>
    </div>
  );
}

function SongView({ handleClick, object }) {
  let [artist, setArtist] = useState([]);

  useEffect(() => {
    $("#SongView").show(0);
    document.getElementById("SongView")!.style.display = "block";
    document.getElementById("SongView")!.style.transition = "opacity 1s";

    retrieveArtist();
  }, []);

  function back() {
    document.getElementById("SongView")!.style.opacity = "0";
    document.getElementById("SongView")!.style.display = "none";
    document.getElementById("MusicView")!.style.display = "block";
    document.getElementById("MusicView")!.style.transition = "opacity 1s";
    setTimeout(function () {
      document.getElementById("MusicView")!.style.opacity = "1";
    }, 50);
    $(".search").show(0);
    handleClick(false);
  }

  function retrieveArtist() {
    $.ajax({
      url: import.meta.env.VITE_REACT_APP_API_URL + "/api/music/artist",
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
        let artist = [];
        for (const el in data) {
          if (data[el]["idArtist"] == object["idArtist"]) {
            artist.push(data[el]);
          }
        }
        setArtist(artist);
        //console.log(artist);
      },
      error: function (error) {
        console.log("ERRORE QUIIIIIIIIIIIIIIII")
        console.error("Error:", error);
      },
    });
  }

  return (
    <div id="SongView">
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
      <div id="BackgroundSi">
        <SongElement1 object={object} artist={artist} />
      </div>
    </div>
  );
}
export default SongView;
