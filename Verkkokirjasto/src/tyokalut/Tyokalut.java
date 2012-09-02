
package tyokalut;

/**
 * Yleishyödyllisiä työkaluja kaikkeen.
 * @author silja
 */

public class Tyokalut {

    /**
     * Kahden kokonaisluvun keskiarvo katkaistuna kokonaislukuna.
     * @param a ensimmäinen luku
     * @param b toinen luku
     * @return keskiarvo joka on katkaistu kokonaisluvuksi pyöristämättä
     */

    public static int keskiarvo(int a, int b) {
        double vastaus = 0.5 * (a+b);
        return (int) vastaus;
    }

    /**
     * Kolmen kokonaisluvun keskiarvo katkaistuna kokonaislukuna.
     * @param a ensimmäinen luku
     * @param b toinen luku
     * @param c kolmas luku
     * @return keskiarvo joka on katkaistu kokonaisluvuksi pyöristämättä
     */

    public static int keskiarvo(int a, int b, int c) {
        double vastaus = (1.0/3) * (a+b+c);
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
     * Kahden kokonaisluvun maksimi.
     * @param a ensimmäinen luku
     * @param b toinen luku
     * @return luvuista suurempi
     */

    public static int maksimi(int a, int b) {
        if (a>b) return a;
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

    public static void kopioiTaulukkoToiseen(int[] kopioitava, int[] uusi) {
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

    public static void kopioiTaulukkoToiseen(Object[] kopioitava, Object[] uusi) {
        int lyhemmanPituus = minimi(kopioitava.length, uusi.length);

        for (int i=0; i<lyhemmanPituus; i++) {
            uusi[i] = kopioitava[i];
        }
    }
    
//    /**
//     * Sekoittaa annetun oliotaulukon alkiot satunnaiseen järjetykseen.
//     *
//     * Aikavaativuus O(n) taulukon pituuden suhteen
//     */
//
//    public static void sekoitaOliotaulukko(Object[] taulukko) {
//        Random r = new Random();
//        int paikka1;
//        int paikka2;
//
//        for (int i=0; i<taulukko.length *4; i++) {
//            paikka1 = r.nextInt(taulukko.length);
//            paikka2 = r.nextInt(taulukko.length);
//            vaihdaPaittain(taulukko, paikka1, paikka2);
//        }
//    }
    
    /**
     * Vaihtaa annetussa int-taulukossa annettujen indeksien kohdalla olevien
     * alkioiden paikkaa keskenään.
     * Indeksit, joita ei ole taulukossa, aiheuttavat poikkeuksen
     * ArrayIndexOutOfBoundsException
     * @param taulukko taulukko jossa alkioita siirretään
     * @param indeksi1 ensimmäisen indeksi
     * @param indeksi2 toisen indeksi
     */

    public static void vaihdaPaittain(int[] taulukko, int indeksi1, int indeksi2) {
        int talteen = taulukko[indeksi1];
        taulukko[indeksi1] = taulukko[indeksi2];
        taulukko[indeksi2] = talteen;
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

    /**
     * Pikajärjestää taulukon, eli järjestää sen quicksort-algoritmia käyttäen.
     * Aikavaativuus keskimäärin O(n log n)
     * @param taulukko järjestettävä taulukko
     */

    public static void pikajarjesta(int[] taulukko) {
        pikajarjesta(taulukko, 0, taulukko.length-1);
    }

        private static void pikajarjesta(int[] taulu, int alku, int loppu) {
            int vasen = alku, oikea = loppu;
            int raja = keskiarvo(taulu[vasen], taulu[oikea], taulu[keskiarvo(vasen, oikea)]);

            while (vasen <= oikea) {
                while (taulu[vasen] < raja) 
                    vasen++;
                while (taulu[oikea] > raja) 
                    oikea--;
                
                if (vasen > oikea) break;

                vaihdaPaittain(taulu, vasen, oikea);
                vasen++;
                oikea--;
                
            }
            if (alku < oikea)
                pikajarjesta(taulu, alku, oikea);
            if (vasen < loppu)
                pikajarjesta(taulu, vasen, loppu);
  }

    /**
     * Etsii annetusta taulukosta annetulta väliltä käyttäen binäärihakua.
     * Huomaa että metodin käyttö edellyttää että taulukko on järjestetty.
     * Aikavaativuus O(log n)
     * @param etsittava luku jota etsitään
     * @param alku ensimmäinen paikka josta etsitään
     * @param loppu viimeinen paikka josta etsitään
     * @param taulukko taulukko josta etsitään
     * @return löytyikö alkio taulukosta
     */

    public static boolean etsiBinaarihaulla(int etsittava, int alku, int loppu, int[] taulukko) {
        return binaarihae(etsittava, alku, loppu, taulukko) >= 0;
    }

    /**
     * Binäärihaku annetusta taulukosta annetulta väliltä.
     * Huomaa että metodin käyttö edellyttää että taulukko on järjestetty.
     * Aikavaativuus O(log n)
     * @param etsittava haettava
     * @param alku ensimmäinen paikka josta etsitään
     * @param loppu viimeinen paikka josta etsitään
     * @param taulukko taulukko josta etsitään
     * @return indeksi josta etsittävä löytyi, tai -1 mikäli sitä ei löytynyt
     */

    public static int binaarihae(int etsittava, int alku, int loppu, int[] taulukko) {
        int vasen = alku;
        int oikea = loppu;
        int keski;

        while (vasen <= oikea) {
            keski = tyokalut.Tyokalut.keskiarvo(vasen, oikea);

            if (taulukko[keski] == etsittava)
                return keski;
            else if (taulukko[keski] > etsittava)
                oikea = keski-1;
            else
                vasen = keski+1;
        }

        return -1;
    }

    /**
     * Etsii alkiota peräkkäishaulla.
     * Aikavaativuus O(n).
     * @param etsittava alkio jota etsitään
     * @param alku ensimmäinen paikka josta etsitään
     * @param loppu viimeinen paikka josta etsitään
     * @param taulukko taulukko josta etsitään
     * @return true jos löytyi, false muuten
     */

    public static boolean etsiPerakkaishaulla(int etsittava, int alku, int loppu, int[] taulukko) {
        return perakkaishae(etsittava, alku, loppu, taulukko) >= 0;
    }

    /**
     * Peräkkäishaku annetusta taulukosta.
     * Aikavaativuus O(n).
     * @param haettava alkio jota haetaan
     * @param alku ensimmäinen paikka josta etsitään
     * @param loppu viimeinen paikka josta etsitään
     * @param taulukko taulukko josta etsitään
     * @return haettavan indeksi jos löytyi, muutoin -1
     */

    public static int perakkaishae(int haettava, int alku, int loppu, int[] taulukko) {
        for (int i=alku; i<=loppu; i++) {
            if (taulukko[i] == haettava) return i;
        }
        return -1;
    }

    /**
     * Ovatko taulukot samat, eli ne ovat saman pituiset ja lisäksi
     * toisiaan vastaavissa indekseissä on samat alkiot.
     * @param taulukko taulukko
     * @param verrattava taulukko johon verrataan
     * @return olivatko taulukot samat
     */

    public static boolean onSamaTaulukko(int[] taulukko, int[] verrattava) {
        if (taulukko.length != verrattava.length) return false;

        for (int i=0; i<taulukko.length; i++)
            if (taulukko[i] != verrattava[i]) return false;

        return true;
    }

    /**
     * Luo taulukon, jossa on alkiot 1...pisteita. Myös taulukon pituus on
     * pisteita.
     * @param pisteita kuinka pitkä taulukko halutaan
     * @return uusi taulukko
     */

    public static int[] luoTaulukko1_N(int pisteita) {
        int[] vastaus = new int[pisteita];

        for (int i=0; i<vastaus.length; i++)
            vastaus[i] = i+1;
        
        return vastaus;
    }

}
