package com.string.replace;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineMatcher {
	private static final String TAG = LineMatcher.class.getName();
    private static LineMatcher sTools;

    private static final int TYPE_GET_CONTENTS= 1;
    private static final int TYPE_GET_LINE= 2;
    private static final int TYPE_MODIFY_CONTNETS= 3;
    private static final int TYPE_MODIFY_GRADLE_CONTNETS= 4;

	private LineMatcher(){
	}

	public static LineMatcher instance(){
		if(null == sTools){
			sTools = new LineMatcher();
		}
		return sTools;
	}

	private class MatchInfo{
		int type;
		InputStream outStream;
		InputStream errorStream;
		Pattern beginPattern;
        Pattern endPattern;
        boolean isInContents;
        Pattern contentPattern;
        String oldValue;
        String newValue;
        StringBuilder fileContents;
        ArrayList<String> contents;
	}

	public ArrayList<String> getContents(InputStream outStream,
			InputStream errorStream,
			String beginFormate, String endFormate,
    		String contentFormate){
		MatchInfo info = new MatchInfo();
		info.contents = new ArrayList<String>();
		info.outStream = outStream;
		info.errorStream = errorStream;
		info.type = TYPE_GET_CONTENTS;
		info.beginPattern = Pattern.compile(beginFormate);
		info.endPattern = Pattern.compile(endFormate);
		info.contentPattern = Pattern.compile(contentFormate);
		info.isInContents = false;
		streamNormalParser(info);
		return info.contents;
	}

	public String getSingleLine(InputStream outStream,
			InputStream errorStream,
    		String contentFormate){
		MatchInfo info = new MatchInfo();
		info.contents = new ArrayList<String>();
		info.outStream = outStream;
		info.errorStream = errorStream;
		info.type = TYPE_GET_LINE;
		info.contentPattern = Pattern.compile(contentFormate);
		streamNormalParser(info);
		if(info.contents.size() > 0){
			return info.contents.get(0);
		}
		return null;
	}

	public String modifyContents(InputStream outStream,
			InputStream errorStream,
    		String contentFormate, String oldValue, String newValue){
		MatchInfo info = new MatchInfo();
		info.contents = new ArrayList<String>();
		info.outStream = outStream;
		info.errorStream = errorStream;
		info.type = TYPE_MODIFY_CONTNETS;
		info.contentPattern = Pattern.compile(contentFormate);
		info.oldValue = oldValue;
		info.newValue = newValue;
		info.fileContents = new StringBuilder();
		streamNormalParser(info);
		return info.fileContents.toString();
	}

	public String modifyGradleContents(InputStream outStream,InputStream errorStream,
    		String beginFormate, String endFormate, String contentFormate,String newValue){
		MatchInfo info = new MatchInfo();
		info.contents = new ArrayList<String>();
		info.outStream = outStream;
		info.errorStream = errorStream;
		info.type = TYPE_MODIFY_GRADLE_CONTNETS;
		info.beginPattern = Pattern.compile(beginFormate);
		//TODO TextUtils
		if(endFormate != null){
			info.endPattern = Pattern.compile(endFormate);
		}
		if(contentFormate != null){
			info.contentPattern = Pattern.compile(contentFormate);
		}
		info.newValue = newValue;
		info.fileContents = new StringBuilder();
		streamNormalParser(info);
		return info.fileContents.toString();
	}

	private void streamNormalParser(MatchInfo info){
		if(info.outStream == null){
			return;
		}
		if(info.errorStream == null){
			streamNormalParserNoErr(info);
			return;
		}
		LineReader stdout = new LineReader(info.outStream);
		LineReader stderr = new LineReader(info.errorStream);
		while (true) {
			boolean finished = waitFor(30);
			boolean outIsReady = stdout.ready() || finished;
			boolean errIsReady = stderr.ready() || finished;
			if (finished) {
				stdout.flush();
				stderr.flush();
			}
			while (outIsReady || errIsReady) {
				String outLine;
				while (outIsReady && (outLine = stdout.readLine()) != null) {
					select(outLine, info);
				}
				while (errIsReady && (outLine = stderr.readLine()) != null) {
					Logger.e(TAG, outLine);
				}
				outIsReady = stdout.ready();
				errIsReady = stderr.ready();
			}
			if (finished) {
				break;
			}
		}
	}

	private void streamNormalParserNoErr(MatchInfo info){
		LineReader stdout = new LineReader(info.outStream);
		while (true) {
			boolean finished = waitFor(30);
			boolean outIsReady = stdout.ready() || finished;
			if (finished) {
				stdout.flush();
			}
			while (outIsReady) {
				String outLine;
				while (outIsReady && (outLine = stdout.readLine()) != null) {
					select(outLine, info);
				}
				outIsReady = stdout.ready();
			}
			if (finished) {
				break;
			}
		}
	}

	private void modifyContents(String line, MatchInfo info){
		Matcher matcher = info.contentPattern.matcher(line);
		if (matcher.matches()) {
			StringBuffer newLine = new StringBuffer();
			String start = line.substring(0, matcher.start(1));
			String end = line.substring(matcher.end(1), line.length());
			String preContent = line.substring(matcher.start(1), matcher.end(1));
			if( (info.oldValue == null || info.oldValue.length() <= 0 ) || info.oldValue.equals(preContent)){
			    newLine.append(start).append(info.newValue).append(end);
			    line = newLine.toString();
			    Logger.d(TAG, "matches! new line is: "+ line);
			}
		}
		info.fileContents.append(line);
        // 如果不用修改, 则按原来的内容回写
        info.fileContents.append(System.getProperty("line.separator"));
	}

	private void select(String outLine, MatchInfo info){
		if(info.type == TYPE_GET_CONTENTS){
			getContents(outLine, info);
		}else if(info.type == TYPE_GET_LINE){
			getSingleLine(outLine, info);
		}else if(info.type == TYPE_MODIFY_CONTNETS){
			modifyContents(outLine, info);
		}else if(info.type == TYPE_MODIFY_GRADLE_CONTNETS){
			modifyGradleContents(outLine, info);
		}
	}

	private void modifyGradleContents(String outLine, MatchInfo info){
		Matcher beginMatcher = info.beginPattern.matcher(outLine);
		if (beginMatcher.matches()) {
			StringBuilder newLine = new StringBuilder();
			String key = outLine.substring(0, beginMatcher.end(1));
			newLine.append(key).append(" ").append(info.newValue);
			outLine = newLine.toString();
			Logger.d(TAG, "Gradle content matches! new line is: " + outLine);
		}
		info.fileContents.append(outLine).append(System.getProperty("line.separator"));
	}

	private void getSingleLine(String outLine, MatchInfo info){
		Matcher matcher = info.contentPattern.matcher(outLine);
		if (matcher.matches()) {
			Logger.e(TAG, "the match is: " + matcher.group(1));
			info.contents.add(matcher.group(1));
		}
	}

	private void getContents(String outLine, MatchInfo info){
		if(info.isInContents){
    		Matcher contentMatcher = info.contentPattern.matcher(outLine);
    		if (contentMatcher.matches()) {
    			info.contents.add(contentMatcher.group(1));
			}else {
				// 此处根据实际需要修改某些行的内容
            	Matcher endMatcher = info.endPattern.matcher(outLine);
				if (endMatcher.matches()) {
					info.isInContents = false;
					return;
				}
			}
    		return;
    	}
        // 此处根据实际需要修改某些行的内容
    	Matcher beginMatcher = info.beginPattern.matcher(outLine);
		if (beginMatcher.matches()) {
			info.isInContents = true;
		}
		return;
	}

	private boolean waitFor(int ms) {
		try {
			Thread.sleep(ms);
			return true;
		} catch (IllegalThreadStateException e) {
		} catch (InterruptedException e) {
		}
		return false;
	}
}
