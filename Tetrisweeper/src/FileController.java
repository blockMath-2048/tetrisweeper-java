import sun.nio.cs.US_ASCII;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class FileController {
    public static void SaveFile(long data) {
        try {
            FileOutputStream out = new FileOutputStream("persistent.dat");
            out.write((String.format(Locale.ENGLISH, "%020d", data)).getBytes(StandardCharsets.US_ASCII));
            out.close();
        } catch (IOException ignored) {
            System.out.println("There was an error writing to the file.");
        }
    }

    public static long LoadFile() {
        try {
            byte[] buf = new byte[20];
            FileInputStream in = new FileInputStream("persistent.dat");
            int code = in.read(buf);
            return Long.parseLong(new String(buf, StandardCharsets.US_ASCII));
        } catch (IOException ignored) {
            return 1000;
        }
    }
}
