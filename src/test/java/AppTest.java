import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    @DisplayName("스캐너에 키보드 입력이 아닌 문자열 입력으로 설정")
    public void scannerWithString() {
        // given
        Scanner sc = TestUtil.genScanner("안녕");

        // when
        String cmd = sc.nextLine().trim();

        // then
        assertThat(cmd).isEqualTo("안녕");
    }

    @Test
    @DisplayName("출력을 콘솔이 아닌 문자열로 얻기")
    void outputStreamWithString() {
        //given
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        //when
        System.out.print("안녕");
        String s = output.toString();
        TestUtil.clearSetOutToByteArray(output);

        //then
        assertThat(s).isEqualTo("안녕");
    }

    @Test
    @DisplayName("문자열을 파일에 저장")
    void saveStringToFile() {
        //given
        Util.file.mkdir("test_data");
        Util.file.saveToFile("test_data/1.txt", "안녕");
        //when
        String body = Util.file.readFromFile("test_data/1.txt");

        //then
        assertThat(body).isEqualTo("안녕");
    }

    @Test
    @DisplayName("프로그램 실행 및 종료")
    void runTest() {
        String s = AppTestRunner.run("종료");

        assertThat(s).isEqualTo("== 명언 SSG ==\n명령) ");
    }

    @Test
    @DisplayName("등록시 명언과 작가 입력을 요구한다.")
    void writeTest() {
        String s = AppTestRunner.run("""
            등록
            배고파
            김도율
            종료
            """);

        assertThat(s).contains("명언을 입력하세요: ");
        assertThat(s).contains("작가를 입력하세요: ");
    }

    @Test
    @DisplayName("등록시 명언의 번호가 출력")
    void writeTest2() {
        String s = AppTestRunner.run("""
            등록
            배고파
            김도율
            등록
            나도 배고파
            사자
            종료
            """);

        assertThat(s).contains("1번 명언이 등록되었습니다.");
        assertThat(s).contains("2번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록 후 목록에서 확인할 수 있다.")
    void listTest() {
        String s = AppTestRunner.run("""
            등록
            배고파
            김도율
            등록
            나도 배고파
            사자
            목록
            종료
            """);

        assertThat(s).contains("번호 / 작가 / 명언");
        assertThat(s).contains("----------------------");
        assertThat(s).contains("1 / 김도율 / 배고파");
        assertThat(s).contains("2 / 사자 / 나도 배고파");
    }

    @Test
    @DisplayName("명언 삭제")
    public void deleteTest() {
        String rs = AppTestRunner.run("""
            등록
            나의 죽음을 적들에게 알리지 말라
            이순신
            등록
            나에게 불가능이란 없다.
            나폴레옹
            삭제?id=1
            목록
            삭제?id=1
            종료
            """);

        assertThat(rs).contains("1번 명언이 삭제되었습니다.");
        assertThat(rs).contains("2 / 나폴레옹 / 나에게 불가능이란 없다.");
        assertThat(rs).doesNotContain("1 / 이순신 / 나의 죽음을 적들에게 알리지 말라");
        assertThat(rs).contains("1번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("명언 수정")
    public void modify() {
        String rs = AppTestRunner.run("""
            등록
            나의 죽음을 적들에게 알리지 말라
            이순신
            등록
            나에게 불가능이란 없다.
            나폴레옹
            목록
            수정?id=1
            나의 죽음을 적들에게 알리지 마라!
            이순신장군
            목록
            종료
            """);

        assertThat(rs).contains("1 / 이순신 / 나의 죽음을 적들에게 알리지 말라");
        assertThat(rs).contains("1 / 이순신장군 / 나의 죽음을 적들에게 알리지 마라!");
    }

}