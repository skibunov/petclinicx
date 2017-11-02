package by.gsu.petclinicx.repository.animal;

import by.gsu.petclinicx.model.Animal;
import by.gsu.petclinicx.model.Cat;
import by.gsu.petclinicx.model.Disease;
import by.gsu.petclinicx.model.Dog;
import by.gsu.petclinicx.repository.common.GetRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalResultSetParser implements RowMapper<Animal> {

    private final GetRepository<Disease> diseaseRepository;

    public AnimalResultSetParser(GetRepository<Disease> diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Nullable
    @Override
    public Animal mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");

        Object miceObj = resultSet.getObject("mice");
        Object bonesObj = resultSet.getObject("bones");


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

        long diseaseId = resultSet.getLong("disease_id");
        Disease disease = diseaseRepository.getById(diseaseId);

        animal.setDisease(disease);

        return animal;
    }
}
