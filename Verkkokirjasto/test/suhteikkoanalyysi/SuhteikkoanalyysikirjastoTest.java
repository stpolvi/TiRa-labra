
package suhteikkoanalyysi;

import suhteikot.VaritettavaSuhteikkoTest;
import tyokalut.Tyokalut;
import suhteikot.TavallinenSuhteikkoTest;
import suhteikot.TavallinenSuhteikko;
import relaatiot.RelaatioVenyvallaTaulukolla;
import suhteikot.Suhteikko;
import org.junit.Test;
import suhteikot.VaritettavaSuhteikko;
import static org.junit.Assert.*;

/**
 * Suhteikkoanalyysikirjaston testiluokka
 * @author silja
 */

public class SuhteikkoanalyysikirjastoTest {

    Suhteikko s;

    /*
     * Testit --------------------------------------------------------
     */

    /**
     * TäyttääVerkkoEhdon-testit:
     */

    @Test
    public void tyhjaSuhteikkoTayttaaVerkkoehdon() {
        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue("Ei ollut silmukaton.", Suhteikkoanalyysikirjasto.onSilmukaton(s));
        assertTrue("Ei ollut symmetrinen.", Suhteikkoanalyysikirjasto.onSymmetrinen(s));
        assertTrue(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void taydellinenVerkkoTayttaaVerkkoehdon() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue("Ei ollut silmukaton.", Suhteikkoanalyysikirjasto.onSilmukaton(s));
        assertTrue("Ei ollut symmetrinen.", Suhteikkoanalyysikirjasto.onSymmetrinen(s));
        assertTrue(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void yksisilmukkainenSymmetrinenEiTaytaVerkkoehtoa() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(1);
        r.lisaaYhteys(1, 1);
        s = new TavallinenSuhteikko(r);
        assertFalse("Piti olla silmukka.",
                Suhteikkoanalyysikirjasto.onSilmukaton(s));
        assertTrue("Piti olla symmetrinen.",
                Suhteikkoanalyysikirjasto.onSymmetrinen(s));
        assertFalse("Ei pidä täyttää verkkoehtoa.",
                Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void silmukatonEisymmetrinenEiTaytaVerkkoehtoa() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue("Piti olla silmukaton.",
                Suhteikkoanalyysikirjasto.onSilmukaton(s));
        assertFalse("Ei pidä olla symmetrinen.",
                Suhteikkoanalyysikirjasto.onSymmetrinen(s));
        assertFalse("Ei pidä täyttää verkkoehtoa.",
                Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    /*
     * Lähtö- ja tuloaste-testit:
     */

    @Test
    public void taydellisessa5PisteisessaVerkossaKaikkiAsteet4() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        for (int i=1; i<=5; i++) {
            assertTrue("Tuloaste väärin.",
                    Suhteikkoanalyysikirjasto.pisteenTuloaste(s, i) == 4);
            assertTrue("Lähtöaste väärin.",
                    Suhteikkoanalyysikirjasto.pisteenLahtoaste(s, i) == 4);
        }
    }

    @Test
    public void asteetOikein1PisteisessaJossaSilmukka() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita1Yhteys1_1();
        assertTrue("Lähtöaste väärin.",
                Suhteikkoanalyysikirjasto.pisteenLahtoaste(s, 1) == 1);
        assertTrue("Tuloaste väärin.",
                Suhteikkoanalyysikirjasto.pisteenTuloaste(s, 1) == 1);
    }

    @Test
    public void asteetOikein2PisteisessaJossaYhteys1_2() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue("Lähtöaste väärin pisteellä 1.",
                Suhteikkoanalyysikirjasto.pisteenLahtoaste(s, 1) == 1);
        assertTrue("Tuloaste väärin pisteellä 1.",
                Suhteikkoanalyysikirjasto.pisteenTuloaste(s, 1) == 0);
        assertTrue("Lähtöaste väärin pisteellä 2.",
                Suhteikkoanalyysikirjasto.pisteenLahtoaste(s, 2) == 0);
        assertTrue("Tuloaste väärin pisteellä 2.",
                Suhteikkoanalyysikirjasto.pisteenTuloaste(s, 2) == 1);
    }

    /*
     * Täydellinen-testit:
     */

    @Test
    public void tyhjaOnTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.onTaydellinen(s));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Suhteikkoanalyysikirjasto.onTaydellinen(s));
    }

    @Test
    public void taydellinenSilmukallinenOnTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen4PisteinenJossaSilmukka();
        assertTrue(Suhteikkoanalyysikirjasto.onTaydellinen(s));
    }

    @Test
    public void kaksipisteinenEpasymmetrinenTaydellinenOnTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.onTaydellinen(s));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Suhteikkoanalyysikirjasto.onTaydellinen(s));

        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Suhteikkoanalyysikirjasto.onTaydellinen(s));
    }

    @Test
    public void kolmepisteinenJossaEiKaikkiaYhteyksiaEiTaydellinen() {
        s = TavallinenSuhteikkoTest.pisteita3Yhteys2_1ja3_3();
        assertFalse(Suhteikkoanalyysikirjasto.onTaydellinen(s));
    }

    /*
     * LöytyyJuuri-testit tavalliselle suhteikolle/verkolle:
     */
//
//    @Test
//    public void yksipisteisellaOnJuuri() {
//        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(1);
//        assertTrue(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//    }
//
//    @Test
//    public void kaksipisteisellaJossaYhteys1_2OnJuuri() {
//        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
//        assertTrue(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//    }
//
//    @Test
//    public void tyhjallaEiJuuria() {
//        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
//        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//    }
//
//    @Test
//    public void useampipisteisellaJossaEiYhteyksiaEiJuuria() {
//        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
//        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//
//        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
//        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//    }

    /*
     * LöytyyJuuri-testit väritettävälle suhteikolle/verkolle:
     */
//
//    @Test
//    public void yksipisteisellaOnJuuriVaritettavalle() {
//        s = suhteikot.VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(1);
//        assertTrue(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//    }
//
//    @Test
//    public void kaksipisteisellaJossaYhteys1_2OnJuuriVaritettavalle() {
//        s = suhteikot.VaritettavaSuhteikkoTest.pisteita2Yhteys1_2();
//        assertTrue("Yhteys väliltä 1-2 puuttuu.", s.onYhteys(1, 2));
//
//        assertTrue("Leveyshaulla ei löytynyt kulkua 1-2.",
//                Suhteikkoanalyysikirjasto.onKulkuLeveyshaulla(s, 1, 2));
//        assertTrue("Pisteen 1 piti olla juuri.",
//                Suhteikkoanalyysikirjasto.onJuuri(s, 1));
//        assertTrue("Pienin juuri piti olla 1.",
//                Suhteikkoanalyysikirjasto.pieninJuuriBruteForce(s) == 1);
//
//        assertTrue(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//    }
//
//    @Test
//    public void tyhjallaEiJuuriaVaritettavalle() {
//        s = suhteikot.VaritettavaSuhteikkoTest.uusiTyhjaSuhteikko();
//        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//    }
//
//    @Test
//    public void useampipisteisellaJossaEiYhteyksiaEiJuuriaVaritettavalle() {
//        s = suhteikot.VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(2);
//        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//
//        s = suhteikot.VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(9);
//        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//    }
//
//    @Test
//    public void taydellisellaOnJuuri() {
//        s = suhteikot.VaritettavaSuhteikkoTest.uusiSuhteikkoTaydellinenN(6);
//        assertTrue(Suhteikkoanalyysikirjasto.loytyyJuuriBruteForce(s));
//    }
//

    /*
     * JokinJuuri-testit tavalliselle suhteikolle/verkolle:
     */

//    @Test
//    public void kaksipisteisellaJossaYhteys1_2AinoaJokinJuuri1() {
//        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
//        assertTrue(Suhteikkoanalyysikirjasto.pieninJuuriBruteForce(s) == 1);
//        assertFalse(Suhteikkoanalyysikirjasto.pieninJuuriBruteForce(s) == 2);
//    }

    /*
     * JokinJuuri-testit väritettävälle suhteikolle/verkolle:
     */

    @Test
    public void kaksipisteisellaJossaYhteys1_2PieninJuuri1Varitettavalle() {
        VaritettavaSuhteikko c = suhteikot.VaritettavaSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.pieninJuuriBruteForce(c) == 1);
    }
    
    /*
     * EristetytPisteet-testit:
     */

    @Test
    public void taydellisessaEiEristettyjaPisteita() {
        s = TavallinenSuhteikkoTest.uusiSuhteikkoTaydellinenN(6);
        assertTrue(Tyokalut.onSamaTaulukko
                (Suhteikkoanalyysikirjasto.eristetytPisteet(s), new int[0]));
    }

    @Test
    public void kaksiPisteisessaJossaYhteysToiseenSuuntaanEiEristettyjaPisteita() {
        s = TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Tyokalut.onSamaTaulukko
                (Suhteikkoanalyysikirjasto.eristetytPisteet(s), new int[0]));
    }

    @Test
    public void yhteydettomassaKaikkiPisteetEristettyja() {
        s = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(7);
        int[] oikeatEristetyt = Tyokalut.luoTaulukko1_N(7);
        assertTrue(Tyokalut.onSamaTaulukko
                (Suhteikkoanalyysikirjasto.eristetytPisteet(s), oikeatEristetyt));
    }

    @Test
    public void onEristettyPisteVaikkaYhteysItseensa() {
        s = TavallinenSuhteikkoTest.pisteita3Yhteys2_1ja3_3();
        int[] oikeatEristetyt = new int[1];
        oikeatEristetyt[0] = 3;
        assertTrue(Tyokalut.onSamaTaulukko
                (Suhteikkoanalyysikirjasto.eristetytPisteet(s), oikeatEristetyt));
    }

    /*
     * onKulkuLeveyshaulla-testit:
     */

    @Test
    public void yhteydettomassaOnKulkuLeveyshaullaPisteestaItseensa() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(13);
        for (int i=1; i<=c.PISTEITA; i++)
            assertTrue(Suhteikkoanalyysikirjasto.onKulkuLeveyshaulla(c, i, i));
    }
    
    @Test
    public void yhteydettomassaEiKulkujaLeveyshaullaPisteistaToisiin() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(13);
        for (int i=1; i<c.PISTEITA; i++) {
            assertFalse(Suhteikkoanalyysikirjasto.onKulkuLeveyshaulla(c, i, 13));
            assertFalse(Suhteikkoanalyysikirjasto.onKulkuLeveyshaulla(c, 13, i));
        }
    }

    @Test
    public void kaksipisteisessaOnKulkuLeveyshaullaPisteestaSeuraajaansa() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.onKulkuLeveyshaulla(c, 1, 2));
    }

    @Test
    public void onKulkuLeveyshaullaVaikkaEiYhteytta() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(6);
        r.lisaaYhteys(3, 4);
        r.lisaaYhteys(3, 6);
        r.lisaaYhteys(6, 5);
        r.lisaaYhteys(5, 1);
        VaritettavaSuhteikko c = new VaritettavaSuhteikko(r);
        assertTrue(Suhteikkoanalyysikirjasto.onKulkuLeveyshaulla(c, 3, 1));
    }
    
}