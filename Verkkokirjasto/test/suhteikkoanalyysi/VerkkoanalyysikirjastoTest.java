
package suhteikkoanalyysi;

import java.awt.Color;
import relaatiot.RelaatioVenyvallaTaulukolla;
import tyokalut.Tyokalut;
import suhteikot.VaritettavaSuhteikko;
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
        assertTrue(Verkkoanalyysikirjasto.onSaannollinen(v));
    }

    @Test
    public void taydellinenVerkkoOnSaannollinen() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.onSaannollinen(v));
    }

    @Test
    public void yhteydettomatOvatSaannollisia() {
        for (int i=1; i<=5; i++) {
            v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(i);
            assertTrue(Verkkoanalyysikirjasto.onSaannollinen(v));
        }
    }

    /*
     * Täydellinen-testit:
     */

    @Test
    public void tyhjaOnTaydellinenVerkko() {
        v = TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Verkkoanalyysikirjasto.onTaydellinen(v));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenVerkko() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.onTaydellinen(v));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenVerkko() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Verkkoanalyysikirjasto.onTaydellinen(v));

        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Verkkoanalyysikirjasto.onTaydellinen(v));
    }

    /*
     * TaydellinenSeuraajienAvulla-testit:
     */

    @Test
    public void tyhjaOnTaydellinenSeuraajienAvulla() {
        v = TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Verkkoanalyysikirjasto.onTaydellinenSeuraajienAvulla(v));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenSeuraajienAvulla() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.onTaydellinenSeuraajienAvulla(v));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenSeuraajienAvulla() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Verkkoanalyysikirjasto.onTaydellinenSeuraajienAvulla(v));

        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Verkkoanalyysikirjasto.onTaydellinenSeuraajienAvulla(v));
    }

    /*
     * TaydellinenSaannollisyydenAvulla-testit:
     */

    @Test
    public void tyhjaOnTaydellinenSaannollisyydenAvulla() {
        v = TavallinenSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Verkkoanalyysikirjasto.onTaydellinenSaannollisyydenAvulla(v));
    }

    @Test
    public void taydellinenVerkkoOnTaydellinenSaannollisyydenAvulla() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.onTaydellinenSaannollisyydenAvulla(v));
    }

    @Test
    public void yhteydetonUseampipisteinenEiTaydellinenSaannollisyydenAvulla() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(2);
        assertFalse(Verkkoanalyysikirjasto.onTaydellinenSaannollisyydenAvulla(v));

        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(9);
        assertFalse(Verkkoanalyysikirjasto.onTaydellinenSaannollisyydenAvulla(v));
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
    public void yksipisteinenOnYhtenainenKulkujenAvulla() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(1);
        assertTrue(Verkkoanalyysikirjasto.onYhtenainenKulkujenAvulla(c));
    }

    @Test
    public void taydellinenVerkkoYhtenainenKulkujenAvulla() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.onYhtenainenKulkujenAvulla(c));
    }

    /*
     * yhtenäisyystestit: juurien avulla
     */

    @Test
    public void yksipisteinenOnYhtenainenJuurienAvulla() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(1);
        assertTrue(Verkkoanalyysikirjasto.onYhtenainenJuurienAvulla(c));
    }

    @Test
    public void taydellinenVerkkoYhtenainenJuurienAvulla() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.onYhtenainenJuurienAvulla(c));
    }

    /*
     * yhtenäisyystestit: varitettavalle
     */

    @Test
    public void yksipisteinenOnYhtenainenVaritettavalle() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(1);
        assertTrue(Verkkoanalyysikirjasto.onYhtenainenLeveyssuunteisellaHaulla(c));
    }

    @Test
    public void taydellinenVerkkoYhtenainenVaritettavalle() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Verkkoanalyysikirjasto.onYhtenainenLeveyssuunteisellaHaulla(c));
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
        assertTrue(Verkkoanalyysikirjasto.onHallitsevaPistejoukko(v, pistejoukko));
    }

    @Test
    public void tyhjaPistejoukkoEiHallitseva() {
        int koko = 8;
        v = TavallinenSuhteikkoTest.uusiSuhteikkoTaydellinenN(koko);
        int[] pistejoukko = new int[0];
        assertFalse(Verkkoanalyysikirjasto.onHallitsevaPistejoukko(v, pistejoukko));
    }

    @Test
    public void yhteydettomassaAitoOsajoukkoEiHallitseva() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(13);
        int[] pistejoukko = new int[4];
        for (int i=0; i<pistejoukko.length; i++)
            pistejoukko[i] = i+2; //huom valmiiksi järjestyksessä
        assertFalse(Verkkoanalyysikirjasto.onHallitsevaPistejoukko(v, pistejoukko));
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

    /*
     * EristetytPisteet-testit:
     */

    @Test
    public void taydellisessaEiEristettyjaPisteita() {
        v = TavallinenSuhteikkoTest.taydellinen5PisteinenVerkko();
        assertTrue(Tyokalut.onSamaTaulukko
                (Verkkoanalyysikirjasto.eristetytPisteet(v), new int[0]));
    }

    @Test
    public void yhteydettomassaKaikkiPisteetEristettyja() {
        v = TavallinenSuhteikkoTest.pisteitaNEiYhteyksia(7);
        int[] oikeatEristetyt = Tyokalut.luoTaulukko1_N(7);
        assertTrue(Tyokalut.onSamaTaulukko
                (Verkkoanalyysikirjasto.eristetytPisteet(v), oikeatEristetyt));
    }

    /*
     * Puuehtotestit:
     */

    @Test
    public void tyhjaTayttaaPuuehdon() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.uusiTyhjaSuhteikko();
        assertTrue(Verkkoanalyysikirjasto.tayttaaPuuehdon(c));
    }

    @Test
    public void yksipisteinenTayttaaPuuehdon() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(1);
        assertTrue(Verkkoanalyysikirjasto.tayttaaPuuehdon(c));
    }

    @Test
    public void taydellinenKaksipisteinenTayttaaPuuehdon() {
        RelaatioVenyvallaTaulukolla r = new RelaatioVenyvallaTaulukolla(2);
        r.lisaaYhteys(1, 2);
        r.lisaaYhteys(2, 1);
        VaritettavaSuhteikko c = new VaritettavaSuhteikko(r);
        assertTrue(Verkkoanalyysikirjasto.tayttaaPuuehdon(c));
    }

    /*
     * yhtenainenKomponentti-testit:
     */

    @Test
    public void yhteydettomassaYhtenainenKomponenttiAinaVainYksiPiste() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.pisteitaNEiYhteyksia(8);
        int[] oikeaKomponentti = new int[1];
        for (int i=1; i<=c.PISTEITA; i++) {
            oikeaKomponentti[0] = i;
            assertTrue(Tyokalut.onSamaTaulukko
                    (Verkkoanalyysikirjasto.yhtenainenKomponentti(c, i), oikeaKomponentti));
        }
    }

    @Test
    public void taydellisessaYhtenainenKomponenttiKokoVerkko() {
        VaritettavaSuhteikko c = VaritettavaSuhteikkoTest.taydellinen5PisteinenVerkko();
        int[] oikeaKomponentti = Tyokalut.luoTaulukko1_N(5);
        int[] vaitettyKomponentti;
        for (int i=1; i<=c.PISTEITA; i++) {
            vaitettyKomponentti = Verkkoanalyysikirjasto.yhtenainenKomponentti(c, i);
            Tyokalut.pikajarjesta(vaitettyKomponentti);
            assertTrue(Tyokalut.onSamaTaulukko
                    (vaitettyKomponentti, oikeaKomponentti));
        }
    }

}