/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rus
 */
@Entity
@Table(name = "modelautomobila")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModelAutomobila.findAll", query = "SELECT m FROM ModelAutomobila m"),
    @NamedQuery(name = "ModelAutomobila.findBySifraModela", query = "SELECT m FROM ModelAutomobila m WHERE m.modelAutomobilaPK.sifraModela = :sifraModela"),
    @NamedQuery(name = "ModelAutomobila.findBySifraMarke", query = "SELECT m FROM ModelAutomobila m WHERE m.modelAutomobilaPK.sifraMarke = :sifraMarke"),
    @NamedQuery(name = "ModelAutomobila.findByNazivModela", query = "SELECT m FROM ModelAutomobila m WHERE m.nazivModela = :nazivModela")})
public class ModelAutomobila implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModelAutomobilaPK modelAutomobilaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NazivModela")
    private String nazivModela;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model", fetch = FetchType.EAGER)
    private List<Automobil> automobilList;
    @JoinColumn(name = "SifraMarke", referencedColumnName = "SifraMarke", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MarkaAutomobila markaAutomobila;

    public ModelAutomobila() {
    }

    public ModelAutomobila(ModelAutomobilaPK modelAutomobilaPK) {
        this.modelAutomobilaPK = modelAutomobilaPK;
    }

    public ModelAutomobila(ModelAutomobilaPK modelAutomobilaPK, String nazivModela) {
        this.modelAutomobilaPK = modelAutomobilaPK;
        this.nazivModela = nazivModela;
    }

    public ModelAutomobila(int sifraModela, int sifraMarke) {
        this.modelAutomobilaPK = new ModelAutomobilaPK(sifraModela, sifraMarke);
    }

    public ModelAutomobilaPK getModelAutomobilaPK() {
        return modelAutomobilaPK;
    }

    public void setModelAutomobilaPK(ModelAutomobilaPK modelAutomobilaPK) {
        this.modelAutomobilaPK = modelAutomobilaPK;
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    @XmlTransient
    public List<Automobil> getAutomobilList() {
        return automobilList;
    }

    public void setAutomobilList(List<Automobil> automobilList) {
        this.automobilList = automobilList;
    }

    public MarkaAutomobila getMarkaAutomobila() {
        return markaAutomobila;
    }

    public void setMarkaAutomobila(MarkaAutomobila markaAutomobila) {
        this.markaAutomobila = markaAutomobila;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelAutomobilaPK != null ? modelAutomobilaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelAutomobila)) {
            return false;
        }
        ModelAutomobila other = (ModelAutomobila) object;
        if ((this.modelAutomobilaPK == null && other.modelAutomobilaPK != null) || (this.modelAutomobilaPK != null && !this.modelAutomobilaPK.equals(other.modelAutomobilaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivModela;
    }
}
