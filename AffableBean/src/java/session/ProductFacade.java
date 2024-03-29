/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Category;
import entity.Product;
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
public class ProductFacade {

    @PersistenceContext(unitName = "AffableBeanPU")
    private EntityManager em;

    public void create(Product product) {
        em.persist(product);
    }

    public void edit(Product product) {
        em.merge(product);
    }

    public void remove(Product product) {
        em.remove(em.merge(product));
    }

    public Product find(Object id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Product.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Product> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Product.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Product> rt = cq.from(Product.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Product> findForCategory(Category category) {
        return em.createQuery("SELECT p FROM Product p WHERE p.categoryId = :categoryId").
                setParameter("categoryId", category).getResultList();
    }
}
