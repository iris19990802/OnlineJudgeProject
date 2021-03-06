package com.iris.java.onlinejudge.judger.Execute;


import com.alibaba.fastjson.JSONObject;
import com.iris.java.onlinejudge.judger.pojo.bean.ExecuteResult;
import com.iris.java.onlinejudge.judger.utils.Enums.JudgeResultTag;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
/**
 * 直接运行系统命令的类
 *（直接管理进程）
 *
 * 得到两重String结果（标准输出stdout + 标准错误输出error）
 */

@Component
public class CommandExecutor implements Executor{


    /**
     * 对用户程序进行"测评"
     *（对程序有时间、空间的限制）
     * 处理出c脚本运行的真正命令，调用exec函数
     * 得到程序运行的结果（封装到ExecuteResult）
     * @param cmd
     * @param timeLimit
     * @param memoryLimit
     * @param inputFile
     * @param outputFile
     * @return
     */
    @Override
    public ExecuteResult judgeExecute(String cmd, int timeLimit, int memoryLimit, String inputFile, String outputFile) throws Exception{

        String command = String.format("%s %s %s %s %s %s",new Object[] { CORE_SCRIPT_PATH,cmd, timeLimit,memoryLimit,inputFile,outputFile});

        CommandExecuteMessage execResult = exec(command,EXEC_WAIT_TIME);

        ExecuteResult res = parseMessage(execResult);

        return res;

    }

    /**
     * 运行普通命令
     *（没有时间、空间限制）
     * @param cmd
     * @return
     */
    @Override
    public ExecuteResult normalExecute(String cmd) throws IOException {

        String command = String.format("%s %s %d %d %s %s",new Object[] { CORE_SCRIPT_PATH,cmd,DEFAULT_TIME_LIMIT,DEFAULT_MEMORY_LIMIT,DEFAULT_INPUT_FILE,DEFAULT_OUTPUT_FILE});

        CommandExecuteMessage execResult = exec(command,EXEC_WAIT_TIME);

        ExecuteResult res = parseMessage(execResult);

        return res;
    }

    /**
     * 执行某条具体的系统命令
     * @param cmd（命令本身）
     * @param milliseconds（超时时间）
     * @return
     * @throws IOException
     */
    private CommandExecuteMessage exec(String cmd, long milliseconds) throws IOException {

        Runtime runtime = Runtime.getRuntime();

        final Process exec;
        try {

            exec = runtime.exec(cmd);

            // waitFor(): 让当前主进程等待这个process指向的子进程执行完成。
            if (!exec.waitFor(milliseconds, TimeUnit.MILLISECONDS)) {

                if (exec.isAlive()) {
                    exec.destroy(); // 终止子进程
                }
                throw new InterruptedException(); // 如果到了规定时间还没终止，则抛出异常
            }

            // 如果异常结束
        } catch (IOException e) { // 读取具体的错误原因
            return new CommandExecuteMessage(e.getMessage(), null);

        } catch (InterruptedException e) { // 超时
            return new CommandExecuteMessage("timeOut", null);
        }

        // (Java 的编译错误会返回到"标准错误输出"，但是c++的编译错误不会。。)
        String errorStream = IOUtils.toString(exec.getErrorStream()); // inputStream 读为 String 的方法 ： https://blog.csdn.net/lmy86263/article/details/60479350
        String outStream = IOUtils.toString(exec.getInputStream(), "utf-8");

        if(!StringUtils.isBlank(errorStream)){
            System.out.println("execute error : " + errorStream);
        }
        if(exec.exitValue() != 0){
            System.out.println("execute error : execCode = %d" + exec.exitValue());
        }
        CommandExecuteMessage res = new CommandExecuteMessage(errorStream,outStream);

        return res;
    }


    private ExecuteResult parseMessage(CommandExecuteMessage execResult){

        // 如果正常运行（标准"错误"输出为空）
        if(StringUtils.isBlank(execResult.getError())){

            // 解析"标准输出"的内容
            JSONObject json = JSON.parseObject(execResult.getStdout());

            Integer status = json.getInteger("status");
            Long timeUsed = json.getLong("timeUsed");
            Long memoryUsed = json.getLong("memoryUsed");

            /**
             * json内不是期望的内容，会抛出 null pointer exception，也不要紧。
             *
             * 直接上抛给消息接受的入口函数，拦截返回system error 即可
              */

            return new ExecuteResult(status, timeUsed, memoryUsed, "");
        }
        // 如果 error里有东西
        else{
            //(比如：运行脚本 .judge_core 不存在)
            String errorMsg = execResult.getError();
            return new ExecuteResult(JudgeResultTag.SE.value, new Long(0), new Long(0), errorMsg);
        }

    }

    // 内部类（因为它只会与 CommandExecutor 关联使用）
    public class CommandExecuteMessage {

        private String error;

        private String stdout;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getStdout() {
            return stdout;
        }

        public void setStdout(String stdout) {
            this.stdout = stdout;
        }

        public CommandExecuteMessage(String error,String stdout){
            this.error = error;
            this.stdout = stdout;
        }
    }

    @Value("${commandexecutor.exexWaitTime}")
    Integer EXEC_WAIT_TIME;

    @Value("${commandexecutor.defaultTimeLimit}")
    Integer DEFAULT_TIME_LIMIT;

    @Value("${commandexecutor.defaultMemoryLimit}")
    Integer DEFAULT_MEMORY_LIMIT;

    @Value("${commandexecutor.defaultInputFile}")
    String DEFAULT_INPUT_FILE;

    @Value("${commandexecutor.defaultOutputFile}")
    String DEFAULT_OUTPUT_FILE;

    @Value("${judger.coreScriptLocation}")
    String CORE_SCRIPT_PATH;

}


/**
 * Runtime : 每个java应用有单个类Runtime实例【让我想起了单例】，这允许应用去和应用正在运行的环境交互
 *
 * 应用程序本身不能创建这个类（Runtime）的实例,只能通过 getRuntime()方法获取
 *
 * Runtime.exec(),可运行系统命令
 */

/**
 *
 * https://blog.csdn.net/dataiyangu/article/details/83988654
 *
 * Process 对象：用于管理操作系统进程
 *
 *  在编写Java程序时，有时候我们需要调用其他的诸如exe,shell这样的程序或脚本。在Java中提供了两种方法来启动其他程序：

 (1) 使用Runtime的exec()方法

 (2) 使用ProcessBuilder的start()方法

 Runtime和ProcessBulider提供了不同的方式来启动程序，设置启动参数、环境变量和工作目录。但是这两种方法都会返回一个用于管理操作系统进程的Process对象。
 */
