/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package tietorakenteet;

/**
 * Kaaripuu on binäärihakupuu, johon voi tallettaa Kaaria.
 * @author silja
 */
public class Kaaripuu {

    private Kaarisolmu juuri;

    /**
     * Luo tyhjän Kaaripuun.
     */

    public Kaaripuu() {
        this.juuri = null;
    }

    /**
     * Lisää puuhun kaaren.
     * @param k lisättävä kaari
     */

    public void lisaa(Kaari k) {
        if (this.juuri == null)
            this.juuri = new Kaarisolmu(k, null, null);
        else
            lisaaRekursiolla(k, juuri);
    }

        private void lisaaRekursiolla(Kaari lisattava, Kaarisolmu s) {
            Kaari vuorossa = s.getKaari();
            if (vuorossa.compareTo(lisattava) > 0) {
                lisaaVasemmalle(lisattava, s);
            }
            if (vuorossa.compareTo(lisattava) < 0) {
                lisaaOikealle(lisattava, s);
            }
        }

            private void lisaaVasemmalle(Kaari lisattava, Kaarisolmu s) {
                if (s.getVasenLapsi() == null) {
                        s.setVasenLapsi(new Kaarisolmu(lisattava, null, null));
                    } else {
                        lisaaRekursiolla(lisattava, s.getVasenLapsi());
                    }
            }

            private void lisaaOikealle(Kaari lisattava, Kaarisolmu s) {
                    if (s.getOikeaLapsi() == null) {
                        s.setOikeaLapsi(new Kaarisolmu(lisattava, null, null));
                    } else {
                        lisaaRekursiolla(lisattava, s.getOikeaLapsi());
                    }
            }

    public void poista(Kaari k) {

        throw new Error("unsupported");
    }

        private void poistaRekursiolla(Kaari poistettava, Kaarisolmu s) {
            if (true) throw new Error("kesken");
            if (s.getKaari().compareTo(poistettava) > 0) {
                if (s.getVasenLapsi().getKaari().equals(poistettava)) {
//                    poistaVasenLapsi(s);
                } else {
                    
                }
            }
            if (s.getKaari().compareTo(poistettava) < 0) {
                if (s.getOikeaLapsi().getKaari().equals(poistettava)) {
//                    poistaOikeaLapsi(s);
                }
            }

        }


    /**
     * Onko kaari puussa.
     * @param k etsittävä kaari
     * @return löytyikö kaari
     */

    public boolean etsi(Kaari k) {
        return etsiRekursiolla(k, this.juuri);
    }

        /**
         * privaatti Etsi-metodin rekursioapuri.
         * @param k etsittävä kaari
         * @param s solmu jota ollaan läpikäymässä
         * @return löytyikö kaari
         */

        private boolean etsiRekursiolla(Kaari k, Kaarisolmu s) {
            if (s == null) return false;
            Kaari vuorossa = s.getKaari();
            if (vuorossa.compareTo(k) > 0)
                return etsiRekursiolla(k, s.getVasenLapsi());
            if (vuorossa.compareTo(k) < 0)
                return etsiRekursiolla(k, s.getOikeaLapsi());
            return true;
        }

}
