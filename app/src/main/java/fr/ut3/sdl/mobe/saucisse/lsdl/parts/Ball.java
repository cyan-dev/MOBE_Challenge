package fr.ut3.sdl.mobe.saucisse.lsdl.parts;

public class Ball {
    private short posX;
    private short posY;

    private DetectMovementStrategy movementDetector;

    public Ball(short posX, short posY) {
        this.posX = posX;
        this.posY = posY;
        this.movementDetector = new DetectAccel();
    }

    public void goUp() {
        this.posY -= 1;
    }

    public void goDown() {
        this.posY += 1;
    }

    public void goLeft() {
        this.posX -= 1;
    }

    public void goRight() {
        this.posX += 1;
    }

    public short getPosX() {
        return posX;
    }

    public void setPosX(short posX) {
        this.posX = posX;
    }

    public short getPosY() {
        return posY;
    }

    public void setPosY(short posY) {
        this.posY = posY;
    }

    public DetectMovementStrategy getMovementDetector() {
        return movementDetector;
    }

    public void setMovementDetector(DetectMovementStrategy movementDetector) {
        this.movementDetector = movementDetector;
    }
}
