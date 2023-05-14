package ru.nsu.ccfit.lopatkin.store.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsDiscountValidator implements ConstraintValidator<IsDiscount, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 0 && value <= 100;
    }
}
