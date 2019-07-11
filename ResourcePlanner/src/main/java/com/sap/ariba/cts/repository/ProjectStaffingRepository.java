package com.sap.ariba.cts.repository;

import com.sap.ariba.cts.model.entity.ProjectStaffing;
import com.sap.ariba.cts.model.entity.Region;
import com.sap.ariba.cts.model.entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ProjectStaffingRepository<T, String extends Serializable> extends BaseEntityRepository<ProjectStaffing, String> {

    @Override
    @Query("select prst from ProjectStaffing prst where prst.active=true")
    List<ProjectStaffing> getActiveEntity();

    ProjectStaffing getProjectStaffingByBaseId(String baseId);

    List<ProjectStaffing> getProjectStaffingByCustomerName(String customerName);

    ProjectStaffing getProjectStaffingBySalesOrder(String salesOrder);

    List<ProjectStaffing> getProjectStaffingByStaffOwner(UserInfo staffOwner);

    List<ProjectStaffing> getProjectStaffingByRegion(Region region);

}
