import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {

    public static String run(String input) {
        Scanner sc = TestUtil.genScanner(input);
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String s = output.toString();
        TestUtil.clearSetOutToByteArray(output);
        return s;
    }
}
