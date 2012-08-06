
package relaatiot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * RelaatioVenyvallaTaulukolla-luokan testiluokka.
 * Testit on nimetty niin ettei erillistä kommentointia tarvita.
 * @author silja
 */
public class RelaatioVenyvallaTaulukollaTest {

    private int lisattavaLoppupiste = 1;
    private int testattavanKoko = 19;
    RelaatioVenyvallaTaulukolla relaatio;

    public RelaatioVenyvallaTaulukollaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        relaatio = new RelaatioVenyvallaTaulukolla(testattavanKoko);
    }

    @After
    public void tearDown() {
    }

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
    public void lisaaYhteysPalauttaaTrueUudessa() {
        for (int i=1; i<=testattavanKoko; i++) {
            fail("kesken");
        }
    }

    

    @Test
    public void onYhteysKunLisattyYhdelle() {
        assertFalse(relaatio.onYhteys(1,1));
        //assertTrue(relaatio.lisaaYhteys(1,1));
        fail("kesken");
        assertTrue(relaatio.onYhteys(1,1));
    }

    @Test
    public void onYhteysKunLisattyKaikille() {
        for (int i=1; i<=testattavanKoko; i++) {
            assertFalse(relaatio.onYhteys(i,lisattavaLoppupiste));
        }
        for (int i=1; i<=testattavanKoko; i++) {
            fail("kesken");
            //assertTrue(relaatio.lisaaYhteys(i,lisattavaLoppupiste));
        }
        for (int i=1; i<=testattavanKoko; i++) {
            assertTrue(relaatio.onYhteys(i,lisattavaLoppupiste));
        }
    }
    
//    @Test
//    public void lisaaYhteysEiOnnistuKahtaKertaaSamalleYhdelle() {
//        assertTrue(relaatio.lisaaYhteys(1,testattavanKoko));
//        assertFalse(relaatio.lisaaYhteys(1,testattavanKoko));
//        assertFalse(relaatio.lisaaYhteys(1,testattavanKoko));
//    }

//    @Test
//    public void lisaaYhteysEiOnnistuKahtaKertaaSamalleKaikille() {
//        for (int i=1; i<=testattavanKoko; i++) {
//            assertTrue(relaatio.lisaaYhteys(i,lisattavaLoppupiste));
//        }
//        for (int i=1; i<=testattavanKoko; i++) {
//            assertFalse(relaatio.lisaaYhteys(i,lisattavaLoppupiste));
//        }
//        for (int i=1; i<=testattavanKoko; i++) {
//            assertFalse(relaatio.lisaaYhteys(i,lisattavaLoppupiste));
//        }
//    }

    @Test
    public void lisaaminenLisaaSeuraajajoukkoonKaikille() {
        for (int i=1; i<=testattavanKoko; i++) {
            assertFalse(relaatio.onYhteys(i,lisattavaLoppupiste));
        }
        for (int i=1; i<=testattavanKoko; i++) {
            //assertTrue(relaatio.lisaaYhteys(i,lisattavaLoppupiste));
        }
        for (int i=1; i<=testattavanKoko; i++) {
            //assertTrue(relaatio.getSeuraajat(i).onAlkio(lisattavaLoppupiste));
        }
        fail("kesken");
    }
    

}