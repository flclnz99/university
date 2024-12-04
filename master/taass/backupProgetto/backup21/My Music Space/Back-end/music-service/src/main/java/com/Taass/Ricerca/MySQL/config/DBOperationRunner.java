package com.Taass.Ricerca.MySQL.config;

import java.util.Arrays;
import java.util.List;

import com.Taass.Ricerca.MySQL.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBOperationRunner implements CommandLineRunner {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongRepository songRepository;

    @Override
    public void run(String... args) throws Exception {
        Artist acdc = new Artist("AC/DC", null, null, "/images/ACDC.png");
        Artist hStyles = new Artist("Harry Styles", null, null, "/images/EnricoStile.png");
        Artist liga = new Artist("Ligabue", null, null, "/images/Ligabue.png");
        Artist salmo = new Artist("Salmo", null, null, "/images/Salmo.png");
        Artist marra = new Artist("Marracash", null, null, "/images/Marracash.png");
        Artist fedez = new Artist("Fedez", null, null, "/images/Fedez.png");
        Artist eminem = new Artist("Eminem", null, null, "/images/Eminem.png");
        Artist pinkFloyd = new Artist("Pink Floyd", null, null, "/images/PinkFloyd.png");
        Artist mengoni = new Artist("Marco Mengoni", null, null, "/images/MarcoMengoni.png");
        Artist berti = new Artist("Orietta Berti", null, null, "/images/NonnaOrietta.png");

        artistRepository.saveAll(Arrays.asList(
                acdc,
                hStyles,
                liga,
                salmo,
                marra,
                fedez,
                eminem,
                pinkFloyd,
                mengoni,
                berti));

        Album playlist = new Album("Playlist", null, null, 2018, 13, "Sony","/images/Playlist.jpg",4);
        Album noiloroglialtri = new Album("Noi,Loro,Gli Altri", null, null, 2021, 14, "Sony","/images/NoiLoroGliAltri.jpg",5);
        Album bib = new Album("Back in Black", null, null, 1980, 10, "Sony","/images/BackInBlack.jpg",1);
        Album hh = new Album("Harry's House", null, null, 2022, 13, "Sony","/images/Harry'sHouse.jpg",2);
        Album nec = new Album("Nome e cognome", null, null, 2023, 15, "Sony","/images/beatles.jpg",0);
        Album disumano = new Album("Disumano", null, null, 2021, 24, "Sony","/images/Disumano.jpeg",6);
        Album encore = new Album("Recovery", null, null, 2010, 17, "Sony","/images/RecoveryEminem.jpg",7);
        Album wall = new Album("The Wall", null, null, 1979, 26, "Sony","/images/TheWall.jpg",8);
        Album materia = new Album("Materia Terra", null, null, 2021, 11, "Sony","/images/MateriaTerra.jpg",9);
        Album orietta = new Album("La Mia Vita È Un Film", null, null, 2021, 22, "Sony","/images/o.jpg",10);
        albumRepository.saveAll(Arrays.asList(
                playlist,
                noiloroglialtri,
                bib,
                hh,
                nec,
                disumano,
                encore,
                wall,
                materia,
                orietta));

        Song min = new Song("90 Min", null, null, 2018, 3.51,"","/images/Playlist.jpg",4,1);
        Song stai = new Song("Stai Zitto", null, null, 2018, 3.22,"Fabri Fibra","/images/Playlist.jpg",4,1);
        Song ricchi = new Song("Ricchi e Morti", null, null, 2018, 2.18,"","/images/Playlist.jpg",4,1);
        Song diospovery = new Song("Dispovery Channel", null, null, 2018, 3.24,"Nitro","/images/Playlist.jpg",4,1);
        Song cabriolet = new Song("Cabriolet", null, null, 2018, 3.05,"Sfera Ebbasta","/images/Playlist.jpg",4,1);
        Song ho = new Song("Ho Paura di Uscire", null, null, 2018, 3.17,"","/images/Playlist.jpg",4,1);
        Song sparare = new Song("Sparare Alla Luna", null, null, 2018, 3.31,"Coez","/images/Playlist.jpg",4,1);
        Song pxm = new Song("PXM", null, null, 2018, 3.04,"","/images/Playlist.jpg",4,1);
        Song cielo = new Song("Il Cielo nella stanza", null, null, 2018, 3.06,"Nstasia","/images/Playlist.jpg",4,1);
        Song tie = new Song("Tiè", null, null, 2018, 2.10,"","/images/Playlist.jpg",4,1);
        Song ora = new Song("Ora che Fai?", null, null, 2018, 3.51,"","/images/Playlist.jpg",4,1);
        Song perdonami = new Song("Perdonami", null, null, 2018, 2.14,"","/images/Playlist.jpg",4,1);
        Song lunedi = new Song("Lunedì", null, null, 2018, 3.25,"","/images/Playlist.jpg",4,1);


        Song loro = new Song("Loro", null, null, 2021, 3.18, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song pagliaccio = new Song("Pagliaccio", null, null, 2021, 2.57, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song love = new Song("∞Love", null, null, 2021, 3.38, "Guè", "/images/NoiLoroGliAltri.jpg",5,2);
        Song io = new Song("Io", null, null, 2021, 3.15, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song crazy = new Song("Crazy", null, null, 2021, 3.12, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song cosplayer = new Song("Cosplayer", null, null, 2021, 3.41, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song dubbi = new Song("Dubbi", null, null, 2021, 3.54, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song laurea = new Song("Laurea ad Honorem", null, null, 2021, 3.15, "Calcutta", "/images/NoiLoroGliAltri.jpg",5,2);
        Song noi = new Song("Noi", null, null, 2021, 4.32, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song noiloro = new Song("Noi, Loro, Gli altri Skit", null, null, 2021, 0.55, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song glialtri = new Song("Gli Altri(Giorni Stupidi)", null, null, 2021, 3.21, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song nemesi = new Song("Nemesi", null, null, 2021, 2.55, "Blanco", "/images/NoiLoroGliAltri.jpg",5,2);
        Song dumbo = new Song("Dumbo Gets Mad Skit", null, null, 2021, 0.39, "", "/images/NoiLoroGliAltri.jpg",5,2);
        Song cliff = new Song("Cliffhanger", null, null, 2021, 3.25, "", "/images/NoiLoroGliAltri.jpg",5,2);


        Song hells = new Song("Hells Bells", null, null, 1980, 5.12, "", "/images/BackInBlack.jpg",1,3);
        Song shoot = new Song("Shoot to Thrill", null, null, 1980, 5.17, "", "/images/BackInBlack.jpg",1,3);
        Song what = new Song("What Do You Do For Money Honey", null, null, 1980, 3.35, "", "/images/BackInBlack.jpg",1,3);
        Song givin = new Song("Givin The Dog Bone", null, null, 1980, 3.13, "", "/images/BackInBlack.jpg",1,3);
        Song let = new Song("Let Me Put My Love Into You", null, null, 1980, 4.15, "", "/images/BackInBlack.jpg",1,3);
        Song back = new Song("Back in Black", null, null, 1980, 4.15, "", "/images/BackInBlack.jpg",1,3);
        Song you = new Song("You Shock Me All Night Long", null, null, 1980, 3.30, "", "/images/BackInBlack.jpg",1,3);
        Song have = new Song("Have A Drink on Me", null, null, 1980, 3.58, "", "/images/BackInBlack.jpg",1,3);
        Song shake = new Song("Shake a Leg", null, null, 1980, 4.05, "", "/images/BackInBlack.jpg",1,3);
        Song rock = new Song("Rock and Roll Ain't Noise Pollution", null, null, 1980, 4.26, "", "/images/BackInBlack.jpg",1,3);

        Song music = new Song("Music For a Sushi Resturant", null, null, 2022, 3.13, "", "/images/Harry'sHouse.jpg",2,4);
        Song late = new Song("Late Night Talking", null, null, 2022, 2.57, "", "/images/Harry'sHouse.jpg",2,4);
        Song grape = new Song("Grapejuice", null, null, 2022, 3.11 , "", "/images/Harry'sHouse.jpg",2,4);
        Song as = new Song("As It Was", null, null, 2022, 2.47, "", "/images/Harry'sHouse.jpg",2,4);
        Song day = new Song("Daylight", null, null, 2022, 2.44, "", "/images/Harry'sHouse.jpg",2,4);
        Song little = new Song("Little Freak", null, null, 2022, 4.26, "", "/images/Harry'sHouse.jpg",2,4);
        Song matilda = new Song("Matilda", null, null, 2022, 4.05, "", "/images/Harry'sHouse.jpg",2,4);
        Song daydr = new Song("Daydreaming", null, null, 2022, 3.07, "", "/images/Harry'sHouse.jpg",2,4);
        Song cinema = new Song("Cinema", null, null, 2022, 4.03, "", "/images/Harry'sHouse.jpg",2,4);
        Song keep = new Song("Keep Dreaming", null, null, 2022, 3.07, "", "/images/Harry'sHouse.jpg",2,4);
        Song satellite = new Song("Satellite", null, null, 2022, 3.38, "", "/images/Harry'sHouse.jpg",2,4);
        Song boy = new Song("BoyFriends", null, null, 2022, 3.14, "", "/images/Harry'sHouse.jpg",2,4);
        Song loveof = new Song("Love Of My Life", null, null, 2022, 3.11, "", "/images/Harry'sHouse.jpg",2,4);

        Song crisi = new Song("Crisi di Stato", null, null, 2021, 3.23, "", "/images/Disumano.jpeg",6,6);
        Song viola = new Song("Viola", null, null, 2021, 3.02, "Salmo", "/images/Disumano.jpeg",6,6);
        Song ladolce = new Song("La Dolce Vita", null, null, 2021, 3.07, "", "/images/Disumano.jpeg",6,6);
        Song morire = new Song("Morire Morire", null, null, 2021, 2.46, "", "/images/Disumano.jpeg",6,6);
        Song stupido = new Song("Stupido Stupido", null, null, 2021, 2.39, "", "/images/Disumano.jpeg",6,6);
        Song bella = new Song("Bella Storia", null, null, 2021, 2.56, "", "/images/Disumano.jpeg",6,6);
        Song sapore = new Song("Sapore", null, null, 2021, 3.15, "Tedua", "/images/Disumano.jpeg",6,6);
        Song bimbi = new Song("Bimibi per Strada(Children)", null, null, 2021, 3.20, "", "/images/Disumano.jpeg",6,6);
        Song vittoria = new Song("Vittoria", null, null, 2021, 3.17, "", "/images/Disumano.jpeg",6,6);
        Song cosa = new Song("Cosa Mi Fai Fare", null, null, 2021, 3.11, "", "/images/Disumano.jpeg",6,6);

        Song cold = new Song("Cold Winds Blows", null, null, 2010, 5.30, "", "/images/RecoveryEminem.jpg",7,7);
        Song talking = new Song("Talking'2 My self", null, null, 2010, 5.00, "", "/images/RecoveryEminem.jpg",7,7);
        Song on  = new Song("On Fire", null, null, 2010, 3.33, "", "/images/RecoveryEminem.jpg",7,7);
        Song wont = new Song("Won't Back Down", null, null, 2010, 4.25, "", "/images/RecoveryEminem.jpg",7,7);
        Song wtp = new Song("WTP", null, null, 2010, 3.58, "", "/images/RecoveryEminem.jpg",7,7);
        Song going = new Song("Going Through Changes", null, null, 2010, 4.58, "", "/images/RecoveryEminem.jpg",7,7);
        Song not = new Song("Not Afraid", null, null, 2010, 4.08, "", "/images/RecoveryEminem.jpg",7,7);
        Song seduction = new Song("Seduction", null, null, 2010, 4.35, "", "/images/RecoveryEminem.jpg",7,7);
        Song no = new Song("No Love", null, null, 2010, 4.59, "", "/images/RecoveryEminem.jpg",7,7);


        Song in = new Song("In The Flesh?", null, null, 1979, 3.18, "", "/images/TheWall.jpg",8,8);
        Song the = new Song("The Thin Ice", null, null, 1979, 2.26, "", "/images/TheWall.jpg",8,8);
        Song another = new Song("Antoher Brick In The Wall, Pt.1", null, null, 1979, 3.11, "", "/images/TheWall.jpg",8,8);
        Song happiest = new Song("The Happiest Days Of Our Lives", null, null, 1979, 1.38, "", "/images/TheWall.jpg",8,8);
        Song another2 = new Song("Antoher Brick In The Wall, Pt.2", null, null, 1979, 3.48, "", "/images/TheWall.jpg",8,8);

        Song cambia = new Song("Cambia Un Uomo", null, null, 2021, 3.55, "", "/images/MateriaTerra.jpg",9,9);
        Song una = new Song("Una Canzone Triste", null, null, 2021, 3.34, "", "/images/MateriaTerra.jpg",9,9);
        Song il = new Song("Il Meno Possibile", null, null, 2021, 2.59, "Gazzelle", "/images/MateriaTerra.jpg",9,9);
        Song indue = new Song("In Due Minuti", null, null, 2021, 2.59, "", "/images/MateriaTerra.jpg",9,9);
        Song mi = new Song("Mi Fiderò", null, null, 2021, 3.30, "Madame", "/images/MateriaTerra.jpg",9,9);
        Song ma = new Song("Ma Stasera", null, null, 2021, 3.43, "", "/images/MateriaTerra.jpg",9,9);
        Song appunto = new Song("Appunto 1. 23-01-2021", null, null, 2021, 1.19, "", "/images/MateriaTerra.jpg",9,9);
        Song proibito = new Song("Proibito", null, null, 2021, 3.08, "", "/images/MateriaTerra.jpg",9,9);
        Song appunto2 = new Song("Appunto 2. 14-04-2021", null, null, 2021, 0.44, "", "/images/MateriaTerra.jpg",9,9);
        Song luce = new Song("Luce", null, null, 2021, 4.04, "", "/images/MateriaTerra.jpg",9,9);
        Song fiore = new Song("Un Fiore Contro Il Diluvio", null, null, 2021, 2.55, "", "/images/MateriaTerra.jpg",9,9);

        Song tusei = new Song("Tu Sei Quello", null, null, 2022, 3.14, "", "/images/o.jpg",10,10);
        Song altalena = new Song("L'Altalena", null, null, 2022, 2.11, "", "/images/o.jpg",10,10);
        Song ioti = new Song("Io Ti Darò di Più", null, null, 2022, 2.36, "", "/images/o.jpg",10,10);
        Song non = new Song("Non Illuderti Mai", null, null, 2022, 2.26, "", "/images/o.jpg",10,10);
        Song solotu = new Song("Solo Tu", null, null, 2022, 2.41, "", "/images/o.jpg",10,10);


        songRepository.saveAll(Arrays.asList(
                min,
                stai,
                ricchi,
                diospovery,
                cabriolet,
                ho,
                sparare,
                pxm,
                cielo,
                tie,
                ora,
                perdonami,
                lunedi,
                loro,
                pagliaccio,
                love,
                io,
                crazy,
                cosplayer,
                dubbi,
                laurea,
                noi,
                noiloro,
                glialtri,
                nemesi,
                dumbo,
                cliff,
                hells,
                shoot,
                what,
                givin,
                let,
                back,
                you,
                have,
                shake,
                rock,
                music,
                late,
                grape,
                as,
                day,
                little,
                matilda,
                daydr,
                cinema,
                keep,
                satellite,
                boy,
                loveof,
                crisi,
                viola,
                ladolce,
                morire,
                stupido,
                bella,
                sapore,
                bimbi,
                vittoria,
                cosa,
                cold,
                talking,
                on,
                wont,
                wtp,
                going,
                not,
                seduction,
                no,
                in,
                the,
                another,
                happiest,
                another2,
                cambia,
                una,
                il,
                indue,
                mi,
                ma,
                appunto,
                proibito,
                appunto2,
                luce,
                fiore,
                tusei,
                altalena,
                ioti,
                non,
                solotu)
                );

        // artist->album && artist->song questi non funzionano
        // salmo.assignSong(cult);

        // album -> artist
        // cult.assignArtist(salmo);
        // persona.assignArtist(marra);

        // song -> album
        // anthem.assignAlbum(cult);

        /*
         * song -> artist non funziona
         * anthem.assignArtist(salmo);
         * cringe.assignArtist(salmo);
         * incubi.assignArtist(salmo);
         * 
         * bodyParts.assignArtist(marra);
         * qicc.assignArtist(marra);
         * qcnp.assignArtist(marra);
         */

        // System.out.println(acdc.getImageURL());
        System.out.println("----------All Data saved into Database----------------------");
    }
}
