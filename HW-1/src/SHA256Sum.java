import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Sum {
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
                System.err.println("Can not read; " + e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Can not such algorithm; " + e.getMessage());
            }

            return;
        }

        for (int i = 0; i < args.length; ++i) {
            try {
                //System.out.println(args[i]);
                byte[] b = Files.readAllBytes(Paths.get(args[i]));
                byte[] hash = MessageDigest.getInstance("SHA-256").digest(b);
                String hash16 = DatatypeConverter.printHexBinary(hash);
                System.out.println(hash16 + " *" + args[i]);
            } catch (IOException e) {
                System.err.println("no file: " + args[i] + "; " + e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Can not such algorithm; " + e.getMessage());
            }
        }
    }
}
