package vn.tungnt.service.dao.common;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.common.base.Preconditions;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> implements IOperations<T> {
    private Class<T> clazz;

//    @Autowired
//    private SessionFactory sessionFactory;

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;
    
    // API

    protected void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    @Override
    public T findOne(final long id) {
        return (T) getEntityManager().find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery("from " + clazz.getName()).getResultList();
    }

    @Override
    public void create(final T entity) {
        Preconditions.checkNotNull(entity);
        getEntityManager().persist(entity);
    }

    @Override
    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getEntityManager().merge(entity);
    }

    @Override
    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getEntityManager().remove(entity);
    }

    @Override
    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

}