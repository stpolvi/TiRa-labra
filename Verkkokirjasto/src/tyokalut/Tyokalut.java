
package tyokalut;

import java.util.Random;

/**
 * Yleishyödyllisiä työkaluja kaikkeen.
 * @author silja
 */
public class Tyokalut {

    /**
     * Kahden kokonaisluvun keskiarvo katkaistuna kokonaislukuna
     * @param a ensimmäinen luku
     * @param b toinen luku
     * @return keskiarvo joka on katkaistu kokonaisluvuksi pyöristämättä
     */

    public static int keskiarvo(int a, int b) {
        double vastaus = 0.5 * (a+b);
        return (int) vastaus;
    }
    
    /**
     * Kahden kokonaisluvun minimi.
     * @param a ensimmäinen luku
     * @param b toinen luku
     * @return luvuista pienempi
     */

    public static int minimi(int a, int b) {
        if (a<b) return a;
        return b;
    }

    /**
     * Ovatko kaksi totuusmuuttujaa ekvivalentit:
     * joko molemmat true tai molemmat false.
     * @param a ensimmäinen totuusarvo
     * @param b toinen totuusarvo
     * @return olivatko ekvivalentit
     */

    public static boolean ekvivalentit(boolean a, boolean b) {
        return (a&&b) || ((!a)&&(!b));
    }
    
    /**
     * Kopioi ensimmäisen int-taulukon alkiot toiseen.
     * Käy läpi lyhyemmän taulukon verran indeksejä, joten kumpi tahansa
     * saa olla pidempi.
     * Aikavaativuus O(n)
     * @param kopioitava taulukko jonka alkioita kopioidaan
     * @param uusi mihin kopioidaan
     */

    public static void kopioiIntTaulukkoToiseen(int[] kopioitava, int[] uusi) {
        int lyhemmanPituus = minimi(kopioitava.length, uusi.length);

        for (int i=0; i<lyhemmanPituus; i++) {
            uusi[i] = kopioitava[i];
        }
    }

    /**
     * Kopioi ensimmäisen oliotaulukon alkiot toiseen.
     * Käy läpi lyhyemmän taulukon verran indeksejä, joten kumpi tahansa
     * saa olla pidempi.
     * Aikavaativuus O(n)
     * @param kopioitava taulukko jonka alkioiden viitteet kopioidaan toiseen
     * @param uusi taulukko johon viitteet kopioidaan
     */

    public static void kopioiOliotaulukkoToiseen(Object[] kopioitava, Object[] uusi) {
        int lyhemmanPituus = minimi(kopioitava.length, uusi.length);

        for (int i=0; i<lyhemmanPituus; i++) {
            uusi[i] = kopioitava[i];
        }
    }
    
    /**
     * Sekoittaa annetun oliotaulukon alkiot satunnaiseen järjetykseen.
     *
     * Aikavaativuus O(n) taulukon pituuden suhteen
     */

    public static void sekoitaOliotaulukko(Object[] taulukko) {
        Random r = new Random();
        int paikka1;
        int paikka2;

        for (int i=0; i<taulukko.length *4; i++) {
            paikka1 = r.nextInt(taulukko.length);
            paikka2 = r.nextInt(taulukko.length);
            vaihdaPaittain(taulukko, paikka1, paikka2);
        }
    }

    /**
     * Vaihtaa annetussa oliotaulukossa annettujen indeksien kohdalla olevien
     * alkioiden paikkaa keskenään.
     * Indeksit, joita ei ole taulukossa, aiheuttavat poikkeuksen
     * ArrayIndexOutOfBoundsException
     * @param taulukko taulukko jossa alkioita siirretään
     * @param indeksi1 ensimmäisen indeksi
     * @param indeksi2 toisen indeksi
     */

    public static void vaihdaPaittain(Object[] taulukko, int indeksi1, int indeksi2) {
        Object talteen = taulukko[indeksi1];
        taulukko[indeksi1] = taulukko[indeksi2];
        taulukko[indeksi2] = talteen;
    }


}
