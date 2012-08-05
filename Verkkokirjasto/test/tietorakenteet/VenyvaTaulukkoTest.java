/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silja
 */
public class VenyvaTaulukkoTest {

    VenyvaTaulukko taulukko;

    public VenyvaTaulukkoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        taulukko = new VenyvaTaulukko();
    }

    @After
    public void tearDown() {
    }

    /**
     * Apumetodi lisää taulukkoon luvut 1,...,n
     */

    void lisaaTaulukkoonNLukua(int n) {
        for (int i=1; i<=n; i++) {
            taulukko.lisaa(i);
        }
    }

    @Test
    public void lisaaYksiKerranJaEtsi() {
        taulukko.lisaa(5);
        taulukko.sort();
        assertTrue(taulukko.etsi(5));
    }

    @Test
    public void lisaaYksiKahdestiJaEtsi() {
        taulukko.lisaa(19);
        taulukko.lisaa(19);
        taulukko.sort();
        assertTrue(taulukko.etsi(19));
    }

    @Test
    public void lisaa10LukuaKerranJaEtsi() {
        lisaaTaulukkoonNLukua(10);
        taulukko.sort();
        for (int i=1; i<=10; i++) {
            assertTrue(taulukko.etsi(i));
        }
    }

    @Test
    public void lisaaYksiKerranJaPerakkaishae() {
        taulukko.lisaa(251);
        assertTrue(taulukko.etsiPerakkaishaulla(251));
    }

    @Test
    public void lisaaYksiKahdestiJaPerakkaishae() {
        taulukko.lisaa(1699);
        taulukko.lisaa(1699);
        assertTrue(taulukko.etsiPerakkaishaulla(1699));
    }

    @Test
    public void lisaa10LukuaKerranJaPerakkaishae() {
        lisaaTaulukkoonNLukua(10);
        for (int i=1; i<=10; i++) {
            assertTrue(taulukko.etsiPerakkaishaulla(i));
        }
    }

    @Test
    public void testEtsi() {
        fail("The test case is a prototype.");
    }

    @Test
    public void testEtsiPerakkaishaulla() {
        fail("The test case is a prototype.");
    }

    @Test
    public void testBinhae() {
        fail("The test case is a prototype.");
    }

    @Test
    public void testAlkioita() {
        fail("The test case is a prototype.");
    }

    @Test
    public void testToIntArray() {
        fail("The test case is a prototype.");
    }

}