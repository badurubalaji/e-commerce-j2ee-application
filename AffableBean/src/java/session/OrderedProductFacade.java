/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.OrderedProduct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dangkhoa
 */
@Stateless
public class OrderedProductFacade {
    @PersistenceContext(unitName = "AffableBeanPU")
    private EntityManager em;

    public void create(OrderedProduct orderedProduct) {
        em.persist(orderedProduct);
    }

    public void edit(OrderedProduct orderedProduct) {
        em.merge(orderedProduct);
    }

    public void remove(OrderedProduct orderedProduct) {
        em.remove(em.merge(orderedProduct));
    }

    public OrderedProduct find(Object id) {
        return em.find(OrderedProduct.class, id);
    }

    public List<OrderedProduct> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(OrderedProduct.class));
        return em.createQuery(cq).getResultList();
    }

    public List<OrderedProduct> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(OrderedProduct.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<OrderedProduct> rt = cq.from(OrderedProduct.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    // manually created
    public List<OrderedProduct> findByOrderId(Object id) {
        return em.createNamedQuery("OrderedProduct.findByCustomerOrderId").setParameter("customerOrderId", id).getResultList();
    }

}
