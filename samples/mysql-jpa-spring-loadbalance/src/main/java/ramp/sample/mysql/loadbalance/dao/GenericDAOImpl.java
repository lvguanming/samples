/**
 * 
 */
package ramp.sample.mysql.loadbalance.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Rama Palaniappan
 * @since Sep 21, 2012
 */
public class GenericDAOImpl<T, ID extends Serializable> implements
		GenericDAO<T, ID> {

	protected EntityManager entityManager = null;

	protected Class<T> klass = null;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		Type[] types = ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments();

		if (types[0] instanceof ParameterizedType) {
			// If the class has parameterized types, it takes the raw type.
			ParameterizedType type = (ParameterizedType) types[0];
			klass = (Class<T>) type.getRawType();
		} else {
			klass = (Class<T>) types[0];
		}
	}

	public GenericDAOImpl(Class<T> klass) {
		this.klass = klass;
	}

	@PersistenceContext(unitName = "test-persistence-unit")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void save(T entity) {
		entityManager.persist(entity);
//		entityManager.flush();
	}

	public void refresh(T entity) {
		entityManager.refresh(entity);
	}

	public T merge(T entity) {
		T t = entityManager.merge(entity);
//		entityManager.flush();
		return t;
	}

	public T find(ID id) {
		return entityManager.find(klass, id);
	}

	public List<T> get(Map<String, Object> params) {
		String query = " from " + klass.getName();
		return get(query, params);
	}
	
	public List<T> get(String queryString, Map<String, Object> params) {
		return get(queryString, params, -1);
	}

	public List<T> get(String queryString, Map<String, Object> params,
			int maxResults) {
		try {
			TypedQuery<T> query = entityManager.createQuery(queryString, klass);
			if (params != null) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			if (maxResults > 0)
				query.setMaxResults(maxResults);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public T getSingleResult(String queryString, Map<String, Object> params) {
		try {
			TypedQuery<T> query = entityManager.createQuery(queryString, klass);
			if (params != null) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public T getSingleResult(String queryString) {
		try {
			TypedQuery<T> query = entityManager.createQuery(queryString, klass);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<T> getAll() {
//		try {
//			return entityManager.createQuery(" from " + klass.getName(), klass)
//					.getResultList();
//		} catch (NoResultException e) {
//			return null;
//		}
		return get(null);
	}

	public Query createNativeQuery(String query) {
		return entityManager.createNativeQuery(query);
	}

	public Query createNativeQuery(String query, Class<?> clazz) {
		return entityManager.createNativeQuery(query, clazz);
	}

	public Query createQuery(String query) {
		return entityManager.createQuery(query);
	}

	public <X> TypedQuery<X> createQuery(String query, Class<X> clazz) {
		return entityManager.createQuery(query, clazz);
	}

	@Override
	public Long count(String columnName, String columnValue) {
		return createQuery(
				"select count(t) from " + klass.getName() + " t where t."
						+ columnName + "=?1", Long.class).setParameter(1,
				columnValue).getSingleResult();
	}

}
