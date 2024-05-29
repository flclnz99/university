package com.example.research.Controller;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@RestController
public class ResearchController {

    //get mapping
    @GetMapping("/research")
    public String Research() throws IOException {
            // Creating a path choosing file from local
            // directory by creating an object of Path class
            Path fileName
                    = Path.of("C:\\Users\\HP\\Desktop\\Pogetto-TAASS\\My Music Space\\Research\\src\\main\\resources\\templates\\Research_page.html");

            // Now calling Files.readString() method to
            // read the file
            String str = Files.readString(fileName);

            // Printing the string
            return  str;
    }

    @GetMapping("/namesong")
    public String Namesong(){
        return "Sei nella pagina della canzone";
    }

}
