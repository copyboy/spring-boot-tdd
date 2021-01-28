package cn.deepj.tdd;

import cn.deepj.tdd.domain.Events;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * TODO 求你写点注释吧
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-01-27 10:52
 */
@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/api/events", produces = "application/json;charset=UTF-8")
    public Events getEvent() {
        return eventService.getEventDetails("");
    }

//    @ExceptionHandler(EventNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    private void eventNotFoundHandler(EventNotFoundException ex) {}


}
