package modele.plateau;

public class Coffre extends Pickup {
    public Coffre(Jeu _jeu) { super(_jeu); }

    @Override
    public boolean traversable() {
        return !disponible;
    }

    @Override
    public void interact() {
        if (disponible)
        {
            int x , y;
            x = jeu.getHeros().getX();
            y = jeu.getHeros().getY();
            switch (jeu.getHeros().getOrientation()) {
                case HAUT:
                    y--;
                    break;
                case BAS:
                    y++;
                    break;
                case DROITE:
                    x++;
                    break;
                case GAUCHE:
                    x--;
                    break;
            }

            // Clés aléatoires
            int n = (int)Math.floor(Math.random()*2)+1;
            int i = 0;
            if (n!=0)
                for(int _x = -1; _x<=1; _x++ )
                {
                    for(int _y = -1; _y<=1; _y++)
                    {
                        EntiteStatique e = jeu.getEntite(_x +x , y + _y);
                        if (e instanceof CaseNormale && !((CaseNormale) e).usage_unique)
                        {
                            i++;
                            jeu.setPickup(new Cle(jeu), _x + x , _y+ y);
                            if (n==i)
                                break;
                        }

                    }
                    if (n==i)
                        break;
                }

            // Capsules aléatoires
            n = (int)Math.floor(Math.random()*3)+1;
            i = 0;
            if (n!=0)
                for(int _x = -1; _x<=1; _x++ )
                {
                    for(int _y = -1; _y<=1; _y++)
                    {
                        EntiteStatique e = jeu.getEntite(_x +x , y + _y);
                        if (e instanceof CaseNormale && !((CaseNormale) e).usage_unique)
                        {
                            i++;
                            jeu.setPickup(new Capsule(jeu), _x + x, _y + y);
                            if (n==i)
                                break;
                        }

                    }
                    if (n==i)
                        break;
                }

        }
        disponible = false;
    }
}
