package com.string;


import org.apache.commons.lang3.text.WordUtils;

/**
 * author: King.Z <br>
 * date:  2020/3/9 13:36 <br>
 * description:  <br>
 */
public class StringWrapTest {

    public static void main(String[] args) {
        String content = "暮色里，小镇名叫泥瓶巷的 僻静地方，有位孤苦伶仃的清 瘦少年，此时 他正按照习俗，";
        String test = WordUtils.wrap("test", 72);
        String wrap = WordUtils.wrap(content, 12);
        System.out.println(test + "\n" + wrap);

        System.out.println(WordUtils.wrap("my love my girl", 9));

        int length = "暮色里，小镇名叫泥瓶巷的僻静地方，有位孤苦伶仃的清瘦少年，此时他正按照习俗，一手持蜡烛，一手持桃枝，照耀房梁、墙壁、木床等处，用桃枝敲敲打打，试图借此驱赶蛇蝎、蜈蚣等，嘴里念念有词，是这座小镇祖祖辈辈传下来的老话：二月二，烛照梁，桃打墙，人间蛇虫无处藏.".length();
        System.out.println(length);
    }
}
