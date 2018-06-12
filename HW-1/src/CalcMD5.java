import javafx.scene.media.SubtitleTrack;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalcMD5 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Enter the name of the file");
            return;
        }

        try (FileInputStream fstream = new FileInputStream(args[0]);
             BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        ) {
            String strLine = br.readLine();

            while (strLine != null){
                try {
                    byte[] b = Files.readAllBytes(Paths.get(strLine));
                    byte[] hash = MessageDigest.getInstance("MD5").digest(b);
                    String hash16 = DatatypeConverter.printHexBinary(hash);
                    System.out.println(hash16);
                } catch (IOException e) {
                    System.err.println("no file: " + strLine + "; " + e.getMessage());
                } finally {
                    strLine = br.readLine();
                }
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }
}
