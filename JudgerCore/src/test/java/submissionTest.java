import com.iris.java.onlinejudge.judger.Application;
import com.iris.java.onlinejudge.judger.core.Scheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class submissionTest {

    @Autowired
    Scheduler scheduler;

    @Test
    public void doTest(){

        //scheduler.handleNewSubmission(SubmissionDemo.Cpp_AC_A_Plus_B);

        //scheduler.handleNewSubmission(SubmissionDemo.Java_AC_A_Plus_B);

        //scheduler.handleNewSubmission(SubmissionDemo.Cpp_CE_A_Plus_B);

        //scheduler.handleNewSubmission(SubmissionDemo.Java_CE_A_Plus_B);

        //scheduler.handleNewSubmission(SubmissionDemo.Cpp_WA_A_Plus_B);

        scheduler.handleNewSubmission(SubmissionDemo.Cpp_TLE);

    }
}
