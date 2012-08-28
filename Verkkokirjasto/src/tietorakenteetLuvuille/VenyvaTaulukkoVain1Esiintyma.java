
package tietorakenteetLuvuille;

import tyokalut.Tyokalut;

/**
 * Kuten {@link VenyvaTaulukko} mutta kustakin alkiosta voi olla
 * taulukossa korkeintaan yksi esiintymä kerrallaan.
 * @author silja
 */
public class VenyvaTaulukkoVain1Esiintyma extends VenyvaTaulukko {

    /**
     * Kuten yliluokan vastaava konstruktori.
     */

    public VenyvaTaulukkoVain1Esiintyma() {
        super();
    }

    /**
     * Kuten yliluokan vastaava konstruktori.
     * @param kapasiteetti aloituskapasiteetti
     * @param kasvatussuhde suhde jolla taulukko kasvaa täyttyessään
     */

    public VenyvaTaulukkoVain1Esiintyma(int kapasiteetti, int kasvatussuhde) {
        super();
    }

    /**
     * Lisää alkion taulukkoon jos se ei jo ollut siellä.
     * Tarkistaa peräkkäishaulla oliko alkio jo taulukossa.
     * Aikavaativuus O(n)
     * @param lisattava lisättävä alkio
     */

    @Override
    public void lisaa(int lisattava) {
        if (!super.etsiPerakkaishaulla(lisattava))
            super.lisaa(lisattava);
    }
}
