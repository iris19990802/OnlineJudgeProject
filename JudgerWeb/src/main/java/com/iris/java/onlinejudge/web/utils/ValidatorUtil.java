package com.iris.java.onlinejudge.web.utils;

import com.iris.java.onlinejudge.web.pojo.db.Language;
import com.iris.java.onlinejudge.web.pojo.db.Problems;
import com.iris.java.onlinejudge.web.service.LanguageService;
import com.iris.java.onlinejudge.web.service.ProblemService;
import org.apache.commons.lang3.StringUtils;
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

        if(StringUtils.isBlank(value)) return false;

        Problems problem = problemService.queryProblemById(value);

        return (problem!=null);

    }

    public boolean isLanguage(Integer value) {

        if(value == null) return false;

        Language language = languageService.queryLanguageById(value);

        return (language != null);
    }

    /**
     * 筛选用户提交代码中的敏感信息，防止系统命令注入
     * @param value
     * @return
     */
    public boolean isSecureCode(String value) {

        if(StringUtils.isBlank(value)) return false;

        for (String key: sensitive.split(",")) {
            if (value.contains(key)) {
                return false;
            }
        }
        return true;
    }



}
