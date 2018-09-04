package com.string.replace;

import com.string.StringTest;

import java.io.*;

public class ReplaceDstContent {

    public static void main(String[] args) throws IOException {
        //File ff = new File("JavaTest\\src\\com\\string");
        StringTest st = new StringTest();
        //write(absolutePath,st.readAndModify(absolutePath,".*(applicationId).*","kingz",null));
        String absolutePath = new File("JavaTest\\src\\com\\string\\build.gradle").getAbsolutePath();
        String strings = LineMatcher.instance().modifyGradleContents(new FileInputStream(new File("JavaTest\\src\\com\\string\\build.gradle")), null,
                ".*(applicationId).*", null, null,"\"com.starcor.hunan\"");
        System.out.println("result is:\n" + strings);

        write(absolutePath,strings);
    }


     private static void write(String filePath, String content) {
        BufferedWriter bw = null;
        try {
            // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(new FileWriter(filePath));
            // 将内容写入文件中
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }
    }

    private static String modifyGradleContentByKey(String filePath, String formate, String newContent){

        return "";
    }
}
