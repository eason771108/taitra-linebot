package org.taitar.linebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@EnableScheduling
@SpringBootApplication
public class LinebotApplication {

	public static Path tempContentDir;

	public static void main(String[] args) throws IOException {
		tempContentDir = Files.createTempDirectory("line-bot");
		SpringApplication.run(LinebotApplication.class, args);
	}


}
