package com.think.in.uptotype;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/6 11:43
 * description: 乐器类
 */
public class MusicInstrument {

    public void play(MusicNote note){
        System.out.println("MusicInstrument.play()------>" + note);
    }
}
enum MusicNote{
    NOTE_TOP,NOTE_LOW,NOTE_MID,
}
