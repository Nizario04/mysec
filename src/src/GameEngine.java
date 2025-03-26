package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameEngine implements Engine, KeyListener {
    /*
    DynamicSprite hero;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                hero.setWalking(true);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        hero.setWalking(false);
    }

     */
    private DynamicSprite hero;
    private Camera camera;
    private ArrayList<Sprite> environment;  // Accepte maintenant une liste de Sprite générique

    public GameEngine(DynamicSprite hero, Camera camera, ArrayList<Sprite> environment) {
        this.hero = hero;
        this.camera = camera;
        this.environment = environment;  // Initialisation avec la liste de sprites
    }

    @Override
    public void update() {
        // Met à jour la position de la caméra pour suivre le héros
        camera.update(hero.getX(), hero.getY());

        // Déplacer le héros s'il marche
        if (hero.isWalking()) {
            hero.moveIfPossible(environment);  // Utiliser l'environnement de type Sprite
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                hero.setWalking(true);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                hero.setWalking(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        hero.setWalking(false);  // Arrêter de marcher lorsque la touche est relâchée
    }
}
