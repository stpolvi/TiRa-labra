
package suhteikkoanalyysi;

import suhteikot.TavallinenSuhteikko;
import relaatiot.RelaatioVenyvallaTaulukolla;
import suhteikot.Suhteikko;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Suhteikkokirjaston testiluokka
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
        assertTrue("Ei ollut symmetrinen.", Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertTrue(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void taydellinenVerkkoTayttaaVerkkoehdon() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue("Ei ollut silmukaton.", Suhteikkoanalyysikirjasto.onSilmukaton(s));
        assertTrue("Ei ollut symmetrinen.", Suhteikkoanalyysikirjasto.symmetrinen(s));
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
                Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertFalse("Ei pidä täyttää verkkoehtoa.",
                Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void silmukatonEisymmetrinenEiTaytaVerkkoehtoa() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue("Piti olla silmukaton.",
                Suhteikkoanalyysikirjasto.onSilmukaton(s));
        assertFalse("Ei pidä olla symmetrinen.",
                Suhteikkoanalyysikirjasto.symmetrinen(s));
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
                    Suhteikkoanalyysikirjasto.tuloaste(s, i) == 4);
            assertTrue("Lähtöaste väärin.",
                    Suhteikkoanalyysikirjasto.lahtoaste(s, i) == 4);
        }
    }

    @Test
    public void asteetOikein1PisteisessaJossaSilmukka() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita1Yhteys1_1();
        assertTrue("Lähtöaste väärin.",
                Suhteikkoanalyysikirjasto.lahtoaste(s, 1) == 1);
        assertTrue("Tuloaste väärin.",
                Suhteikkoanalyysikirjasto.tuloaste(s, 1) == 1);
    }

    @Test
    public void asteetOikein2PisteisessaJossaYhteys1_2() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue("Lähtöaste väärin pisteellä 1.",
                Suhteikkoanalyysikirjasto.lahtoaste(s, 1) == 1);
        assertTrue("Tuloaste väärin pisteellä 1.",
                Suhteikkoanalyysikirjasto.tuloaste(s, 1) == 0);
        assertTrue("Lähtöaste väärin pisteellä 2.",
                Suhteikkoanalyysikirjasto.lahtoaste(s, 2) == 0);
        assertTrue("Tuloaste väärin pisteellä 2.",
                Suhteikkoanalyysikirjasto.tuloaste(s, 2) == 1);
    }

    /*
     * Säännöllinen-testit:
     */

    @Test
    public void tyhjaVerkkoOnSaannollinen() {
        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.saannollinenVerkko(s));
    }

    @Test
    public void taydellinenVerkkoOnSaannollinen() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Suhteikkoanalyysikirjasto.saannollinenVerkko(s));
    }

    @Test
    public void josEiVerkkoNiinEiSaannollinenVaikkaLahtoasteetSamat() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita1Yhteys1_1();
        assertFalse(Suhteikkoanalyysikirjasto.saannollinenVerkko(s));
    }

    @Test
    public void yhteydettomatOvatSaannollisia() {
        for (int i=1; i<=5; i++) {
            s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(i);
            assertTrue(Suhteikkoanalyysikirjasto.saannollinenVerkko(s));
        }
    }

    /*
     * Täydellinen-testit:
     */

    @Test
    public void tyhjaOnTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    @Test
    public void taydellinenSilmukallinenOnTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen4PisteinenJossaSilmukka();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    @Test
    public void kaksipisteinenEpasymmetrinenTaydellinenOnTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinen() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinen(s));

        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    /*
     * TäydellinenVerkko-testit:
     */

    @Test
    public void tyhjaOnTaydellinenVerkko() {
        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenVerkko() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));
    }

    @Test
    public void taydellinenSuhteikkoEiTaydellinenVerkkoJosEiVerkko() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen4PisteinenJossaSilmukka();
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenVerkko() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));

        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));
    }

    /*
     * TaydellinenVerkkoSeuraajienAvulla-testit:
     */

    @Test
    public void tyhjaOnTaydellinenVerkkoSeuraajienAvulla() {
        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenVerkkoSeuraajienAvulla() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));
    }

    @Test
    public void taydellinenSuhteikkoEiTaydellinenVerkkoSeuraajienAvullaJosEiVerkko() {
        s = suhteikot.TavallinenSuhteikkoTest.taydellinen4PisteinenJossaSilmukka();
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenVerkkoSeuraajienAvulla() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));

        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));
    }

    /*
     * LöytyyJuuri-testit:
     */
    
    @Test
    public void yksipisteisellaOnJuuri() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(1);
        assertTrue(Suhteikkoanalyysikirjasto.loytyyJuuri(s));
    }

    @Test
    public void kaksipisteisellaJossaYhteys1_2OnJuuri() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.loytyyJuuri(s));
    }

    @Test
    public void tyhjallaEiJuuria() {
        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuri(s));
    }

    @Test
    public void useampipisteisellaJossaEiYhteyksiaEiJuuria() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuri(s));

        s = suhteikot.TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuri(s));
    }

    /*
     * JokinJuuri-testit:
     */

    @Test
    public void kaksipisteisellaJossaYhteys1_2AinoaJokinJuuri1() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.jokinJuuri(s) == 1);
        assertFalse(Suhteikkoanalyysikirjasto.jokinJuuri(s) == 2);
    }

    /*
     * TODO Renkaiden olemassaolo:
     */

    @Test
    public void tyhjassaEiRenkaita() {
        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();

    }

    /*
     * TODO yhtenäisyys:
     */

    @Test
    public void yksipisteinenOnYhtenainen() {
        s = suhteikot.TavallinenSuhteikkoTest.pisteita1Yhteys1_1();
        fail("kesken");
    }
    
}