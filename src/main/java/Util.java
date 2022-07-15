import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class Util {

    public static class file {

        public static void saveToFile(String path, String body) {
            try (RandomAccessFile stream = new RandomAccessFile(path, "rw");
                FileChannel channel = stream.getChannel()) {
                byte[] strBytes = body.getBytes();
                ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
                buffer.put(strBytes);
                buffer.flip();
                channel.write(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void mkdir(String path) {
            new File(path).mkdirs();
        }

        public static String readFromFile(String path) {
            try (RandomAccessFile reader = new RandomAccessFile(path, "r")) {
                StringBuilder sb = new StringBuilder();

                String line;

                boolean isFirst = true;

                while ((line = reader.readLine()) != null) {
                    if (!isFirst) {
                        sb.append("\n");
                    }

                    sb.append(new String(line.getBytes(StandardCharsets.ISO_8859_1),
                        StandardCharsets.UTF_8));

                    isFirst = false;
                }

                return sb.toString();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}