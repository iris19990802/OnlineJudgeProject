package com.iris.java.onlinejudge.web.utils;

import com.iris.java.onlinejudge.web.pojo.db.Language;
import com.iris.java.onlinejudge.web.pojo.db.Problems;
import com.iris.java.onlinejudge.web.service.LanguageService;
import com.iris.java.onlinejudge.web.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidatorUtil {

    @Autowired
    ProblemService problemService;

    @Autowired
    LanguageService languageService;

    @Value("${judge.sensitive-key}")
    private String sensitive;


    public boolean isProblem(String value) {


        Problems problem = problemService.queryProblemById(value);

        return (problem!=null);

    }

    public boolean isLanguage(String value) {

        Language language = languageService.queryProblemById(value);

        return (language != null);
    }

    public boolean isSecureCode(String value) {

        for (String key: sensitive.split(",")) {
            if (value.contains(key)) {
                return false;
            }
        }
        return true;
    }



}
