package modele.plateau;

public class Capsule extends EntiteStatique {
    public Capsule(Jeu _jeu) { super(_jeu); }

    @Override
    public boolean traversable() {
        return false;
    }

    @Override
    public void interact() {
        if (disponible)
        {
            super();
            jeu.getHeros().getInventaire().ajouterCapsule();
        }
    }
}
