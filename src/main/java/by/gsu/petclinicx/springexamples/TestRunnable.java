package by.gsu.petclinicx.springexamples;

import org.springframework.stereotype.Component;

@Component("testBean")
public class TestRunnable implements Runnable {

    @InjectRandomInt(min = 2, max = 10)
    int repeat;


    @InjectRandomInt(min = 1000, max = 2000)
    int x;

    public TestRunnable() {
    }

    @Override
    @Profiling
    public void run() {
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
