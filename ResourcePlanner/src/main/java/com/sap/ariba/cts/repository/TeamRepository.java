package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sap.ariba.cts.model.entity.Department;
import com.sap.ariba.cts.model.entity.Team;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * The interface Team repository.
 *
 * @param <T>      the type parameter
 * @param <String> the type parameter
 * @author Vijay.G
 * @version $Id : $
 */
@Repository
public interface TeamRepository<T, String extends Serializable> extends BaseEntityRepository<Team, String> {

  @Override
  @Query("select t from Team t where t.active=true")
  List<Team> getActiveEntity();

  /**
   * Gets team by base id.
   *
   * @param baseId the base id
   * @return the team by base id
   */
  Team getTeamByBaseId(String baseId);

  Collection<Team> getTeamsByDepartment(Department department);

}
