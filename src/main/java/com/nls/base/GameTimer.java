package com.nls.base;

import com.badlogic.gdx.utils.TimeUtils;

public class GameTimer {
    private long startTime;

    public GameTimer() { reset(); }

    public void reset() { startTime = TimeUtils.millis(); }

    public float getElapsedSeconds() { return (TimeUtils.millis() - startTime) / 1000f; }

    public float getElapsedMinutes() { return getElapsedSeconds() / 60f; }

    public float getElapsedHours() { return getElapsedMinutes() / 60f; }

    public int getElapsedSecondsMod60() { return ( int ) getElapsedSeconds() % 60; }

    public int getElapsedMinutesMod60() { return ( int ) getElapsedMinutes() % 60; }

    public int getElapsedHoursMod12() { return getElapsedHoursMod24() % 12; }

    public int getElapsedHoursMod24() { return ( int ) getElapsedHours() % 24; }

    @Override
    public String toString() {
        return String.format("%02d:%02d", getElapsedMinutesMod60(), getElapsedSecondsMod60());
    }
}
