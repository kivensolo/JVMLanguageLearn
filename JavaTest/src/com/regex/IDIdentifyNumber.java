package com.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/11/16 0:03
 * description:
 *
 *   	正则表达式的() [] {}有不同的意思。
		() 是为了提取匹配的字符串。表达式中有几个()就有几个相应的匹配字符串。 (\s*)表示连续空格的字符串。
		[]是定义匹配的字符范围。比如 [a-zA-Z0-9] 表示相应位置的字符要匹配英文字符和数字。[\s*]表示空格或者*号。
		{}一般用来表示匹配的长度，比如 \s{3} 表示匹配三个空格，\s[1,3]表示匹配一到三个空格

 (0-9) 匹配 '0-9'本身。 [0-9]* 匹配数字（注意后面有 *，可以为空）[0-9]+ 匹配数字（注意后面有 +，不可以为空）{1-9} 写法错误。


 验证1-3位小数的正实数: ^[0-9]+(.[0-9]{1,3})?$    eg:0.234
 验证非零的正整数：^\+?[1-9][0-9]*$



/^[1-9]\d{5}  [1-9]\d{3}  ((0\d)|(1[0-2]))  (([0|1|2]\d)|3[0-1])  \d{3}  ([0-9]|X) $/;\

 [1-9]\d{5}  [1-9]\d{3}  不明白为什么不写成[1-9]\d{8}

 12个月:(0?[1-9]|1[0-2]) 正确格式为：“01”-“09”和“1”“12”
 		解析：0?表示匹配零个或1个"0" 1[0-2]表示匹配数字10、11、12。
 31天:(([0|1|2]\d)|3[0-1])  00~29|30~31

 510682 1993 03(01~12) 23(01~31) 2019(X)

 */
public class IDIdentifyNumber {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		showRightTips("请输入身份证号码:");
		String ID = reader.next();
//		showRightTips(ID);
//		String regex = "^\[1-9]\d[0-9]\d{16}$";//不是数字的匹配规则
//		Pattern pattern = Pattern.compile(regex);
//		Matcher isRight = pattern.matcher(ID);
//		if(isRight.matches()){

	}
	public static void showError(){
		System.out.println("无效！");
	}
	private static void showRightTips(String tips) {
		System.out.println(tips);
	}
}
