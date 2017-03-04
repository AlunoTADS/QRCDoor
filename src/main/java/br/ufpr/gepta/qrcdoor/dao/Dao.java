package br.ufpr.gepta.qrcdoor.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mklos
 */
public class Dao<T> {

    private final Class persistentClass;

    @SuppressWarnings("unchecked")
    public Dao() {
        System.out.println(((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments());
        this.persistentClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getById(Integer id) {
        return (T) getSession().get(persistentClass, id);
    }

    public List<T> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<T>) criteria.list();
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }
}
