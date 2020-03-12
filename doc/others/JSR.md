# **JSR参数校验**

## **Controller中的参数校验**
- Spring框架中的Contrller层，负责接收用户请求的参数，但用户输入的参数不可直接信任，需要经过复杂、严密的验证，其步骤通常是比较繁琐的。  
- 更重要的是，参数的验证与Controller的业务逻辑并不强相关，而与JavaBean/Pojo/BO更加相关。
- 所以，就考虑把参数校验从业务逻辑中剥离出来，通过注解的方式写在Bean/Pojo的定义里，就形成了“JSR参数校验”。  

## **使用方式**

### **定义Validator注解接口**
```java
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
```
### **创建接口的实现类**
```java
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
            // 真正的校验逻辑，在ValidatorUtil里实现
        }else{
            if(StringUtils.isEmpty(value)){
                return true;
            }else{
                return validatorUtil.isProblem(value);
            }
        }
    }
}

```

### **校验的实现**
```java
@Component
public class ValidatorUtil {

    @Autowired
    ProblemService problemService;

    @Autowired
    LanguageService languageService;

    @Value("${judge.sensitive-key}")
    private String sensitive;


    public boolean isProblem(String value) {

        if(StringUtils.isBlank(value)) return false;

        Problems problem = problemService.queryProblemById(value);

        return (problem!=null);

    }
```
### **在Bean中使用接口，验证对应属性**
```java
package com.iris.java.onlinejudge.web.pojo.bo;
import com.iris.java.onlinejudge.web.Validator.IsLanguage;
import com.iris.java.onlinejudge.web.Validator.IsProblem;
import com.iris.java.onlinejudge.web.Validator.IsSecureCode;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


public class SubmissionBO_small implements Serializable {

    @NotNull
    @IsProblem // 使用Problem Validator
    private String problemId;

    @NotNull
    @IsSecureCode
    private String userCode;

    @NotNull
    @IsLanguage
    private Integer languageId;

    // Getter and Setter

}

```