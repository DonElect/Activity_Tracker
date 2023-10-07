package com.donatus.activityTracker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "activity")
    private String activity;

    @Column(name = "activity_detail", columnDefinition = "VARCHAR(45)")
    private String activityDetail;

    @Column(name = "creation_time")
    @CreationTimestamp
    private Instant createdDate;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Instant lastUpdate;

    @Column(name = "close_time")
    private Date closeDate;

    @Column(name = "activity_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_task_id")
    private Users users;

    public Task(String activity, String activityDetail, Instant createdDate, Instant lastUpdate, Status status) {
        this.activity = activity;
        this.activityDetail = activityDetail;
        this.createdDate = createdDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
    }
}
