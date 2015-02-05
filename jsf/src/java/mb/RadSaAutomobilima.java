/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Automobil;
import domen.MarkaAutomobila;
import domen.ModelAutomobila;
import domen.VlasnikAutomobila;
import ejb.CallEJB;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Rus
 */
@ManagedBean
@SessionScoped
public class RadSaAutomobilima {

    public static final int UNOS_AUTOMOBILA = 1;
    public static final int IZMENA_AUTOMOBILA = 2;
    private int operacija;
    private Automobil automobil;
    private List<Automobil> listaAutomobila;
    private List<Automobil> filtriraniAutomobili;
    private List<MarkaAutomobila> listaMarkeAutomobila;
    private ModelAutomobila modelAutomobila;
    private MarkaAutomobila markaAutomobila;
    private List<ModelAutomobila> listaModelaAutomobila;
    private VlasnikAutomobila vlasnik;
    private List<VlasnikAutomobila> listaVlasnika;

    public List<VlasnikAutomobila> getListaVlasnika() {
        return listaVlasnika;
    }

    public void setListaVlasnika(List<VlasnikAutomobila> listaVlasnika) {
        this.listaVlasnika = listaVlasnika;
    }

    public VlasnikAutomobila getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(VlasnikAutomobila vlasnik) {
        this.vlasnik = vlasnik;
    }

    public MarkaAutomobila getMarkaAutomobila() {
        return markaAutomobila;
    }

    public void setMarkaAutomobila(MarkaAutomobila markaAutomobila) {
        this.markaAutomobila = markaAutomobila;
    }

    public List<ModelAutomobila> getListaModelaAutomobila() {
        return listaModelaAutomobila;
    }

    public void setListaModelaAutomobila(List<ModelAutomobila> listaModelaAutomobila) {
        this.listaModelaAutomobila = listaModelaAutomobila;
    }

    public ModelAutomobila getModelAutomobila() {
        return modelAutomobila;
    }

    public void setModelAutomobila(ModelAutomobila modelAutomobila) {
        this.modelAutomobila = modelAutomobila;
    }

    public List<MarkaAutomobila> getListaMarkeAutomobila() {
        return listaMarkeAutomobila;
    }

    public void setListaMarkeAutomobila(List<MarkaAutomobila> listaMarkeAutomobila) {
        this.listaMarkeAutomobila = listaMarkeAutomobila;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    public List<Automobil> getFiltriraniAutomobili() {
        return filtriraniAutomobili;
    }

    public void setFiltriraniAutomobili(List<Automobil> filtriraniAutomobili) {
        this.filtriraniAutomobili = filtriraniAutomobili;
    }

    public List<Automobil> getListaAutomobila() {
        return listaAutomobila;
    }

    public void setListaAutomobila(List<Automobil> listaAutomobila) {
        this.listaAutomobila = listaAutomobila;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    /** Creates a new instance of RadSaAutomobilima */
    public RadSaAutomobilima() {
        listaMarkeAutomobila = CallEJB.vratiInstancu().getMarkaAutomobila().vratiSveMarke();
        listaModelaAutomobila = CallEJB.vratiInstancu().getModelAutomobila().vratiSveModele();
        inicijalizujListu();
    }

    private void inicijalizujListu() {
        listaAutomobila = CallEJB.vratiInstancu().getAutomobil().vratiSveAutomobile();
    }

    public String dodavanjeAutomobila() {
        automobil = new Automobil();
        operacija = UNOS_AUTOMOBILA;
        return "radSaAutomobilima/dodavanjeAutomobila.xhtml";
    }

    public String sacuvajAutomobil() {
//        try {
//            if (operacija == UNOS_AUTOMOBILA) {
//                CallEJB.vratiInstancu().getAutomobil().sacuvajAuotmobil(automobil);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobil nije sacuvan!", null));
//                inicijalizujListu();
//                automobil = new Automobil();
                return "../pocetna.xhtml";
//            } else {
//                CallEJB.vratiInstancu().getAutomobil().izmeniAutomobil(automobil);
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobil je uspesno izmenjen!", null));
//                inicijalizujListu();
//                return "../pocetna.xhtml";
//            }
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobil nije sacuvan!", null));
//            return null;
//        }
    }

    public String izmeniAutomobil() {

        if (automobil == null) {
            return null;
        }

        UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("");
        DataTable dt = (DataTable) komponenta;
        automobil = (Automobil) dt.getRowData(automobil.getSifraAutomobila().toString());
        operacija = IZMENA_AUTOMOBILA;
        return "radSaAutomobilima/dodavanjeAutomobila.xhtml";

    }

    public void obrisiAutomobil() {

//        if (automobil == null) {
//            return;
//        }
//
//        UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("");
//        DataTable dt = (DataTable) komponenta;
//        automobil = (Automobil) dt.getRowData(automobil.getSifraAutomobila().toString());
//
//        try {
//
//            CallEJB.vratiInstancu().getAutomobil().obrisiAutomobil(automobil);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobil nije obrisan!", null));
//
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobil nije obrisan!", null));
//        }
    }

    public List<SelectItem> vratiListuMarkeAutomobilaZaCombo() {
        List<SelectItem> lista = new LinkedList<SelectItem>();
        for (int i = 0; i < listaMarkeAutomobila.size(); i++) {
            SelectItem si = new SelectItem(listaMarkeAutomobila.get(i), listaMarkeAutomobila.get(i).getNazivMarke());
            lista.add(si);
        }
        return lista;
    }

    public List<SelectItem> vratiListuModelaAutomobilaZaOdredjenuMarkuZaCombo() {
        List<SelectItem> listaM = new LinkedList<SelectItem>();
        for (int i = 0; i < listaModelaAutomobila.size(); i++) {
            SelectItem selectItem = new SelectItem(listaModelaAutomobila.get(i), listaModelaAutomobila.get(i).getNazivModela());
            listaM.add(selectItem);
        }
        return listaM;
    }

    public List<SelectItem> vratiListuVlasnikaAutomobilaZaCombo() {
        List<SelectItem> listaV = new LinkedList<SelectItem>();
        for (int i = 0; i < listaVlasnika.size(); i++) {
            SelectItem selectItem = new SelectItem(listaVlasnika.get(i), listaVlasnika.get(i).getIme() + listaVlasnika.get(i).getPrezime());
            listaV.add(selectItem);
        }
        return listaV;
    }
}
