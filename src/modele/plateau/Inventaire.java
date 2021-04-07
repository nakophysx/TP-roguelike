package modele.plateau;
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

    public void ajouterCle() {
        nCles++;
        System.out.println("une clé a été ajoutée");
    }

    public boolean utiliserCapsule(){
        if (nCapsules != 0)
        {
            nCapsules--;
            return true;
        } else return false;
    }

    public void ajouterCapsule() {
        nCapsules++;
        System.out.println("une capsule a été ajoutée");
    }
}