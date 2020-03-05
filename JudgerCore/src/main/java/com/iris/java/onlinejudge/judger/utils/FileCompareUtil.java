package com.iris.java.onlinejudge.judger.utils;

import com.iris.java.onlinejudge.judger.pojo.bean.TaskCase;
import com.iris.java.onlinejudge.judger.utils.Enums.JudgeResultTag;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileCompareUtil {


    @Autowired
    MyFileUtils myFileUtils;

    public Integer compare(TaskCase taskCase){
        String userOutput = null;
        String stdOutput = null;
        try {

            userOutput = myFileUtils.readFileContent(taskCase.getOutputFilePath()).trim();
            stdOutput = myFileUtils.readFileContent(taskCase.getStdOutputFilePath()).trim();

        }catch (Exception e){
            e.printStackTrace();
        }

        if(userOutput.equals(stdOutput)){
            return JudgeResultTag.AC.value;
        }
        else{
            // TODO: 注意修正（这里判断 PE 和 WA 的方法，是有问题的）
            String userStripe = StringUtils.deleteWhitespace(userOutput);
            String stdStripe = StringUtils.deleteWhitespace(userOutput);

            if(userStripe.equals(stdStripe)){
                return JudgeResultTag.PE.value;
            }
            else{
                return JudgeResultTag.WA.value;
            }
        }

    }
}
