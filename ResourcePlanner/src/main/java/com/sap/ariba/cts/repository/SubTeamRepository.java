package com.sap.ariba.cts.repository;

import com.sap.ariba.cts.model.entity.SubTeam;
import com.sap.ariba.cts.model.entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * The interface Sub team repository.
 *
 * @param <T>      the type parameter
 * @param <String> the type parameter
 * @author Vijay.G
 * @version $Id : $
 */
@Repository
public interface SubTeamRepository<T, String extends Serializable> extends BaseEntityRepository<SubTeam, String> {

    @Override
    @Query("select st from SubTeam st where st.active=true")
    List<SubTeam> getActiveEntity();

    /**
     * Gets sub team by base id.
     *
     * @param baseId the base id
     * @return the sub team by base id
     */
    SubTeam getSubTeamByBaseId(String baseId);

    /**
     * Gets sub teams by team base id.
     *
     * @param team the team base id
     * @return the sub teams by team base id
     */
    Collection<SubTeam> getSubTeamsByTeam(Team team);

}
