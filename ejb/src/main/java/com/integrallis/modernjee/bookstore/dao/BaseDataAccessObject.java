package com.integrallis.modernjee.bookstore.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class BaseDataAccessObject<PrimaryKeyClass, Entity> implements DataAccessObject<PrimaryKeyClass, Entity> {
	protected Class<Entity> entityClass;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public BaseDataAccessObject() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[1];
	}
	
	public void persist(Entity entity) { entityManager.persist(entity); }
	public void remove(Entity entity) { entityManager.remove(entity); }
	public Entity findById(PrimaryKeyClass id) { return entityManager.find(entityClass, id); }
	
	@SuppressWarnings("unchecked")
	public List<Entity> findAll() {
		Query query = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e");
		return (List<Entity>)query.getResultList();
	}
}

