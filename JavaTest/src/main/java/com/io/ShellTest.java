package com.io;

import java.io.*;

public class ShellTest {
    public static void main(String[] args) {
        String cmd = "cd /tmp && ln -s /data/www/op_platform/local_repo/git@gitee_com_starcor-com_terminal-hebei-launcher_git /tmp/apkbuilder/.git && git show dvb:app/src/main/assets/version/version_info.xml";
        try {
            File file=new File("tmp1.xml");
            OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            final BufferedWriter writer = new BufferedWriter(writerStream);
            excuteLinuxCmd(cmd, "GITUtils", new ILogger() {
                @Override
                public void i(String stage, String info) {
                    try {
                        System.out.println("I: " + info);
                        writer.write(info);
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface ILogger {
        void i(String stage, String info);
    }

    public static boolean excuteLinuxCmd(String cmd,String stage, ILogger logger) {
        System.out.println("CMD: " + cmd);
        boolean success = false;
        ProcessBuilder builder = new ProcessBuilder("/bin/sh", "-c",cmd);
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(process.getInputStream(),"utf-8")
            );
            String line = null;
            while((line = read.readLine())!=null){
                logger.i(stage, line);
            }
            success = process.waitFor() == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (process != null) {
                process.destroy();
                process = null;
            }
        }
        return success;
    }
}
