package com.bh.workouts.bhworkoutapp.thyme.controllers.exercise;

import com.bh.workouts.bhworkoutapp.models.Exercise;
import com.bh.workouts.bhworkoutapp.models.ExerciseName;
import com.bh.workouts.bhworkoutapp.models.Workout;
import com.bh.workouts.bhworkoutapp.repositories.ExerciseNameRepository;
import com.bh.workouts.bhworkoutapp.repositories.ExerciseRepository;
import com.bh.workouts.bhworkoutapp.repositories.WorkoutRepository;
import com.bh.workouts.bhworkoutapp.services.helpers.AuthInitiatorService;
import com.bh.workouts.bhworkoutapp.services.exercise.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EditExerciseController {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseService exerciseService;
    private final ExerciseNameRepository exerciseNameRepository;
    private final AuthInitiatorService authInitiatorService;

    @Autowired
    public EditExerciseController(ExerciseRepository exerciseRepository,
                                  WorkoutRepository workoutRepository,
                                  ExerciseService exerciseService,
                                  ExerciseNameRepository exerciseNameRepository,
                                  AuthInitiatorService authInitiatorService) {
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.exerciseService = exerciseService;
        this.exerciseNameRepository = exerciseNameRepository;
        this.authInitiatorService = authInitiatorService;
    }


    @GetMapping("/workout/{workoutId}/exercise/{exerciseId}/edit")
    public String getForm(@PathVariable long workoutId, @PathVariable long exerciseId,  Model model) {

        Workout workout = workoutRepository.findById(workoutId).get();

        Exercise exercise = exerciseRepository.findById(exerciseId).get();

        model.addAttribute("exercise", exercise);
        model.addAttribute("workout", workout);

        List<ExerciseName> exerciseList = exerciseNameRepository.findAll();
        List<ExerciseName> userExercisesList =
                exerciseService.getExercisesListByUserAndType(exerciseList,
                        exercise.getWorkout().getWorkoutType(),
                        authInitiatorService.getUserFromAuth());

        model.addAttribute("userExercisesList", userExercisesList);

        return "exercises/edit-exercise";
    }

    @PostMapping("/exercise/edit")
    public String editExercise(@ModelAttribute Exercise exercise) {

//        exercise.setWorkout(workoutRepository.findById(exercise.getWorkout().getId()).get());

        exerciseRepository.save(exercise);

        return "redirect:/exercise/" + exercise.getId() + "/view";
    }


}
