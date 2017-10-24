package by.gsu.petclinicx;

import by.gsu.petclinicx.repository.animal.AnimalRepository;
import by.gsu.petclinicx.repository.animal.AnimalResultSetParser;
import by.gsu.petclinicx.repository.common.IdResultSetParser;
import by.gsu.petclinicx.repository.common.QueryExecutor;
import by.gsu.petclinicx.repository.disease.DiseaseRepository;
import by.gsu.petclinicx.repository.disease.DiseaseResultSetParser;
import by.gsu.petclinicx.service.AnimalFactory;
import by.gsu.petclinicx.service.AnimalMenu;

public class Main {

    public static void main(String[] args) {

        IdResultSetParser idRSParser = new IdResultSetParser();
        QueryExecutor queryExecutor = new QueryExecutor(idRSParser);

        DiseaseResultSetParser diseaseResultSetParser = new DiseaseResultSetParser();
        DiseaseRepository diseaseRepository = new DiseaseRepository(diseaseResultSetParser, queryExecutor);

        AnimalResultSetParser animalResultSetParser = new AnimalResultSetParser(diseaseRepository);
        AnimalRepository animalRepository = new AnimalRepository(queryExecutor, animalResultSetParser);

        AnimalFactory factory = new AnimalFactory(diseaseRepository);

        new AnimalMenu(factory, animalRepository).start();
    }
}
