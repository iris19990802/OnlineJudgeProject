package com.iris.java.onlinejudge.judger.pojo;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Language {
    /**
     * language_id
     */
    @Id
    @Column(name = "language_id")
    private Integer languageId;

    /**
     * language_name
     */
    @Column(name = "langurage_name")
    private String langurageName;

    /**
     * language_compile_command
     */
    @Column(name = "language_compile_command")
    private String languageCompileCommand;

    /**
     * language_run_command
     */
    @Column(name = "language_run_command")
    private String languageRunCommand;

    /**
     * 获取代码文件的后缀名.
     * @return 代码文件的后缀名
     */
    public String getCodeFileSuffix() {
        String compileCommand = this.languageCompileCommand;

        Pattern pattern = Pattern.compile("\\{filename\\}\\.((?!exe| ).)+");
        Matcher matcher = pattern.matcher(compileCommand);

        if ( matcher.find() ) {
            String sourceFileName = matcher.group();
            return sourceFileName.replaceAll("\\{filename\\}\\.", "");
        }
        return "";
    }

    /**
     * 获取language_id
     *
     * @return language_id - language_id
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * 设置language_id
     *
     * @param languageId language_id
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * 获取language_name
     *
     * @return langurage_name - language_name
     */
    public String getLangurageName() {
        return langurageName;
    }

    /**
     * 设置language_name
     *
     * @param langurageName language_name
     */
    public void setLangurageName(String langurageName) {
        this.langurageName = langurageName;
    }

    /**
     * 获取language_compile_command
     *
     * @return language_compile_command - language_compile_command
     */
    public String getLanguageCompileCommand() {
        return languageCompileCommand;
    }

    /**
     * 设置language_compile_command
     *
     * @param languageCompileCommand language_compile_command
     */
    public void setLanguageCompileCommand(String languageCompileCommand) {
        this.languageCompileCommand = languageCompileCommand;
    }

    /**
     * 获取language_run_command
     *
     * @return language_run_command - language_run_command
     */
    public String getLanguageRunCommand() {
        return languageRunCommand;
    }

    /**
     * 设置language_run_command
     *
     * @param languageRunCommand language_run_command
     */
    public void setLanguageRunCommand(String languageRunCommand) {
        this.languageRunCommand = languageRunCommand;
    }
}