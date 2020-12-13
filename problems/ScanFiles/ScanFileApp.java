import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScanFileApp {
  public static void main(String[] args) {
    final String folderString = "/Volumes/CODE/SpringBootBasic2";
    final File folder = new File(folderString);

    List<String> result = new ArrayList<>();
    ScanFile scanFile = new ScanFile();
    scanFile.search(".*\\.java", folder, result);   
   /* for (String s : result) {
      System.out.println(s);
    }*/

    var result2 = scanFile.listFile(".java", folderString);
    if (!result2.isEmpty()) {
      for (String s : result2) {
        System.out.println(s);
      }
    }
   
  }
}
