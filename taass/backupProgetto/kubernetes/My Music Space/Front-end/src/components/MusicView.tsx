import { useState } from "react";
import { useEffect } from "react";
const ImgPlaylist = "/src/img/texas-road-trip-playlist.jpg";
const ImgRadio = "/src/img/music-note.png";
const imgArtist = "/src/img/harryStyles.webp";
const imgPath = import.meta.env.VITE_REACT_APP_API_URL + "/music-service";
import "../css/MusicView.css";
import ArtistView from "./ArtistView";
import SongView from "./SongView";
import AlbumView from "./AlbumView";

function selectArtist() {
  $(".search").hide(0);
  document.getElementById("MusicView")!.style.opacity = "0";
  document.getElementById("MusicView")!.style.display = "none";
  setTimeout(function () {
    document.getElementById("ArtistView")!.style.opacity = "1";
  }, 50);
}

function selectSong() {
  $(".search").hide(0);
  document.getElementById("MusicView")!.style.opacity = "0";
  document.getElementById("MusicView")!.style.display = "none";
  setTimeout(function () {
    document.getElementById("SongView")!.style.opacity = "1";
  }, 50);
}

function selectAlbum() {
  $(".search").hide(0);
  document.getElementById("MusicView")!.style.opacity = "0";
  document.getElementById("MusicView")!.style.display = "none";
  setTimeout(function () {
    document.getElementById("AlbumView")!.style.opacity = "1";
  }, 50);
}

function Album({ object, setAlbum }) {
  let path = imgPath + object["imageURL"];

  function clickOnAlbum() {
    $("#AlbumView").show(0);
    setAlbum(object);
    selectAlbum({ object });
  }
  return (
    <div className="albumDiv" onClick={() => clickOnAlbum()}>
      <div className="externDiv">
        <img id="albumImg" src={path}></img>
        <div className="description">{object["title"]}</div>
      </div>
    </div>
  );
}

function Artist({ object, setArtist }) {
  let path = imgPath + object["imageURL"];

  function clickOnArtist() {
    $("#ArtistView").show(0);
    setArtist(object);
    selectArtist({ object });
  }

  return (
    <div className="artistDiv" onClick={() => clickOnArtist()}>
      <div className="externDiv">
        <img id="artistImg" src={path}></img>
        <h1 className="description">{object["name"]}</h1>
      </div>
    </div>
  );
}

function Song({ object, setSong }) {
  let path = imgPath + object["imageURL"];

  function clickOnSong() {
    $("#SongView").show(0);
    setSong(object);
    selectSong({ object });
  }
  return (
    <div className="songDiv" onClick={() => clickOnSong()}>
      <div className="externDiv">
        <img id="songImg" src={path}></img>
        <h1 className="description">{object["title"]}</h1>
      </div>
    </div>
  );
}

function ForegroundSong({ object, setSong }) {
  let path = imgPath + object["imageURL"];

  function clickOnSong() {
    $("#SongView").show(0);
    setSong(object);
    selectSong({ object });
    //console.log(object);
  }

  return (
    <div className="sinistro" onClick={() => clickOnSong()}>
      <img className="forSongImg" src={path}></img>
    </div>
  );
}

function ForegroundArtist({ object, setArtist }) {
  let path = imgPath + object["imageURL"];

  function clickOnArtist() {
    $("#ArtistView").show(0);
    setArtist(object);
    selectArtist({ object });
  }
  return (
    <div className="sinistro" onClick={() => clickOnArtist()}>
      <img className="forArtImg" src={path}></img>
    </div>
  );
}

function ForegroundAlbum({ object, setAlbum }) {
  let path = imgPath + object["imageURL"];

  function clickOnAlbum() {
    $("#AlbumView").show(0);
    setAlbum(object);
    selectAlbum({ object });
  }
  return (
    <div className="destro" onClick={() => clickOnAlbum()}>
      <img className="forAlbumImg" src={path}></img>
    </div>
  );
}

function MusicView({userProfile, focus, query }) {
  const [artist, setArtist] = useState({});
  //lista degli artist
  const [artistArray, setArtistArray] = useState([]);

  const [song, setSong] = useState({});
  //lista delle canzoni
  const [songArray, setSongArray] = useState([]);

  const [album, setAlbum] = useState({});
  //lista degli album
  const [albumArray, setAlbumArray] = useState([]);

  //singoli canzone, album, artista da inserire in alto nel frontend
  const [songElem, setSongElem] = useState([]);
  const [artistElem, setArtistElem] = useState([]);
  const [albumElem, setAlbumElem] = useState([]);

  const [buttonClickedArtist, setButtonClickedArtist] = useState(false);
  const [buttonClickedSong, setButtonClickedSong] = useState(false);
  const [buttonClickedAlbum, setButtonClickedAlbum] = useState(false);

  // questa lista verrà richiesta quando clicco sul bottone music, quindi sarà memorizzata nel padre e passata al figlio.
  useEffect(() => {
    $("#loaderBar").fadeIn(0);
    $("#ArtistView").hide(0);
    $("#SongView").hide(0);
    $("#AlbumView").hide(0);
    if (focus == "musicButton") {
      retrieveAlbum();
      retrieveSong();
      retrieveArtist();
    }
    $("#loaderBar").fadeOut(200);
  }, [focus, query]);

  useEffect(() => {
    setButtonClickedArtist(false);
    setButtonClickedSong(false);
    setButtonClickedAlbum(false);
  }, []);

  function retrieveSong() {
    $.ajax({
      url: import.meta.env.VITE_REACT_APP_API_URL + "/api/music/song",
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
          song.push(data[el]);
        }
        let currentIndex = song.length;
        while (currentIndex != 0) {
          let randomIndex = Math.floor(Math.random() * currentIndex);
          currentIndex--;
          [song[currentIndex], song[randomIndex]] = [
            song[randomIndex],
            song[currentIndex],
          ];
        }
        setSongElem(song[0]);
        setSongArray(song.slice(0, 9));
        //console.log(songElem);
      },
      error: function (error) {
        console.error("Error:", error);
      },
    });
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
          artist.push(data[el]);
        }
        let currentIndex = artist.length;
        while (currentIndex != 0) {
          let randomIndex = Math.floor(Math.random() * currentIndex);
          currentIndex--;
          [artist[currentIndex], artist[randomIndex]] = [
            artist[randomIndex],
            artist[currentIndex],
          ];
        }
        setArtistElem(artist[0]);
        setArtistArray(artist);
      },
      error: function (error) {
        console.error("Error:", error);
      },
    });
  }

  function retrieveAlbum() {
    $.ajax({
      url: import.meta.env.VITE_REACT_APP_API_URL + "/api/music/album",
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
          album.push(data[el]);
        }
        let currentIndex = album.length;
        while (currentIndex != 0) {
          let randomIndex = Math.floor(Math.random() * currentIndex);
          currentIndex--;
          [album[currentIndex], album[randomIndex]] = [
            album[randomIndex],
            album[currentIndex],
          ];
        }
        setAlbumElem(album[0]);
        setAlbumArray(album);
      },
      error: function (error) {
        console.error("Error:", error);
      },
    });
  }

  function selectArtist(newObject) {
    setButtonClickedArtist(true);
    setArtist(newObject);
  }

  function selectSong(newObject) {
    setButtonClickedSong(true);
    setSong(newObject);
  }

  function selectAlbum(newObject) {
    setButtonClickedAlbum(true);
    setAlbum(newObject);
  }

  function handleClickArtist(param) {
    setButtonClickedArtist(false);
  }

  function handleClickAlbum(param) {
    setButtonClickedAlbum(false);
  }

  function handleClickSong(param) {
    setButtonClickedSong(false);
  }

  return (
    <div className="" id="">
      <div className="MusicView" id="MusicView">
        <div className="claudio">
          <ForegroundSong
            object={songElem}
            setSong={selectSong}
          ></ForegroundSong>
          <ForegroundArtist
            object={artistElem}
            setArtist={selectArtist}
          ></ForegroundArtist>
          <ForegroundAlbum
            object={albumElem}
            setAlbum={selectAlbum}
          ></ForegroundAlbum>
        </div>

        <div className="title">Album</div>
        <div className="Container">
          <div className="scrollBar">
            {albumArray.map((obj, index) => (
              <Album key={index} object={obj} setAlbum={selectAlbum} />
            ))}
          </div>
        </div>

        <div className="title">Artisti</div>
        <div className="Container">
          <div className="scrollBar">
            {artistArray.map((obj, index) => (
              <Artist key={index} object={obj} setArtist={selectArtist} />
            ))}
          </div>
        </div>

        <div className="title">Brani</div>
        <div className="Container">
          <div className="scrollBar">
            {songArray.map((obj, index) => (
              <Song key={index} object={obj} setSong={selectSong} />
            ))}
          </div>
        </div>
      </div>
      {buttonClickedArtist && (
        <ArtistView object={artist} handleClick={handleClickArtist} />
      )}
      {buttonClickedAlbum && (
        <AlbumView object={album} handleClick={handleClickAlbum} />
      )}
      {buttonClickedSong && (
        <SongView object={song} handleClick={handleClickSong} />
      )}
    </div>
  );
}
export default MusicView;
