package com.scoreboard.match.controller.request.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = TeamValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface ValidTeam {

    String message() default "{Team not exist in our system.}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
