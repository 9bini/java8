package com.koobin.optional;

import java.time.Duration;

public class Progress {
    private Duration studyDuration;

    private boolean finished;

    public Duration getStudyDuration() {
        return studyDuration;
    }

    public boolean isFinished() {
        return finished;
    }
}
