package modele.plateau;

import java.io.File;
import java.util.Scanner;

public class Room {

    public Room(Jeu j, Orientation[] oris) {
        jeu = j;
        orientations = oris;
    }

    private Orientation[] orientations;
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

    //9,4
    public void initialisationDesEntites(int room_x, int room_y) {

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

        // Géneration de Salle
        setRoom((int)Math.floor(Math.random()*3));
        // Fin géneration de salle

        for (Orientation o:orientations)
        {
            switch (o){
                case GAUCHE:
                    addEntiteStatique(new Porte(jeu, room_x-1, room_y, 19, 5, true), 0, 5);
                    break;
                case DROIT:
                    addEntiteStatique(new Porte(jeu, room_x+1, room_y, 0, 5, true), 19, 5);
                    break;
                case BAS:
                    addEntiteStatique(new Porte(jeu, room_x, room_y+1, 10, 0, true), 10, 9);
                    break;
                case HAUT:
                    addEntiteStatique(new Porte(jeu, room_x, room_y-1, 10, 9, true), 10, 0);
                    break;
            }
        }

        boolean set = false;
        int x, y;
        EntiteStatique e;

        while (!set)
        {
            x = (int)Math.floor(Math.random()*20);
            y = (int)Math.floor(Math.random()*10);

            e = grilleEntitesStatiques[x][y];
            if (e instanceof CaseNormale && !((CaseNormale) e).usage_unique)
            {
                grilleEntitesStatiques[x][y] = new Cle(jeu);
                set = true;
            }

        }

        set = false;
        while (!set)
        {
            x = (int)Math.floor(Math.random()*20);
            y = (int)Math.floor(Math.random()*10);
            e = grilleEntitesStatiques[x][y];
            if (e instanceof CaseNormale && !((CaseNormale) e).usage_unique)
            {
                grilleEntitesStatiques[x][y] = new Capsule(jeu);
                set= true;
            }
        }
    }

    public void addEntiteStatique(EntiteStatique e, int x, int y) {
        grilleEntitesStatiques[x][y] = e;

    }

    public void setRoom(int file_id)
    {
        switch (file_id)
        {
            case 2 :
                addEntiteStatique(new CasePic(jeu), 4, 3);
                break;
            case 1:
                addEntiteStatique(new CaseVide(jeu), 3, 4);
                addEntiteStatique(new CaseVide(jeu), 3, 5);
                addEntiteStatique(new CaseVide(jeu), 3, 6);
                addEntiteStatique(new CaseVide(jeu), 7, 4);
                addEntiteStatique(new CaseVide(jeu), 7, 5);
                addEntiteStatique(new CaseVide(jeu), 7, 6);
                addEntiteStatique(new CaseVide(jeu), 4, 3);
                addEntiteStatique(new CaseVide(jeu), 5, 3);
                addEntiteStatique(new CaseVide(jeu), 6, 3);
                addEntiteStatique(new CaseVide(jeu), 4, 7);
                addEntiteStatique(new CaseVide(jeu), 5, 7);
                addEntiteStatique(new CaseVide(jeu), 6, 7);
                addEntiteStatique(new Coffre(jeu), 5, 5);
                break;
            case 0:
            default:
                addEntiteStatique(new Mur(jeu), 2, 6);
                addEntiteStatique(new Mur(jeu), 3, 6);
                addEntiteStatique(new CaseVide(jeu), 5, 5);
                addEntiteStatique(new Coffre(jeu), 2, 2);
                addEntiteStatique(new CaseNormale(jeu, true), 4, 5);
                break;

        }

        for (int x = 0; x < size_x; x++) {
            for (int y = 0; y < size_y; y++) {
                if (grilleEntitesStatiques[x][y] == null) {
                    grilleEntitesStatiques[x][y] = new CaseNormale(jeu, false);
                }
            }
        }
    }
}
