package fr.ut3.sdl.mobe.saucisse.lsdl.parts;

public class Ball {
    private int posX;
    private int posY;

    public Ball(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
