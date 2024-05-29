const LogoUrl = "/src/img/Logo_senza_sfondo.png";
import "../css/Login.css"

const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

function Login() {
    function mostraPopup() {
        $("#registrazionePopup").fadeIn(400)
    }

    function chiudiPopup() {
        $("#registrazionePopup").fadeOut(400)
    }

    function selected (){
        document.getElementById("liquidContainer")!.style.display="block";
        document.getElementById("liquidContainer")!.style.transition = "opacity 1s";
        setTimeout(function() {
            document.getElementById("All")!.style.display = "block";
            document.getElementById("All")!.style.transition = "opacity 1s";
            document.getElementById("liquidContainer")!.style.opacity = "0";
            document.getElementById("login")!.style.opacity = "0";
        }, 2500);
        setTimeout(function (){
            document.getElementById("All")!.style.opacity = "1";
            document.getElementById("login")!.style.display = "none";
            document.getElementById("liquidContainer")!.style.display="none";}, 3000);
    }


    function checkInputAuth() {
        const email = $('#emailInput').val();
        const password = $('#passwordInput').val();
        if (!regex.test(email)) {
            swal("Login", "Email non corretta. Inserisci una mail valida!", "warning");
            return false;
        }
        if(password==""){
            swal("Login", "Campo password vuoto!", "info");
            return false;
        }
        return true;
    }

    function checkInputRegistr(){
        const name = $('#firstName').val();
        const surname = $('#lastName').val();
        const email = $('#emailRegistr').val();
        const password = $('#passwordRegistr').val();
        const passwordRep = $('#passwordRep').val();
        if(name=="" || surname=="" || email=="" || password=="" || passwordRep==""){
            swal("Registrazione", "Compila tutti i campi, prima!", "info");
            return false;
        }

        if(password!=passwordRep){
            swal("Registrazione", "Le password non coincidono!", "warning");
            return false;
        }

        if(password.length<4){
            swal("Registrazione", "La password deve avere almeno 4 caratteri!", "warning");
            return false;
        }

        if (!regex.test(email)) {
            swal("Registrazione", "Email non corretta. Inserisci una mail valida!", "warning");
            return false;
        }

        return true;
    }

    function authentication(){
        //<button id="registrationButton" onClick={() => selected()}> AVANZA</button>
        //<div id="MessaggioRegistrazione">Non sei registrato? Registrati!</div>
        if(checkInputAuth()){
            $.ajax({
                url: 'http://localhost:8090/api/auth/authenticate',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    email: $('#emailInput').val(),
                    password: $('#passwordInput').val()
                }),
                success: function(response) {
                    if(response.response=="User not found."){
                        //alert(response.response);
                        swal("Login", "Credenziali errate! Riprova", "error");

                    } else if(response.response=="Password incorrect."){
                        //alert(response.response);
                        swal("Login", "Password non corretta! Riprova", "error");

                    } else {
                        //swal("Login", "Login eseguito correttamente!", "success");
                        //alert(response.response);
                        console.log("token: " + response.token);
                        selected();

                    }
                },
                error: function(xhr, status, error) {
                    swal("Login", "Credenziali errate! Riprova", "error");
                    console.error('Error during the request:', error);
                }
            });
        } else {
            // gestire il caso in cui il login non va a buon fine
        }
    }


    function register() {
        if(checkInputRegistr()) {
            $.ajax({
                url: 'http://localhost:8090/api/auth/register',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    firstName: $('#firstName').val(),
                    lastName: $('#lastName').val(),
                    email: $('#emailRegistr').val(),
                    password: $('#passwordRegistr').val()
                }),
                success: function (response) {

                    if (response.response == "Error: username already exist") {
                        swal("Registrazione", "Email esistente. Perfavore inserisci un'altra mail", "error");
                    } else {
                        //swal("Registrazione", "Utente registrato correttamente!", "success");
                        //alert(response.response);
                        console.log("token: " + response.token);
                        selected();
                    }
                },
                error: function (xhr, status, error) {
                    swal("Registrazione", "Credenziali errate! Riprova", "error");
                }
            });
        }else {
            // gestire il caso in cui il login non va a buon fine
        }
    }

    return (
        <div className="Login" id="login">
            <div className="slider-thumb"></div>
            <div className="ContainerLogin">
                <div id = "loginContent">
                    <img className="LogoImg" src={LogoUrl}></img>
                    <div>
                        <label htmlFor="emailInput">Email:</label>
                        <br></br>
                        <input id="emailInput" placeholder="Inserisci l'email"></input>
                        <br></br>
                        <label htmlFor="passwordInput">Password:</label>
                        <br></br>
                        <input type="password" id="passwordInput" placeholder="Inserisci la password"></input>
                        <br></br>

                        {/* Ho rimosso il comportamento sincrono di default per debuggare meglio */}
                        <button id="loginButton" value="Login" onClick={() => authentication()}>Entra</button>
                        <div id="registrazioneDiv" onClick={() => mostraPopup()}>
                            Non sei registrato? Registrati ora.
                        </div>
                    </div>
                </div>

                
            </div>


            

            <div id="registrazionePopup">
                <div id="registrazionePopupContent">
                    <div className="formTitle">Registrazione</div>
                    <span className="closeButton" onClick={() => chiudiPopup()}>&#x2717;</span>
                    <div>
                        <label htmlFor="firstName">Nome:</label>
                        <br></br>
                        <input id="firstName" placeholder="Inserisci il tuo nome"></input>
                        <br></br>

                        <label htmlFor="lastName">Cognome:</label>
                        <br></br>
                        <input id="lastName" placeholder="Inserisci il tuo cognome"></input>
                        <br></br>

                        <label htmlFor="emailRegistr">Email:</label>
                        <br></br>
                        <input id="emailRegistr" placeholder="Inserisci la tua email"></input>
                        <br></br>

                        <label htmlFor="passwordRegistr">Password:</label>
                        <br></br>
                        <input id="passwordRegistr" type="password" placeholder="Scegli una password"></input>
                        <br></br>

                        {/*TODO: aggiungere un bottone per mostrare la password*/}

                        <label htmlFor="passwordRep">Ripeti la password:</label>
                        <br></br>
                        <input id="passwordRep" type="password" placeholder="Conferma password"></input>
                        <br></br>
                        
                        <button id="registerButton" value="Login" onClick={() => register()}>Registrati ora</button>
                    </div>

                </div>
            </div>


            <button id="registrationButton" onClick={() => selected()}> AVANZA</button>
        </div>
    );
}
export default Login;