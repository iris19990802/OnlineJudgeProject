package com.iris.java.onlinejudge.web.Validator;


import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IsSecureCodeValidator.class}
)

public @interface IsSecureCode {

    boolean required() default true;

    String message() default "提交代码包含非法字符";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
