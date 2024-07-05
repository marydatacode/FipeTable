package br.com.alura.FipTable;

import br.com.alura.FipTable.Main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipTableApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipTableApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Main main = new Main();
		main.showMenu();
	}
}
