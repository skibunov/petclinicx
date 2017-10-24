package by.gsu.petclinicx;

import by.gsu.petclinicx.service.AnimalMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("root-config.xml");
        AnimalMenu menu = context.getBean(AnimalMenu.class);
        menu.start();
    }
}
