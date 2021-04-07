package modele.plateau;

public class Porte extends EntiteStatique implements Interactive{
    private boolean verrouille;
    private int next_room;
    private int next_x;
    private int next_y;

    public Porte(Jeu _jeu, int to_room, int _x, int _y, boolean _locked)
    {
        super(_jeu);
        next_room = to_room;
        next_x = _x;
        next_y = _y;
        verrouille = _locked;

    }

    @Override
    public boolean traversable() {
        System.out.println("texte");
        return !verrouille;
    }

    public boolean disponible()
    {
        return verrouille;
    }

    public void interact()
    {
        if(verrouille) {
            if (jeu.getHeros().getInventaire().utiliserCle()) {
                verrouille = false;
                System.out.println("porte deverrouillée");
            }
        } else {
                jeu.switch_room(next_room, next_x, next_y);
        }

    }

}
