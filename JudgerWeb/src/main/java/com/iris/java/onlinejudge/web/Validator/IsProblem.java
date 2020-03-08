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
        validatedBy = {IsProblemValidator.class}
)
public @interface IsProblem {

    boolean required() default true;

    String message() default "题目编号有误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
