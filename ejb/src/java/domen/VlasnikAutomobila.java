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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "vlasnikautomobila")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VlasnikAutomobila.findAll", query = "SELECT v FROM VlasnikAutomobila v"),
    @NamedQuery(name = "VlasnikAutomobila.findByJmbg", query = "SELECT v FROM VlasnikAutomobila v WHERE v.jmbg = :jmbg"),
    @NamedQuery(name = "VlasnikAutomobila.findByIme", query = "SELECT v FROM VlasnikAutomobila v WHERE v.ime = :ime"),
    @NamedQuery(name = "VlasnikAutomobila.findByPrezime", query = "SELECT v FROM VlasnikAutomobila v WHERE v.prezime = :prezime")})
public class VlasnikAutomobila implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "JMBG")
    private String jmbg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Prezime")
    private String prezime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jMBGVlasnika", fetch = FetchType.EAGER)
    private List<Automobil> automobilList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jMBGVlasnika", fetch = FetchType.EAGER)
    private List<Dugovanje> dugovanjeList;

    public VlasnikAutomobila() {
    }

    public VlasnikAutomobila(String jmbg) {
        this.jmbg = jmbg;
    }

    public VlasnikAutomobila(String jmbg, String ime, String prezime) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @XmlTransient
    public List<Automobil> getAutomobilList() {
        return automobilList;
    }

    public void setAutomobilList(List<Automobil> automobilList) {
        this.automobilList = automobilList;
    }

    @XmlTransient
    public List<Dugovanje> getDugovanjeList() {
        return dugovanjeList;
    }

    public void setDugovanjeList(List<Dugovanje> dugovanjeList) {
        this.dugovanjeList = dugovanjeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jmbg != null ? jmbg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VlasnikAutomobila)) {
            return false;
        }
        VlasnikAutomobila other = (VlasnikAutomobila) object;
        if ((this.jmbg == null && other.jmbg != null) || (this.jmbg != null && !this.jmbg.equals(other.jmbg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
