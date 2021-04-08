package modele.plateau;

public class CaseVide extends EntiteStatique implements Interactive {
    boolean traversable = false;

    public CaseVide(Jeu _jeu) {
        super(_jeu);
    }

    public boolean traversable() {
        return this.traversable;
    }

    public boolean disponible() {
        return !this.traversable;
    }

    public void interact() {

    }
}
