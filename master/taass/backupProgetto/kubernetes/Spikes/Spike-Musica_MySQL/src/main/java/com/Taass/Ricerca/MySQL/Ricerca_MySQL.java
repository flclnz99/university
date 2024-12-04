package com.Taass.Ricerca.MySQL;

//https://www.youtube.com/watch?v=f5bdUjEIbrg

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/canzoni")
public class Ricerca_MySQL {

    public static void main(String[] args){
        SpringApplication.run(Ricerca_MySQL.class, args);
    }

    //public final CanzoniRepository canzoniRepository;

    /*public Ricerca_MySQL(CanzoniRepository canzoniRepository) {
        this.canzoniRepository = canzoniRepository;
    }

    @GetMapping
    public List<Canzoni> getCanzoni(){
        return canzoniRepository.findAll();
    }

    record NewCanzoniRequest(
            String Titolo,
            Integer AlbumC,
            Integer ArtistaC,
            Integer Anno,
            Integer Durata,
            String Feat
    ){}

    @PostMapping
    public void  addCanzoni(@RequestBody NewCanzoniRequest request){
        Canzoni canzoni = new Canzoni();
        canzoni.setTitolo(request.Titolo());
        canzoni.setAlbumC(request.AlbumC());
        canzoni.setArtistaC(request.ArtistaC());
        canzoni.setAnno(request.Anno());
        canzoni.setDurata(request.Durata());
        canzoni.setFeat(request.Feat());
        canzoniRepository.save(canzoni);
    }

    @DeleteMapping("{canzoniId}")
    public void deleteCanzoni(@PathVariable("canzoniId") Integer id){
        canzoniRepository.deleteById(id);
    }
     */
}
