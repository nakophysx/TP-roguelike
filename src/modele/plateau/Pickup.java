public class Pickup extends EntiteStatique implements Interactive{
    private boolean disponible = true;

    public Interactive(Jeu _jeu) { super(_jeu);}

    @Override
    public void interact(){
        disponible = false;
    }
}