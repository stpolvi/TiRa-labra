
package suhteikkoanalyysi;

import java.awt.Color;
import suhteikot.Suhteikko;
import suhteikot.VaritettavaSuhteikko;
import tietorakenteet.*;

/**
 * Kirjasto, joka tarjoaa suhteikkojen rakenteen tutkimiseen sopivia algoritmeja.
 * O-analyysiä javadocissa metodien kohdalla.
 * @author silja
 */
public class Suhteikkoanalyysikirjasto {

    private Suhteikkoanalyysikirjasto() {}
    
    /**
     * Täyttääkö suhteikko verkkoehdon: onko se onSilmukaton ja symmetrinen
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * @param suhteikko analysoitava suhteikko
     * @return täyttääkö verkkoehdon
     */

    public static boolean tayttaaVerkkoehdon(Suhteikko suhteikko) {
        return onSilmukaton(suhteikko) && symmetrinen(suhteikko);
    }   // O(pisteidenLkm log pisteidenLkm) + O(pisteidenLkm^2 log pisteidenLkm)

    /**
     * Onko suhteikko silmukaton:
     * mistään pisteestä ei ole itseensä yhteyttä.
     * Aikavaativuus O(pisteidenLkm log pisteidenLkm)
     * Tilavaativuus O(1)
     * @param suhteikko analysoitava suhteikko
     * @return oliko silmukaton
     */

    public static boolean onSilmukaton(Suhteikko suhteikko) {
        for (int i=1; i<=suhteikko.PISTEITA; i++) {     // O(pisteidenLkm)
            if (suhteikko.onYhteys(i, i))               // O(log pisteidenLkm)
                return false;
        }
        return true;
    }

    /**
     * MUUTETAAN TEHOKKAAMMAKSI
     * Nykyisellään aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * Onko suhteikko symmetrinen:
     * suhteikon kahden pisteen välillä on joko yhteys molempiin suuntiin
     * tai sitten ei lainkaan yhteyttä.
     * @param suhteikko analysoitava suhteikko
     * @return oliko symmetrinen
     */
    
    public static boolean symmetrinen(Suhteikko suhteikko) {
        for (int i=1; i<=suhteikko.PISTEITA; i++) {
            for (int j=1; j<=suhteikko.PISTEITA; j++) {
                if (!tyokalut.Tyokalut.ekvivalentit(suhteikko.onYhteys(i, j), suhteikko.onYhteys(j, i)))
                    return false; // O(log pisteidenLkm)
            }
        }
        return true;
    }

    public static boolean symmetrinenJokaEiToimi(Suhteikko s) {
        VenyvaTaulukko[] kaannetty = new VenyvaTaulukko[s.PISTEITA];
        int[] seuraajat;
        for (int i=1; i<= s.PISTEITA; i++) {
            seuraajat = s.getSeuraajat(i).toIntArray();
            if (seuraajat == null) continue;
            for (int j=1; j<=seuraajat.length; j++) {
                if (kaannetty[seuraajat[j]] == null)
                    kaannetty[seuraajat[j]] = new VenyvaTaulukko();
                kaannetty[seuraajat[j]].lisaa(i);
            }
        }

        for (int i=1; i<= s.PISTEITA; i++) {
            kaannetty[i].jarjesta();
            if (!kaannetty[i].equals(s.getSeuraajat(i))) return false;
        }

        return true;
    }

    /**
     * Lähtöaste on pisteestä lähtevien yhteyksien lukumäärä.
     * Aikavaativuus O(1)
     * @param s suhteikko
     * @param piste suhteikon piste
     * @return pisteen lähtöaste suhteikossa
     */

    public static int lahtoaste(Suhteikko s, int piste) {
        IntSailio seuraajat = s.getSeuraajat(piste);    // O(1)
        if (seuraajat == null) return 0;
        return seuraajat.alkioita();                    // O(1)
    }

    /**
     * Tuloaste on pisteeseen saapuvien yhteyksien lukumäärä.
     * Aikavaativuus O(pisteidenlkm log pisteidenLkm)
     * Tilavaativuus O(1)
     * @param s suhteikko
     * @param piste suhteikon piste
     * @return pisteen tuloaste suhteikossa
     */

    public static int tuloaste(Suhteikko s, int piste) {
        int vastaus = 0;
        for (int i=1; i<=s.PISTEITA; i++) {
            if (s.onYhteys(i, piste)) vastaus++;
        }
        return vastaus;
    }

    /**
     * Onko suhteikko säännöllinen verkko:
     * täyttääkö se verkkoehdon ja onko lisäksi kaikkien pisteiden aste
     * (eli lähtöaste) sama.
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * @param s analysoitava suhteikko
     */

    public static boolean saannollinenVerkko(Suhteikko s) {
        if (!tayttaaVerkkoehdon(s)) return false; // O(pisteidenLkm^2 log pisteidenLkm)
        if (s.PISTEITA == 0) return true;

        if (!kaikkiAsteetSamatEpatyhjassa(s)) return false; // O(pisteidenLkm)
        return true;
    }

    /**
     * Onko suhteikko täydellinen:
     * onko jokaisen kahden eri pisteen välillä yhteys ainakin toiseen suuntaan.
     * Pisteestä ei tarvitse olla itseensä yhteyttä.
     * Aikavaativuus: O(pisteidenLkm^2 log pisteidenLkm)
     * @param s analysoitava suhteikko
     * @return oliko täydellinen
     */

    public static boolean taydellinen(Suhteikko s) {
        for (int i=1; i<=s.PISTEITA; i++) {
            for (int j=i+1; j<=s.PISTEITA; j++) {
                if (!s.onYhteys(i, j) && !s.onYhteys(j, i)) return false;
            }
        }
        return true;
    }
    
    /**
     * Onko annettu suhteikko täydellinen verkko:
     * onko jokaisesta pisteestä yhteys kaikkiin muihin pisteisiin.
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * Tilavaativuus O(1)
     * @param s analysoitava suhteikko
     * @return oliko täydellinen verkko
     */

    public static boolean taydellinenVerkko(Suhteikko s) {
        if (!tayttaaVerkkoehdon(s)) return false; //O(pisteidenLkm^2 log pisteidenLkm)

        for (int i=1; i<=s.PISTEITA; i++) {
            for (int j=i+1; j<=s.PISTEITA; j++) {
                if (!s.onYhteys(i, j)) return false; //O(log pisteidenLkm)
            }
        }

        return true;
    }

    /**
     * Sama kuin TaydellinenVerkko mutta toteutus eri.
     * Aikavaativuus O(pisteidenLkm^2 log pisteidenLkm)
     * @param s analysoitava suhteikko
     * @return oliko täydellinen verkko
     */
    
    public static boolean taydellinenVerkkoSeuraajienAvulla(Suhteikko s) {
        if (!tayttaaVerkkoehdon(s)) return false;

        for (int i=1; i<=s.PISTEITA; i++) {
            if (s.seuraajienLkm(i) != s.PISTEITA -1) return false;
        }

        return true;
    }

    /**
     * TODO: renkaat
     * Rengas verkossa on vähintään kahden muun pisteen kautta kulkeva
     * kulku jostakin pisteestä itseensä.
     * Huom. kulun täytyy kulkea vähintään kolmen pisteen kautta.
     * @param s analysoitava suhteikko
     * @return onko suhteikossa rengas
     */

    public static boolean sisaltaaRenkaan(Suhteikko s) {
        

        throw new Error("kesken");
    }
    
    /**
     * Onko suhteikossa kulku pisteestä toiseen:
     * onko alkupisteestä reitti, mahdollisesti muiden pisteiden kautta,
     * yhteyksiä pitkin loppupisteeseen.
     * Aikavaativuus??
     * @param s analysoitava suhteikko
     * @param alkupiste piste josta lähdetään
     * @param loppupiste piste johon yritetään kulkea
     * @return onko kulkua olemassa
     */

    public static boolean onKulkuLeveyshaulla(Suhteikko s, int alkupiste, int loppupiste) {
        if (alkupiste == loppupiste) return true;
        VaritettavaSuhteikko v;
        try {
            v = (VaritettavaSuhteikko) s;
            return onKulkuLeveyshaullaVaritettavalle(v, alkupiste, loppupiste);
        } catch (ClassCastException e) {
            throw new Error("Kulun tutkimista ei toteutettu muulle kuin "
                    + "väritettävälle suhteikolle.");
        }
    }
    
    /**
     * Onko suhteikossa juuria. Käyttää pieninJuuriBruteForce-metodia.
     * Aikavaativuus sama kuin pieninjuuribruteforce
     * @param s analysoitava suhteikko
     * @return oliko suhteikolla juurta
     */

    public static boolean loytyyJuuriBruteForce(Suhteikko s) {
        return pieninJuuriBruteForce(s) > 0;
    }

    /**
     * Palauttaa suhteikon sen juuren nimen, jonka nimi on pienin luku.
     * Jos juuria ei ole, palauttaa -1.
     * Juuri on piste, josta on kulku suhteikon jokaiseen muuhun pisteeseen.
     * Aikavaativuus riippuu leveyshaun analyysistä, joka kesken
     * @param s analysoitava suhteikko
     * @return pienin juuri
     */

    public static int pieninJuuriBruteForce(Suhteikko s) {
        for (int i=1; i<=s.PISTEITA; i++) { // O(pisteidenLkm)
            if (onJuuri(s, i)) return i;    // O-analyysi kesken
        }
        return -1;
    }

    /**
     * Onko annettu piste suhteikon juuri:
     * onko siitä kulku kaikkiin muihin pisteisiin.
     * Aikavaativuus riippuu leveyshausta, jonka analyysi kesken
     * @param s analysoitava suhteikko
     * @param piste onko juuri
     * @return oliko juuri
     */

    public static boolean onJuuri(Suhteikko s, int piste) {
        for (int i=1; i<=s.PISTEITA; i++) {
            if (!onKulkuLeveyshaulla(s, piste, i)) return false;
        }
        return true;
    }

    /**
     * Täyttääkö suhteikko puuehdon:
     * onko se verkko, jossa ei ole renkaita, mutta jolla on juuri.
     * @param s analysoitava suhteikko
     * @return oliko puu
     */

    public static boolean tayttaaPuuehdon(Suhteikko s) {
        if (!tayttaaVerkkoehdon(s)) return false;
        if (sisaltaaRenkaan(s)) return false;
        if (!onYhtenainenVerkkoKulkujenAvulla(s)) return false;
        return true;
    }

    /**
     * Onko suhteikko yhtenäinen verkko: se on verkko, ja
     * jokaisesta pisteestä löytyy reitti kaikkiin muihin pisteisiin,
     * mahdollisesti muiden pisteiden kautta.
     * Toisin sanoen, ovatko kaikki pisteet yhteydessä toisiinsa niin ettei
     * pisteitä tai pistejoukkoja ole eristyksissä.
     * Tämä metodi selvittää yhtenäisyyden tutkimalla,
     * onko kaikista pisteistä kulku pisteeseen 1.
     * Aikavaativuus todella surkea
     * @param s analysoitava suhteikko
     * @return oliko yhtenäinen
     */

    public static boolean onYhtenainenVerkkoKulkujenAvulla(Suhteikko s) {
        if (!tayttaaVerkkoehdon(s))
            return false;

        if (s.PISTEITA == 0 || s.PISTEITA == 1)
            return true;

        return kaikistaPisteistaOnKulkuPisteeseen(s, 1);
    }

    /**
     * Onko suhteikko vahvasti yhtenäinen verkko:
     * verkolle yhtenäisyys ja vahva yhtenäisyys ovat yhtäpitäviä.
     * @param s analysoitava suhteikko
     * @return oliko (vahvasti) onYhtenainenVerkkoKulkujenAvulla verkko
     */

    public static boolean vahvastiYhtenainenVerkko(Suhteikko s) {
        if (!tayttaaVerkkoehdon(s)) return false;
        if (!onYhtenainenVerkkoKulkujenAvulla(s)) return false;
        return true;
    }

    /**
     * TODO vahva yhtenäisyys:
     */

    public static boolean vahvastiYhtenainen(Suhteikko s) {
        throw new Error("kesken");
    }

    /*
     * Privaattimetodit ---------------------------------------------
     */

    /**
     * Ovatko kaikkien pisteiden asteet samat.
     * Toimii samoin kuin saannollinenVerkko, mutta
     * mikäli metodille annetaan tyhjä verkko, virhe kaataa ohjelman.
     * Aikav. O(pisteidenLkm)
     * @param s
     * @return
     */

    private static boolean kaikkiAsteetSamatEpatyhjassa(Suhteikko s) {
        int ekanAste = lahtoaste(s, 1);
        for (int i=2; i<=s.PISTEITA; i++) {
            if (lahtoaste(s, i) != ekanAste) return false;
        }
        return true;
    }

    private static boolean kaikistaPisteistaOnKulkuPisteeseen(Suhteikko s, int piste) {
        for (int i=1; i<=s.PISTEITA; i++) {
            if (!onKulkuLeveyshaulla(s, piste, i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean onKulkuLeveyshaullaVaritettavalle
            (VaritettavaSuhteikko v, int alkupiste, int loppupiste) {

        v.varitaKaikki(Color.WHITE);
        Jono jono = new Jono();
        jono.lisaa(alkupiste);
        int vuorossa;
        IntSailio vuorossaOlevanSeuraajat;

        while (jono.alkioita() > 0) {
            vuorossa = jono.ota();
            v.varita(vuorossa, Color.BLACK);
            vuorossaOlevanSeuraajat = v.getSeuraajat(vuorossa);
            if (vuorossaOlevanSeuraajat == null) continue;

            for (int seuraaja : vuorossaOlevanSeuraajat.toIntArray()) {
                if (v.getVari(seuraaja).equals(Color.BLACK)) continue;
                if (seuraaja == loppupiste) return true;
                jono.lisaa(seuraaja);
            }
        }
        return false;
    }



}
