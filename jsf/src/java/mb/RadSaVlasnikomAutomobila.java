/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.VlasnikAutomobila;
import domen.Zaposleni;
import ejb.CallEJB;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Rus
 */
@ManagedBean
@SessionScoped
public class RadSaVlasnikomAutomobila {

    private VlasnikAutomobila vlasnikAutomobila;
    private VlasnikAutomobila selektovaniVlasnikAutomobila;
    private List<VlasnikAutomobila> listaVlasnika;
    private List<VlasnikAutomobila> filtriraniVlasnici;

    public VlasnikAutomobila getSelektovaniVlasnikAutomobila() {
        return selektovaniVlasnikAutomobila;
    }

    public void setSelektovaniVlasnikAutomobila(VlasnikAutomobila selektovaniVlasnikAutomobila) {
        this.selektovaniVlasnikAutomobila = selektovaniVlasnikAutomobila;
    }

    public List<VlasnikAutomobila> getFiltriraniVlasnici() {
        return filtriraniVlasnici;
    }

    public void setFiltriraniVlasnici(List<VlasnikAutomobila> filtriraniVlasnici) {
        this.filtriraniVlasnici = filtriraniVlasnici;
    }

    public List<VlasnikAutomobila> getListaVlasnika() {
        return listaVlasnika;
    }

    public void setListaVlasnika(List<VlasnikAutomobila> listaVlasnika) {
        this.listaVlasnika = listaVlasnika;
    }

    public VlasnikAutomobila getVlasnikAutomobila() {
        return vlasnikAutomobila;
    }

    public void setVlasnikAutomobila(VlasnikAutomobila vlasnikAutomobila) {
        this.vlasnikAutomobila = vlasnikAutomobila;
    }

    /** Creates a new instance of RadSaVlasnikomAutomobila */
    public RadSaVlasnikomAutomobila() {
        inicijalizujListu();
    }

    private void inicijalizujListu() {
        listaVlasnika = CallEJB.vratiInstancu().getVlasnikAutomobila().vratiSveVlasnike();
    }

    public String dodavanjeVlasnika() {
        vlasnikAutomobila = new VlasnikAutomobila();
        return "radSaVlasnikom/unosVlasnika.xhtml";
    }

    public String sacuvajVlasnika() {
        try {
            CallEJB.vratiInstancu().getVlasnikAutomobila().sacuvajVlasnika(vlasnikAutomobila);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vlasnik je uspesno sacuvan!", null));
            inicijalizujListu();
            vlasnikAutomobila = new VlasnikAutomobila();
            return "unosVlasnika.xhtml";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vlasnik nije sacuvan!", null));
            return null;
        }
    }

    public void obrisiVlasnika() {

        if (vlasnikAutomobila == null) {
            return;
        }

        UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmBrisanjeVlasnika:tblBrisanjeVlasnika");
        DataTable dt = (DataTable) komponenta;
        vlasnikAutomobila = (VlasnikAutomobila) dt.getRowData(vlasnikAutomobila.getJmbg());

        try {

            CallEJB.vratiInstancu().getVlasnikAutomobila().obrisiVlasnika(vlasnikAutomobila);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vlasnik je uspesno obrisan!", null));
            inicijalizujListu();

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vlasnik nije obrisan!", null));

        }
    }

    public void validateJmbg(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti JMBG.", null);
            throw new ValidatorException(msg);
        }

        if (value.toString().length() != 13) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "JMBG mora sadrzati 13 cifara.", null);
            throw new ValidatorException(msg);
        }

        List<VlasnikAutomobila> lista = CallEJB.vratiInstancu().getVlasnikAutomobila().vratiSveVlasnike();

        for (VlasnikAutomobila va : lista) {
            if (va.getJmbg().equals(value.toString())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postoji vlasnik sa unetim JMBG-om.", null);
                throw new ValidatorException(msg);
            }
        }

        for (VlasnikAutomobila va : lista) {
            if (va.getJmbg().equals(value.toString())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postoji vlasnik sa unetim JMBG-om.", null);
                throw new ValidatorException(msg);
            }
        }
    }

    public void validateIme(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti ime.", null);
            throw new ValidatorException(msg);
        }

        for (int i = 0; i < value.toString().length(); i++) {
            char znak = value.toString().charAt(i);
            if (Character.isDigit(znak)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ime ne moze sadrzati brojeve.", null);
                throw new ValidatorException(msg);
            }
        }
    }

    public void validatePrezime(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti prezime.", null);
            throw new ValidatorException(msg);
        }

        for (int i = 0; i < value.toString().length(); i++) {
            char znak = value.toString().charAt(i);
            if (Character.isDigit(znak)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prezime ne moze sadrzati brojeve.", null);
                throw new ValidatorException(msg);
            }
        }
    }
}
