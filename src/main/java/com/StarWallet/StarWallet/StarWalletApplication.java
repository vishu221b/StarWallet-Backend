package com.StarWallet.StarWallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StarWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarWalletApplication.class, args);
	}

}
