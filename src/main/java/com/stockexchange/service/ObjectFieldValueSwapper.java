package com.stockexchange.service;

import com.stockexchange.exception.InvalidMethodNamesException;
import com.stockexchange.exception.UnavailableElementException;
import com.stockexchange.model.PossessId;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ObjectFieldValueSwapper<T extends PossessId> {

    private static final String from = "get";
    private static final String to = "set";
    private static final int prefixLength = 3;

    public void antiNullSwap(T fromObject, T toObject) throws UnavailableElementException, InvalidMethodNamesException {
        Map<String, Method> gettersFromDeliveredObject = getMethodsWhichStartWithPrefix(from, fromObject.getClass());
        Map<String, Method> settersFromDatabaseObject = getMethodsWhichStartWithPrefix(to, toObject.getClass());

        Map<String, Method> fromGetters = getRidOfPrefixFromKeys(gettersFromDeliveredObject);
        Map<String, Method> toSetters = getRidOfPrefixFromKeys(settersFromDatabaseObject);
        validate(fromGetters, toSetters);

        Set<String> methodNames = fromGetters.keySet();

        try {
            for (String methodByName : methodNames) {
                Object deliveredValue = fromGetters.get(methodByName).invoke(fromObject);

                if (deliveredValue != null) {
                    toSetters.get(methodByName).invoke(toObject, deliveredValue);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(Map<String, Method> gettersFrom, Map<String, Method> settersTo)
            throws InvalidMethodNamesException {

        if (gettersFrom.size() != settersTo.size())
            throw new InvalidMethodNamesException("Not equal setters and getters amount.Inconsistent naming.");

        Set<String> names = new HashSet<>();
        names.addAll(gettersFrom.keySet());
        names.addAll(settersTo.keySet());

        if (names.size() != gettersFrom.size())
            throw new InvalidMethodNamesException("Inconsistent naming of setters and getters");
    }

    private Map<String, Method> getMethodsWhichStartWithPrefix(String prefix, Class<?> deliveredClass) {
        Method[] methods = deliveredClass.getDeclaredMethods();

        return Arrays.stream(methods)
                .filter(mth -> mth.getName()
                                  .startsWith(prefix))
                .collect(Collectors.toMap(Method::getName, method -> method));
    }

    private Map<String, Method> getRidOfPrefixFromKeys(Map<String, Method> map) {
        Function<String, String> abandonPrefix = s -> s.substring(prefixLength, s.length());

        return map.entrySet().stream()
                .collect(Collectors.toMap(es -> abandonPrefix.apply(es.getKey()),
                        Map.Entry::getValue));
    }
}
