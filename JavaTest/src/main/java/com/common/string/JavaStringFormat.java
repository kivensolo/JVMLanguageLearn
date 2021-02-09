package com.common.string;

/**
 * author: King.Z <br>
 * date:  2016/5/11 15:41 <br>
 * description: 常用的字符串格式化 <br>
 *     format()方法有两种重载形式。 <br>
 *      {@link String}format(String format, Object... args)<link>新字符串使用本地语言环境，制定字符串格式和参数生成格式化的新字符串。<br>
 *       format(Locale locale, String format, Object... args) 使用指定的语言环境，制定字符串格式和参数生成格式化的字符串。<br>
 */
public class JavaStringFormat {
    public static String str;
    public static void main(String[] args) {
        normalFormat();
        changeFlagFromat();

    }

    /**
     * 搭配转换符的标志
     */
    private static void changeFlagFromat() {
        str=String.format("格式参数$:(被格式化的参数索引)的使用：%2$d,%1$s","abc",99);
        System.out.println(str);
        //+使用 对于负数无效
        System.out.printf("格式参数+:显示正负数的符号：%+d与%d  和%+d %n", 99,-99,-99);
        //-使用
        System.out.printf("格式参数-:左对齐：%-5d  %n",15);
        //补O使用
        System.out.printf("格式参数0:数字前面补0,最牛的编号是：%03d  %n", 7);
        //(使用
        System.out.printf("格式参数(:包裹负数-99.99: %(f   %n", -99.99);
        //空格使用
        System.out.printf("无Tab键的效果是：%08d  %n", 7);
        System.out.printf("字符串 的效果是：%8s  %n", "7");
        System.out.printf("有Tab键的效果是：%7d   %n", 7);
        //.使用
        System.out.printf("整数分组的效果是：%,d   %n", 9989997);
        //空格和小数点后面个数
        System.out.printf("一本书的价格是：% 50.5f元 %n", 49.8);

        System.out.printf("Merging ext-files from %s \n%7s into %s ", "D:\\test\\ere","","E:\\ff");
    }

    /**
     * 常规类型的格式化
     */
    private static void normalFormat() {
        str=String.format("Hi,%s", "王力");
        System.out.println(str);
        str=String.format("Hi,%s:%s.%s", "王南","王力","王张");
        System.out.println(str);
        System.out.printf("字母a的大写是：%c %n", 'A');
        System.out.printf("3>7的结果是：%b %n", 3>7);
        System.out.printf("100的一半是：%d %n", 100/2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);
        System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);
        System.out.printf("上面价格的指数表示：%e %n", 50*0.85);
        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);
        System.out.printf("上面的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');
        System.out.println("");
    }
}
