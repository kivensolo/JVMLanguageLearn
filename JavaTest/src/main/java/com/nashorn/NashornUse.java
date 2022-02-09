package com.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 在Java中调用Nashorn引擎
 *
 * https://mouse0w0.github.io/2018/12/02/Introduction-to-Nashorn/
 */
public class NashornUse {
    public static void main(String[] args) throws ScriptException, FileNotFoundException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        //执行js代码
        engine.eval("print('Hello World!');");

        // 还可以从文件中运行JS
        engine.eval(new FileReader("E:\\GitHubProjects\\JVMLanguageLearn\\JavaTest\\src\\main\\java\\com\\nashorn\\hello.js"));
    }
}
