package by.gsu.petclinicx.springexamples;

import org.springframework.stereotype.Component;

@Component("testBean")
public class Test implements Runnable {

    @InjectRandomInt(min = 2, max = 10)
    int repeat;


    @InjectRandomInt(min = 1000, max = 2000)
    int x;

    public Test() {
        System.out.println(x);
    }

    @Override
    @Profiling
    public void run() {
        System.out.println(x);
        try {
            Thread.sleep(repeat * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < repeat; i++) {
            System.out.println("hello!");

        }
    }
}
