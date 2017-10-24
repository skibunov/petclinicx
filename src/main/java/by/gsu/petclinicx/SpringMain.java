package by.gsu.petclinicx;

import by.gsu.petclinicx.service.AnimalMenu;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("root-config.xml");
        AnimalMenu menu = context.getBean(AnimalMenu.class);
        menu.start();
        context.registerShutdownHook();
    }
}
