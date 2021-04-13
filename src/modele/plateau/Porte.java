package modele.plateau;

public class Porte extends EntiteStatique implements Interactive{
    private boolean locked;
    private int next_room_x;
    private int next_room_y;
    private int next_x;
    private int next_y;

    public Porte(Jeu _jeu, int to_room_x, int to_room_y, int _x, int _y, boolean _locked)
    {
        super(_jeu);
        next_room_x = to_room_x;
        next_room_y = to_room_y;
        next_x = _x;
        next_y = _y;
        locked = _locked;

    }

    @Override
    public boolean traversable() {
        return !locked;
    }

    public boolean disponible()
    {
        return locked;
    }

    public void interact()
    {
        if(locked) {
            if (jeu.getHeros().getInventaire().utiliserCle()) {
                locked = false;
                System.out.println("porte deverrouillée");
            }
        } else {
                jeu.switch_room(next_room_x, next_room_y, next_x, next_y);
        }

    }

    public void unlock() {locked = false;}

}
