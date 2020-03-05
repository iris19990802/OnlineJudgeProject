
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/user.h>
#include <sys/ptrace.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <sys/wait.h>


#define AC 0
#define PE 1
#define TLE 2
#define MLE 3
#define WA 4
#define RE 5
#define OLE 6
#define CE 7
#define SE 8

struct result {
    int status;
    int timeUsed;
    int memoryUsed;
};

/*
set the process limit(cpu and memory)
*/
void setProcessLimit(int timelimit, int memory_limit) {
    struct rlimit rl;
    /* set the time_limit (second)*/
    rl.rlim_cur = timelimit / 1000;
    rl.rlim_max = rl.rlim_cur + 1;
    setrlimit(RLIMIT_CPU, &rl);

    /* set the memory_limit (b)*/
    rl.rlim_cur = memory_limit * 1024;
    rl.rlim_max = rl.rlim_cur + 1024;
    setrlimit(RLIMIT_DATA, &rl);
}

/*
 run the user process
*/
void runCmd(char *args[],long timeLimit, long memoryLimit, char *in, char *out) {
    int newstdin = open(in,O_RDWR|O_CREAT,0644);
    int newstdout = open(out,O_RDWR|O_CREAT,0644);

    // 设置此进程的时间、空间限制
    setProcessLimit(timeLimit, memoryLimit);
    if (newstdout != -1 && newstdin != -1){

        // 输入/输出重定向（C语言版本）https://stackoverflow.com/questions/14543443/in-c-how-do-you-redirect-stdin-stdout-stderr-to-files-when-making-an-execvp-or
        dup2(newstdout,fileno(stdout));
        dup2(newstdin,fileno(stdin));

        /*
            execvp: 具体执行系统命令
            http://c.biancheng.net/cpp/html/275.html
        */
        if (execvp(args[0],args) == -1) {
            printf("====== Failed to start the process! =====\n");
        }
        close(newstdin);
        close(newstdout);
    } else {
        printf("====== Failed to open file! =====\n");
    }
}

/*
get data of the user process
*/
void getResult(struct rusage *ru, int data[2]) {
    data[0] = ru->ru_utime.tv_sec * 1000
            + ru->ru_utime.tv_usec / 1000
            + ru->ru_stime.tv_sec * 1000
            + ru->ru_stime.tv_usec / 1000;
    data[1] = ru->ru_maxrss;
}

/*
monitor the user process
*/
void monitor(pid_t pid, int timeLimit, int memoryLimit, struct result *rest) {
    int status;
    struct rusage ru;
    /*
        pid_t wait4(pid_t pid,int *status,int options,struct rusage *rusage)   （https://www.cnblogs.com/tongye/p/9558320.html）
        args:
            pid: 要等待的目标进程号
            status：保存子进程退出时的状态信息
            options == 0: waitpid() 会一直等待，直到有进程退出
            ru: 接收子进程资源利用信息
    */
    if (wait4(pid, &status, 0, &ru) == -1)
        printf("wait4 failure");

    rest->timeUsed = ru.ru_utime.tv_sec * 1000
            + ru.ru_utime.tv_usec / 1000
            + ru.ru_stime.tv_sec * 1000
            + ru.ru_stime.tv_usec / 1000;
    rest->memoryUsed = ru.ru_maxrss;
    // printf("memory used: %d\n",rest->memoryUsed);
    // 异常终止
    if (WIFSIGNALED(status)) { // WIFSIGNALED(status)为非0 , 表明进程异常终止。

        switch (WTERMSIG(status)) { // WTERMSIG : 取出异常退出时，信号值的编号

            case SIGSEGV: // 内存异常
                if (rest->memoryUsed > memoryLimit)
                    rest->status = MLE;
                else
                    rest->status = RE;
                break;
            case SIGALRM:
            case SIGXCPU: // 时间异常（cpu时间过多）
                rest->status = TLE;
                break;
            default:
                rest->status = RE;
                break;
        }
    }
    // 正常终止
    else {
        if (rest->timeUsed > timeLimit)
            rest->status = TLE;
        else if (rest->memoryUsed > memoryLimit)
            rest->status = MLE;
        else
            rest->status = AC;
    }
}


int run(char *args[],int timeLimit, int memoryLimit, char *in, char *out){
    pid_t pid = vfork(); // 创建一个子进程，与父进程共享内存数据 （https://www.cnblogs.com/1932238825qq/p/7373443.html）
    if(pid<0)
        printf("error in fork!\n");

    // 子进程：执行命令
    else if(pid == 0) {
        runCmd(args, timeLimit, memoryLimit, in, out);
    }
    // 父进程：监视子进程
    else {
        struct result rest;
        monitor(pid, timeLimit, memoryLimit, &rest);
        printf("{\"status\":%d,\"timeUsed\":%d,\"memoryUsed\":%d}", rest.status, rest.timeUsed, rest.memoryUsed);
    }

    return 0;
}

void split( char **arr, char *str, const char *del){

    char tmp[50];
    int tmp_p = 0;

    int cmd_p = 0;
    int n = strlen(str);
    for(int i=0;i<strlen(str);i++){
        char ch = str[i];
        if(ch == (*del)){
            tmp[tmp_p] = 0;
            strcpy(arr[cmd_p],tmp);
            memset(tmp,0,sizeof(tmp));
            tmp_p = 0;
            cmd_p++;
        }
        else{
            tmp[tmp_p++] = ch;
        }

        if(i == n-1){
            tmp[tmp_p] = 0;
            strcpy(arr[cmd_p],tmp);
            memset(tmp,0,sizeof(tmp));
            tmp_p = 0;
            cmd_p++;
        }
    }
    arr[cmd_p] = NULL;
}

int main(int argc,char *argv[])
{

    char *cmd[20];
    for(int i=0;i<20;i++){
        cmd[i] = (char*)malloc(sizeof(char)*50);
    }
    // // 执行：
    // cmd[0] = "java";
    // cmd[1] ="Main";
    // cmd[2] = NULL;

    // 编译：
    // cmd[0] = "javac";
    // cmd[1] ="/Users/liuyuexuan/1211/1211/Main.java";
    // cmd[2] = NULL;

//    argv[1] = "java@Main";
    split(cmd, argv[1], "@");

//    //argv[1] = "javac@demo.java";
//    argv[2] = "10000";
//    argv[3] = "65536000"; //(多三个0？)
//    argv[4] = "input.txt";
//    argv[5] = "output.txt";
//

    run(cmd, atoi(argv[2]), atoi(argv[3]), argv[4], argv[5]);
    return 0;
}
