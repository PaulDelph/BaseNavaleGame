/**
 * @groupe 92
 * @author : Saa Paul, Millimouno
 * @adresse: millimouno,saa-paul@couriel.uqam.ca
 * @code permanent: MILS30019703
 *
 * @author :Jaures-Xavier Bi Tizie,Gouli
 * @adress:Jauresgouli @gmail.com
 */
public class BaseNavale {

    /**
     * la methode principale qui execute tout le programme
     * @param args
     */
    public static void main(String[] args) {
        String messageAccueil = "Entrer la description et la position des bateaux\n" +
                "selon le format suivant, separes par des espaces:\n" +
                "taille[p/m/g] orientation[h/v] colonne[A-R] rangée[1-9]\n" ;
        String messageFeu = "Feu à volonté!\n" +
                "(entrer les coups à tirer: colonne [A-R] rangée [1-9])\n";

                char entry = ' ';
        char[][] batailleNav = {{ '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                { '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},};

        Pep8.stro(messageAccueil);
        while(entry != '\n'){
            char grandeur = Pep8.chari();
            char orientation = Pep8.chari();
            char colonne = Pep8.chari();
            char rangee = Pep8.chari();
            batailleNav = affiTabModified(batailleNav,grandeur, orientation,colonne,rangee);
            entry = Pep8.chari();
        }

        afficheHallTab(batailleNav);
        Pep8.stro(messageFeu);
        char entry2=' ';
        boolean notFound =true;
       while (notFound==true && entry2!='\n') {
           while (entry2 != '\n') {
               char colonne = Pep8.chari();
               char rangee = Pep8.chari();
               batailleNav = tireSurBateau(batailleNav, colonne, rangee);
               afficheHallTab(batailleNav);
               entry2 = Pep8.chari();
           }
           notFound = verifieDestructionBateau(batailleNav);

           if (notFound==true) entry2=' ';
           else {
               Pep8.stro("Vous avez anéanti la flotte!\n" +
                       "Appuyer sur <Enter> pour jouer à nouveau ou\n" +
                       "n'importe quelle autre saisie pour quitter.\n"
                       );
               Pep8.chari();
               Pep8.stro("Au revoir!");
           }
       }

    }
    /**
     * VERIFIE si la base contient encore des bateau
     * @param tab
     * @return
     */
    public static boolean verifieDestructionBateau(char[][]tab) {
        boolean found = false;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 18; ++j) {
                if (tab[i][j] == 'v' || tab[i][j] == '>') found = true;
            }
        }
        return found;
    }

    /**
     * affiche le contenue de la base-Navale
     * @param tab
     */
    public static void afficheHallTab(char [][]tab){
        int tailleColone = 18;
        int tailleLigne = 9;
        int positionI=0;
        int positionJ=0;
        Pep8.stro(" ABCDEFGHIJKLMNOPQR \n");
            int val= 1;
        for(int i=0; i < tailleLigne; ++i ){
            Pep8.deco(val);
            Pep8.charo('|');
            for (int j =0 ; j < tailleColone; ++j ){
                Pep8.charo(tab[positionI][positionJ]);
                ++positionJ;
            }
            positionJ =0;
            ++positionI;
            Pep8.charo('|');
            Pep8.charo('\n');
            ++val;
        }


    }

    /**
     *modifie le contenue de la table en fonction des parametre reçus
     * @param tab
     * @param grandeur
     * @param orientation
     * @param colone
     * @param range
     * @return
     */
    public static  char [][] affiTabModified(char [][]tab, char grandeur, char orientation, char colone, char range) {
        int tailleBateau = 0;
        int posiColone = 0;
        posiColone = getColoneValue(colone);
        int positRange = getRangeValue(range);

        if (grandeur == 'p') tailleBateau = 1;
        if (grandeur == 'm') tailleBateau = 3;
        if (grandeur == 'g') tailleBateau = 5;

        if (orientation == 'h') {
            int nombreItere = 1;
            if (18 - posiColone < tailleBateau) {
                nombreItere = tailleBateau;
                for (int i = posiColone; nombreItere > 0; --i) {

                    tab[positRange - 1][i] = '>';
                    --nombreItere;
                }
            }
            else {
                for (int i = posiColone - 1; nombreItere <= tailleBateau; ++i) {
                    tab[positRange - 1][i] = '>';
                    ++nombreItere;
                }
            }
        }
        else if (orientation == 'v') {
            int nombreItere = 1;
            if (9-positRange < tailleBateau) {
                nombreItere = tailleBateau;
                for (int i = positRange ; nombreItere >0 ; --i) {
                    tab[i][posiColone - 1] = 'v';
                    --nombreItere;
                }

            } else {
                for (int i = positRange - 1; nombreItere <= tailleBateau; ++i) {
                    tab[i][posiColone - 1] = 'v';
                    ++nombreItere;
                }
            }
        }
        return tab;
    }

    /**
     * Retourne la position de la range en de la lettre reçu en parametre
     * @param range
     * @return
     */
    public static int  getColoneValue(char range){
        int rang =0;
        if(range =='A') rang=1;else if(range =='B') rang=2;else if(range =='C') rang=3;
        else if(range =='D') rang=4;else if(range =='E') rang=5; else if(range =='F') rang=6; else if(range =='G') rang=7;
        else if(range =='H') rang=8; else if(range =='I') rang=9; else if(range =='J') rang=10; else if(range =='K') rang=11;
        else if(range =='L') rang=12; else if(range =='M') rang=13; else if(range =='N') rang=14; else if(range =='O') rang=15;
        else  if(range =='P') rang=16; else if(range =='Q') rang=17; else if(range =='R') rang=18;

        return  rang;
    }

    /**
     * Retourne la position de la colone en de la lettre reçu en parametre
     * @param colone
     * @return
     */
    public static int getRangeValue(char colone){
        int col=0;
        if(colone =='1') col =1; else if(colone =='2') col =2;else if(colone =='3') col =3; else if(colone =='4') col =4;
        else if(colone =='5') col =5; else if(colone =='6') col =6; else if(colone =='7') col =7; else if(colone =='8') col =8;
        else if(colone =='9') col =9;
        return col;

    }

    public static char[][] tireSurBateau(char [][]tab, char colonne, char rangee){

        int ctire = getColoneValue(colonne)-1;
        int rtire = getRangeValue(rangee)-1;
        if(tab[rtire][ctire]=='~') {
            tab[rtire][ctire]= 'o';
         }
        else if(tab[rtire][ctire]=='v' || tab[rtire][ctire]=='>'){
            tab =detruireBateau(tab,ctire,rtire);
        }

        return tab;
    }
    /**
     * Effectue une destruction des bateau dans un sens vertical
     * @param tab
     * @param range
     * @param colone
     */
    public static char [][] detruireBateau(char [][]tab,int colone,int range){

        destructionHorizontaleAvant(tab, range,  colone);
        int valR = range;
        if(range +1 >=0 && range+1 <9){
            if(tab[range+1][colone]=='>'){
                destructionHorizontaleAvant( tab, range+1,  colone);
                destructionHorizontaleDerriere(tab,  range+1,  colone);
            }
        }
        while ((valR>=0 && valR<9)&& tab[valR][colone]=='v'){
            if(colone-1 >=0 && tab[valR][colone-1]!='*')tab[valR][colone-1]='o';
            tab[valR][colone]='*';
            if(colone+1 <18 && tab[valR][colone+1]!='*')tab[valR][colone+1]='o';
            ++valR;
        }
        tab = detruireBateauArriere(tab,colone,range);
        return tab;
    }
    /**
     * Effectue une destruction des bateau dans un sens vertical
     * @param tab
     * @param range
     * @param colone
     */
    public static char [][] detruireBateauArriere(char [][]tab,int colone,int range){

        destructionHorizontaleDerriere(tab,  range,  colone);
        int valR = range-1;

        while ((valR>=0 && valR<9)&& tab[valR][colone]=='v'){
            if(colone-1 >=0 && tab[valR][colone-1]!='*' && tab[valR][colone-1]!='v' )tab[valR][colone-1]='o';
            tab[valR][colone]='*';
            if(colone+1 <18 && tab[valR][colone+1]!='*' && tab[valR][colone+1]!='v')tab[valR][colone+1]='o';
            --valR;
        }
        if(valR>=0 && valR<9){
            if(tab[valR][colone]=='>'){
                destructionHorizontaleAvant( tab, valR,  colone);
                destructionHorizontaleDerriere(tab,  valR,  colone);
            }
        }

        return tab;
    }
    /**
     * Effectue une destruction des bateau dans un sens horizontale
     * @param tab
     * @param range
     * @param colone
     */
    public static void destructionHorizontaleAvant(char[][] tab, int range, int colone){
        int valC= colone;
               while ((valC>=0 && valC < 18) && tab[range][valC]=='>'){
            if(range-1 >= 0 && tab[range-1][valC]!='*'&& tab[range-1][valC]!='>'){
                //if(tab[range-1][valC]=='v') detruireBateau(tab,range-1, valC);
                tab[range-1][valC]='o';
            }
            tab[range][valC]='*';
            if(range+1 < 9 && tab[range+1][valC]!='*'&& tab[range+1][valC]!='>'){
               // if(tab[range+1][valC]=='v') detruireBateau(tab,range+1, valC);
                tab[range+1][valC]='o';
            }
            ++valC;
        }
    }

    /**
     * Effectue une destruction des bateau dans un sens horizontale
     * @param tab
     * @param range
     * @param colone
     */
    public static void destructionHorizontaleDerriere(char[][] tab, int range, int colone){
        int valC= colone-1;
        while ((valC>=0 && valC< 18)&& tab[range][valC]=='>'){
            if(range-1 >= 0 && tab[range-1][valC]!='*'&& tab[range-1][valC]!='>')tab[range-1][valC]='o';
            tab[range][valC]='*';
            if(range+1 < 9 && tab[range+1][valC]!='*' && tab[range+1][valC]!='>')tab[range+1][valC]='o';
            --valC;
        }
    }

}
