package by.gsu.petclinicx;

import by.gsu.petclinicx.service.AnimalMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PetclinicConfig.class);


        AnimalMenu menu = context.getBean(AnimalMenu.class);
        menu.start();

        context.registerShutdownHook();
    }
}
