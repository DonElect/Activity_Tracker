package com.donatus.activityTracker.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "first_name", columnDefinition = "VARCHAR(25)")
    private String firstName;
    @Column(name = "last_name", columnDefinition = "VARCHAR(25)")
    private String lastName;
    @Column(name = "email", columnDefinition = "VARCHAR(35)")
    private String email;
    @Column(name = "password", columnDefinition = "VARCHAR(15)")
    private  String password;
    @Column(name = "user_name", columnDefinition = "VARCHAR(15)")
    private String userName;
    @Column(name = "occupation", columnDefinition = "VARCHAR(25)")
    private String occupation;
    @Column(name = "address", columnDefinition = "VARCHAR(45)")
    private String address;

    @OneToMany(fetch =  FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
    private List<Task> tasks;


    public Users(String firstName, String lastName, String email, String password, String userName, String occupation, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.occupation = occupation;
        this.address = address;
    }

    // Add new task to a already existing user
    public void addTask(Task task){
        if (tasks == null){
            tasks = new ArrayList<>();
        }

        tasks.add(task);
        task.setUsers(this);
    }
}
