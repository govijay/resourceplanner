package com.sap.ariba.cts.service.impl;

import com.sap.ariba.cts.model.entity.SubTeam;
import com.sap.ariba.cts.model.entity.Team;
import com.sap.ariba.cts.repository.SubTeamRepository;
import com.sap.ariba.cts.repository.TeamRepository;
import com.sap.ariba.cts.service.SubTeamService;
import com.sap.ariba.cts.utils.GenericUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SubTeamServiceImpl implements SubTeamService {

    private static Logger logger = LoggerFactory.getLogger(SubTeamServiceImpl.class);

    @Autowired
    private SubTeamRepository subTeamRepo;

    @Autowired
    private TeamRepository teamRepo;

    @Override
    public Long countSubTeams() {
        return subTeamRepo.count();
    }

    @Override
    public SubTeam createSubTeam(SubTeam subTeam) {

        String teamId = subTeam.getTeamBaseId();
        if (teamId != null) {
            logger.info(teamId);
            Team teamFromDB = teamRepo.getTeamByBaseId(teamId);
            if (teamFromDB != null) {
                subTeam.setTeam(teamFromDB);
            }
        }
        subTeamRepo.save(subTeam);
        return (SubTeam) subTeamRepo.findById(subTeam.getBaseId()).get();
    }

    @Override
    public SubTeam updateSubTeam(SubTeam subTeam) {

        Team teamFromDb = null;
        String teamBaseId = subTeam.getTeamBaseId();
        if (teamBaseId != null) {
            teamFromDb = teamRepo.getTeamByBaseId(teamBaseId);
        }

        SubTeam subTeamToUpdate = subTeamRepo.getSubTeamByBaseId(subTeam.getBaseId());

        if (subTeamToUpdate != null) {
            GenericUtil.copyNonNullProperties(subTeam, subTeamToUpdate);
            if (teamFromDb != null) subTeamToUpdate.setTeam(teamFromDb);
            subTeamRepo.save(subTeamToUpdate);
        }
        return subTeamToUpdate;
    }

    @Override
    public SubTeam deactivateSubTeam(String baseId) {
        SubTeam subTeamToDeactivate = null;
        if (baseId != null) {
            subTeamToDeactivate = subTeamRepo.getSubTeamByBaseId(baseId);
        }
        if (subTeamToDeactivate != null && subTeamToDeactivate.isActive()) {
            subTeamToDeactivate.setActive(false);
            subTeamRepo.save(subTeamToDeactivate);
        }
        return subTeamToDeactivate;
    }

    @Override
    public SubTeam reactivateSubTeam(String baseId) {
        SubTeam subTeamToReactivate = null;
        if (baseId != null) {
            subTeamToReactivate = subTeamRepo.getSubTeamByBaseId(baseId);
        }
        if (subTeamToReactivate != null && !subTeamToReactivate.isActive()) {
            subTeamToReactivate.setActive(true);
            subTeamRepo.save(subTeamToReactivate);
        }
        return subTeamToReactivate;
    }

    @Override
    public Boolean deleteSubTeam(String baseId) {
        SubTeam subTeamToDelete = null;
        if (baseId != null) {
            subTeamToDelete = subTeamRepo.getSubTeamByBaseId(baseId);
        }
        if (subTeamToDelete != null) {
            subTeamRepo.delete(subTeamToDelete);
        }
        return true;
    }

    @Override
    public Boolean isSubTeamActive(String baseId) {
        SubTeam subTeam = subTeamRepo.getSubTeamByBaseId(baseId);
        if (subTeam != null) {
            return subTeam.isActive();
        }
        return false;
    }

    @Override
    public Boolean isSubTeamExists(String baseId) {
        return subTeamRepo.existsById(baseId);
    }

    @Override
    public SubTeam getSubTeamByBaseId(String baseId) {
        return subTeamRepo.getSubTeamByBaseId(baseId);
    }

    @Override
    public Team getTeamBySubBaseId(String baseId) {

        Team team = null;
        SubTeam subTeam = subTeamRepo.getSubTeamByBaseId(baseId);
        if (subTeam != null) {
            team = subTeam.getTeam();
        }
        return team;
    }

    @Override
    public List<SubTeam> getSubTeams() {
        return subTeamRepo.getActiveEntity();
    }

    @Override
    public List<SubTeam> getSubTeamsByTeamBaseId(String teamBaseId) {
        List<SubTeam> subTeams = new ArrayList<>();

        Team team = teamRepo.getTeamByBaseId(teamBaseId);

        if (team != null) {
            subTeams = (List<SubTeam>) subTeamRepo.getSubTeamsByTeam(team);
        }
        return subTeams;
    }
}
