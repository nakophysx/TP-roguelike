/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

import com.sun.source.tree.CaseTree;

import java.lang.reflect.Type;
import java.util.Observable;


public class Jeu extends Observable implements Runnable {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 10;

    private int pause = 200; // période de rafraichissement

    private Heros heros;
    private Room[][] room_array;
    private int current_x, current_y, room_max_x = 4, room_max_y = 4;

    public Jeu() {
        room_array = new Room[room_max_x][room_max_y];
        current_x = 0;
        current_y = 0;

        initialisationDesEntites();
    }

    public Heros getHeros() {
        return heros;
    }

    public EntiteStatique[][] getGrille() {
        return room_array[current_x][current_y].getGrille();
    }

	public EntiteStatique getEntite(int x, int y) {
		return room_array[current_x][current_y].getEntite(x, y);
	}

    private void initialisationDesEntites() {
        heros = new Heros(this, 4, 4);


        room_array[0][0] = new Room(this, new Orientation[]{Orientation.BAS, Orientation.DROIT});
        room_array[0][room_max_y-1] = new Room(this, new Orientation[]{Orientation.BAS, Orientation.GAUCHE});
        room_array[room_max_x-1][0] = new Room(this, new Orientation[]{Orientation.HAUT, Orientation.DROIT});
        room_array[room_max_x-1][room_max_y-1] = new Room(this, new Orientation[]{Orientation.HAUT, Orientation.GAUCHE});

        for (int i = 1 ; i < room_max_x - 1; i++)
        {
            room_array[i][0] = new Room(this, new Orientation[]{Orientation.BAS, Orientation.GAUCHE, Orientation.DROIT});
            room_array[i][room_max_y-1] = new Room(this, new Orientation[]{Orientation.HAUT, Orientation.GAUCHE, Orientation.DROIT});
        }

        for (int i = 1 ; i < room_max_y - 1; i++)
        {
            room_array[0][i] = new Room(this, new Orientation[]{Orientation.BAS, Orientation.HAUT, Orientation.DROIT});
            room_array[room_max_x-1][i] = new Room(this, new Orientation[]{Orientation.HAUT, Orientation.GAUCHE, Orientation.BAS});
        }

        for (int i = 1 ; i < room_max_x - 1; i++) {
            for (int j = 1; j < room_max_y - 1; j++) {
                room_array[i][j] = new Room(this, new Orientation[]{Orientation.HAUT, Orientation.BAS, Orientation.GAUCHE, Orientation.DROIT});
            }
        }

        for (int i = 0; i < room_max_x; i++) {
            for (int j = 0; j < room_max_y; j++) {
                room_array[i][j].initialisationDesEntites(i, j);
            }
        }
    }

    public void start() {
        new Thread(this).start();
    }

    public void run() {

        while(true) {

            setChanged();
            notifyObservers();

            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void interact(int x, int y){
        EntiteStatique e = getEntite(x,y);
        if(e instanceof Interactive){
            Interactive p = (Interactive) e;
            p.interact();
        }
    }

    public void switch_room(int room_number_x, int room_number_y, int new_x, int new_y)
    {
        current_x = room_number_x;
        current_y = room_number_y;
        heros.setPosition(new_x, new_y);
        heros.getInventaire().viderCapsule();
    }

    public void setPickup (Pickup p, int _x, int _y)
    {
        room_array[current_x][current_y].addEntiteStatique(p, _x, _y);
    }
}
