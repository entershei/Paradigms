import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalcSHA256 {
    public static void main(String[] args) {
        if (args.length == 0) {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[32 * 1024];

            int bytesRead;

            try {
                while ((bytesRead = System.in.read(buffer)) > 0) {
                    byteStream.write(buffer, 0, bytesRead);
                }

                byte[] bytes = byteStream.toByteArray();

                byte[] hash = MessageDigest.getInstance("SHA-256").digest(bytes);
                String hash16 = DatatypeConverter.printHexBinary(hash);
                System.out.println(hash16 + " *-");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                System.err.println(e.getMessage());
            }

            return;
        }

        try (FileInputStream fstream = new FileInputStream(args[0]);
             BufferedReader br = new BufferedReader(new InputStreamReader(fstream, Charset.forName("utf8")));
        ) {
            String strLine = br.readLine();

            while (strLine != null) {
                try {
                    byte[] b = Files.readAllBytes(Paths.get(strLine));
                    byte[] hash = MessageDigest.getInstance("SHA-256").digest(b);
                    String hash16 = DatatypeConverter.printHexBinary(hash);
                    System.out.println(hash16);
                } catch (IOException e) {
                    System.err.println("no file: " + strLine + "; " + e.getMessage());
                } finally {
                    strLine = br.readLine();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }
}
