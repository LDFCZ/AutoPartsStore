package ru.nsu.ccfit.lopatkin.store.common.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsPriceValidator.class)
public @interface IsPrice {
    String message() default "{Price.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
