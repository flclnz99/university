package com.example.spikemongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class SpikeMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpikeMongoDbApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(UserRepository repository, MongoTemplate template){
		return args -> {

			String email = "pippo.franco@gmail.com";
			User user = new User("Pippo",
								"Franco",
								email,
								"password",
								List.of("Perfect, Canzone2, Canzone3")
			);


			// QUERY PERSONALIZZATE DA CODICE SENZA JPA
			/*Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));

			List<User> users = template.find(query, User.class);
			if (users.size() > 1){
				throw  new IllegalStateException("Found more than 1 user with the same email:" + email);
			}

			if (users.isEmpty()){
				System.out.println("Insert user with email: " + email);
				repository.insert(user);
			}else {
				System.out.println("User with email " + email + " already exists");
			}*/

			//repository.insert(user);

			repository.findUserByEmail(email).ifPresentOrElse(user1 -> {
				System.out.println("User with email " + email + " already exists");
			}, () -> {
				System.out.println("Insert user with email: " + email);
				repository.insert(user);
			});
		};
	}
}
