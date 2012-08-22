
package lataaja;

import suhteikot.Suhteikko;
import tietorakenteet.Kaari;
import tietorakenteet.Kaaripuu;

/**
 * Tämän luokan avulla voi ladata suhteikon valmiiksi tutkimista varten.
 *
 * @author silja
 */
public class Lataajakone {

    private Suhteikko suhteikko;
    private Kaaripuu kaaripuu;

    /**
     * Luo lataajakoneen annetulle suhteikolle.
     *
     * Aikavaativuus O(1)
     * @param s ladattava suhteikko
     */

    public Lataajakone(Suhteikko suhteikko) {
        this.suhteikko = suhteikko;
    }

    /**
     * Luo suhteikon kaaria vastaavat Kaaret ja tallettaa ne binäärihakupuuhun.
     *
     * Aikavaativuus O(???)
     */

    public void teeKaaripuu() {
        kaaripuu = new Kaaripuu();
        int[] seuraajat;
        Kaari kaari;
        Kaari[] kaaret = new Kaari[suhteikko.PISTEITA * suhteikko.PISTEITA];

        for (int i=1; i<=suhteikko.PISTEITA; i++) {
            seuraajat = suhteikko.getSeuraajat(i).toIntArray();

            for (int j=0; j<seuraajat.length; j++) {
                kaari = new Kaari(i, seuraajat[j]);
                kaaret[i*suhteikko.PISTEITA + j-1] = kaari;
            }
        }

        tyokalut.Tyokalut.sekoitaOliotaulukko(kaaret);
    }

}
