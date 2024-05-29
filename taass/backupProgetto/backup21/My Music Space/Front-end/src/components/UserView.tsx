import "../css/UserView.css"
const imageUrl = "/src/img/user.jpg";
import {useState} from "react";
import {useEffect} from "react";

function UserView({userProfile}) {

    useEffect(() => {
    }, [userProfile]);

    function logout() {
        localStorage.clear();
        window.location.reload();
    }

return(
    <div className="UserView" id="UserView">
        <div className="BlueRectangle">
            <img className="UserImage" src={imageUrl} alt="Immagine"/>
        </div>
        <div className="GreyMiniRectangle">
            <p>Cambio password</p>
        </div>
        <div className="GreyMiniRectangle" id="logoutButton" onClick={() => logout()}>
            <p>Logout &nbsp;&nbsp;►</p>
        </div>
        <div className="GreyRectangle">
            <h1>Account</h1>
            <p>Nome: {userProfile['name']}</p>
            <p>Cognome: {userProfile['surname']}</p>
            <p>Email:  {userProfile['email']}</p>
            <p>Piano: free</p>
        </div>
    </div>
)
}

export default UserView;