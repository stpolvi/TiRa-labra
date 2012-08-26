
package suhteikkoanalyysi;

import java.awt.Color;
import suhteikot.Suhteikko;
import suhteikot.VaritettavaSuhteikko;
import tietorakenteetLuvuille.*;

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
        VenyvaTaulukkoVain1Esiintyma[] kaannetty = new VenyvaTaulukkoVain1Esiintyma[s.PISTEITA];
        int[] seuraajat;
        for (int i=1; i<= s.PISTEITA; i++) {
            seuraajat = s.getSeuraajat(i).toIntArray();
            if (seuraajat == null) continue;
            for (int j=1; j<=seuraajat.length; j++) {
                if (kaannetty[seuraajat[j]] == null)
                    kaannetty[seuraajat[j]] = new VenyvaTaulukkoVain1Esiintyma();
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
     * Onko suhteikossa kulku pisteestä toiseen:
     * onko alkupisteestä reitti, mahdollisesti muiden pisteiden kautta,
     * yhteyksiä pitkin loppupisteeseen.
     * Aikavaativuus O(1) jos alkupiste ja lopuppiste ovat samat,
     * O(log n) jos alkupisteestä on suoraan yhteys loppupisteeseen,
     * TODO O(???) muuten.
     * @param s analysoitava suhteikko
     * @param alkupiste piste josta lähdetään
     * @param loppupiste piste johon yritetään kulkea
     * @return onko kulkua olemassa
     */

    public static boolean onKulkuLeveyshaulla(Suhteikko s, int alkupiste, int loppupiste) {
        if (alkupiste == loppupiste) return true;
        if (s.onYhteys(alkupiste, loppupiste)) return true;
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
     * TODO yhtenäisyys:
     * Määritelmä: suhteikko on yhtenäinen, jos sen pisteiden joukon
     * jokaisella aidolla epätyhjällä osajoukolla P,
     * suhteikossa on nuoli P:stä tai nuoli P:hen.
     */

    public static boolean yhtenainen(Suhteikko s) {
        throw new Error("kesken");
    }

    /**
     * Tutkii leveyshaulla, onko annettu väritettävä suhteikko yhtenäinen.
     * Huom palauttaa false mikäli syötteeksi annetaan muu kuin
     * väritettävä suhteikko, vaikka se olisikin yhtenäinen.
     * Yhtenäisyys, määritelmä: suhteikko on yhtenäinen, jos sen pisteiden joukon
     * jokaisella aidolla epätyhjällä osajoukolla P,
     * suhteikossa on nuoli P:stä tai nuoli P:hen.
     */

    public static boolean yhtenainenVaritettavalle(Suhteikko s) {
        VaritettavaSuhteikko t;
        try {
            t = (VaritettavaSuhteikko) s;
        } catch (ClassCastException e) {
            return false;
        }
        
        throw new Error("Kesken");
    }

    /**
     * TODO vahva yhtenäisyys:
     * Määritelmä: suhteikko on vahvasti yhtenäinen, jos sen pisteiden joukon
     * jokaisella aidolla epätyhjällä osajoukolla P,
     * suhteikossa on nuoli P:stä ja nuoli P:hen.
     */

    public static boolean vahvastiYhtenainen(Suhteikko s) {
        throw new Error("kesken");
    }
    
    /**
     * TODO väritysluku
     * Kuinka monta eri väriä vähintään tarvitaan kun halutaan värittää
     * suhteikko niin ettei siinä ole kahta samanväristä pistettä vierekkäin.
     * @param s analysoitava suhteikko
     * @return suhteikon väritysluku
     */

    public static int varitysluku(Suhteikko s) {
        throw new Error("kesken");
    }

    /**
     * TODO komponentit
     * Annetun pisteen yhtenäinen komponentti annetussa suhteikossa:
     * suppein mahdollinen joukko suhteikon pisteitä siten, että annettu
     * piste kuuluu joukkoon,
     * joukon virittämä aliverkko on yhtenäinen,
     * ja joukkoon ei voida lisätä pisteitä menettämättä yhtenäisyyttä.
     * Toisin sanoen, pienimmän sellaisen yhtenäisen aliverkon pisteet,
     * johon annettu piste kuuluu.
     * @param s analysoitava suhteikko
     * @return pisteen yhtenäisen komponentin pisteet satunnaisessa järjestyksessä
     */

    public static int[] yhtenainenKomponentti(Suhteikko s, int piste) {
        IntSailio seur = s.getSeuraajat(piste);
        if (seur == null || seur.toIntArray().length == 0) {
            int[] vastaus = new int[1];
            vastaus[0] = piste;
            return vastaus;
        }
        VaritettavaSuhteikko v;
        try {
            v = (VaritettavaSuhteikko) s;
            return yhtenainenKomponenttiVaritettavalle(v, piste);
        } catch (ClassCastException e) {
            throw new Error("yhtenainen komponentti varittamattomalle kesken");
        }
    }

    /*
     * Privaattimetodit ---------------------------------------------
     */

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

    private static int[] yhtenainenKomponenttiVaritettavalle
            (VaritettavaSuhteikko v, int piste) {

        VenyvaTaulukko komponentti = new VenyvaTaulukko();

        v.varitaKaikki(Color.WHITE);
        Jono jono = new Jono();
        jono.lisaa(piste);
        int vuorossa;
        int[] vuorossaOlevanSeuraajat;

        while(jono.alkioita() > 0) {
            vuorossa = jono.ota();
            v.varita(vuorossa, Color.BLACK);
            vuorossaOlevanSeuraajat = v.getSeuraajat(vuorossa).toIntArray();
            if (vuorossaOlevanSeuraajat == null) continue;

            for (int seuraaja : vuorossaOlevanSeuraajat) {
                if (! v.getVari(seuraaja).equals(Color.BLACK))
                    komponentti.lisaa(seuraaja);
            }
        }

        return komponentti.toIntArray();
    }

}
