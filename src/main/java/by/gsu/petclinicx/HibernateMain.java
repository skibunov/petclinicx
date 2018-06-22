package by.gsu.petclinicx;

import by.gsu.petclinicx.model.Animal;
import by.gsu.petclinicx.repository.common.CrudRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class HibernateMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PetclinicConfig.class);


        CrudRepository<Animal> repository =
                (CrudRepository<Animal>) context.getBean("animalRepository");

//        Animal animal = repository.getById(1L);
        List<Animal> all = repository.getAll();

        System.out.println(all);

    }
}
