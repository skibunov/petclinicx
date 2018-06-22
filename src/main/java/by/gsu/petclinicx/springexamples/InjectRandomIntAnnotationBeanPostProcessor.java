package by.gsu.petclinicx.springexamples;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class InjectRandomIntAnnotationBeanPostProcessor
        implements BeanPostProcessor{

    Random random = new Random();


    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        List<Field> fields = Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(InjectRandomInt.class) != null)
                .collect(Collectors.toList());

        for (Field field : fields) {
            field.setAccessible(true);
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            int max = annotation.max();
            int min = annotation.min();
            int value = min + random.nextInt(max - min);

            ReflectionUtils.setField(field, bean, value);
        }


        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
