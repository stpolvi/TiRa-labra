
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
        s = suhteikot.LukusuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue("Ei ollut silmukaton.", Suhteikkoanalyysikirjasto.silmukaton(s));
        assertTrue("Ei ollut symmetrinen.", Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertTrue(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void taydellinenVerkkoTayttaaVerkkoehdon() {
        s = suhteikot.LukusuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue("Ei ollut silmukaton.", Suhteikkoanalyysikirjasto.silmukaton(s));
        assertTrue("Ei ollut symmetrinen.", Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertTrue(Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void yksisilmukkainenSymmetrinenEiTaytaVerkkoehtoa() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(1);
        r.lisaaYhteys(1, 1);
        s = new TavallinenSuhteikko(r);
        assertFalse("Piti olla silmukka.",
                Suhteikkoanalyysikirjasto.silmukaton(s));
        assertTrue("Piti olla symmetrinen.",
                Suhteikkoanalyysikirjasto.symmetrinen(s));
        assertFalse("Ei pidä täyttää verkkoehtoa.",
                Suhteikkoanalyysikirjasto.tayttaaVerkkoehdon(s));
    }

    @Test
    public void silmukatonEisymmetrinenEiTaytaVerkkoehtoa() {
        s = suhteikot.LukusuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue("Piti olla silmukaton.",
                Suhteikkoanalyysikirjasto.silmukaton(s));
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
        s = suhteikot.LukusuhteikkoTest.taydellinen5PisteinenVerkko();
        for (int i=1; i<=5; i++) {
            assertTrue("Tuloaste väärin.",
                    Suhteikkoanalyysikirjasto.tuloaste(s, i) == 4);
            assertTrue("Lähtöaste väärin.",
                    Suhteikkoanalyysikirjasto.lahtoaste(s, i) == 4);
        }
    }

    @Test
    public void asteetOikein1PisteisessaJossaSilmukka() {
        s = suhteikot.LukusuhteikkoTest.pisteita1Yhteys1_1();
        assertTrue("Lähtöaste väärin.",
                Suhteikkoanalyysikirjasto.lahtoaste(s, 1) == 1);
        assertTrue("Tuloaste väärin.",
                Suhteikkoanalyysikirjasto.tuloaste(s, 1) == 1);
    }

    @Test
    public void asteetOikein2PisteisessaJossaYhteys1_2() {
        s = suhteikot.LukusuhteikkoTest.pisteita2Yhteys1_2();
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
        s = suhteikot.LukusuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.saannollinenVerkko(s));
    }

    @Test
    public void taydellinenVerkkoOnSaannollinen() {
        s = suhteikot.LukusuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Suhteikkoanalyysikirjasto.saannollinenVerkko(s));
    }

    @Test
    public void josEiVerkkoNiinEiSaannollinenVaikkaLahtoasteetSamat() {
        s = suhteikot.LukusuhteikkoTest.pisteita1Yhteys1_1();
        assertFalse(Suhteikkoanalyysikirjasto.saannollinenVerkko(s));
    }

    @Test
    public void yhteydettomatOvatSaannollisia() {
        for (int i=1; i<=5; i++) {
            s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(i);
            assertTrue(Suhteikkoanalyysikirjasto.saannollinenVerkko(s));
        }
    }

    /*
     * Täydellinen-testit:
     */

    @Test
    public void tyhjaOnTaydellinen() {
        s = suhteikot.LukusuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinen() {
        s = suhteikot.LukusuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    @Test
    public void taydellinenSilmukallinenOnTaydellinen() {
        s = suhteikot.LukusuhteikkoTest.taydellinen4PisteinenJossaSilmukka();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    @Test
    public void kaksipisteinenEpasymmetrinenTaydellinenOnTaydellinen() {
        s = suhteikot.LukusuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinen() {
        s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinen(s));

        s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinen(s));
    }

    /*
     * TäydellinenVerkko-testit:
     */

    @Test
    public void tyhjaOnTaydellinenVerkko() {
        s = suhteikot.LukusuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenVerkko() {
        s = suhteikot.LukusuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));
    }

    @Test
    public void taydellinenSuhteikkoEiTaydellinenVerkkoJosEiVerkko() {
        s = suhteikot.LukusuhteikkoTest.taydellinen4PisteinenJossaSilmukka();
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenVerkko() {
        s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));

        s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkko(s));
    }

    /*
     * TaydellinenVerkkoSeuraajienAvulla-testit:
     */

    @Test
    public void tyhjaOnTaydellinenVerkkoSeuraajienAvulla() {
        s = suhteikot.LukusuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenVerkkoSeuraajienAvulla() {
        s = suhteikot.LukusuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));
    }

    @Test
    public void taydellinenSuhteikkoEiTaydellinenVerkkoSeuraajienAvullaJosEiVerkko() {
        s = suhteikot.LukusuhteikkoTest.taydellinen4PisteinenJossaSilmukka();
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenVerkkoSeuraajienAvulla() {
        s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));

        s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Suhteikkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(s));
    }

    /*
     * LöytyyJuuri-testit:
     */
    
    @Test
    public void yksipisteisellaOnJuuri() {
        s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(1);
        assertTrue(Suhteikkoanalyysikirjasto.loytyyJuuri(s));
    }

    @Test
    public void kaksipisteisellaJossaYhteys1_2OnJuuri() {
        s = suhteikot.LukusuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.loytyyJuuri(s));
    }

    @Test
    public void tyhjallaEiJuuria() {
        s = suhteikot.LukusuhteikkoTest.uusiTyhjaSuhteikko();
        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuri(s));
    }

    @Test
    public void useampipisteisellaJossaEiYhteyksiaEiJuuria() {
        s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuri(s));

        s = suhteikot.LukusuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Suhteikkoanalyysikirjasto.loytyyJuuri(s));
    }

    /*
     * JokinJuuri-testit:
     */

    @Test
    public void kaksipisteisellaJossaYhteys1_2AinoaJokinJuuri1() {
        s = suhteikot.LukusuhteikkoTest.pisteita2Yhteys1_2();
        assertTrue(Suhteikkoanalyysikirjasto.jokinJuuri(s) == 1);
        assertFalse(Suhteikkoanalyysikirjasto.jokinJuuri(s) == 2);
    }

    /*
     * TODO Renkaiden olemassaolo:
     */

    @Test
    public void tyhjassaEiRenkaita() {
        s = suhteikot.LukusuhteikkoTest.uusiTyhjaSuhteikko();

    }

    /*
     * TODO yhtenäisyys:
     */

    @Test
    public void yksipisteinenOnYhtenainen() {
        s = suhteikot.LukusuhteikkoTest.pisteita1Yhteys1_1();
        fail("kesken");
    }
    
}