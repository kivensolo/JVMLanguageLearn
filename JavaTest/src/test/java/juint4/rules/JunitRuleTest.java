package juint4.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestName;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The samples use of @Rule
 *
 * Base Rules Provided in Junit4.
 *
 * See more: https://github.com/junit-team/junit4/wiki/Rules
 */
public class JunitRuleTest {

    private File parentFolder = new File("E:\\GitHubProjects\\JVMLanguageLearn\\JavaTest\\src\\com\\java\\juint4\\rules");

    /**
     * Use TemporaryFolder to create temporary  file or folder during testing.
     */
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder(parentFolder);

    @Test
    public void testFileCreateAndWrite() throws IOException {
        File file = tempFolder.newFile("simple.txt");

        System.out.println("temp file:" + file.getPath());
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotNull(file);
    }


    /**
     * Get running method name by TestName.
     * The TestName Rule makes the current test name available inside test methods
     */

    @Rule
    public TestName name = new TestName();

    @Test
    public void testMethodName() {
         assertEquals("testMethodName", name.getMethodName());
    }

    /**
     * 使用 ErrorCollector 类，可以在一个测试方法中收集多个测试错误
     * 也就是说，一个测试方法执行中，不会在第一个确认出错后就停止执行。
     * 使用 ErrorCollector 可以在所有点确认完后统一报错。
     */
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    //@Test
    //public void testMoreCollector() {
    //    String s = null;
    //    collector.checkThat("Value should not be null", null, Is(s));
    //
    //    s = "";
    //    collector.checkThat("Value should have the length of 1", s.length(), Is(1));
    //
    //    s = "Junit!";
    //    collector.checkThat("Value should have the length of 10", s.length(), Is(10));
    //
    //}
}

