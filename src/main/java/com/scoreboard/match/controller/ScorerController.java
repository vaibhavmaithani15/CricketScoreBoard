package com.scoreboard.match.controller;

import com.scoreboard.match.controller.request.ScoreRequest;
import com.scoreboard.match.service.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Annotation indicating a method parameter should be bound to the body of the web request.
 * The body of the request is passed through an {@link HttpMessageConverter} to resolve the
 * method argument depending on the content type of the request. Optionally, automatic
 * validation can be applied by annotating the argument with {@code @Valid}.
 *
 * <p>Supported for annotated handler methods.
 *
 * @author Arjen Poutsma
 * @see RequestHeader
 * @see ResponseBody
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
 * @since 3.0
 */
@RestController
@RequestMapping(path = "/score")
@CrossOrigin(origins = "*")
public class ScorerController {
    private ScoreService scoreService;

    public ScorerController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * Whether body content is required.
     * <p>Default is {@code true}, leading to an exception thrown in case
     * there is no body content. Switch this to {@code false} if you prefer
     * {@code null} to be passed when the body content is {@code null}.
     *
     * @param request
     * @return ResponseEntity
     */
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity addScore(@Valid @RequestBody ScoreRequest request) {
        boolean success = scoreService.enterScore(request);
        if (success) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
