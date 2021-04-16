package io.ootb.schoolinfrontend.domain.dao;

import java.util.Optional;

import javax.inject.Provider;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import io.ootb.schoolinfrontend.domain.entity.Identifiable;
import io.ootb.schoolinfrontend.domain.entity.User;
import io.ootb.schoolinfrontend.domain.entity.UserSession;
import io.ootb.schoolinfrontend.util.Generics;

public abstract class AbstractDAO<T extends Identifiable> implements AutoCloseable {

	// Query constants
    public static final int QUERY_FROM_FIRST = 0;
    public static final int QUERY_ALL = -1;

    @Autowired
    protected UserSession skyeSession;

    @Autowired
    private Provider<EntityManager> emProvider;
    
    @SuppressWarnings("unchecked")
	private Class<T> entityClass = (Class<T>) Generics.getTypeParameter(getClass());
    
    
    public EntityManager getEm() {
        return emProvider.get();
    }
    
    public Session getSession() {
        return getEm().unwrap(Session.class);
    }
    
    public User getCurrentUser() {
        return skyeSession.getUser();
    }
    
    public CriteriaBuilder getCriteriaBuilder() {
        return getEm().getCriteriaBuilder();
    }
    
    /**
     * Update the given instance of the object
     *
     * @param instance the instance to update
     */
    public void update(T instance) {
        update(instance.getId(), instance);
    }

    /**
     * Delete the given instance of the object
     *
     * @param instance
     */
    public void delete(T instance) {
        delete(instance.getId());
    }
    
    public Optional<T> get(String id) {
        T result = getEm().find(entityClass, id);

        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
    
    public boolean delete(String id) {
        T entity = getEm().find(entityClass, id);
        if (entity != null) {
            getEm().remove(entity);
            return true;
        }
        return false;
    }
    
    /**
     * Creates a new, persistent instance of the domain object, based on
     * the newInstance given.  This method should check to ensure that
     * the domain object is not already found, and if it is, the method should
     * throw an exception.
     *
     * @param newInstance The instance to be created.
     * @return The created instance.
     * @throws EntityExistsException Indicates that the Entity to be created
     *                               would be a duplicate record.
     */
    public T create(T newInstance) {
        getSession().saveOrUpdate(newInstance);
        return newInstance;
    }

    /**
     * Creates or updates an instance of the domain object
     *
     * @param instance The instance to be created or updated.
     * @return The instance.
     */
    public T saveOrUpdate(T instance) {
        getSession().saveOrUpdate(instance);
        return instance;
    }

    /**
     * Saves an instance of the domain object
     *
     * @param instance The instance to be saved.
     * @return The instance.
     */
    public T save(T instance) {
        getSession().save(instance);
        return instance;
    }

    /**
     * Persist a domain object.
     * Persist is slightly different than Save in that it will not initialize object IDs that come from the database.
     * See discussion here : http://stackoverflow.com/questions/5862680/whats-the-advantage-of-persist-vs-save-in-hibernate
     *
     * @param instance The instance to be saved.
     * @return The instance.
     */
    public T persist(T instance) {
        getSession().persist(instance);
        return instance;
    }
    
    /**
     * Updates the instance found at the given id.  These methods
     * should check to ensure that the domain object already exists, and if
     * it does not, an exception should be thrown.
     *
     * @param id              The Id of the Entity to be updated.
     * @param updatedInstance The updated instance of the Entity.
     * @return The updated instance of the Entity.
     * @throws EntityNotFoundException Indicates that the Entity to be updated
     *                                 was not found, and should be created first.
     */
    public T update(String id, T updatedInstance) {
        updatedInstance = this.getEm().merge(updatedInstance);
        return updatedInstance;
    }
    
    @Override
    // This is implemented as a convenience so that DAOs can be defined in try with resource blocks.
    public void close() throws Exception {
    }

}
