/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.plateau;
/**
 * Héros du jeu
 */
public class Heros {
    private int lifes = 3;
    private int x;
    private int y;
    private Inventaire inv;
    private Jeu jeu;
    private Orientation ori;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return ori;
    }

    public Heros(Jeu _jeu, int _x, int _y) {
        jeu = _jeu;
        x = _x;
        y = _y;
        ori = Orientation.DROITE;
        inv = new Inventaire();
    }

    public Inventaire getInventaire() {return inv;}

    public void droite() {
        if(ori == Orientation.DROITE)
        {
            if (traversable(x+1, y)) {
                x++;
                jeu.interact(x, y);
            }
            else {
               EntiteStatique trou = jeu.getEntite(x+1, y);
               if(trou instanceof CaseVide){
                   if(traversable(x+2, y)){
                       x = x+2;
                       jeu.interact(x, y);
                   }
               }
            }
        } else { ori = Orientation.DROITE; }
    }

    public void gauche() {
        if(ori == Orientation.GAUCHE) {
            if (traversable(x - 1, y)) {
                x--;
                jeu.interact(x, y);
            }
            else {
                EntiteStatique trou = jeu.getEntite(x-1, y);
                if(trou instanceof CaseVide){
                    if(traversable(x-2, y)){
                        x = x-2;
                        jeu.interact(x, y);
                    }
                }
            }
        } else { ori = Orientation.GAUCHE; }
    }

    public void bas() {
        if (ori == Orientation.BAS) {
            if (traversable(x, y + 1)) {
                y++;
                jeu.interact(x, y);
            }
            else {
                EntiteStatique trou = jeu.getEntite(x, y+1);
                if(trou instanceof CaseVide){
                    if(traversable(x, y+2)){
                        y = y+2;
                        jeu.interact(x, y);
                    }
                }
            }
        } else {ori = Orientation.BAS;}
    }

    public void haut() {
        if (ori == Orientation.HAUT) {
            if (traversable(x, y-1)) {
                y --;
                jeu.interact(x,y);
            }
            else {
                EntiteStatique trou = jeu.getEntite(x, y-1);
                if(trou instanceof CaseVide){
                    if(traversable(x, y-2)){
                        y = y-2;
                        jeu.interact(x, y);
                    }
                }
            }
        } else {ori = Orientation.HAUT; }

    }

    public void setPosition(int _x, int _y)
    {
        x = _x;
        y = _y;
    }

    private boolean traversable(int x, int y) {

        if (x >= 0 && x <= jeu.SIZE_X && y >= 0 && y <= jeu.SIZE_Y) {
            return jeu.getEntite(x, y).traversable();
        } else {
            return false;
        }
    }

    public int getLifes() {return lifes;}

    public void loseLifes(){
        lifes--;
    }

    public void gainLifes(){
        lifes++;
    }
}

