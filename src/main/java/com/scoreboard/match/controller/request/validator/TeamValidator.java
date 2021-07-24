package com.scoreboard.match.controller.request.validator;

import com.scoreboard.match.entity.TeamEntity;
import com.scoreboard.match.exception.TeamNotFoundException;
import com.scoreboard.match.service.TeamService;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class TeamValidator implements ConstraintValidator<ValidTeam, String> {

    private TeamService teamService;

    public TeamValidator(TeamService teamService) {
        this.teamService = teamService;
    }


    @Override
    public boolean isValid(String teamName, ConstraintValidatorContext context) {

        TeamEntity teamEntity = null;
        try {
            teamEntity = teamService.getTeam(teamName);
            if (teamEntity != null) {
                return true;
            }
        } catch (TeamNotFoundException e) {
            return false;
        }
        return false;
    }
}
