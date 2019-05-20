package com.sap.ariba.cts.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

  /**
   * Gets team by team code.
   *
   * @param teamCode the team code
   * @return the team by team code
   */
  Team getTeamByTeamCode(String teamCode);

  /**
   * Gets team by team name.
   *
   * @param teamName the team name
   * @return the team by team name
   */
  Team getTeamByTeamName(String teamName);

  /**
   * Gets teams by department base id.
   *
   * @param departmentBaseId the department base id
   * @return the teams by department base id
   */
  Collection<Team> getTeamsByDepartmentBaseId(String departmentBaseId);

}
