package src;

import java.awt.*;

public class Camera {
    //on va devoire d'abord definir
    private double x, y; // Position de la camera
    private final double width, height; // Taille de l'écran
    private final double worldWidth, worldHeight; // Taille du labyrinthe

    public Camera(double width, double height, double worldWidth, double worldHeight) {
        this.width = width;
        this.height = height;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.x = 0;
        this.y = 0;
    }

    public void update(double targetX, double targetY) {
        // Centrer la camera sur le héros
        x = targetX - width / 2;
        y = targetY - height / 2;

        // Empêcher la camera de sortir du donjon
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > worldWidth - width) x = worldWidth - width;
        if (y > worldHeight - height) y = worldHeight - height;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public static class HealthBar {
        private int maxHealth;
        private int currentHealth;

        public HealthBar(int maxHealth) {
            this.maxHealth = maxHealth;
            this.currentHealth = maxHealth;
        }

        public void decreaseHealth(int amount) {
            currentHealth -= amount;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
        }

        public void draw(Graphics g, int x, int y) {
            // Affichage de la barre de vie
            g.setColor(Color.RED);
            g.fillRect(x, y, 200, 20); // Barre de fond (rouge)

            g.setColor(Color.GREEN);
            g.fillRect(x, y, (int) ((currentHealth / (double) maxHealth) * 200), 20); // Vie actuelle (vert)

            // Contour de la barre de vie
            g.setColor(Color.BLACK);
            g.drawRect(x, y, 200, 20);
        }

        public int getCurrentHealth() {
            return currentHealth;
        }
    }
}
