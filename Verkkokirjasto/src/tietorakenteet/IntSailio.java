/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteet;

/**
 * Int-säiliöt ovat tietorakenteita, joihin voi tallettaa int-lukuja.
 * Säiliössä ei saa olla useampaa kertaa samaa alkiota.
 * @author silja
 */
public interface IntSailio {

    /**
     * Säiliössä olevien alkioiden lukumäärä.
     * @return alkioiden määrä
     */

    public abstract int alkioita();

    /**
     * Säiliössä olevat luvut int-taulukossa.
     * Taulukon pituus on sama kuin alkioiden lukumäärä.
     * @return alkiot taulukoituna
     */
    public abstract int[] toIntArray();

    /**
     * Lisää säiliöön alkion.
     * @param alkio lisättävä
     */

    public abstract void lisaa(int alkio);

    /**
     * Onko anettu alkio säiliössä
     * @param etsittava etsittävä alkio
     * @return löytyikö säiliöstä etsittävä alkio
     */

    public abstract boolean etsi(int etsittava);

    /**
     * Järjestää säiliön sisällön siten, että toIntArray-metodin 
     * palauttama taulukko on järjestyksessä.
     */
    public abstract void sort();
}
