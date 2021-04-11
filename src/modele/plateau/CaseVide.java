package modele.plateau;

public class CaseVide extends EntiteStatique {
    boolean traversable = false;

    public CaseVide(Jeu _jeu) {
        super(_jeu);
    }

    public boolean traversable() {
        return this.traversable;
    }
}
