package com.think.in.uptotype;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/6 11:50
 * description:
 */
public class MusicTest {
    public static void main(String[] args) {
        Wind wind = new Wind();
        tune(wind);
    }

    public static void tune(MusicInstrument musicInstrument) {
        musicInstrument.play(MusicNote.NOTE_TOP);
    }
}
