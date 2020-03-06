import com.iris.java.onlinejudge.judger.pojo.bo.SubmissionBO;
import com.iris.java.onlinejudge.judger.pojo.db.Submission;

import java.util.Date;

public class SubmissionDemo {

    /**
     * Java Test
     */
    public static SubmissionBO Java_AC_A_Plus_B = makeSubmission("1","1000",3,
            "import java.util.*;public class Main{public static void main(String[] args){Scanner in = new Scanner(System.in);int a=in.nextInt();int b=in.nextInt();System.out.println(a+b);}}");

    public static SubmissionBO Java_CE_A_Plus_B = makeSubmission("2","1000",3,
            "import java.util.*;public class Main{public static void main(String[] args){Scanner in = new Scanner(System.in);int a=in.nextInt();int b=in.nextInt();Systm.out.println(a+b);}}");

    public static SubmissionBO Java_WA_A_Plus_B = makeSubmission("3","1000",3,
            "import java.util.*;public class Main{public static void main(String[] args){Scanner in = new Scanner(System.in);int a=in.nextInt();int b=in.nextInt();System.out.println(a+a);}}");

    public static SubmissionBO Java_TLE = makeSubmission("4","1000",3,
            "import java.util.*;public class Main { public static void main(String[] args) { Scanner in = new Scanner(System.in);int a = in.nextInt();int b = in.nextInt();for(int i=0;;i++){ a = a; }return ; }}");


    // TODO: MLE


    /**
     * C++ Test
     */

    public static SubmissionBO Cpp_AC_A_Plus_B = makeSubmission("10","1000",2,
            "#include <iostream>#include <cstdio> \nint main(){\nint a;int b;scanf(\"%d\",&a);scanf(\"%d\",&b);\nprintf(\"%d\\n\",a+b);\nreturn 0;}");

    public static SubmissionBO Cpp_CE_A_Plus_B = makeSubmission("11","1000",2,
            "#include <iostream>\n#include <cstdio>\nint main(){\n int a;int b;scanf(\"%d\",&a);scanf(\"%d\",&b);\nprintf(\"%d\\n\",a+b);return 0;");

    public static SubmissionBO Cpp_WA_A_Plus_B = makeSubmission("12","1000",2,
            "#include <iostream>\n#include <cstdio>\nint main(){\n int a;int b; scanf(\"%d\",&a); scanf(\"%d\",&b);\nprintf(\"%d\\n\",a+a);}");

    public static SubmissionBO Cpp_TLE = makeSubmission("13","1000",2,
            "#include <iostream>\n#include <cstdio>\n int main(){\n int a;int b; scanf(\"%d\",&a);scanf(\"%d\",&b);\nfor(int i=0;;i++){a = a;}return 0;}");



    private static SubmissionBO makeSubmission(String submissionId,String probId,Integer languageId,String userCode){
        SubmissionBO submissionBO = new SubmissionBO();
        submissionBO.setLanguageId(languageId);
        submissionBO.setProblemId(probId);
        submissionBO.setSubmissionId(submissionId);
        submissionBO.setUserCode(userCode);
        submissionBO.setSubmitDate(new Date());

        return submissionBO;

    }

}
