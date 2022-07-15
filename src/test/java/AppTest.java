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

}