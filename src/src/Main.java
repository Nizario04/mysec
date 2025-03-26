package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    private JFrame displayZoneFrame;

    public Main() {
        displayZoneFrame = new JFrame("Nizar Link");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayZoneFrame.setLocationRelativeTo(null);

        showLobby();
    }

    private void showLobby() {
        Lobby lobby = new Lobby(e -> startGame());
        displayZoneFrame.setContentPane(lobby);
        displayZoneFrame.setVisible(true);
    }


    private void startGame() {
        // Nettoyer l'écran et démarrer le jeu
        displayZoneFrame.getContentPane().removeAll();
        displayZoneFrame.revalidate();
        displayZoneFrame.repaint();

        try {
            // Création de l'objet hero
            DynamicSprite hero = new DynamicSprite(200, 300,
                    ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

            // Dimensions du monde
            double worldWidth = 30000;
            double worldHeight = 1190;

            // Créer la barre de vie
            Healthbar heroHealth = new Healthbar(100, 200);

            // Créer une instance de Camera avec la taille du monde
            Camera camera = new Camera(displayZoneFrame.getWidth(), displayZoneFrame.getHeight(), worldWidth, worldHeight);

            // Créer les objets de RenderEngine, PhysicEngine, et GameEngine
            RenderEngine renderEngine = new RenderEngine(displayZoneFrame, camera, hero);
            PhysicEngine physicEngine = new PhysicEngine(heroHealth);

            // Charger le niveau
            Playground level = new Playground("./data/level1.txt");

            // Passer l'environnement au gameEngine (maintenant de type Sprite)
            GameEngine gameEngine = new GameEngine(hero, camera, level.getSolidSpriteList());

            // Timers
            Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
            Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
            Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

            renderTimer.start();
            gameTimer.start();
            physicTimer.start();

            // Mettre à jour le contenu de la fenêtre
            displayZoneFrame.setContentPane(renderEngine);
            displayZoneFrame.setVisible(true);

            // Ajouter les objets à la liste de rendu
            renderEngine.addToRenderList(level.getSpriteList());
            renderEngine.addToRenderList(hero);
            physicEngine.addToMovingSpriteList(hero);
            physicEngine.setEnvironment(level.getSolidSpriteList());

            // Ajouter les événements du clavier
            displayZoneFrame.addKeyListener(gameEngine);
            displayZoneFrame.setFocusable(true);
            displayZoneFrame.requestFocusInWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new Main();
    }
}