package modele.plateau;

public class Room {

    public Room(Jeu j) {
        jeu = j;
    }

    private int size_x = 20;
    private int size_y = 10;
    private Jeu jeu;
    private EntiteStatique[][] grilleEntitesStatiques = new EntiteStatique[size_x][size_y];

    public EntiteStatique getEntite(int x, int y) {
        if (x < 0 || x >= size_x || y < 0 || y >= size_y) {
            // L'entité demandée est en-dehors de la grille
            return null;
        }
        return grilleEntitesStatiques[x][y];
    }

    public EntiteStatique[][] getGrille()
    {
        return grilleEntitesStatiques;
    }

    public void initialisationDesEntites(int room_number) {

        // murs extérieurs horizontaux
        for (int x = 0; x < 20; x++) {
            addEntiteStatique(new Mur(jeu), x, 0);
            addEntiteStatique(new Mur(jeu), x, 9);
        }

        // murs extérieurs verticaux
        for (int y = 1; y < 9; y++) {
            addEntiteStatique(new Mur(jeu), 0, y);
            addEntiteStatique(new Mur(jeu), 19, y);
        }

        addEntiteStatique(new Mur(jeu), 2, 6);
        addEntiteStatique(new Mur(jeu), 3, 6);
        addEntiteStatique(new Cle(jeu), 2, 7);
        addEntiteStatique(new Capsule(jeu), 2, 8);
        addEntiteStatique(new Porte(jeu, room_number+1, 19, 5, true), 0, 5);
        addEntiteStatique(new Porte(jeu, room_number-1, 0, 5, false), 19, 5);
        addEntiteStatique(new Coffre(jeu), 1, 1);

        for (int x = 0; x < size_x; x++) {
            for (int y = 0; y < size_y; y++) {
                if (grilleEntitesStatiques[x][y] == null) {
                    grilleEntitesStatiques[x][y] = new CaseNormale(jeu, false);
                }
            }
        }
    }

    public void addEntiteStatique(EntiteStatique e, int x, int y) {
        grilleEntitesStatiques[x][y] = e;

    }

}
