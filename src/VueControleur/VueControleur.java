package VueControleur;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


import modele.plateau.*;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle (flèches direction, etc.))
 *
 */
public class VueControleur extends JFrame implements Observer {
    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private int sizeX; // taille de la grille affichée
    private int sizeY;

    // icones affichées dans la grille
    private ImageIcon icoHeroDroite;
    private ImageIcon icoHeroGauche;
    private ImageIcon icoHeroHaut;
    private ImageIcon icoHeroBas;
    private ImageIcon icoCaseNormale;
    private ImageIcon icoMur;
    private ImageIcon icoCle;
    private ImageIcon icoCapsule;
    private ImageIcon icoCoffre;
    private ImageIcon icoPorte;
    private ImageIcon icoHole;
    private ImageIcon icoFire;
    private ImageIcon icoDrops;



    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)


    public VueControleur(Jeu _jeu) {
        sizeX = _jeu.SIZE_X;
        sizeY = _jeu.SIZE_Y + 1;
        jeu = _jeu;

        chargerLesIcones();
        placerLesComposantsGraphiques();
        ajouterEcouteurClavier();
    }

    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT : jeu.getHeros().gauche(); break;
                    case KeyEvent.VK_RIGHT : jeu.getHeros().droite(); break;
                    case KeyEvent.VK_DOWN : jeu.getHeros().bas(); break;
                    case KeyEvent.VK_UP : jeu.getHeros().haut(); break;
                    case KeyEvent.VK_SPACE:
                        switch (jeu.getHeros().getOrientation()){
                            case BAS : jeu.interact( jeu.getHeros().getX(), jeu.getHeros().getY() + 1); break;
                            case HAUT : jeu.interact( jeu.getHeros().getX(), jeu.getHeros().getY() - 1); break;
                            case DROIT : jeu.interact( jeu.getHeros().getX() + 1, jeu.getHeros().getY()); break;
                            case GAUCHE: jeu.interact( jeu.getHeros().getX() - 1, jeu.getHeros().getY()); break;
                        } break;
                    //case KeyEvent.VK_I: jeu.openinventaire(); break;
                }
            }
        });
    }

    private void chargerLesIcones() {
        icoHeroDroite = chargerIcone("Images/fairyRight.png");
        icoHeroGauche = chargerIcone("Images/fairyLeft.png");
        icoHeroBas = chargerIcone("Images/fairyDown.png");
        icoHeroHaut = chargerIcone("Images/fairyUp.png");
        icoCaseNormale = chargerIcone("Images/Vide.png");
        icoMur = chargerIcone("Images/Mur.png");
        icoCle = chargerIcone("Images/Cle.png");
        icoCoffre = chargerIcone("Images/Coffre.png");
        icoCapsule = chargerIcone("Images/Capsule.png");
        icoPorte = chargerIcone("Images/Porte.png");
        icoHole = chargerIcone("Images/Hole.png");
        icoFire = chargerIcone("Images/fire.png");
        icoDrops = chargerIcone("Images/drops.png");
    }

    private ImageIcon chargerIcone(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return new ImageIcon(image);
    }

    private void placerLesComposantsGraphiques() {
        setTitle("Roguelike");
        setSize(400, 275);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels);
    }

    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {
        for (int i = 0; i < jeu.getHeros().getLifes(); i++)
        {
            tabJLabel[i][0].setIcon(icoHeroDroite);
        }

        tabJLabel[12][0].setIcon(icoCle);
        tabJLabel[13][0].setText(Integer.toString(jeu.getHeros().getInventaire().getnCles()));

        tabJLabel[16][0].setIcon(icoCapsule);
        tabJLabel[17][0].setText(Integer.toString(jeu.getHeros().getInventaire().getnCapsules()));

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY-1; y++) {
				EntiteStatique e = jeu.getEntite(x, y);
                if (e instanceof Mur) {
                    tabJLabel[x][y+1].setIcon(icoMur);
                }else if (e instanceof Interactive){
                    Interactive i = (Interactive) e;
                    if (i.disponible()) {
                        if (e instanceof Cle) {
                            tabJLabel[x][y+1].setIcon(icoCle);
                        } else if (e instanceof Coffre) {
                            tabJLabel[x][y+1].setIcon(icoCoffre);
                        } else if (e instanceof Capsule) {
                            tabJLabel[x][y+1].setIcon(icoCapsule);
                        } else if (e instanceof Porte) {
                            tabJLabel[x][y+1].setIcon(icoPorte);
                        } else if (e instanceof CaseNormale) {
                            tabJLabel[x][y+1].setIcon(icoFire);
                        }
                    }else {tabJLabel[x][y+1].setIcon(icoCaseNormale);}
                } else if (e instanceof CaseVide) {
                    tabJLabel[x][y+1].setIcon(icoHole);
                }
            }

        }

        switch(jeu.getHeros().getOrientation()) {
            case HAUT:
                tabJLabel[jeu.getHeros().getX()][jeu.getHeros().getY()+1].setIcon(icoHeroHaut);
                break;
            case BAS:
                tabJLabel[jeu.getHeros().getX()][jeu.getHeros().getY()+1].setIcon(icoHeroBas);
                break;
            case DROIT:
                tabJLabel[jeu.getHeros().getX()][jeu.getHeros().getY()+1].setIcon(icoHeroDroite);
                break;
            case GAUCHE:
                tabJLabel[jeu.getHeros().getX()][jeu.getHeros().getY()+1].setIcon(icoHeroGauche);
                break;
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        mettreAJourAffichage();
        /*
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */

    }
}
