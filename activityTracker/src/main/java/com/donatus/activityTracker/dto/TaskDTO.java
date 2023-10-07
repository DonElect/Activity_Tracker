package com.donatus.activityTracker.dto;

import com.donatus.activityTracker.entity.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.Instant;


@Getter
@Setter
public class TaskDTO {
    private String activity;
    private String activityDetail;
    private Instant createdDate;
    private Instant lastUpdate;
    private Date closeDate;
    private Status status;

    public TaskDTO(String activity, String activityDetail, Instant createdDate, Instant lastUpdate, Status status) {
        this.activity = activity;
        this.activityDetail = activityDetail;
        this.createdDate = createdDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
    }
}
