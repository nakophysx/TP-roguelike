/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;

import com.sun.source.tree.CaseTree;

import java.util.Observable;


public class Jeu extends Observable implements Runnable {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 10;

    private int pause = 200; // période de rafraichissement

    private Heros heros;
    private Room[] room_array;
    private int current_room, room_max = 4;

    public Jeu() {
        room_array = new Room[room_max];
        current_room = 0;

        initialisationDesEntites();
    }

    public Heros getHeros() {
        return heros;
    }

    public EntiteStatique[][] getGrille() {
        return room_array[current_room].getGrille();
    }

	public EntiteStatique getEntite(int x, int y) {
		return room_array[current_room].getEntite(x, y);
	}

    private void initialisationDesEntites() {
        heros = new Heros(this, 4, 4);

        for (int i = 0 ; i < room_max ; i++)
        {
            room_array[i] = new Room(this);
            room_array[i].initialisationDesEntites(i);
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

    public void switch_room(int room_number, int new_x, int new_y)
    {
        current_room = room_number;
        heros.setPosition(new_x, new_y);
    }
}
