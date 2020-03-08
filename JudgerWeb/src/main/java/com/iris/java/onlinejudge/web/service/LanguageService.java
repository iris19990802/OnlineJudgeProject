package com.iris.java.onlinejudge.web.service;


import com.iris.java.onlinejudge.web.pojo.db.Language;

public interface LanguageService {

    public Language queryProblemById(String languageId);
}
