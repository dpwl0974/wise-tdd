import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        experiment1();
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
