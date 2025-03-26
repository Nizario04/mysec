package src;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Trap extends SolidSprite {
    public Trap(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    @Override
    public Rectangle2D.Double getHitBox() {
        return new Rectangle2D.Double(x, y, width, height); // Retourne la hitbox du piège
    }

    @Override
    public void draw(Graphics g, double adjustedX, double adjustedY) {
        // Dessiner l'image du piège à sa position ajustée par la caméra
        g.drawImage(image, (int) adjustedX, (int) adjustedY, (int) width, (int) height, null);
    }
}
