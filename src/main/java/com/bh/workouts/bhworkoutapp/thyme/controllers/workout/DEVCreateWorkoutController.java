package com.bh.workouts.bhworkoutapp.thyme.controllers.workout;

import com.bh.workouts.bhworkoutapp.models.Workout;
import com.bh.workouts.bhworkoutapp.repositories.WorkoutRepository;
import com.bh.workouts.bhworkoutapp.services.user.UserServiceImpl;
import com.bh.workouts.bhworkoutapp.services.workout.WorkoutColorService;
import com.bh.workouts.bhworkoutapp.services.dates.WorkoutDateTrimToMonthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Profile("DEV")
public class DEVCreateWorkoutController {

    private final UserServiceImpl userService;
    private final WorkoutRepository workoutRepository;

    private Logger logger = LoggerFactory.getLogger(DEVCreateWorkoutController.class);

    @Autowired
    public DEVCreateWorkoutController(UserServiceImpl userService,
                                      WorkoutRepository workoutRepository) {
        this.userService = userService;
        this.workoutRepository = workoutRepository;
    }

    @GetMapping("/workout/new")
    public String getCreateForm(Model model) {

        model.addAttribute("workout", new Workout());

        return "workouts/create-workout";
    }

    @PostMapping("/workout/new")
    public String createWorkout(@ModelAttribute Workout workout, Model model) throws ParseException {

        workout.setWorkoutMonth(WorkoutDateTrimToMonthService.getTrimmedMonthFromDate(workout.getWorkoutDate()));
        workout.setImagePath(WorkoutColorService.workoutColorSet(workout.getWorkoutType()));
        workout.setUser(userService.findUserByLogin("anma"));

        Date dayDate = new SimpleDateFormat("MM/dd/yyyy").parse(workout.getWorkoutDate());

        workout.setWorkoutDay(new SimpleDateFormat("EEEE").format(dayDate));

        workoutRepository.save(workout);

        model.addAttribute("newWorkout", workout);

        logger.info("========= Workout created: " + workout);

        return "redirect:/workout/" + workout.getId();
    }

}
