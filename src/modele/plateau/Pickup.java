package modele.plateau;
public abstract class Pickup extends EntiteStatique implements Interactive{
    protected boolean disponible = true;

    public Pickup(Jeu _jeu) { super(_jeu);}

    public boolean disponible(){return disponible;}

    public void interact() {
        disponible = false;
        System.out.println("nouvelle clé!");
    }
}
