package by.gsu.petclinicx;

import by.gsu.petclinicx.model.Animal;
import by.gsu.petclinicx.repository.common.CrudRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PetclinicConfig.class);


//        SessionFactory factory = context.getBean(SessionFactory.class);
//
//        Session session = factory.openSession();
//        Transaction tx = session.beginTransaction();
//
////        Disease disease = session.get(Disease.class, 1L);
//
//        Animal animal = session.get(Animal.class, 2L);
//        System.out.println(animal);
//
//        tx.commit();

        CrudRepository<Animal> repository =
                (CrudRepository<Animal>) context.getBean("animalRepository");

        Animal animal = repository.getById(1L);

        System.out.println(animal);

//        System.out.println(disease);


    }
}
