import { useEffect, useState } from "react";
import "../css/ArtistView.css";

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

function AlbumElement({ object }) {
  let path = imgPath + object["imageURL"];
  return (
    <div className="albumElement">
      <div className="albumImg">
        <img src={path}></img>
      </div>

      <div className="infoAlbum">
        <h1 className="albumTitle">{object["title"]}</h1>
        <h1 className="albumYear">{object["year"]}</h1>
      </div>
    </div>
  );
}

function ArtistView({ handleClick, object }) {
  const [items, setItems] = useState([]);
  const [song, setSong] = useState([]);
  const [album, setAlbum] = useState([]);

  useEffect(() => {
    $("#ArtistView").show(0);
    document.getElementById("ArtistView")!.style.display = "block";
    document.getElementById("ArtistView")!.style.transition = "opacity 1s";
  }, []);

  function back() {
    document.getElementById("ArtistView")!.style.opacity = "0";
    document.getElementById("ArtistView")!.style.display = "none";
    document.getElementById("MusicView")!.style.display = "block";
    document.getElementById("MusicView")!.style.transition = "opacity 1s";
    setTimeout(function () {
      document.getElementById("MusicView")!.style.opacity = "1";
    }, 50);
    $(".search").show(0);
    handleClick(false);
  }

  function changeContainer(Clicked) {
    document.getElementById("DescriptionC")!.style.opacity = "0";
    document.getElementById("DescriptionC")!.style.display = "none";
    document.getElementById("ReviewsC")!.style.opacity = "0";
    document.getElementById("ReviewsC")!.style.display = "none";
    if (Clicked == "Descrizione") {
      document.getElementById("DescriptionC")!.style.opacity = "1";
      document.getElementById("DescriptionC")!.style.display = "block";
    }
    if (Clicked == "Recensioni") {
      document.getElementById("ReviewsC")!.style.opacity = "1";
      document.getElementById("ReviewsC")!.style.display = "block";
    }

    retrieveSong();
    retrieveAlbum();
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
          if (data[el]["idArtist"] == object["idArtist"]) {
            song.push(data[el]);
          }
        }
        setSong(song);
        //console.log(song);
      },
      error: function (error) {
        console.error("Error:", error);
      },
    });
  }

  function retrieveAlbum() {
    $.ajax({
      url: "http://localhost:8092/album",
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
        let album = [];
        for (const el in data) {
          if (data[el]["idArtist"] == object["idArtist"]) {
            album.push(data[el]);
          }
        }
        setAlbum(album);
        console.log(album);
      },
      error: function (error) {
        console.error("Error:", error);
      },
    });
  }

  return (
    <div id="ArtistView">
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

      <div id="BackgroundArtist">
        <h1>Artista</h1>
        <h2>{object["name"]}</h2>
        <img src={"http://localhost:8092/" + object["imageURL"]}></img>
      </div>
      <div id="OperationS">
        <button
          onClick={() => {
            changeContainer("Descrizione");
          }}
        >
          Brani
        </button>
        <button
          onClick={() => {
            changeContainer("Recensioni");
          }}
        >
          Album
        </button>
        <div id="ArtistContainer">
          <div id="DescriptionC">
            {song.map((obj, index) => (
              <SongElement key={index} object={obj} />
            ))}
          </div>
          <div id="ReviewsC">
            {album.map((obj, index) => (
              <AlbumElement key={index} object={obj} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
export default ArtistView;
