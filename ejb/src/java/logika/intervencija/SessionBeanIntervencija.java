/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.intervencija;

import domen.Intervencija;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanIntervencija implements SessionBeanIntervencijaLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<Intervencija> vratiSveIntervencije() {
        return em.createQuery("SELECT i FROM Intervencija i").getResultList();
    }

    @Override
    public Intervencija vratiIntervenciju(Intervencija i) {
        return em.find(Intervencija.class, i.getSifraIntervencije());
    }

    @Override
    public void sacuvajIntervenciju(Intervencija i) {
        em.persist(i);
    }

    @Override
    public void izmeniIntervenciju(Intervencija i) {
        Intervencija intervencija = em.find(Intervencija.class, i.getSifraIntervencije());

    }

    @Override
    public void obrisiIntervenciju(Intervencija i) {
        Intervencija in = em.find(Intervencija.class, i.getSifraIntervencije());
        em.remove(in);
    }

    @Override
    public int vratiPK() {
        List<Intervencija> li = vratiSveIntervencije();
        if (li.isEmpty()) {
            return 1;
        } else {
            int max = 0;
            for (Intervencija i : li) {
                if (i.getSifraIntervencije() > max) {
                    max = i.getSifraIntervencije();
                }
            }
            return max + 1;
        }
    }
}
