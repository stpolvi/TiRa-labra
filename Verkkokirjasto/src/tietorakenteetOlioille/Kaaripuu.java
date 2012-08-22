
package tietorakenteetOlioille;

import tietorakenteetOlioille.Kaari;

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
     * Lisää puuhun kaaren jos se ei jo ollut siellä.
     * Kaaret kannattaa lisätä puuhun satunnaisessa järjestyksessä.
     * Siten saadaan puu, jonka oksat ovat tasapituisia ja niitä on paljon.
     * Tällaisessa tuuheassa puussa operaatiot toimivat tehokkaasti.
     *
     * Aikavaativuus O(log n) olettaen että puu on tuuhea,
     * tilavaativuus O(log n)
     * @param k lisättävä kaari
     */

    public void lisaa(Kaari k) {
        if (this.juuri == null)
            this.juuri = new Kaarisolmu(k, null, null, null);
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
                if (s.getVasenLapsi() == null) 
                    s.setVasenLapsi(new Kaarisolmu(lisattava, s, null, null));
                else 
                    lisaaRekursiolla(lisattava, s.getVasenLapsi());                   
            }

            private void lisaaOikealle(Kaari lisattava, Kaarisolmu s) {
                if (s.getOikeaLapsi() == null)
                    s.setOikeaLapsi(new Kaarisolmu(lisattava, s, null, null));
                else
                    lisaaRekursiolla(lisattava, s.getOikeaLapsi());
            }

    /**
     * Poistaa annetun kaaren puusta.
     *
     * Aikavaativuus O(log n) olettaen että puu on tuuhea,
     * tilavaativuus O(log n)
     * @param k poistettava kaari
     */

    public void poista(Kaari k) {
        poistaRekursiolla(k, juuri);
    }

        private void poistaRekursiolla(Kaari poistettava, Kaarisolmu s) {
            if (s == null) return; //poistettavaa ei ole puussa

            if (s.getKaari().compareTo(poistettava) > 0)
                poistaRekursiolla(poistettava, s.getVasenLapsi());
            else if (s.getKaari().compareTo(poistettava) < 0)
                poistaRekursiolla(poistettava, s.getOikeaLapsi());
            else
                poistaSolmu(s);
        }

            private void poistaSolmu(Kaarisolmu s) {
                if (this.juuri == s)
                    this.juuri = null;
                else if(onLehti(s))
                    poistaLehti(s);
                else if(s.getVasenLapsi() == null)
                    poistaSolmuJollaEiVasentaLasta(s);
                else if(s.getOikeaLapsi() == null)
                    poistaSolmuJollaEiOikeaaLasta(s);
                else
                    poistaKaksilapsinenSolmu(s);
            }

                private boolean onLehti(Kaarisolmu s) {
                    return s.getOikeaLapsi() == null && s.getVasenLapsi() == null;
                }

                private void poistaLehti(Kaarisolmu s) {
                    Kaarisolmu emo = s.getEmo();
                    if (emonsaVasenLapsi(s))
                        emo.setVasenLapsi(null);
                    else
                        emo.setOikeaLapsi(null);
                }

                private void poistaSolmuJollaEiVasentaLasta(Kaarisolmu s) {
                    Kaarisolmu emo = s.getEmo();
                    if (emonsaVasenLapsi(s))
                        emo.setVasenLapsi(s.getOikeaLapsi());
                    else
                        emo.setOikeaLapsi(s.getOikeaLapsi());
                }

                private void poistaSolmuJollaEiOikeaaLasta(Kaarisolmu s) {
                    Kaarisolmu emo = s.getEmo();
                    if (emonsaVasenLapsi(s))
                        emo.setVasenLapsi(s.getVasenLapsi());
                    else
                        emo.setOikeaLapsi(s.getVasenLapsi());
                }

                private void poistaKaksilapsinenSolmu(Kaarisolmu poistettava) {
                    Kaarisolmu pieninOikealla = etsiPieninPerillinen(poistettava.getOikeaLapsi());

                    pieninOikealla.getEmo().setVasenLapsi(pieninOikealla.getOikeaLapsi());
                    pieninOikealla.setVasenLapsi(poistettava.getVasenLapsi());
                    pieninOikealla.setOikeaLapsi(poistettava.getOikeaLapsi());

                    if (emonsaVasenLapsi(poistettava))
                        poistettava.getEmo().setVasenLapsi(pieninOikealla);
                    else 
                        poistettava.getEmo().setOikeaLapsi(pieninOikealla);
                }

                    private Kaarisolmu etsiPieninPerillinen(Kaarisolmu s) {
                        if (s.getVasenLapsi() == null) return s;
                        return etsiPieninPerillinen(s.getVasenLapsi());
                    }

                    private boolean emonsaVasenLapsi(Kaarisolmu s) {
                        return s.getEmo().getKaari().compareTo(s.getKaari()) > 0;
                    }
                
    /**
     * Onko kaari puussa.
     *
     * Aikavaativuus O(log n) olettaen että puu on tuuhea,
     * tilavaativuus O(log n)
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
            else if(vuorossa.compareTo(k) < 0)
                return etsiRekursiolla(k, s.getOikeaLapsi());
            else
                return true;
        }
        
}
