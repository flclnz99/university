import {useState} from "react";
const imageLente = "/src/img/Lente.png";
import "../css/Search.css"
import {useEffect} from "react";

function Search({focus, onClick, chatLog}) {

  const [searchQuery, setSearchQuery] = useState("");
  useEffect(() => {
    setSearchQuery("");
  }, [focus]);

  const handleChange = (event) => {
    setSearchQuery(event.target.value);
  };

  function propagateEvent(event){
    if (event.key !== "Enter") {return;}
    onClick(searchQuery);
  }

  function Search({onClick}) {
    onClick(searchQuery);
  }

  function myFocus(){
    document.getElementById("searchBar").focus();
    //console.log(focus);
  }

  return (
      <div className="search">
          <img className="Lente" src={imageLente} alt="Immagine" />

          <input id="searchBar"
            onChange={handleChange}
            value={searchQuery}
            onKeyDown={(event) => {propagateEvent(event)}}
            onMouseOver={() => {myFocus();}}
            type="text"
            className="searchTerm"
            placeholder="Cerca..."
            list="searchResults"
          />
          {focus == "chatButton" && 
            <datalist id="searchResults">
                {chatLog.map((chat,i) => (
                  <option key={i} value={chat['chatroom']}>{chat['chatroom']}</option>
                ))}
            </datalist>
          } 
          <i className="fa fa-search"></i>
      </div>
  );
}
export default Search;
