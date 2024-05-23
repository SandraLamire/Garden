package fr.eni.garden;

import fr.eni.garden.entity.Garden;
import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Planting;
import fr.eni.garden.entity.Square;
import fr.eni.garden.enums.ExposureType;
import fr.eni.garden.enums.PlantType;
import fr.eni.garden.enums.SoilType;
import fr.eni.garden.service.GardenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

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

        Garden heaven = Garden.builder().name("Heaven").location("Stairway").gardenSurface(300).build();
        Garden hell = Garden.builder().name("Hell").location("Highway").gardenSurface(900).build();
        Garden purgatory = Garden.builder().name("Purgatory").location("Nowhere").gardenSurface(600).build();

        Square squareHeaven1 = Square.builder().squareSurface(100).soilType(SoilType.CLAYEY).exposureType(ExposureType.SUN).garden(heaven).build();
        Square squareHeaven2 = Square.builder().squareSurface(150).soilType(SoilType.SANDY).exposureType(ExposureType.SHADE).garden(heaven).build();

        Square squareHell1 = Square.builder().squareSurface(300).soilType(SoilType.LOAMY).exposureType(ExposureType.PARTIAL_SHADE).garden(hell).build();
        Square squareHell2 = Square.builder().squareSurface(400).soilType(SoilType.CLAYEY).exposureType(ExposureType.SUN).garden(hell).build();

        Square squarePurgatory1 = Square.builder().squareSurface(200).soilType(SoilType.SANDY).exposureType(ExposureType.SHADE).garden(purgatory).build();
        Square squarePurgatory2 = Square.builder().squareSurface(200).soilType(SoilType.LOAMY).exposureType(ExposureType.PARTIAL_SHADE).garden(purgatory).build();

        Plant beefHeartTomato = Plant.builder().name("Tomato").plantType(PlantType.FRUIT).variety("Beef Heart").plantSurface(10).build();
        Plant cherryTomato = Plant.builder().name("Tomato").plantType(PlantType.FRUIT).variety("Cherry").plantSurface(8).build();
        Plant newPotato = Plant.builder().name("Potato").plantType(PlantType.ROOT).variety("New").plantSurface(6).build();
        Plant redPotato = Plant.builder().name("Potato").plantType(PlantType.ROOT).variety("Red").plantSurface(5).build();
        Plant kale = Plant.builder().name("Cabbage").plantType(PlantType.ROOT).variety("Kale").plantSurface(15).build();
        Plant cauliflower = Plant.builder().name("Cabbage").plantType(PlantType.ROOT).variety("Cauliflower").plantSurface(20).build();

        Planting planting1 = Planting.builder().quantity(6).setUpDate(LocalDate.now().plusDays(1)).harvestDate(LocalDate.now().plusDays(8)).square(squareHeaven1).plant(beefHeartTomato).build();
        Planting planting2 = Planting.builder().quantity(5).setUpDate(LocalDate.now().plusDays(2)).harvestDate(LocalDate.now().plusDays(9)).square(squareHeaven1).plant(cherryTomato).build();

        Planting planting3 = Planting.builder().quantity(50).setUpDate(LocalDate.now().plusDays(3)).harvestDate(LocalDate.now().plusDays(10)).square(squareHell1).plant(newPotato).build();

        Planting planting4 = Planting.builder().quantity(5).setUpDate(LocalDate.now().plusDays(4)).harvestDate(LocalDate.now().plusDays(11)).square(squarePurgatory1).plant(kale).build();
        Planting planting5 = Planting.builder().quantity(5).setUpDate(LocalDate.now().plusDays(5)).harvestDate(LocalDate.now().plusDays(12)).square(squarePurgatory1).plant(cauliflower).build();





    }
}
