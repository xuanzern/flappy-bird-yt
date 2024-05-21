import java.awt.*;
import java.util.Random;

public class Pipe {
    private int xCoordinate;
    private int yCoordinate;
    final private int pipeWidth = 64;
    final private int pipeHeight = 512;
    private int velocityX = -4;
    private Image img;

    private Random random;

    private boolean passed = false;

    private final int openingSpace = 640/4;


    public Pipe(Image img, int x, int y, boolean isBottom){
        this.img = img;
        this.xCoordinate = x;
        if (isBottom){
            this.yCoordinate = y + this.pipeHeight + this.openingSpace;
        }
        else {
            this.yCoordinate = (int) (y-pipeHeight/4 - Math.random()*(pipeHeight/2));
        }
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getPipeWidth() {
        return this.pipeWidth;
    }

    public int getPipeHeight(){
        return this.pipeHeight;
    }

    public int getVelocityX() {
        return this.velocityX;
    }

    public Image getImg(){
        return this.img;
    }

    public void updateVelocity(){
        this.xCoordinate += this.velocityX;
    }

    public void togglePassed(){
        this.passed = !this.passed;
    }

    public boolean isPassed(){
        return this.passed;
    }
}
