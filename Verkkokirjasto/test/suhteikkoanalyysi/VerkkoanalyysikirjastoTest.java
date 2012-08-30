
package suhteikkoanalyysi;

import suhteikot.VaritettavaSuhteikkoTest;
import suhteikot.TavallinenSuhteikkoTest;
import suhteikot.Suhteikko;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class VerkkoanalyysikirjastoTest {

    private Suhteikko v;

    /*
     * Säännöllinen-testit:
     */

    @Test
    public void tyhjaVerkkoOnSaannollinen() {
        v = TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Verkkoanalyysikirjasto.saannollinen(v));
    }

    @Test
    public void taydellinenVerkkoOnSaannollinen() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.saannollinen(v));
    }

    @Test
    public void yhteydettomatOvatSaannollisia() {
        for (int i=1; i<=5; i++) {
            v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(i);
            assertTrue(Verkkoanalyysikirjasto.saannollinen(v));
        }
    }

    /*
     * Täydellinen-testit:
     */

    @Test
    public void tyhjaOnTaydellinenVerkko() {
        v = TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Verkkoanalyysikirjasto.taydellinen(v));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenVerkko() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.taydellinen(v));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenVerkko() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Verkkoanalyysikirjasto.taydellinen(v));

        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Verkkoanalyysikirjasto.taydellinen(v));
    }

    /*
     * TaydellinenSeuraajienAvulla-testit:
     */

    @Test
    public void tyhjaOnTaydellinenSeuraajienAvulla() {
        v = TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Verkkoanalyysikirjasto.taydellinenSeuraajienAvulla(v));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenSeuraajienAvulla() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.taydellinenSeuraajienAvulla(v));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenSeuraajienAvulla() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Verkkoanalyysikirjasto.taydellinenSeuraajienAvulla(v));

        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Verkkoanalyysikirjasto.taydellinenSeuraajienAvulla(v));
    }

    /*
     * TaydellinenSaannollisyydenAvulla-testit:
     */

    @Test
    public void tyhjaOnTaydellinenSaannollisyydenAvulla() {
        v = TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Verkkoanalyysikirjasto.taydellinenSaannollisyydenAvulla(v));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenSaannollisyydenAvulla() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.taydellinenSaannollisyydenAvulla(v));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenSaannollisyydenAvulla() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Verkkoanalyysikirjasto.taydellinenSaannollisyydenAvulla(v));

        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Verkkoanalyysikirjasto.taydellinenSaannollisyydenAvulla(v));
    }

    /*
     * TODO Renkaiden olemassaolo:
     */
//
//    @Test
//    public void tyhjassaEiRenkaita() {
//        s = suhteikot.TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
//
//    }

    /*
     * yhtenäisyystestit: kulkujen avulla
     */

    @Test
    public void yksipisteinenOnYhtenainenVaritettavalle() {
        v = VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(1);
        assertTrue(Verkkoanalyysikirjasto.onYhtenainenKulkujenAvulla(v));
    }

    @Test
    public void taydellinenVerkkoYhtenainen() {
        v = VaritettavaSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.onYhtenainenKulkujenAvulla(v));
    }

    /*
     * Hallitseva-testit:
     */

    @Test
    public void kokoVerkkoOnHallitsevaPistejoukko() {
        int koko = 19;
        v = TavallinenSuhteikkoTest.uusiSuhteikkoTaydellinenN(koko);
        int[] pistejoukko = new int[koko];
        for (int i=0; i<pistejoukko.length; i++) 
            pistejoukko[i] = i; //huom taulukko on nyt järjestyksessä
        assertTrue(Verkkoanalyysikirjasto.hallitseva(v, pistejoukko));
    }

    @Test
    public void tyhjaPistejoukkoEiHallitseva() {
        int koko = 8;
        v = TavallinenSuhteikkoTest.uusiSuhteikkoTaydellinenN(koko);
        int[] pistejoukko = new int[0];
        assertFalse(Verkkoanalyysikirjasto.hallitseva(v, pistejoukko));
    }

    @Test
    public void yhteydettomassaAitoOsajoukkoEiHallitseva() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(13);
        int[] pistejoukko = new int[4];
        for (int i=0; i<pistejoukko.length; i++)
            pistejoukko[i] = i+2; //huom valmiiksi järjestyksessä
        assertFalse(Verkkoanalyysikirjasto.hallitseva(v, pistejoukko));
    }

    /*
     * onYhteysJoukkoon -testit:
     */

    @Test
    public void yhteydettomassaEiYhteysOsajoukkoon() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(6);
        int[] joukko = new int[5];
        for (int i = 0; i< joukko.length; i++)
            joukko[i] = i+1;

        assertFalse(Verkkoanalyysikirjasto.onYhteysJoukkoon(v, 6, joukko));
    }

    @Test
    public void yhteydettomassaYhteysOsajoukkoonItseJoukonAlkiosta() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(6);
        int[] joukko = new int[3];
        for (int i = 0; i< joukko.length; i++)
            joukko[i] = i+2;

        assertTrue(Verkkoanalyysikirjasto.onYhteysJoukkoon(v, 2, joukko));
        assertTrue(Verkkoanalyysikirjasto.onYhteysJoukkoon(v, 3, joukko));
        assertTrue(Verkkoanalyysikirjasto.onYhteysJoukkoon(v, 4, joukko));
    }

    @Test
    public void taydellisessaAinaYhteysJoukkoon() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        int[] joukko = new int[3];
        for (int i = 0; i < joukko.length; i++)
            joukko[i] = i+2;

        for (int i=1; i<=v.PISTEITA; i++)
            assertTrue(Verkkoanalyysikirjasto.onYhteysJoukkoon(v, i, joukko));
    }

    public void tyhjaanJoukkoonEiYhteytta() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        int[] joukko = new int[0];
        for (int i=1; i<=v.PISTEITA; i++)
            assertFalse(Verkkoanalyysikirjasto.onYhteysJoukkoon(v, i, joukko));
    }

}