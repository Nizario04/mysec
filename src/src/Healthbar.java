package src;

import java.awt.*;

public class Healthbar {
    private int maxHealth;
    private int currentHealth;

    public Healthbar(int currentHealth, int maxHealth) {
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
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

    public int getMaxHealth() {
        return maxHealth;
    }
}
