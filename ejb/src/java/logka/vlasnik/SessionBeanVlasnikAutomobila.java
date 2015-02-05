/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logka.vlasnik;

import domen.VlasnikAutomobila;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanVlasnikAutomobila implements SessionBeanVlasnikAutomobilaLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<VlasnikAutomobila> vratiSveVlasnike() {
        return em.createQuery("SELECT v FROM VlasnikAutomobila v").getResultList();
    }

    @Override
    public VlasnikAutomobila vratiVlasnika(VlasnikAutomobila va) {
        return em.find(VlasnikAutomobila.class, va.getJmbg());
    }

    @Override
    public void sacuvajVlasnika(VlasnikAutomobila va) {
        em.persist(va);
    }

    @Override
    public void obrisiVlasnika(VlasnikAutomobila va) {
        VlasnikAutomobila vlasnik = em.find(VlasnikAutomobila.class, va.getJmbg());
        em.remove(vlasnik);
    }

}
