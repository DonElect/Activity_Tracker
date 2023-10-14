package com.donatus.activityTracker.dto;

import com.donatus.activityTracker.entity.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
public class TaskResponseDTO {
    private String activity;
    private String activityDetail;
    private String dueDate;
    private Instant createdDate;
    private Instant lastUpdate;
    private Date closeDate;
    private Status status;

    public TaskResponseDTO(String activity, String activityDetail, String  dueDate, Instant createdDate, Instant lastUpdate, Status status) {
        this.activity = activity;
        this.activityDetail = activityDetail;
        this.createdDate = createdDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
        this.dueDate = dueDate;
    }
}
