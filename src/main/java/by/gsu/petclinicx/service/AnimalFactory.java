package by.gsu.petclinicx.service;

import by.gsu.petclinicx.model.Animal;
import by.gsu.petclinicx.model.Cat;
import by.gsu.petclinicx.model.Disease;
import by.gsu.petclinicx.model.Dog;
import by.gsu.petclinicx.repository.common.GetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class AnimalFactory {
    private final Scanner scanner = new Scanner(System.in);
    private final GetRepository<Disease> diseaseRepository;
    private int generatedCount = 0;

    @Autowired
    public AnimalFactory(GetRepository<Disease> diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public Animal readAnimal() {
        generatedCount++;
        int typeId = 0;
        while (typeId != 1 && typeId != 2) {
            System.out.println("cat(1) or dog(2)?");
            typeId = readInt();
        }

        System.out.println("input name");
        String name = scanner.nextLine();

        Animal result;
        if (typeId == 1) {
            System.out.println("input mice count");
            int mice = readInt(0, Integer.MAX_VALUE);
            Disease disease = readDisease();
            result = new Cat((long) generatedCount, name, disease, mice);
        } else {
            System.out.println("input bones count");
            int bones = readInt(0, Integer.MAX_VALUE);
            Disease disease = readDisease();
            result = new Dog((long) generatedCount, name, disease, bones);
        }
        return result;
    }

    public Animal generateAnimal() {
        generatedCount++;
        String name = "generated" + generatedCount;
        List<Disease> diseases = diseaseRepository.getAll();
        Disease disease = diseases.get(generatedCount % ((diseases.size() - 1) != 0 ? (diseases.size() - 1) : 1));
        if (generatedCount % 2 == 0) {
            return new Cat((long) generatedCount, name, disease, generatedCount);
        } else {
            return new Dog((long) generatedCount, name, disease, generatedCount);
        }
    }

    private Disease readDisease() {
        System.out.println("choose disease:");
        List<Disease> diseases = diseaseRepository.getAll();

        int i = 1;
        for (Disease disease : diseases) {
            System.out.println(i + ": " + disease.getName());
            i++;
        }
        int diseaseId = -1;
        while (diseaseId < 1 || diseaseId > diseases.size()) {
            diseaseId = readInt(1, diseases.size());
        }
        return diseases.get(diseaseId - 1);
    }

    private int readInt() {
        return readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
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
