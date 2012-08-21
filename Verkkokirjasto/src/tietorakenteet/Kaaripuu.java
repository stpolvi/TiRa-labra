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
        poistaRekursiolla(k, juuri);
    }

        private void poistaRekursiolla(Kaari poistettava, Kaarisolmu s) {
            if (s == null) return;
            if (s != null && s.getKaari().equals(poistettava)) this.juuri = null;
            else if(s.getKaari().compareTo(poistettava) > 0) {
                if (s.getVasenLapsi().getKaari().equals(poistettava)) {
                    poistaVasenLapsi(s);
                } else {
                    poistaRekursiolla(poistettava, s.getVasenLapsi());
                }
            }
            else if(s.getKaari().compareTo(poistettava) < 0) {
                if (s.getOikeaLapsi().getKaari().equals(poistettava)) {
                    poistaOikeaLapsi(s);
                } else {
                    poistaRekursiolla(poistettava, s.getOikeaLapsi());
                }
            }
        }

            private void poistaVasenLapsi(Kaarisolmu s) {
                if (lehti(s.getVasenLapsi()))
                    s.setVasenLapsi(null);
                else if(s.getVasenLapsi().getVasenLapsi() == null)
                    s.setVasenLapsi(s.getVasenLapsi().getOikeaLapsi());
                else if(s.getVasenLapsi().getOikeaLapsi() == null)
                    s.setVasenLapsi(s.getVasenLapsi().getVasenLapsi());
                else
                    poistaKaksilapsinenVasenLapsi(s);
            }

                private void poistaKaksilapsinenVasenLapsi(Kaarisolmu s) {
                    Kaarisolmu pienimmanVanhempiOikealla
                            = etsiPienimmanVanhempi(s.getOikeaLapsi());
                    pienimmanVanhempiOikealla.getVasenLapsi()
                            .setVasenLapsi(s.getVasenLapsi().getVasenLapsi());
                    pienimmanVanhempiOikealla
                            .setVasenLapsi
                            (pienimmanVanhempiOikealla.getVasenLapsi().getOikeaLapsi());
                    pienimmanVanhempiOikealla.getVasenLapsi()
                            .setOikeaLapsi(s.getVasenLapsi().getOikeaLapsi());
                    s.setVasenLapsi(pienimmanVanhempiOikealla.getVasenLapsi());
                }

                    private Kaarisolmu etsiPienimmanVanhempi(Kaarisolmu s) {
                        if (s.getVasenLapsi().getVasenLapsi() == null) return s;
                        return etsiPienimmanVanhempi(s.getVasenLapsi());
                    }

            private void poistaOikeaLapsi(Kaarisolmu s) {
                if (lehti(s.getOikeaLapsi()))
                    s.setOikeaLapsi(null);
                else if(s.getOikeaLapsi().getVasenLapsi() == null)
                    s.setOikeaLapsi(s.getOikeaLapsi().getOikeaLapsi());
                else if(s.getOikeaLapsi().getOikeaLapsi() == null)
                    s.setOikeaLapsi(s.getOikeaLapsi().getVasenLapsi());
                else
                    poistaKaksilapsinenOikeaLapsi(s);
            }

                private void poistaKaksilapsinenOikeaLapsi(Kaarisolmu s) {
                    Kaarisolmu pienimmanVanhempiOikealla
                            = etsiPienimmanVanhempi(s.getOikeaLapsi());
                    pienimmanVanhempiOikealla.getVasenLapsi()
                            .setVasenLapsi(s.getVasenLapsi().getVasenLapsi());
                    pienimmanVanhempiOikealla
                            .setVasenLapsi
                            (pienimmanVanhempiOikealla.getVasenLapsi().getOikeaLapsi());
                    pienimmanVanhempiOikealla.getVasenLapsi()
                            .setOikeaLapsi(s.getVasenLapsi().getOikeaLapsi());
                    s.setVasenLapsi(pienimmanVanhempiOikealla.getVasenLapsi());
                }

                private boolean lehti(Kaarisolmu s) {
                    return s.getOikeaLapsi() == null && s.getVasenLapsi() == null;
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
