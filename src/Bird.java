import java.awt.*;

public class Bird {
    final int birdWidth = 34;
    final int birdHeight = 24;
    int x;
    int y;
    Image img;

    private int velocityY = 0;


    public Bird(Image img, int x, int y){
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public int getBirdWidth() {
        return birdWidth;
    }

    public int getBirdHeight() {
        return birdHeight;
    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate() {
        return y;
    }

    public void setXCoordinate(int x) {
        this.x = x;
    }

    public void setYCoordinate(int y) {
        this.y = y;
    }

    public void setVelocityY(int velocity) {
        this.velocityY = velocity;
    }

    public int getVelocityY() {
        return this.velocityY;
    }

}
