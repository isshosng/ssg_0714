import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private final Scanner sc;
    static int id = 1;
    String cmd, content, author;

    static WiseSaying wiseSaying;
    static List<WiseSaying> wiseSayings = new ArrayList<>();

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언 SSG ==");
        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "종료":
                    break outer;
                case "등록":
                    write();
                    break;
                case "목록":
                    list();
                    break;
                case "삭제":
                    remove(rq);
                    break;
                case "수정":
                    modify(rq);
                    break;
            }
        }
    }

    private void modify(Rq rq) {
        int id = rq.getIntParam("id", 0);

        if (id == 0) {
            System.out.println("번호를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.content);
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.printf("작가(기존) : %s\n", wiseSaying.author);
        System.out.print("작가 : ");
        String author = sc.nextLine();

        wiseSaying.content = content;
        wiseSaying.author = author;
    }

    private void remove(Rq rq) {
        int id = rq.getIntParam("id", 0);

        if (id == 0) {
            System.out.println("번호를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        wiseSayings.remove(wiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    private WiseSaying findById(int id) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == id) {
                return wiseSaying;
            }
        }

        return null;
    }

    private void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayings.get(i);

            System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author,
                wiseSaying.content);
        }
    }

    private void write() {
        System.out.print("명언을 입력하세요: ");
        String content = sc.nextLine().trim();
        System.out.print("작가를 입력하세요: ");
        String author = sc.nextLine().trim();
        wiseSayings.add(new WiseSaying(id, content, author));
        System.out.println(id++ + "번 명언이 등록되었습니다.");
    }
}