package com.annotation.exoDemo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Player {

    /**
     * Repeat modes for playback.
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({REPEAT_MODE_OFF, REPEAT_MODE_ONE, REPEAT_MODE_ALL})
    public @interface RepeatMode {
    }

    int REPEAT_MODE_OFF = 0;

    int REPEAT_MODE_ONE = 1;

    int REPEAT_MODE_ALL = 2;

    void setRepeatMode(@RepeatMode int repeatMode);

    @RepeatMode int getRepeatMode();
}
