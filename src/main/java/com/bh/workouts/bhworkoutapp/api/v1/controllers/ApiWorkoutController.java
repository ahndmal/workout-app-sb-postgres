package com.bh.workouts.bhworkoutapp.api.v1.controllers;

import com.bh.workouts.bhworkoutapp.models.Workout;
import com.bh.workouts.bhworkoutapp.repositories.WorkoutRepository;
import com.bh.workouts.bhworkoutapp.services.workout.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiWorkoutController.BASE_URL)
public class ApiWorkoutController {

    static final String BASE_URL = "/rest/api/v1/workouts";

    private final WorkoutRepository workoutRepository;
    private final WorkoutService workoutService;

    @Autowired
    public ApiWorkoutController(WorkoutRepository workoutRepository, WorkoutService workoutService) {
        this.workoutRepository = workoutRepository;
        this.workoutService = workoutService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Workout> getWorkoutsForApi() {

        return workoutRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Workout getWorkoutByIdApi(@PathVariable long id) {

        return workoutRepository.findById(id).get();
    }

    @GetMapping(path = "/date/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Workout> getWorkoutsByYear(@PathVariable(value = "year") Integer year) {
        return workoutService.getWorkoutsByYear(workoutRepository.findAll(), year);
    }

    @GetMapping(path = "/date/{year}/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Workout> getWorkoutsByYear(@PathVariable(value = "year") Integer year, @PathVariable String month) {
        return workoutService.getWorkoutsByYearMonth(workoutRepository.findAll(), year, month);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Workout createWorkout(@RequestBody Workout workout) {

        return workoutRepository.save(workout);
    }
}
