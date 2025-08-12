import com.back.App;
import com.back.AppContext;
import com.back.domain.WiseSaying.entity.WiseSaying;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WiseSaying w = new WiseSaying( "너 자신을 알라", "소크라테스");
        System.out.println(w);

        //experiment2();
    }

    //출력을 모니터가 아닌 내 저장소로 연결
    //그럼 꺼내쓸 수 있음 출력
    public static void experiment2() {
        System.out.println("Hello World!");

        PrintStream originalOut = System.out; //백업용

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        System.setOut(printStream);

        System.out.println("하하");

        String outStr = outputStream.toString();
        System.setOut(originalOut); //모니터로 데이터 보내므로 보임
        printStream.close();

        if(outStr.equals("Hello World!")){
            System.out.println("일치");
        } else{
            System.out.println("불일치");
        }

        System.out.println(outStr); //저장소로 데이터 보내므로 안보임


    }

    public static void experiment1() {

        //String input = "등록 \n너 자신을 알라";
        //\n 대신 편리한 방법 """
        String input = """
                등록
                너 자신을 알라
                """;
        Scanner sc = new Scanner(input);

        String cmd = sc.nextLine();
        String saying = sc.nextLine();

        System.out.println("입력한 명령어: " + cmd);
        System.out.println("입력한 명령어: " + saying);

    }
}
