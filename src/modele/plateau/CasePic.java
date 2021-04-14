package modele.plateau;

public class CasePic extends EntiteStatique implements Interactive {
    public CasePic(Jeu _jeu) {
        super(_jeu);
    }

    @Override
    public boolean disponible() {
        return true;
    }

    public boolean traversable() {
        return true;
    }

    public void interact() {
        jeu.getHeros().loseLifes();
    }
}
