package fr.eni.garden;

import fr.eni.garden.service.GardenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GardenApplication implements CommandLineRunner {

    private final GardenService gardenService;

    public GardenApplication(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GardenApplication.class, args);
    }


    @Override
    public void run(String... args) {


    }
}
