package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * The interface Base entity repository.
 *
 * @param <T>      the type parameter
 * @param <String> the type parameter
 * @author Vijay.G
 * @version $Id : $
 */
@NoRepositoryBean
public interface BaseEntityRepository<T, String extends Serializable> extends JpaRepository<T, String> {

    /**
     * Gets active entity.
     *
     * @return the active entity
     */
    List<T> getActiveEntity();
}
