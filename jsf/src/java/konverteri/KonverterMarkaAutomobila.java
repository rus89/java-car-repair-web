/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package konverteri;

import domen.MarkaAutomobila;
import ejb.CallEJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rus
 */
@FacesConverter(forClass = MarkaAutomobila.class, value = "markaKonverter")
public class KonverterMarkaAutomobila implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        int sifraMarke = Integer.parseInt(value);
        MarkaAutomobila marka = CallEJB.vratiInstancu().getMarkaAutomobila().vratiMarku(new MarkaAutomobila(sifraMarke));
        return marka;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        MarkaAutomobila marka = (MarkaAutomobila) value;
        return String.valueOf(marka.getSifraMarke());

    }
}
