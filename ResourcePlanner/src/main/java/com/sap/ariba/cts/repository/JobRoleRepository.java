package com.sap.ariba.cts.repository;

import com.sap.ariba.cts.model.entity.JobRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * The interface JobRole repository.
 *
 * @param <T>      the type parameter
 * @param <String> the type parameter
 * @author Vijay.G
 * @version $Id : $
 */
@Repository
public interface JobRoleRepository<T, String extends Serializable> extends BaseEntityRepository<JobRole, String> {

    @Override
    @Query("select jr from JobRole jr where jr.active=true")
    List<JobRole> getActiveEntity();

    JobRole getJobRoleByBaseId(String baseId);

}
