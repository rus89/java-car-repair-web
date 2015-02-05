/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rus
 */
@Embeddable
public class ModelAutomobilaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraModela")
    private int sifraModela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraMarke")
    private int sifraMarke;

    public ModelAutomobilaPK() {
    }

    public ModelAutomobilaPK(int sifraModela, int sifraMarke) {
        this.sifraModela = sifraModela;
        this.sifraMarke = sifraMarke;
    }

    public int getSifraModela() {
        return sifraModela;
    }

    public void setSifraModela(int sifraModela) {
        this.sifraModela = sifraModela;
    }

    public int getSifraMarke() {
        return sifraMarke;
    }

    public void setSifraMarke(int sifraMarke) {
        this.sifraMarke = sifraMarke;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) sifraModela;
        hash += (int) sifraMarke;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelAutomobilaPK)) {
            return false;
        }
        ModelAutomobilaPK other = (ModelAutomobilaPK) object;
        if (this.sifraModela != other.sifraModela) {
            return false;
        }
        if (this.sifraMarke != other.sifraMarke) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.ModelAutomobilaPK[ sifraModela=" + sifraModela + ", sifraMarke=" + sifraMarke + " ]";
    }
    
}
