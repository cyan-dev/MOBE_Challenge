package fr.ut3.sdl.mobe.saucisse.lsdl.parts;

import android.hardware.SensorEvent;

public interface DetectMovementStrategy {
    enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    Direction detectMovement(SensorEvent event);
}

