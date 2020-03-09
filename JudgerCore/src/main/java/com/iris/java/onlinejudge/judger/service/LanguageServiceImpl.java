package com.iris.java.onlinejudge.judger.service;

import com.iris.java.onlinejudge.judger.mapper.normal.LanguageMapper;
import com.iris.java.onlinejudge.judger.pojo.db.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageServiceImpl implements LanguageService{

    @Autowired
    LanguageMapper languageMapper;

    @Override
    public Language queryLanguageById(Integer languageId) {

        Language res = languageMapper.selectByPrimaryKey(languageId);

        return res;
    }
}
