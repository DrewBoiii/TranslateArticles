package translatearticles.constraint.validator;

import org.apache.commons.beanutils.BeanUtils;
import translatearticles.constraint.annotation.FieldMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            final Object firstName = BeanUtils.getProperty(o, firstFieldName);
            final Object secondName = BeanUtils.getProperty(o, secondFieldName);

            return firstName == null && secondName == null || firstName != null && firstName.equals(secondName);
        } catch (final Exception ignore) {}

        return true;
    }
}
