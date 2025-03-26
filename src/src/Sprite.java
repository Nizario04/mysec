package src;

import java.awt.*;

public class Sprite implements Displayable{

    protected double x;
    protected double y;
    protected final Image image;
    protected final double width;
    protected final double height;
    /*
        public Sprite(double x, double y, Image image, double width, double height) {
            this.x = x;
            this.y = y;
            this.image = image;
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw(Graphics g) {
            g.drawImage(image,(int)x,(int)y,null);
        }
         */
    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g, double adjustedX, double adjustedY) {
        g.drawImage(image, (int) adjustedX, (int) adjustedY, null);
    }

    @Override
    public int getX() {
        return (int) x;
    }


    @Override
    public int getY() {
        return (int) y;
    }
}
