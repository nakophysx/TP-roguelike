package modele.plateau;

public class Potion extends Pickup{

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
            jeu.getHeros().
        }
    }
}
