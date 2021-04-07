package modele.plateau;

public class CaseNormale extends EntiteStatique implements Interactive {
    boolean traversable;
    boolean usage_unique;
    public CaseNormale(Jeu _jeu, boolean unique) { super(_jeu); traversable = true; usage_unique = unique;}

    @Override
    public boolean traversable() {
        return traversable;
    }

    public boolean disponible(){return !traversable;}

    public void interact()
    {
        if(usage_unique) { traversable = false; /* Changer l'icone!*/ }
    }

}
