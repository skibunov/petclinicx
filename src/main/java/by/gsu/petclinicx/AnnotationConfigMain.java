package by.gsu.petclinicx;

import by.gsu.petclinicx.repository.disease.DiseaseRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PetclinicConfig.class);

        DiseaseRepository repository = context.getBean(DiseaseRepository.class);
        repository.getAll().forEach(System.out::println);

        context.registerShutdownHook();
    }
}
