
package relaatiot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * RelaatioVenyvallaTaulukolla-luokan testiluokka.
 * Testit on nimetty niin ettei erillistä kommentointia tarvita.
 * @author silja
 */
public class RelaatioVenyvallaTaulukollaTest {

    private int lisattavaLoppupiste = 7;
    private int testattavanKoko = 19;
    RelaatioVenyvallaTaulukolla relaatio;

    @Before
    public void setUp() {
        relaatio = new RelaatioVenyvallaTaulukolla(testattavanKoko);
    }

    /*
     * TESTIT ------------------------------------------------------
     */
    
    @Test
    public void tyhjaRelaatioLuotavissa() {
        assertNotNull(new RelaatioVenyvallaTaulukolla(0));
    }

    @Test
    public void seuraajatNullUudessa() {
        for (int i=1; i<=testattavanKoko; i++) {
            assertNull(relaatio.getSeuraajat(i));
        }
    }

    @Test
    public void eiYhteyksiaUudessa() {
        for (int i=1; i<=testattavanKoko; i++) {
            for (int j=1; j<=testattavanKoko; j++) {
                assertFalse(relaatio.onYhteys(i, j));
            }
        }
    }

    @Test
    public void onYhteysKunLisattyYhdelle() {
        assertFalse(relaatio.onYhteys(1,1));
        relaatio.lisaaYhteys(1,1);
        assertTrue(relaatio.onYhteys(1,1));
    }

    @Test
    public void onYhteysKunLisattyKaikille() {
        for (int i=1; i<=testattavanKoko; i++) {
            assertFalse(relaatio.onYhteys(i,lisattavaLoppupiste));
        }
        for (int i=1; i<=testattavanKoko; i++) {
            relaatio.lisaaYhteys(i,lisattavaLoppupiste);
        }
        for (int i=1; i<=testattavanKoko; i++) {
            assertTrue(relaatio.onYhteys(i,lisattavaLoppupiste));
        }
    }
    
    @Test
    public void lisaaminenLisaaSeuraajajoukkoonYhdelle() {
        assertFalse(relaatio.onYhteys(1,lisattavaLoppupiste));
        relaatio.lisaaYhteys(1,lisattavaLoppupiste);
        assertTrue(relaatio.getSeuraajat(1).etsi(lisattavaLoppupiste));
    }

    @Test
    public void lisaaminenLisaaSeuraajajoukkoonKaikille() {
        for (int i=1; i<=testattavanKoko; i++) {
            assertFalse(relaatio.onYhteys(i,lisattavaLoppupiste));
        }
        for (int i=1; i<=testattavanKoko; i++) {
            relaatio.lisaaYhteys(i,lisattavaLoppupiste);
        }
        for (int i=1; i<=testattavanKoko; i++) {
            assertTrue(relaatio.getSeuraajat(i).etsi(lisattavaLoppupiste));
        }
    }
    

}