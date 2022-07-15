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
        //given
        Scanner sc = TestUtil.genScanner("종료");
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        //when
        new App(sc).run();

        String s = output.toString();
        TestUtil.clearSetOutToByteArray(output);
        //then
        assertThat(s).isEqualTo("== 명언 SSG ==\n명령) ");
    }

    @Test
    @DisplayName("등록시 명언과 작가 입력을 요구한다.")
    void writeTest() {
        //given
        Scanner sc = TestUtil.genScanner("""
            등록
            배고파
            김도율
            종료
            """);
        ByteArrayOutputStream out = TestUtil.setOutToByteArray();
        //when
        new App(sc).run();
        String s = out.toString();
        TestUtil.clearSetOutToByteArray(out);

        //then
        assertThat(s).contains("명언을 입력하세요: ");
        assertThat(s).contains("작가를 입력하세요: ");
    }

}