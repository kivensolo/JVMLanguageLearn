package com.annotation.exoDemo;

public class SimpleExoPlayer implements Player {
    @Override
    public void setRepeatMode(@RepeatMode int repeatMode) {
        //do setRepeatMode(repeatMode);
    }


    @Override
    public @RepeatMode int getRepeatMode() {
        //这种用法只有在android support-annotations下的时候，IDE才会报错
        setRepeatMode(6);
        return 0;
    }
}
