package com.scoreboard.match.controller.request.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = MatchValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
@Documented
public @interface ValidMatch {

    String message() default "{Mismatch between match balling team and bating team.}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
