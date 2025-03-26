package src;

import java.awt.*;

public interface Displayable {
    public void draw(Graphics g, double adjustedX, double adjustedY);
    int getX();  // Ajoute cette méthode
    int getY();
}
