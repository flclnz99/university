package com.example.demo.config;

import com.example.demo.dummy.Dummy;
import com.example.demo.dummy.DummyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DummyConfiguration {

    @Bean
    CommandLineRunner init (DummyRepository repository){
        return args -> {
            List<Dummy> dummy = (List<Dummy>) repository.findAll();
            if(dummy.isEmpty()){
                System.out.println("Insert dummy element on DB");
                repository.save(new Dummy("dummy@email.provider"));
            }
        };
    }
}
