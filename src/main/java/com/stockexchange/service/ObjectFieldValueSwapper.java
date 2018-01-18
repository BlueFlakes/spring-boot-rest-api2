package com.stockexchange.service;

import com.stockexchange.exception.InvalidMethodNamesException;
import com.stockexchange.exception.UnavailableElementException;
import com.stockexchange.model.Customer;
import com.stockexchange.model.PossessId;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ObjectFieldValueSwapper {

    private static final int maxPrefixLength = 3;
    private static final int minPrefixLength = 2;

    public static void main(String[] args) throws Exception {
        Customer customer1 = new Customer();
        customer1.setFirstName("Kamil");

        Customer customer2 = new Customer();

        ObjectFieldValueSwapper objectFieldValueSwapper = new ObjectFieldValueSwapper();

        objectFieldValueSwapper.antiNullSwap(customer1, customer2);
        System.out.println(customer2.getFirstName());
    }

    public <T> void antiNullSwap(T fromObject, T toObject) throws InvalidMethodNamesException {
        List<String> validPrefixesForGetters = Arrays.asList("get", "is");
        List<String> validPrefixesForSetters = Arrays.asList("set");

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

    private Map<String, Method> collectMethodsJustForSettersAndGetters(Class<?> deliveredClass, List<String> validPrefixes) {
        List<String> fieldsNames = Arrays.asList(getFieldsNames(deliveredClass));
        List<Method> methods = Arrays.asList(deliveredClass.getMethods());

        Map<String, Method> validSetters = new HashMap<>();
        Function<String, String> lower = String::toLowerCase;

        for (String fieldName : fieldsNames) {
            String lowerCasedFieldName = lower.apply(fieldName);

            Method method = methods.stream()
                                   .filter(mth -> lower.apply(mth.getName()).endsWith(lowerCasedFieldName))
                                   .filter(mth -> validPrefixes.stream().anyMatch(prefix -> mth.getName().startsWith(prefix)))
                                   .findAny()
                                   .orElse(null);

            if (method != null && isMethodValid(fieldName, method, validPrefixes)) {
                validSetters.put(fieldName.toLowerCase(), method);
            }
        }

        return validSetters;
    }

    private boolean isMethodValid(String fieldName, Method method, List<String> validPrefixes) {
        String methodName = method.getName();

        int fieldNameLength = fieldName.length();
        int methodNameLength = methodName.length();

        int actualPrefixLength = methodNameLength - fieldNameLength;

        if (actualPrefixLength <= maxPrefixLength && actualPrefixLength >= minPrefixLength) {
            String prefix = methodName.substring(0, actualPrefixLength);
            String lowerCasedPrefix = prefix.toLowerCase();
            String methodNameWithoutPrefix = methodName.substring(actualPrefixLength, methodNameLength);

            if (validPrefixes.contains(lowerCasedPrefix) && methodNameWithoutPrefix.equalsIgnoreCase(fieldName)) {
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
}
