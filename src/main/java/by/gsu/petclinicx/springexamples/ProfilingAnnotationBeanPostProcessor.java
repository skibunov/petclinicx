package by.gsu.petclinicx.springexamples;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {

    Map<String, Object> map = new HashMap<>();

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        long count = Arrays.stream(
                bean.getClass().getDeclaredMethods())
                .filter(method ->
                        method.getAnnotation(Profiling.class) != null)
                .count();
        if (count > 0) {
            map.put(beanName, bean);
        }
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (map.containsKey(beanName)) {
            Object original = map.get(beanName);
            List<Method> methods = Arrays.stream(original.getClass().getDeclaredMethods())
                    .filter(method -> method.getAnnotation(Profiling.class) != null)
                    .collect(Collectors.toList());

            return Proxy.newProxyInstance(
                    original.getClass().getClassLoader(),
                    original.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        long start = System.currentTimeMillis();
                        Object result = method.invoke(bean, args);
                        long stop = System.currentTimeMillis();

                        if (methods.contains(method)) {
                            System.out.println(stop - start);
                        }

                        return result;
                    }
            );
        }
        return bean;
    }
}
