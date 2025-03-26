package src;

import java.util.ArrayList;
import java.awt.geom.Rectangle2D;

public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList <Sprite> environment;
    private Healthbar heroHealth;

    public PhysicEngine(Healthbar heroHealth) {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
        this.heroHealth = heroHealth;
    }

    public void addToEnvironmentList(Sprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }

    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }

    @Override

    public void update() {
        for(DynamicSprite dynamicSprite : movingSpriteList){
            if(dynamicSprite.isWalking()){
                dynamicSprite.moveIfPossible(environment);
                checkCollisions(dynamicSprite);
            }
        }
    }
    /*
    private void checkCollisions(DynamicSprite dynamicSprite) {
        // Vérifier les collisions avec les pièges uniquement
        Rectangle2D.Double heroHitbox = (Rectangle2D.Double) dynamicSprite.getHitBox();  // Utilise Rectangle2D.Double ici

        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) {
                SolidSprite solidSprite = (SolidSprite) sprite;

                if (solidSprite.intersect(heroHitbox)) {
                    // Si collision avec un piège
                    if (sprite instanceof Trap) {
                        heroHealth.decreaseHealth(50);  // Réduire la vie (par exemple, 5 points)
                    }
                }
            }
        }
    }

     */
    private void checkCollisions(DynamicSprite dynamicSprite) {
        // Récupère la hitbox du héros
        Rectangle2D.Double heroHitbox = dynamicSprite.getHitBox(); // Assure-toi que tu utilises la bonne méthode dans DynamicSprite pour récupérer la hitbox

        // Parcours tous les sprites de l'environnement
        for (Sprite sprite : environment) {
            // Si le sprite est un piège (instance de Trap)
            if (sprite instanceof Trap) {
                // Récupère la hitbox du piège
                Rectangle2D.Double trapHitbox = ((Trap) sprite).getHitBox();

                // Vérifie si la hitbox du héros intersecte celle du piège
                if (heroHitbox.intersects(trapHitbox)) {
                    System.out.println("Collision avec un piège !");
                    heroHealth.decreaseHealth(50);  // Appliquer des dégâts au héros
                }
            }
        }
    }

}
