package fr.eni.garden;

import fr.eni.garden.entity.Garden;
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

        Garden garden1 = Garden.builder().location("Sud").name("G1").surface(20).city("Rennes").build();
        this.gardenService.addGarden(garden1);
        System.out.println(garden1);
        garden1.setLocation("Nord");
        this.gardenService.updateGarden(garden1);
        System.out.println(garden1);
        System.out.println(this.gardenService.getGarden(1));
        this.gardenService.deleteGarden(garden1);

    }
}
