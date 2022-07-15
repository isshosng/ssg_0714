import java.util.Scanner;

public class App {

    private final Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언 SSG ==");

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "종료":
                    break outer;
                case "등록":
                    write();
            }
        }
    }

    private void write() {
        System.out.print("명언을 입력하세요: ");
        String content = sc.nextLine().trim();
        System.out.print("작가를 입력하세요: ");
        String author = sc.nextLine().trim();
    }
}
