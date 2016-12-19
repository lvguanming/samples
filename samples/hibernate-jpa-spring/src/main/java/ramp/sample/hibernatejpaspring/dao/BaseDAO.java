/**
 * 
 */
package ramp.sample.hibernatejpaspring.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * All DAO Interface-s should extend this.
 * T - Entity represented by this DAO
 * ID - Type of the identity
 * @author Rama Palaniappan
 * @since Sep 20, 2012
 */
public interface BaseDAO<T, ID extends Serializable> {
	/**
	 * Sets the entity manager.  Normally this shouldn't be used 
	 * as this will be injected by Spring
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager);
	/**
	 * Returns the entity manager. Avoid using this method, instead use 
	 * save(), refresh(), merger(), find(), get(),...
	 * @return EntityManager
	 */
	public EntityManager getEntityManager();
	/**
	 * Saves(persists) the entity
	 * @param entity
	 */
	public void save(T entity);
	/**
	 * Refreshes the entity object based on the persisted copy.
	 * May overwrite entity object contents
	 * @param entity
	 */
	public void refresh(T entity);
	/**
	 * Does an update on the persistent store (DB), if there is any.
	 * @param entity
	 * @return
	 */
	public T merge(T entity);
	/**
	 * Returns an entity object that matches the primary key
	 * @param id
	 * @return
	 */
	public T find(ID id);
	/**
	 * Returns a list of entities(all of them) that matches the given query
	 * @param queryString
	 * @param params
	 * @return
	 */
	public List<T> find(String queryString, Map<String, Object> params);
	/**
	 * Returns a list of entities that matches the given query and number of results
	 * @param queryString
	 * @param params
	 * @param maxResults
	 * @return
	 */
	public List<T> find(String queryString, Map<String, Object> params, int maxResults);
//	/**
//	 * Return a single entity that matches the given query
//	 * @param queryString
//	 * @param params
//	 * @return
//	 */
//	public T findSingleResult(String queryString, Map<String, Object> params);
	/**
	 * Return a single entity that matches the given query
	 * @param queryString
	 * @param params
	 * @return
	 */
	public T findSingleResult(String queryString);
	/**
	 * Get all entities available for the type T
	 * Equivalent to "select * from <table>"
	 * @return
	 */
	public List<T> findAll();
	/**
	 * Returns a query object.  Useful to call methods on query object 
	 * like setParameter()
	 * Equivalent to EntityManager.createQuery(query, T.class)
	 * @param query
	 * @return
	 */
	public Query createQuery(String query);
	
	public <X> TypedQuery<X> createQuery(String query, Class<X> clazz);
//	/**
//	 * Find a record based on the unique column whose type is String/Varchar
//	 * @param columnName
//	 * @param columnValue
//	 * @throws NoResultException When no matching result is found
//	 * @throws NonUniqueResultException When more than one result is found
//	 * @return Matching single result
//	 */
//	public T getByUniqueCol(String columnName, String columnValue);
	
}
