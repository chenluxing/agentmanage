package com.agentmanage.module.common.entity.enums;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.UnmodifiableMap;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @since 2016/4/26
 */
public class EnumPool {

    private static Map<String, Map<Integer, BaseEnum>> POOL = new HashMap<>();

    public static void addEnums(String name, Class<? extends BaseEnum> clazz){
        try {
            Method valuesMethod = clazz.getMethod("values");
            Object obj = valuesMethod.invoke(clazz);
            BaseEnum[] enums = (BaseEnum[]) obj;
//            POOL.put(name, clazz);
        } catch (Exception ex) {

        }
    }

    public static Map<Integer, BaseEnum> getEnums(String name){
        Map<Integer, BaseEnum> map = POOL.get(name);
        if (map == null){
            map = MapUtils.EMPTY_MAP;
        }else{
            map = UnmodifiableMap.decorate(map);
        }
        return map;
    }

    public static <T> T getEnum(String name, Integer value){
        return (T) getEnums(name).get(value);
    }
}
