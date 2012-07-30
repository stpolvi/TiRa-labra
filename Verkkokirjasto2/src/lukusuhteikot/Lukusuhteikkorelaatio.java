/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lukusuhteikot;

import tietorakenteet.LukujoukkoTehoton;

/**
 *
 * @author silja
 */
public class Lukusuhteikkorelaatio {

    public final int JOUKONKOKO;
    private final int INDEKSIKORJAUS = 1;

    private LukujoukkoTehoton[] yhteydet;

    public Lukusuhteikkorelaatio(int joukonKoko) {
        this.JOUKONKOKO = joukonKoko;
        yhteydet = new LukujoukkoTehoton[joukonKoko];
    }
    
    public boolean lisaaYhteys(int alkupiste, int loppupiste) {
        LukujoukkoTehoton seuraajat = getSeuraajat(alkupiste);

        if (seuraajat == null) {
            seuraajat = new LukujoukkoTehoton();
            setSeuraajat(alkupiste, seuraajat);
        }

        return seuraajat.lisaa(loppupiste);
    }


    public LukujoukkoTehoton getSeuraajat(int piste) {
        return yhteydet[piste - INDEKSIKORJAUS];
    }

    public boolean onYhteys(int alkupiste, int loppupiste) {
        LukujoukkoTehoton seuraajat = getSeuraajat(alkupiste);

        if (seuraajat == null) {
            return false;
        }

        return seuraajat.onAlkio(loppupiste);
    }





    private void setSeuraajat(int alkupiste, LukujoukkoTehoton seuraajat) {
        yhteydet[alkupiste - INDEKSIKORJAUS] = seuraajat;
    }

}
