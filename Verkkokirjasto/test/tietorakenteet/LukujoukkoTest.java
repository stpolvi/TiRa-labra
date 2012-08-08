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
 * Lukujoukon testiluokka. Metodit on nimetty kuvaavasti siten ettei erillistä
 * kommentointia tarvittaisi.
 * @author silja
 */
public class LukujoukkoTest {

    LukujoukkoTehoton joukko;

    public LukujoukkoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        joukko = new LukujoukkoTehoton();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void uudessaEiAlkiota() {
        assertFalse(joukko.onAlkio(88));
        assertFalse(joukko.onAlkio(0));
        assertFalse(joukko.onAlkio(1));
        assertFalse(joukko.onAlkio(-19));
    }

   
    @Test
    public void lisaaUuteenYksiOnnistuu() {
        assertTrue(joukko.lisaa(592));
    }

    @Test
    public void lisaaKaksiSamaaEiOnnistu1Alkiolla() {
        assertTrue(joukko.lisaa(59));
        assertFalse(joukko.lisaa(59));
        assertFalse(joukko.lisaa(59));
    }

    @Test
    public void lisaaKaksiSamaaEiOnnistu900Alkiolla() {
        for (int i=0; i<900; i++) {
            assertTrue(joukko.lisaa(i));
        }
        for (int i=0; i<900; i++) {
            assertFalse(joukko.lisaa(i));
        }
        for (int i=0; i<900; i++) {
            assertFalse(joukko.lisaa(i));
        }
    }

   
    @Test
    public void poistaUudestaEiOnnistu() {
        assertFalse(joukko.poista(78));
        assertFalse(joukko.poista(6));
        assertFalse(joukko.poista(0));
        assertFalse(joukko.poista(-1));
    }
    @Test
    public void lisaaminenKasvattaaKokoa() {
        assertEquals(joukko.getKoko(), 0);

        assertTrue(joukko.lisaa(7));
        assertEquals(joukko.getKoko(), 1);

        assertTrue(joukko.lisaa(6));
        assertEquals(joukko.getKoko(), 2);
        
        assertTrue(joukko.lisaa(0));
        assertEquals(joukko.getKoko(), 3);
    }

    @Test
    public void samanLisaaminenEiKasvataKokoa() {
        assertEquals(joukko.getKoko(), 0);

        assertTrue(joukko.lisaa(7));
        assertEquals(joukko.getKoko(), 1);

        assertFalse(joukko.lisaa(7));
        assertEquals(joukko.getKoko(), 1);

        assertFalse(joukko.lisaa(7));
        assertEquals(joukko.getKoko(), 1);
    }

    
    @Test
    public void lisaamisenJalkeenOnAlkio1Alkiolla() {
        assertFalse(joukko.onAlkio(88));
        assertTrue(joukko.lisaa(88));
        assertTrue(joukko.onAlkio(88));
    }

    @Test
    public void lisaamisenJalkeenOnAlkio900Alkiolla() {
        for (int i=-100; i<800; i++) {
            assertFalse(joukko.onAlkio(i));
            assertTrue(joukko.lisaa(i));
        }
        for (int i=-100; i<800; i++) {
            assertTrue(joukko.onAlkio(i));
        }
    }

    @Test
    public void lisaamisenJaPoistonJalkeenEiOleAlkio1Alkiolla() {
        assertFalse(joukko.onAlkio(88));
        assertTrue(joukko.lisaa(88));
        assertTrue(joukko.poista(88));
        assertFalse(joukko.onAlkio(88));
    }

    @Test
    public void lisaamisenJaPoistonJalkeenEiOleAlkio900Alkiolla() {
        for (int i=-100; i<800; i++) {
            assertFalse(joukko.onAlkio(i));
            assertTrue(joukko.lisaa(i));
            assertTrue(joukko.onAlkio(i));
        }
        for (int i=-100; i<800; i++) {
            assertTrue(joukko.poista(i));
        }
        for (int i=-100; i<800; i++) {
            assertFalse(joukko.onAlkio(i));
        }
    }

}