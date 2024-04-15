package wildfire.io;

import com.io.utils.FileIOUtils;
import org.junit.Test;

public class IOTest {
    @Test
    public void testRdomAccessFile() {
        byte[] contentBytes = FileIOUtils.readFileRadom("C:\\Users\\ZekeWang\\OneDrive\\文档\\记录数据.txt", 13,55);
        System.out.println(new String(contentBytes));

    }
}
