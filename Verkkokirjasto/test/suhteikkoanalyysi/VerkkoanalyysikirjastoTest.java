
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
     * TODO puuehtotestit:
     */

//    @Test
//    public void tyhjaOnPuu() {
//        fail("kesken");
//    }

}