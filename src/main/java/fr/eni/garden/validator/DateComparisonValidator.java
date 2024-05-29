package fr.eni.garden.validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class DateComparisonValidator implements ConstraintValidator<DateComparison, Object> {

    private String startDate;
    private String endDate;

    @Override
    public void initialize(DateComparison constraintAnnotation) {
        this.startDate = constraintAnnotation.startDate();
        this.endDate = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Field startDateField = value.getClass().getDeclaredField(startDate);
            Field endDateField = value.getClass().getDeclaredField(endDate);

            startDateField.setAccessible(true);
            endDateField.setAccessible(true);

            Object startDateValue = startDateField.get(value);
            Object endDateValue = endDateField.get(value);

            if (startDateValue == null || endDateValue == null) {
                return true;
            }

            if (startDateValue instanceof Comparable && endDateValue instanceof Comparable) {
                return ((Comparable) startDateValue).compareTo(endDateValue) < 0;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}