package by.gsu.petclinicx.service;

import by.gsu.petclinicx.model.Animal;
import by.gsu.petclinicx.repository.common.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class AnimalMenu {

    private final   AnimalFactory factory;
    private final CrudRepository<Animal> animalRepository;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public AnimalMenu(AnimalFactory factory, CrudRepository<Animal> animalRepository) {
        this.factory = factory;
        this.animalRepository = animalRepository;
    }

    public void start() {
        while (true) {
            try {
                printHelp();
                int n = readInt(1, 6);
                switch (n) {
                    case 1:
                        animalRepository.create(factory.readAnimal()); break;
                    case 2:
                        Animal animal = factory.readAnimal();
                        animal.setId((long) readId());
                        animalRepository.update(animal);
                        break;
                    case 3:
                        animalRepository.delete((long) readId());break;
                    case 4:
                        animalRepository.getAll().forEach(System.out::println); break;
                    case 5:
                        animalRepository.create(factory.generateAnimal()); break;
                    case 6:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printHelp() {
        String s = "1. add\n" +
                "2. update\n" +
                "3. delete\n" +
                "4. print all\n" +
                "5. add generated\n" +
                "6. exit\n";
        System.out.println(s);
    }

    private int readId() {
        System.out.println("input id");
        return readInt(1, Integer.MAX_VALUE);
    }

    private int readInt(int min, int max) {
        int result;
        while (true) {
            try {
                result = Integer.parseInt(scanner.nextLine());
                if (result < min || result > max) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("sorry, try again");
            }
        }
        return result;
    }

}
