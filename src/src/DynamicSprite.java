package src;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private Direction direction = Direction.EAST;
    private double speed = 5;
    private double timeBetweenFrame = 250;
    private boolean isWalking =false;
    private final int spriteSheetNumberOfColumn = 10;

   // Vitesse normale

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
    }

    public boolean isWalking() {
        return this.isWalking;
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment){
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch(direction){
            case EAST: moved.setRect(super.getHitBox().getX()+speed,super.getHitBox().getY(),
                                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:  moved.setRect(super.getHitBox().getX()-speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()-speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()+speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite s : environment){
            if ((s instanceof SolidSprite) && (s!=this)){
                if (((SolidSprite) s).intersect(moved)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int getX() {
        return (int) x;  // Retourne la coordonnée x
    }

    @Override
    public int getY() {
        return (int) y;  // Retourne la coordonnée y
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void move(){
        switch (direction){
            case NORTH -> {
                this.y-=speed;
            }
            case SOUTH -> {
                this.y+=speed;
            }
            case EAST -> {
                this.x+=speed;
            }
            case WEST -> {
                this.x-=speed;
            }
        }
    }

    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            move();
        }
    }

    @Override
    public void draw(Graphics g, double adjustedX, double adjustedY) {
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        // Récupère la ligne du spriteSheet en fonction de la direction
        int frameLine = direction.getFrameLineNumber();

        // Calcul des coordonnées dans le sprite sheet
        int sourceX = (int) (index * this.width);
        int sourceY = (int) (frameLine * this.height);
        int sourceWidth = (int) this.width;
        int sourceHeight = (int) this.height;

        // Dessiner l'image avec la position ajustée par la caméra
        g.drawImage(image, (int) adjustedX, (int) adjustedY,
                (int) (adjustedX + width), (int) (adjustedY + height),
                sourceX, sourceY, sourceX + sourceWidth, sourceY + sourceHeight, null);
    }

    public Rectangle2D.Double getHitBox() {

        return new Rectangle2D.Double(x, y, width, height);
    }

}
