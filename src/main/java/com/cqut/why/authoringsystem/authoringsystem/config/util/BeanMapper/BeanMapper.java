package com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@Component
public class BeanMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.create()
            .withMappingFiles("dozer-config.xml")
            .build();

    public static Mapper getMapper() {
        return mapper;
    }


    public static <T, S> T map(S source, Class<T> destinationClass) {
        if (null == source)
            return null;
        return mapper.map(source, destinationClass);
    }

    public static <T, S> T map(S source, Class<T> destinationClass, BiConsumer<S,T> postHandler){
        return map(source, destinationClass, (s,t) -> { postHandler.accept(s,t); return t;});
    }

    public static <T, S> T map(S source, Class<T> destinationClass, BiFunction<S, T, T> postHandler) {
        if (null == source)
            return null;
        return postHandler.apply(source, mapper.map(source, destinationClass));
    }

    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        if (null == sourceList)
            return null;
        List<T> destinationList = new ArrayList<>();
        for (Object sourceObject : sourceList) {
            destinationList.add(map(sourceObject, destinationClass));
        }
        return destinationList;
    }

    public static <T, S> List<T> mapList(Collection<S> sourceList, Class<T> destinationClass, BiFunction<S, T, T> postHandler) {
        if (null == sourceList)
            return null;
        List<T> destinationList = new ArrayList<>();
        for (S sourceObject : sourceList) {
            destinationList.add(map(sourceObject, destinationClass, postHandler));
        }
        return destinationList;
    }

    public static <T, S> List<T> mapList(Collection<S> sourceList, Class<T> destinationClass, BiConsumer<S,T> postHandler) {
        return mapList(sourceList, destinationClass, (s,t) -> {postHandler.accept(s,t); return t;});
    }

    public static void copy(Object source, Object destinationObject) {
        mapper.map(source, destinationObject);
    }
}
