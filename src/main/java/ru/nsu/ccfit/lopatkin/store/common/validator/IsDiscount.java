package ru.nsu.ccfit.lopatkin.store.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsDiscountValidator.class)
public @interface IsDiscount {

    String message() default "{Discount.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
