package com.think.in.uptotype;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/6 11:48
 * description:
 */
public class Wind extends MusicInstrument{
    @Override
    public void play(MusicNote note) {
        super.play(note);
        System.out.println("Wind.play() ------->" + note);
    }
}
