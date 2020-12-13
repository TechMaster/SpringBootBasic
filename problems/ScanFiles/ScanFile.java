import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScanFile {
  public void search(final String pattern, final File folder, List<String> result) {
    var files = folder.listFiles();
    if (files == null) return;
    for (final File f : files) {

      if (f.isDirectory()) {
        search(pattern, f, result);
      }

      if (f.isFile() && f.getName().matches(pattern)) {
        result.add(f.getAbsolutePath());
      }
    }
  }

  public List<String> listFile(final String pattern, final String folder){
    try (Stream<Path> walk = Files.walk(Paths.get(folder))) {
      return walk.map(x -> x.toString()).filter(f -> f.endsWith(pattern)).collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
