package com.annotation.exoDemo;

public class SimpleExoPlayer implements Player {
    @Override
    public void setRepeatMode(@RepeatMode int repeatMode) {
        //do setRepeatMode(repeatMode);
    }

    @Override
    public @RepeatMode int getRepeatMode() {
        return 0;
    }
}
