package com.sap.ariba.cts.model.entity;

import com.sap.ariba.cts.model.base.BaseEntity;
import com.sap.ariba.cts.model.dto.UserProjectsScheduleDto;
import com.sap.ariba.cts.model.support.ClassMetaProperty;
import com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_PROJECTS_SCHEUDLE")
@ClassMetaProperty(code = "UPS")
public class UserProjectsSchedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "upsSequenceGenerator")
    @GenericGenerator(name = "upsSequenceGenerator",
            strategy = "com.sap.ariba.cts.model.support.EntitySequenceNumberGenerator",
            parameters = {
                    @Parameter(name = EntitySequenceNumberGenerator.SEQUENCE_PARAM, value = "ups_seq"),
                    @Parameter(name = EntitySequenceNumberGenerator.INITIAL_PARAM, value = "1000"),
                    @Parameter(name = EntitySequenceNumberGenerator.INCREMENT_PARAM, value = "1")
            })
    @Column(name = "BASE_ID")
    private String baseId;

    @OneToOne
    private UserDetails userDetails;

    @OneToOne
    private ProjectStaffing projectStaffing;

    @Column(name = "TOTAL_HRS")
    private int totalHrs;

    @Column(name = "HRS_PER_DAY")
    private int hrsPerDay;

    @Column(name = "STARTDATE")
    private Date startDate;

    @Column(name = "ENDDATE")
    private Date endDate;

    @Column(name = "NOTES")
    private String notes;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "TASK_TYPE")
    private TaskType taskType;

    /**
     * Instantiates a new Base entity.
     */
    public UserProjectsSchedule() {
    }

    /**
     * Instantiates a new Base entity.
     *
     * @param active the active
     */
    public UserProjectsSchedule(boolean active, String baseId, UserDetails userDetails, ProjectStaffing projectStaffing,
                                int totalHrs, int hrsPerDay, Date startDate, Date endDate, String notes,
                                TaskType taskType) {
        super(active);
        this.baseId = baseId;
        this.userDetails = userDetails;
        this.projectStaffing = projectStaffing;
        this.totalHrs = totalHrs;
        this.hrsPerDay = hrsPerDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.taskType = taskType;
    }

    public static UserProjectsSchedule toEntity(UserProjectsScheduleDto userProjectsScheduleDto) {
        UserProjectsSchedule userProjectsSchedule = new UserProjectsSchedule(
                userProjectsScheduleDto.isActive(),
                userProjectsScheduleDto.getBaseId(),
                UserDetails.toEntity(userProjectsScheduleDto.getUserDetails()),
                ProjectStaffing.toEntity(userProjectsScheduleDto.getProjectStaffing()),
                userProjectsScheduleDto.getTotalHrs(),
                userProjectsScheduleDto.getHrsPerDay(),
                userProjectsScheduleDto.getStartDate(),
                userProjectsScheduleDto.getEndDate(),
                userProjectsScheduleDto.getNotes(),
                userProjectsScheduleDto.getTaskType());

        return userProjectsSchedule;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public ProjectStaffing getProjectStaffing() {
        return projectStaffing;
    }

    public void setProjectStaffing(ProjectStaffing projectStaffing) {
        this.projectStaffing = projectStaffing;
    }

    public int getTotalHrs() {
        return totalHrs;
    }

    public void setTotalHrs(int totalHrs) {
        this.totalHrs = totalHrs;
    }

    public int getHrsPerDay() {
        return hrsPerDay;
    }

    public void setHrsPerDay(int hrsPerDay) {
        this.hrsPerDay = hrsPerDay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    /**
     * Pre persist.
     */
    @PrePersist
    public void prePersist() {
        this.setActive(true);
    }
}
