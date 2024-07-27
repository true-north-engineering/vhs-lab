package com.anam.vhsrentalshop;

import com.anam.vhsrentalshop.domain.entities.RentalEntity;
import com.anam.vhsrentalshop.domain.entities.UserEntity;
import com.anam.vhsrentalshop.domain.entities.VhsEntity;
import com.anam.vhsrentalshop.repositories.RentalRepository;
import com.anam.vhsrentalshop.repositories.UserRepository;
import com.anam.vhsrentalshop.repositories.VhsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;

@SpringBootApplication
public class VhsRentalApp implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;

	@Autowired
	VhsRepository vhsRepository;

	@Autowired
	RentalRepository rentalRepository;

	public static void main(String[] args) {
		SpringApplication.run(VhsRentalApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserEntity user1 = new UserEntity("John Moore", "john.moore@example.com");
		userRepository.save(user1);
		UserEntity user2 = new UserEntity("Mary White", "mary.white@example.com");
		userRepository.save(user2);

		VhsEntity vhs1 = new VhsEntity("The Matrix", "Sci-Fi", 1999);
		vhsRepository.save(vhs1);
		VhsEntity vhs2 = new VhsEntity("The Godfather", "Crime", 1972);
		vhsRepository.save(vhs2);
		VhsEntity vhs3 = new VhsEntity("Gladiator", "Action", 2000);
		vhsRepository.save(vhs3);
		VhsEntity vhs4 = new VhsEntity("Home Alone", "Comedy", 1990);
		vhsRepository.save(vhs3);

		RentalEntity rental1 = new RentalEntity(user1, vhs1, LocalDate.parse("2024-07-20") );
		rentalRepository.save(rental1);
		RentalEntity rental2 = new RentalEntity(user2, vhs3,LocalDate.parse("2024-07-23"));
		rentalRepository.save(rental2);
	}
}
