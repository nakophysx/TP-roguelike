package modele.plateau;

public class CaseNormale extends EntiteStatique {
    boolean traversable;
    boolean usage_unique;
    public CaseNormale(Jeu _jeu, boolean unique) { super(_jeu); traversable = true; usage_unique = unique;}

    @Override
    public boolean traversable() {
        return traversable;
    }

}
