package com.iris.java.onlinejudge.web.controller.normal;


import com.iris.java.onlinejudge.web.Validator.IsProblem;
import com.iris.java.onlinejudge.web.mapper.normal.ProblemsMapper;
import com.iris.java.onlinejudge.web.pojo.db.Problems;
import com.iris.java.onlinejudge.web.service.ProblemService;
import com.iris.java.onlinejudge.web.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value="题目",tags={"查询题目相关信息的接口"})
@RequestMapping("problems")
@RestController
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @ApiOperation(value="查询所有题目",notes="查询所有题目",httpMethod = "GET") // swagger文档对此api的注释
    @GetMapping("/queryall")
    public JSONResult queryAllProblems(){

        // TODO: 分页显示信息

        List<Problems> result = problemService.queryAllProblems();

        return JSONResult.ok(result);
    }


    @ApiOperation(value="查询题目详情",notes="查询题目详情",httpMethod = "GET")
    @GetMapping("/queryinfo/{problemId}")
    public JSONResult queryProblemInfo(
            @ApiParam(name = "problemId",value="题目id",required = true)
            @PathVariable @IsProblem String problemId
    ){

        if(StringUtils.isBlank(problemId)){
            return JSONResult.errorMsg("题目不存在");
        }


        Problems problem = problemService.queryProblemById(problemId);

        return JSONResult.ok(problem);

    }

}
