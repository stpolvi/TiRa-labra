
package suhteikkoanalyysi;

import java.awt.Color;
import suhteikot.*;
import tietorakenteetLuvuille.*;
import tyokalut.Tyokalut;

/**
 * Verkkoanalyysikirjastossa on algoritmeja, joilla voidaan tutkia verkkoja
 * eli verkkoehdon täyttäviä suhteikkoja. Useimmat algoritmit eivät toimi oikein,
 * jos syötteeksi annetaan sellaisia suhteikkoja, jotka eivät ole verkkoja.
 * Verkkoehdon tutkiminen jätetään käyttäjän vastuulle.
 * @author silja
 */

public class Verkkoanalyysikirjasto {

    private Verkkoanalyysikirjasto() {}

    /**
     * Eristetty piste on piste, josta ei ole yhteyksiä muihin pisteisiin.
     * Aikavaativuus O(pisteidenLkm) kun analysoitavana on TavallinenSuhteikko.
     * Tilavaativuus O(1)
     * @param v
     * @return
     */

    public static int[] eristetytPisteet(Suhteikko v) {
        VenyvaTaulukko eristetyt = new VenyvaTaulukko();
        for (int i=1; i<=v.PISTEITA; i++) {
            if (v.seuraajienLkm(i) == 0) //tavallisessa suhteikossa O(1)
                eristetyt.lisaa(i);
        }
        return eristetyt.toIntArray();
    }

    /**
     * Onko annettu pistejoukko hallitseva annetussa verkossa:
     * onko jokainen verkon piste joko joukossa tai sitten joukossa
     * olevan naapuri. Joukon on oltava valmiiksi järjestetty.
     * Aikavaativuus O(pisteidenLkm^2 * log pisteidenLkm),
     * tilavaativuus O(1)
     * @param v analysoitava verkko
     * @param pistejoukko onko hallitseva annetussa verkossa
     * @return oliko pistejoukko hallitseva
     */

    public static boolean onHallitsevaPistejoukko(Suhteikko v, int[] pistejoukko) {
        for (int i=1; i<=v.PISTEITA; i++) { //O(pisteidenLkm)
            if (Tyokalut.etsiBinaarihaulla(i, 0, pistejoukko.length-1, pistejoukko)) continue;
            if (!onYhteysJoukkoon(v, i, pistejoukko)) return false; //O(pisteidenLkm + yhteyksienLkm)
        }
        return true;
    }

    /**
     * Onko verkko säännöllinen:
     * onko kaikkien pisteiden aste sama.
     * Jos kaikkien n-pisteisen verkon pisteiden aste on k, niin
     * verkon kaarien lukumäärä on (1/2)nk.
     *
     * Aikavaativuus O(pisteidenLkm),
     * tilavaativuus O(1)
     * @param v analysoitava verkko
     * @return oliko säännöllinen
     */

    public static boolean onSaannollinen(Suhteikko v) {
        if (v.PISTEITA == 0) return true;

        if (!kaikkiAsteetSamatEpatyhjassa(v)) return false; // O(pisteidenLkm)
        return true;
    }

        /**
         * Privaatti. Aikavaativuus O(pisteidenLkm)
         */

        private static boolean kaikkiAsteetSamatEpatyhjassa(Suhteikko v) {
            int ekanAste = pisteenAste(v, 1); //O(1)
            for (int i=2; i<=v.PISTEITA; i++) { //O(pisteidenLkm)
                if (pisteenAste(v, i) != ekanAste) return false;
            }
            return true;
        }

    /**
     * Onko annettu verkko täydellinen:
     * onko jokaisesta pisteestä yhteys kaikkiin muihin pisteisiin.
     * Huomaa että täydellisen n-pisteisen verkon kaarien lukumäärä on
     * (1/2)n(n-1).
     *
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * Tilavaativuus O(1)
     * @param v analysoitava verkko
     * @return oliko täydellinen
     */

    public static boolean onTaydellinen(Suhteikko v) {
        for (int i=1; i<=v.PISTEITA; i++) {
            for (int j=i+1; j<=v.PISTEITA; j++) {
                if (!v.onYhteys(i, j)) return false; //O(log pisteidenLkm)
            }
        }

        return true;
    }

    /**
     * Sama kuin Taydellinen mutta toteutus eri.
     * Aikavaativuus O(pisteidenLkm),
     * tilavaativuus O(1)
     * @param v analysoitava verkko
     * @return oliko täydellinen verkko
     */

    public static boolean onTaydellinenSaannollisyydenAvulla(Suhteikko v) {
        if (v.PISTEITA == 0 || v.PISTEITA == 1) return true;
        return pisteenAste(v, 1) == v.PISTEITA-1 && onSaannollinen(v);
    }

    /**
     * Sama kuin Taydellinen mutta toteutus eri.
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm),
     * tilavaativuus O(1)
     * @param v analysoitava verkko
     * @return oliko täydellinen verkko
     */

    public static boolean onTaydellinenSeuraajienAvulla(Suhteikko v) {
        for (int i=1; i<=v.PISTEITA; i++) {
            if (v.seuraajienLkm(i) != v.PISTEITA -1) return false;
        }

        return true;
    }

    /**
     * Selvittää onko annettu väritettävä verkko yhtenäinen käyttämällä tietoa
     * että verkko on yhtenäinen täsmälleen silloin kun siinä on juuri.
     * Aikavaativuus O((pisteidenLkm + yhteyksienLkm) *pisteidenLkm),
     * tilavaativuus O(pisteidenLkm)
     * @param v analysoitava verkko
     * @return oliko yhtenäinen
     */

    public static boolean onYhtenainenJuurienAvulla(VaritettavaSuhteikko v) {
        if (v.PISTEITA == 0) return true;
        return Suhteikkoanalyysikirjasto.onJuuri(v, 1);
    }

    /**
     * Onko annettu väritettävä verkko yhtenäinen:
     * jokaisesta pisteestä löytyy reitti kaikkiin muihin pisteisiin,
     * mahdollisesti muiden pisteiden kautta.
     * Toisin sanoen, ovatko kaikki pisteet yhteydessä toisiinsa niin ettei
     * pisteitä tai pistejoukkoja ole eristyksissä.
     * Tämä metodi selvittää yhtenäisyyden tutkimalla,
     * onko kaikista pisteistä kulku pisteeseen 1.
     *
     * Huom verkoilla yhtenäisyys ja vahva yhtenäisyys ovat yhtäpitäviä.
     *
     * Aikavaativuus O(pisteidenLkm * (pisteidenLkm + yhteyksienLkm)),
     * tilavaativuus O(pisteidenLkm)
     * @param v analysoitava verkko
     * @return oliko yhtenäinen
     */

    public static boolean onYhtenainenKulkujenAvulla(VaritettavaSuhteikko v) {
        if (v.PISTEITA == 0 || v.PISTEITA == 1)
            return true;

        return kaikistaPisteistaOnKulkuPisteeseen(v, 1);
    }

        /**
         * Privaatti.
         * Selvittää onko kaikista muista verkon pisteistä kulku annettuun pisteeseen.
         */

        private static boolean kaikistaPisteistaOnKulkuPisteeseen(VaritettavaSuhteikko v, int piste) {
            for (int i=1; i<=v.PISTEITA; i++) { //O(pisteidenLkm)
                if (!Suhteikkoanalyysikirjasto.onKulkuLeveyshaulla(v, piste, i)) {
                    return false; //O(pisteidenLkm + yhteyksienLkm)
                }
            }
            return true;
        }

    /**
     * Yhtenäisyyden tutkiminen väritettävälle suhteikolle.
     * Aikavaativuus O(pisteidenLkm + yhteyksienLkm),
     * tilavaativuus O(pisteidenLkm)
     * @param v analysoitava verkko
     * @return oliko yhtenäinen
     */

    public static boolean onYhtenainenLeveyssuunteisellaHaulla(VaritettavaSuhteikko v) {
        v.varitaKaikki(Color.WHITE);
        Jono jono = new Jono();
        jono.lisaa(1);
        int vuorossa;
        IntSailio vuorossaOlevanSeuraajat;

        while (jono.alkioita() > 0) {
            vuorossa = jono.ota();
            v.varita(vuorossa, Color.BLACK);
            vuorossaOlevanSeuraajat = v.getSeuraajat(vuorossa);
            if (vuorossaOlevanSeuraajat != null)
                lisaaSeuraajatJonoonJosEivatVaria
                        (v, vuorossaOlevanSeuraajat.toIntArray(), jono, Color.BLACK);
        }

        return kaikkiPisteetOvatVaria(v, Color.BLACK);
    }

        /**
         * Privaatti.
         * Aikavaativuus O(seuraajienLkm) eli O(yhteyksienLkm)
         */

        private static void lisaaSeuraajatJonoonJosEivatVaria
                (VaritettavaSuhteikko v, int[] seuraajat, Jono j, Color vari) {
            for (int seur : seuraajat) {
                if (!v.getVari(seur).equals(vari))
                    j.lisaa(seur);
            }
        }

        /**
         * Privaatti.
         * Aikavaativuus O(pisteidenLkm)
         */

        private static boolean kaikkiPisteetOvatVaria(VaritettavaSuhteikko v, Color vari) {
            for (int i=1; i<=v.PISTEITA; i++)
                if (!v.getVari(i).equals(vari))
                    return false;
            return true;
        }

    /**
     * Onko annetusta pisteestä yhteys annettuun pistejoukkoon, eli johonkin
     * joukon pisteeseen.
     * Huomaa että metodi toimii ainoastaan järjestetylle taulukolle.
     * Aikavaativuus O(pisteidenLkm + yhteyksienLkm),
     * tilavaativuus O(1)
     * @param v analysoitava suhteikko
     * @param piste yhteyden lähtöpiste
     * @param jarjestettyPistejoukko joukko jonka pisteeseen yhteys olisi
     * @return oliko yhteyttä
     */

    public static boolean onYhteysJoukkoon
            (Suhteikko v, int piste, int[] jarjestettyPistejoukko) {
        int joukonKoko = jarjestettyPistejoukko.length;
        if (joukonKoko == 0) return false;

        if (v.seuraajienLkm(piste) == 0) //O(log n)
            return Tyokalut.etsiBinaarihaulla
                    (piste, 0, joukonKoko-1, jarjestettyPistejoukko);

        int[] seuraajat = v.getSeuraajat(piste).toIntArray(); //O(1) koska järjestetty
        int s = 0, p = 0;
        while (true) { //koko looppi O(seuraajienLkm + jarjestettyPistejoukkoLkm)
            if (seuraajat[s] < jarjestettyPistejoukko[p]) {
                if (s == seuraajat.length - 1) return false;
                s++;
            } else if (seuraajat[s] > jarjestettyPistejoukko[p]) {
                if (p == jarjestettyPistejoukko.length - 1) return false;
                p++;
            } else
                return true;
        }
    }
    
    /**
     * Pisteen aste on pisteestä lähtevien yhteyksien lukumäärä.
     * Aikavaativuus O(1),
     * tilavaativuus O(1)
     * @param v analysoitava verkko
     * @param piste verkon piste
     * @return pisteen aste verkossa
     */

    public static int pisteenAste(Suhteikko v, int piste) {
        IntSailio seuraajat = v.getSeuraajat(piste);    // O(1)
        if (seuraajat == null) return 0;
        return seuraajat.alkioita();                    // O(1)
    }
    
    /**
     * Onko annettu väritettävä verkko puu:
     * onko se yhtenäinen eikä siinä ole yhtään rengasta.
     * Ehdon tutkimisessa on käytetty Heikki Junnilan Verkot-kurssin
     * luentomateriaalin (mainittu lähteissä) lausetta IV 1.8, jonka mukaan
     * epätyhjälle verkolle ovat seuraavat kaksi ehtoa yhtäpitävät:
     * (1) verkko on puu, (2) verkko on yhtenäinen ja sen viivojen lukumäärä
     * on korkeintaan pisteiden lukumäärä miinus yksi.
     * Aikavaativuus O(pisteidenLkm + yhteyksienLkm),
     * tilavaativuus O(pisteidenLkm)
     * @param v analysoitava verkko
     * @return täyttääkö annettu verkko puuehdon
     */

    public static boolean tayttaaPuuehdon(VaritettavaSuhteikko v) {
        if (v.PISTEITA == 0) return true;

        if (!onYhtenainenLeveyssuunteisellaHaulla(v)) //O(pisteidenLkm + yhteyksienLkm)
            return false;
        return viivojaMaxPisteitaMiinusYksi(v);
    }
    
        /**
         * Käytetty Verkkojen luentomateriaalin lausetta II 2.3
         * jonka mukaan verkon viivojen lukumäärä on puolet 
         * kaikkien pisteiden asteiden summasta.
         * Aikavaativuus O(pisteidenLkm),
         * tilavaativuus O(1)
         * @param v
         * @return
         */

        private static boolean viivojaMaxPisteitaMiinusYksi(Suhteikko v) {
            int asteidenSumma = 0;
            for (int i=1; i<=v.PISTEITA; i++) 
                asteidenSumma += pisteenAste(v, i);
            
            double viivojenLkm = 0.5 * asteidenSumma;
            return viivojenLkm <= (v.PISTEITA - 1);
        }

    /**
     * Annetun pisteen yhtenäinen komponentti annetussa väritettävässä verkossa:
     * suppein mahdollinen joukko verkon pisteitä siten, että annettu
     * piste kuuluu joukkoon,
     * joukon virittämä aliverkko on yhtenäinen,
     * ja joukkoon ei voida lisätä pisteitä menettämättä yhtenäisyyttä.
     * Toisin sanoen, pienimmän sellaisen yhtenäisen aliverkon pisteet,
     * johon annettu piste kuuluu.
     * Aikavaativuus O(pisteidenLkm * yhteyksienLkm),
     * tilavaativuus O(pisteidenLkm)
     * @param v analysoitava verkko
     * @param piste piste jonka yhtenäinen komponentti haetaan
     * @return pisteen yhtenäisen komponentin pisteet satunnaisessa järjestyksessä
     */

    public static int[] yhtenainenKomponentti(VaritettavaSuhteikko v, int piste) {
        IntSailio seur = v.getSeuraajat(piste);
        if (seur == null || seur.toIntArray().length == 0) {
            int[] vastaus = new int[1];
            vastaus[0] = piste;
            return vastaus;
        }
        return yhtenainenKomponenttiIsommalle(v, piste);
    }

        /**
         * Privaatti.
         * Palauttaa annetun pisteen yhtenäisen komponentin pisteet.
         *
         */

        private static int[] yhtenainenKomponenttiIsommalle
                (VaritettavaSuhteikko v, int piste) {

            VenyvaTaulukko komponentti = new VenyvaTaulukkoVain1Esiintyma();

            v.varitaKaikki(Color.WHITE);
            Jono jono = new Jono();
            jono.lisaa(piste);
            int vuorossa;
            int[] vuorossaOlevanSeuraajat;

            while(jono.alkioita() > 0) { //O(pisteidenLkm * yhteyksienLkm)
                vuorossa = jono.ota();
                v.varita(vuorossa, Color.BLACK);
                vuorossaOlevanSeuraajat = v.getSeuraajat(vuorossa).toIntArray();
                if (vuorossaOlevanSeuraajat == null) continue;

                for (int seuraaja : vuorossaOlevanSeuraajat) {
                    if (! v.getVari(seuraaja).equals(Color.BLACK))
                        komponentti.lisaa(seuraaja);
                }
            }

            komponentti.lisaa(piste);
            return komponentti.toIntArray();
        }

}
