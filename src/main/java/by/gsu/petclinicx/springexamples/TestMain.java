package by.gsu.petclinicx.springexamples;

import by.gsu.petclinicx.PetclinicConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PetclinicConfig.class);

        Runnable bean = (Runnable) context.getBean("testBean");
        bean.run();

        context.registerShutdownHook();
    }


}
