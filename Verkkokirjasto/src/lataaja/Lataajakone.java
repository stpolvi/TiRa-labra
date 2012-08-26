
package lataaja;

import suhteikot.Suhteikko;
import tietorakenteetOlioille.*;
import tyokalut.Tyokalut;

/**
 * Tämän luokan avulla voi ladata suhteikon valmiiksi tutkimista varten.
 * @author silja
 */

public class Lataajakone {

    private Suhteikko suhteikko;
    private Kaaripuu kaaripuu;

    /**
     * Luo lataajakoneen annetulle {@link Suhteikko}-oliolle.
     *
     * Aikavaativuus O(1)
     * @param suhteikko
     */

    public Lataajakone(Suhteikko suhteikko) {
        this.suhteikko = suhteikko;
    }

    /**
     * Luo suhteikon yhteyksiä vastaavat {@link Kaari}-oliot ja
     * tallettaa ne binäärihakupuuhun.
     *
     * Aikavaativuus O(???)
     */

    public void teeKaaripuu() {
        kaaripuu = new Kaaripuu();
        Kaari[] kaaret = luoKaaret();
        Tyokalut.sekoitaOliotaulukko(kaaret);

        for (Kaari k : kaaret) {
            if (k == null) return;
            kaaripuu.lisaa(k);
        }
    }

        private Kaari[] luoKaaret() {
            int[] seuraajat;
            Kaari kaari;
            Kaari[] kaaret = new Kaari[suhteikko.PISTEITA * suhteikko.PISTEITA];
            int laskuri = 0;

            for (int i=1; i<=suhteikko.PISTEITA; i++) {
                seuraajat = suhteikko.getSeuraajat(i).toIntArray();

                for (int seur : seuraajat) {
                    kaari = new Kaari(i, seur);
                    kaaret[laskuri++] = kaari;
                }
            }

            return kaaret;
        }

}
