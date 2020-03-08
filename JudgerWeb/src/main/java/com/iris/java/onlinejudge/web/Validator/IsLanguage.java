package com.iris.java.onlinejudge.web.Validator;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IsLanguageValidator.class}
)
public @interface IsLanguage {

    boolean required() default true;

    String message() default "题目编号有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
