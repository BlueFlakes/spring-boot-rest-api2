package com.stockexchange.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

public class EnumUtils<E extends Enum<E>> {
    private static final Set<Class> approvedClasses = new HashSet<>();
    private E[] enumConstants;

    public EnumUtils(Class<E> givenClass) {
        optimizedValidation(givenClass);
        this.enumConstants = givenClass.getEnumConstants();
    }

    private void optimizedValidation(Class<E> givenClass) {
        if (!approvedClasses.contains(givenClass)) {

            if (!givenClass.isEnum())
                throw new IllegalArgumentException("Given class is not enum");

            approvedClasses.add(givenClass);
        }
    }

    public E getValue(final String givenName) {
        BiPredicate<E, String> isEqualByName = (enumConst, identity) -> enumConst.toString()
                                                                                 .equals(identity);

        return Arrays.stream(this.enumConstants)
                     .filter(e -> isEqualByName.test(e, givenName))
                     .findFirst()
                     .orElse(null);
    }
}
