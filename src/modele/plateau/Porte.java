package modele.plateau;

public class Porte extends EntiteStatique implements Interactive{
    private boolean verrouille;
    public Porte(Jeu _jeu) { super(_jeu); verrouille = true; }

    @Override
    public boolean traversable() {
        return !verrouille;
    }

    @Override
    public void interact()
    {
        if(verrouille && jeu.getHeros().getInventaire().utiliserCle())
            verrouille = false;
    }
}
package modele.plateau;