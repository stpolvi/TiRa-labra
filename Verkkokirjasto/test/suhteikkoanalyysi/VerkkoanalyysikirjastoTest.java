
package suhteikkoanalyysi;

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
     * TaydellinenVerkkoSeuraajienAvulla-testit:
     */

    @Test
    public void tyhjaOnTaydellinenVerkkoSeuraajienAvulla() {
        v = TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Verkkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(v));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenVerkkoSeuraajienAvulla() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(v));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenVerkkoSeuraajienAvulla() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Verkkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(v));

        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Verkkoanalyysikirjasto.taydellinenVerkkoSeuraajienAvulla(v));
    }


}