package com.iris.java.onlinejudge.web.Validator;

import com.iris.java.onlinejudge.web.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class IsProblemValidator  implements ConstraintValidator<IsProblem, String> {

    private boolean required = false;

    @Autowired
    ValidatorUtil validatorUtil;

    @Override
    public void initialize(IsProblem constraintAnnotation) {

        required = constraintAnnotation.required();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return validatorUtil.isProblem(value);
        }else{
            if(StringUtils.isEmpty(value)){
                return true;
            }else{
                return validatorUtil.isProblem(value);
            }
        }
    }
}
