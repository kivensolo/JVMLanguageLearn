package com.string.replace;

import java.util.Locale;

public class Logger {
	public static int PUT_TO_OUT = 1;
	public static int PUT_TO_ERR = 2;

	public static int state;
	public static void d(String stage, String info) {
		writeLog("D", stage, info);

	}

	public static void i(String stage, String info) {
		writeLog("I", stage, info);
	}

	public static void i(String info) {
		writeLog("I", "notice", info);
	}

	public static void w(String stage, String info) {
		writeLog("W", stage, info);
	}

	public static void e(String stage, String info) {
		writeLog("E", stage, info);
	}

	public static void e(String info) {
		writeLog("E", "error", info);
	}

	private static void writeLog(String level, String stage, String info) {
		if(state == PUT_TO_ERR){
			System.err.printf(Locale.SIMPLIFIED_CHINESE, "[%s][%s]: %s\n", level, stage, info);
		}else {
			System.out.printf(Locale.SIMPLIFIED_CHINESE, "[%s][%s]: %s\n", level, stage, info);
		}
	}
}
