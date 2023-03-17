package fr.ut3.sdl.mobe.saucisse.lsdl.parts;

public class Ball {
    private int coordX;
    private int coordY;

    public Ball(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public void goUp() {
        this.coordY -= 1;
    }

    public void goDown() {
        this.coordY += 1;
    }

    public void goLeft() {
        this.coordX -= 1;
    }

    public void goRight() {
        this.coordX += 1;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
}
