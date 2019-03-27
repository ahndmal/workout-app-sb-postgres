package com.bh.workouts.bhworkoutapp.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class ExerciseName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String category;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "exercise_id", referencedColumnName = "exercise_id", nullable = false)
//    private Exercise exercise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

}