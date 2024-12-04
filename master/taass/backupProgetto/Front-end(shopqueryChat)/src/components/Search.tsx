import {useState} from "react";
const imageLente = "/src/img/Lente.png";
import "../css/Search.css"


function Search({onClick}) {

  const [searchQuery, setSearchQuery] = useState("");
  const handleChange = (event) => {
    setSearchQuery(event.target.value);
  };

  function propagateEvent(event){
    if (event.key !== "Enter") {return;}
    onClick(searchQuery);
  }

  return (
      <div className="search">
          <img className="Lente" src={imageLente} alt="Immagine" />
        <input id="searchBar"
          onChange={handleChange}
          value={searchQuery}
          onKeyDown={(event) => {propagateEvent(event)}}
          type="text"
          className="searchTerm"
          placeholder="Cerca..."
        />
          <i className="fa fa-search"></i>
      </div>
  );
}
export default Search;
