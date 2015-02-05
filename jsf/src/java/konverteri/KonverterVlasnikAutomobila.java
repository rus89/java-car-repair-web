/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package konverteri;

import domen.VlasnikAutomobila;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rus
 */
@FacesConverter(forClass = VlasnikAutomobila.class, value = "konverterVlasnik")
public class KonverterVlasnikAutomobila implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        return null;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
