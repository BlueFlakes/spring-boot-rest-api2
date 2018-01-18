package com.stockexchange.service;

import com.stockexchange.exception.InvalidMethodNamesException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class ObjectFieldValueSwapper {

    private static final int maxPrefixLength = 3;
    private static final int minPrefixLength = 2;

    public <T> void antiNullSwap(T fromObject, T toObject) throws InvalidMethodNamesException {
        List<String> validPrefixesForGetters = Arrays.asList("get", "is");
        List<String> validPrefixesForSetters = Collections.singletonList("set");

        Map<String, Method> fromGetters = collectMethodsJustForSettersAndGetters(fromObject.getClass(), validPrefixesForGetters);
        Map<String, Method> toSetters = collectMethodsJustForSettersAndGetters(toObject.getClass(), validPrefixesForSetters);

        validate(fromGetters, toSetters, fromObject);
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

    private Map<String, Method> collectMethodsJustForSettersAndGetters(Class<?> deliveredClass, List<String> validPrefixes) {
        List<String> fieldsNames = Arrays.asList(getFieldsNames(deliveredClass));
        List<Method> methods = Arrays.asList(deliveredClass.getMethods());

        Map<String, Method> methodsContainer = new HashMap<>();

        for (String fieldName : fieldsNames) {
            Method method = findExpectedMethodForGivenField(methods, validPrefixes, fieldName);

            if (method != null && isMethodValid(fieldName, method)) {
                String lowerFieldName = fieldName.toLowerCase();
                methodsContainer.put(lowerFieldName, method);
            }
        }

        return methodsContainer;
    }

    private Method findExpectedMethodForGivenField(List<Method> methods,
                                                   List<String> validPrefixes,
                                                   String fieldName) {

        return methods.stream()
                      .filter(mth -> methodNameEndsWithFieldNameIgnoreCase(mth.getName(), fieldName))
                      .filter(mth -> doSentenceStartsWithAnyValidPrefix(mth.getName(), validPrefixes))
                      .findAny()
                      .orElse(null);
    }

    private boolean methodNameEndsWithFieldNameIgnoreCase(String methodName, String fieldName) {
        methodName = methodName.toLowerCase();
        fieldName = fieldName.toLowerCase();

        return methodName.endsWith(fieldName);
    }

    private boolean doSentenceStartsWithAnyValidPrefix(String sentence, List<String> validPrefixes) {
        return validPrefixes.stream()
                            .anyMatch(sentence::startsWith);
    }

    private boolean isMethodValid(String fieldName, Method method) {
        String methodName = method.getName();

        int fieldNameLength = fieldName.length();
        int methodNameLength = methodName.length();

        int actualPrefixLength = methodNameLength - fieldNameLength;

        if (actualPrefixLength <= maxPrefixLength && actualPrefixLength >= minPrefixLength) {
            String methodNameWithoutPrefix = methodName.substring(actualPrefixLength, methodNameLength);

            if (methodNameWithoutPrefix.equalsIgnoreCase(fieldName)) {
                return true;
            }
        }

        return false;
    }

    private String[] getFieldsNames(Class<?> deliveredClass) {
        Field[] fields = deliveredClass.getDeclaredFields();

        return Arrays.stream(fields)
                     .map(Field::getName)
                     .toArray(String[]::new);
    }

    private <T> void validate(Map<String, Method> gettersFrom, Map<String, Method> settersTo, T fromObj)
            throws InvalidMethodNamesException {

        String errorMsg = "Inconsistent naming of setters and getters";

        if (gettersFrom.size() != settersTo.size())
            throw new InvalidMethodNamesException(errorMsg);

        Set<String> names = new HashSet<>();
        names.addAll(gettersFrom.keySet());
        names.addAll(settersTo.keySet());

        if (names.size() != gettersFrom.size())
            throw new InvalidMethodNamesException(errorMsg);

        if (getFieldsNames(fromObj.getClass()).length != names.size()) {
            throw new InvalidMethodNamesException(errorMsg);
        }
    }
}
