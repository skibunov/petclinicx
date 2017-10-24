package by.gsu.petclinicx.repository.animal;

import by.gsu.petclinicx.model.Animal;
import by.gsu.petclinicx.model.Cat;
import by.gsu.petclinicx.model.Disease;
import by.gsu.petclinicx.model.Dog;
import by.gsu.petclinicx.repository.common.GetRepository;
import by.gsu.petclinicx.repository.common.ResultSetParser;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnimalResultSetParser implements ResultSetParser<Animal> {

    private final GetRepository<Disease> diseaseRepository;

    public AnimalResultSetParser(GetRepository<Disease> diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public List<Animal> parse(ResultSet rs) {
        List<Animal> animals = new ArrayList<>();

        try {
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");

                Object miceObj = rs.getObject("mice");
                Object bonesObj = rs.getObject("bones");


                Animal animal;
                if (bonesObj != null) {
                    Dog dog = new Dog();
                    dog.setBones(Integer.valueOf(bonesObj.toString()));
                    animal = dog;
                } else {
                    Cat cat = new Cat();
                    cat.setMice(Integer.valueOf(miceObj.toString()));
                    animal = cat;
                }

                animal.setId(id);
                animal.setName(name);

                long diseaseId = rs.getLong("disease_id");
                Disease disease = diseaseRepository.getById(diseaseId);

                animal.setDisease(disease);

                animals.add(animal);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return animals;
    }
}
