public class Inventaire {
    public int nCles;
    public int nCapsules;

    public Inventaire(){
        nCles = 0;
    }

    public boolean utiliserCle(){
        if (nCles != 0)
        {
            nCles--;
            return true;
        } else return false;
    }

    public void ajouterCle() { nCles++;}

    public boolean utiliserCapsules(){
        if (nCapsules != 0)
        {
            nCapsules--;
            return true;
        } else return false;
    }

    public void ajouterCapsules() { nCapsules++;}
}