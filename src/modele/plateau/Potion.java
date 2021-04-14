package modele.plateau;

public class Potion extends Pickup{

    public Potion(Jeu _jeu){
        super(_jeu);
    }
    @Override
    public boolean disponible() {
        return super.disponible();
    }

    @Override
    public boolean traversable() {
        return true;
    }

    @Override
    public void interact() {
        if(disponible)
        {
            jeu.getHeros().getInventaire().ajouterPotion();
            disponible = false;
        }
    }
}
