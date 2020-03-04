package com.iris.java.onlinejudge.judger.pojo.bean;


import com.iris.java.onlinejudge.judger.core.Preprocessor;
import com.iris.java.onlinejudge.judger.mapper.normal.LanguageMapper;
import com.iris.java.onlinejudge.judger.mapper.normal.ProblemCaseMapper;
import com.iris.java.onlinejudge.judger.mapper.normal.ProblemsMapper;
import com.iris.java.onlinejudge.judger.pojo.Language;
import com.iris.java.onlinejudge.judger.pojo.ProblemCase;
import com.iris.java.onlinejudge.judger.pojo.Problems;
import com.iris.java.onlinejudge.judger.pojo.bo.SubmissionBO;
import com.iris.java.onlinejudge.judger.utils.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class TaskFactory {


    @Value("${judger.baseWorkDir}")
    private String WORK_DIR_PATH_TEMPLATE;

    @Value("${judger.checkpointDir}")
    private String CHECKPOINT_DIR_PATH_TEMPLATE;


    @Autowired
    LanguageMapper languageMapper;

    @Autowired
    ProblemsMapper problemsMapper;

    @Autowired
    ProblemCaseMapper problemCaseMapper;

    @Autowired
    Preprocessor preprocessor;


    /**
     * 构造函数：利用提交信息，直接生成Task
     * @param submissionBO
     */
    public Task getNewTaskFromSubmissionBO(SubmissionBO submissionBO){

        Language language = languageMapper.selectByPrimaryKey(submissionBO.getLanguageId());

        Problems problems = problemsMapper.selectByPrimaryKey(submissionBO.getProblemId());

        Example example = new Example(ProblemCase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("problemId",submissionBO.getProblemId());
        List<ProblemCase> problemCases = problemCaseMapper.selectByExample(example);

        String submissionId = submissionBO.getSubmissionId();

        String workDir = WORK_DIR_PATH_TEMPLATE.replace("SUBMISSION_ID",submissionId);

        String userCodeFileName = DigestUtils.getRandomString(12, DigestUtils.Mode.ALPHA);

        String checkpointFileDir =  CHECKPOINT_DIR_PATH_TEMPLATE.replace("PROBLEM_ID",problems.getProblemId().toString());

        return new Task(submissionId,workDir,userCodeFileName,checkpointFileDir,language,problems,problemCases);
    }


}
