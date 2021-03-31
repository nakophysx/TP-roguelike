package modele.plateau;

public class Coffre extends Pickup {
    public Coffre(Jeu _jeu) { super(_jeu); }

    @Override
    public boolean traversable() {
        return false;
    }

    @Override
    public void interact() {
        if (disponible)
        {
            super();

        }
    }
}
