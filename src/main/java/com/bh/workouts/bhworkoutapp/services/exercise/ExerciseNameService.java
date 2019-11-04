package com.bh.workouts.bhworkoutapp.services.exercise;

import com.bh.workouts.bhworkoutapp.models.ExerciseName;
import com.bh.workouts.bhworkoutapp.models.User;
import com.bh.workouts.bhworkoutapp.repositories.ExerciseNameRepository;
import com.bh.workouts.bhworkoutapp.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseNameService {

    private final ExerciseNameRepository exerciseNameRepository;
    private final UserServiceImpl userService;

    @Autowired
    public ExerciseNameService(ExerciseNameRepository exerciseNameRepository,
                               UserServiceImpl userService) {
        this.exerciseNameRepository = exerciseNameRepository;
        this.userService = userService;
    }

    public List<ExerciseName> getOwnExerciseNames() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User userByLogin = userService.findUserByLogin(authentication.getName());

        List<ExerciseName> exerciseNames = exerciseNameRepository.findAll();
        List<ExerciseName> userExerciseNames = new ArrayList<>();

        for (ExerciseName exerciseName : exerciseNames) {

            if (exerciseName.getAccess().equals("COMMON")) {
                userExerciseNames.add(exerciseName);
            }

            if (exerciseName.getAccess().equals("PERSONAL") && exerciseName.getUser().equals(userByLogin)) {
                userExerciseNames.add(exerciseName);
            }
        }

        return userExerciseNames;
    }

//    public void saveExerciseByUser(ExerciseName exerciseName) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        User userByLogin = userService.findUserByLogin(authentication.getName());
//
//        exerciseName.setUser(userByLogin);
//
//        exerciseNameRepository.save(exerciseName);
//    }

}
