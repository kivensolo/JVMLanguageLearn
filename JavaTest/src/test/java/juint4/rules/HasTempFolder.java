package juint4.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TemporaryFolder规则允许创建文件和文件夹，这些文件和文件夹在测试方法完成时被删除（无论通过还是失败）。
 * 默认情况下，如果无法删除资源，则不会引发异常.
 */
public class HasTempFolder {
  @Rule
  public final TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void testUsingTempFolder() throws IOException {
    File createdFile = folder.newFile("myfile.txt");
    File createdFolder = folder.newFolder("subfolder");
    // ...
  }

  @Test
  public void testHeap() {
    ArrayList<Object> objects = new ArrayList<>();
    while (true) {
      objects.add(new PeopleGC());
      System.out.println("testHeap");
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  class PeopleGC {
    byte[] bytes = new byte[5 * 1024 * 1024]; //1M
  }
}