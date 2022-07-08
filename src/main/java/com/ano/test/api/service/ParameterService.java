package com.ano.test.api.service;

import com.ano.test.api.annotation.ParameterMethod;
import com.ano.test.api.annotation.ParameterSize;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ParameterService {

    public ParameterService() {
        annotationCheck(findAnnotatedClasses("com.ano.test"));
    }

    public static <T> List<Class<?>> findAnnotatedClasses(String scanPackage) {
        List<Class<?>> foundClass = new ArrayList<>();
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
        for(BeanDefinition beanDefinition : provider.findCandidateComponents(scanPackage)) {
            try {
                foundClass.add(Class.forName(beanDefinition.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return foundClass;
    }

    private static ClassPathScanningCandidateComponentProvider createComponentScanner() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(ParameterSize.class));
        return provider;
    }

    public static void annotationCheck(List<Class<?>> annotatedClass) {
        ParameterMethod anno = null;
        for(Class<?> arrayToItem : annotatedClass) {
            Method[] fields = arrayToItem.getDeclaredMethods();
            for(Method field : fields) {
                anno = field.getDeclaredAnnotation(ParameterMethod.class);
                if(anno == null) continue;
                System.out.println(field.getName());
            }
        }
    }

}
