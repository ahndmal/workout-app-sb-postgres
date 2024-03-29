package com.bh.workouts.bhworkoutapp.thyme.controllers.workout;

import com.bh.workouts.bhworkoutapp.models.Workout;
import com.bh.workouts.bhworkoutapp.repositories.WorkoutRepository;
import com.bh.workouts.bhworkoutapp.services.helpers.AuthInitiatorService;
import com.bh.workouts.bhworkoutapp.services.dates.CurrentWeekDaysService;
import com.bh.workouts.bhworkoutapp.services.workout.WorkoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WeekWorkoutViewController {

    private Logger logger = LoggerFactory.getLogger(WeekWorkoutViewController.class);

    private final WorkoutRepository workoutRepository;
    private final AuthInitiatorService authInitiatorService;
    private final WorkoutService workoutService;

    @Autowired
    public WeekWorkoutViewController(WorkoutRepository workoutRepository,
                                     AuthInitiatorService authInitiatorService,
                                     WorkoutService workoutService) {
        this.workoutRepository = workoutRepository;
        this.authInitiatorService = authInitiatorService;
        this.workoutService = workoutService;
    }

    @RequestMapping("/workouts/week")
    public String getWorkoutsWeekView(Model model) {

        List<Workout> workouts = workoutRepository.findAll();

        model.addAttribute("userWorkouts", workoutService.userWorkouts(workouts, authInitiatorService.getUserFromAuth()));
        model.addAttribute("daysMap", CurrentWeekDaysService.getCurrentWeekDaysAnsWorkouts());

        logger.info("=========== Getting Workout Week page");

        return "workouts/workouts-week-panels";
    }
}
