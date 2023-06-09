package ru.nsu.ccfit.lopatkin.store.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsPriceValidator implements ConstraintValidator<IsPrice, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^(\\d{1,8})\\.?(\\d{0,2})$");
        Matcher matcher = pattern.matcher(value.toString());
        return  matcher.matches();
    }
}
