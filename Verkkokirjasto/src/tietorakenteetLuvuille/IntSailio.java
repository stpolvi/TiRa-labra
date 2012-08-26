
package tietorakenteetLuvuille;

/**
 * Int-säiliöt ovat tietorakenteita, joihin voi tallettaa int-lukuja.
 * 
 * @author silja
 */
public interface IntSailio {

    /**
     * Säiliössä olevien alkioiden lukumäärä.
     * @return alkioiden määrä
     */

    public int alkioita();

    /**
     * Lisää säiliöön alkion.
     * @param alkio lisättävä
     */

    public void lisaa(int alkio);

    /**
     * Järjestää säiliön sisällön siten, että toIntArray-metodin 
     * palauttama taulukko on järjestyksessä.
     */

    public void jarjesta();
    
    /**
     * Säiliön alkiot int-taulukossa, jonka koko on alkioiden lukumäärä.
     * @return alkiot taulukossa
     */

    public int[] toIntArray();

    /**
     * Onko anettu alkio säiliössä
     * @param etsittava etsittävä alkio
     * @return löytyikö säiliöstä etsittävä alkio
     */

    public boolean etsi(int etsittava);

}
