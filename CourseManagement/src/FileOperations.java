import java.io.*;

public interface FileOperations {
  public File getFile(String fileName);

  public Person GetIfExists(String email, String fileName);

  public void Add(String row, String fileName);

  public boolean deleteById(int id, String filename);

  public int lastUsedId(String fileName);

  public void update(
    String fileName,
    String targetId,
    int index,
    String newValue
  );
}
