package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RenderEngine extends JPanel implements Engine,Displayable {

    /*
    private long lastTime = System.nanoTime(); // Dernière mise à jour du temps
    private int fps = 0; // Framerate calculé
    private long lastFpsTime = 0; // Temps pour calculer les FPS toutes les secondes
    private int frameCount = 0; // Nombre de frames depuis la dernière mise à jour du FPS
    private final int TARGET_FPS = 60; // FPS cible
    private final long nsPerTick = 1000000000L / TARGET_FPS;  // 1 seconde divisée par le nombre de FPS



    private ArrayList<Displayable> renderList;
    private Camera camera;
    private DynamicSprite hero;
    private Healthbar healthBar;

    public RenderEngine(JFrame jFrame, Camera camera, DynamicSprite hero) {
        renderList = new ArrayList<>();
        this.camera = camera;
        this.hero = hero;
        healthBar = new Healthbar(200,200); // Corrigé
    }



    public void draw(Graphics g) {
        super.draw(g); // Dessine le sprite normalement

        // Dessiner la hitbox du héros (en rouge)
        g.setColor(Color.RED);
        g.drawRect((int) dynamicSprite.getHitBox().x,
                (int) dynamicSprite.getHitBox().y,
                (int) dynamicSprite.getHitBox().width,
                (int) dynamicSprite.getHitBox().height);

        // Dessiner la hitbox du piège (en bleu)
        for (Sprite sprite : environment) {
            if (sprite instanceof Trap) {
                Trap trap = (Trap) sprite;
                g.setColor(Color.BLUE);
                g.drawRect((int) trap.getHitBox().x,
                        (int) trap.getHitBox().y,
                        (int) trap.getHitBox().width,
                        (int) trap.getHitBox().height);
            }
        }
    }





    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable) {
        if (!renderList.contains(displayable)) {
            renderList.addAll(displayable);
        }
    }




    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject : renderList) {
            double adjustedX = renderObject.getX() - camera.getX();
            double adjustedY = renderObject.getY() - camera.getY();
            renderObject.draw(g, adjustedX, adjustedY);
        }

        // Afficher le FPS
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("FPS: " + fps, 300, 30);

        healthBar.draw(g, 10, 10);
    }

    public void update() {
        long now = System.nanoTime();
        frameCount++;

        // Calculer les FPS chaque seconde
        if ((now - lastFpsTime) >= 1000000000L) {  // Si 1 seconde s'est écoulée
            fps = frameCount;
            frameCount = 0;
            lastFpsTime = now;
        }

        camera.update(hero.getX(), hero.getY());
        repaint();  // Redessiner la fenêtre

        // Calculer la limite de FPS
        long sleepTime = nsPerTick - (now - lastTime);
        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime / 1000000L);  // Limiter le temps de frame pour respecter le FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lastTime = System.nanoTime();
    }

     */

    private long lastTime = System.nanoTime(); // Dernière mise à jour du temps
    private int fps = 0; // Framerate calculé
    private long lastFpsTime = 0; // Temps pour calculer les FPS toutes les secondes
    private int frameCount = 0; // Nombre de frames depuis la dernière mise à jour du FPS
    private final int TARGET_FPS = 60; // FPS cible
    private final long nsPerTick = 1000000000L / TARGET_FPS;  // 1 seconde divisée par le nombre de FPS

    private ArrayList<Displayable> renderList;
    private Camera camera;
    private DynamicSprite hero;
    private Healthbar healthBar;

    public RenderEngine(JFrame jFrame, Camera camera, DynamicSprite hero) {
        renderList = new ArrayList<>();
        this.camera = camera;
        this.hero = hero;
        this.healthBar = new Healthbar(200, 200);
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {  // Touche Espace
                    isRunning = true; // Le héros commence à courir
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {  // Touche Espace
                    isRunning = false; // Le héros arrête de courir
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);  // Appel à la méthode parent pour le rendu de base

        // Affichage des objets dans renderList
        for (Displayable renderObject : renderList) {
            double adjustedX = renderObject.getX() - camera.getX();
            double adjustedY = renderObject.getY() - camera.getY();
            renderObject.draw(g, adjustedX, adjustedY);
        }

        // Afficher la hitbox du héros en rouge
        g.setColor(Color.RED);
        g.drawRect((int) hero.getHitBox().x,
                (int) hero.getHitBox().y,
                (int) hero.getHitBox().width,
                (int) hero.getHitBox().height);

        // Dessiner la hitbox du piège en bleu
        for (Displayable displayable : renderList) {
            if (displayable instanceof Trap) {
                Trap trap = (Trap) displayable;
                g.setColor(Color.BLUE);
                g.drawRect((int) trap.getHitBox().x,
                        (int) trap.getHitBox().y,
                        (int) trap.getHitBox().width,
                        (int) trap.getHitBox().height);
            }
        }



        // Afficher le FPS dans le coin supérieur droit
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("FPS: " + fps, 300, 30);

        // Afficher la barre de vie (healthBar) dans le coin supérieur gauche
        healthBar.draw(g, 10, 10);
    }

    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable) {
        for (Displayable obj : displayable) {
            addToRenderList(obj);
        }
    }

    // Mise à jour des informations de la scène
    public void update() {
        long now = System.nanoTime();
        frameCount++;

        // Calculer les FPS chaque seconde
        if ((now - lastFpsTime) >= 1000000000L) {  // Si 1 seconde s'est écoulée
            fps = frameCount;
            frameCount = 0;
            lastFpsTime = now;
        }

        camera.update(hero.getX(), hero.getY());
        repaint();  // Redessiner la fenêtre

        // Calculer la limite de FPS
        long sleepTime = nsPerTick - (now - lastTime);
        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime / 1000000L);  // Limiter le temps de frame pour respecter le FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lastTime = System.nanoTime();
        updateHeroSpeed();
    }

    @Override
    public void draw(Graphics g, double adjustedX, double adjustedY) {
        // Implémentation de la méthode draw() héritée de Displayable (si nécessaire)
        // Par exemple, dessiner le sprite à ses coordonnées ajustées.
    }


    private static final int DEFAULT_SPEED = 5;
    private static final int RUNNING_SPEED = 10; // Vitesse en mode course


    private boolean isRunning = false;



    // Mise à jour de la vitesse du héros en fonction de l'état de course
    public void updateHeroSpeed() {
        if (isRunning) {
            hero.setSpeed(RUNNING_SPEED); // Augmenter la vitesse si le héros court
        } else {
            hero.setSpeed(DEFAULT_SPEED); // Vitesse normale
        }
    }

    // Appeler cette méthode dans la boucle de mise à jour pour actualiser la vitesse du héros



}



 /*
    @Override
    public int getY() {
        return (int) hero.getY();  // Renvoie la position Y du héros
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);  // Appel à la méthode parent pour le rendu de base

        // Affichage des objets dans renderList
        for (Displayable renderObject : renderList) {
            double adjustedX = renderObject.getX() - camera.getX();
            double adjustedY = renderObject.getY() - camera.getY();
            renderObject.draw(g, adjustedX, adjustedY);
        }

        // Afficher la hitbox du héros en rouge
        g.setColor(Color.RED);
        g.drawRect((int) hero.getHitBox().x,
                (int) hero.getHitBox().y,
                (int) hero.getHitBox().width,
                (int) hero.getHitBox().height);

        // Dessiner la hitbox du piège en bleu
        for (Displayable displayable : renderList) {
            if (displayable instanceof Trap) {
                Trap trap = (Trap) displayable;
                g.setColor(Color.BLUE);
                g.drawRect((int) trap.getHitBox().x,
                        (int) trap.getHitBox().y,
                        (int) trap.getHitBox().width,
                        (int) trap.getHitBox().height);
            }
        }

        // Afficher le FPS dans le coin supérieur droit
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("FPS: " + fps, 300, 30);

        // Afficher la barre de vie (healthBar) dans le coin supérieur gauche
        healthBar.draw(g, 10, 10);
    }

 */

