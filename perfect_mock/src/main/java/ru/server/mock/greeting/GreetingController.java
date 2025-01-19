package ru.server.mock.greeting;

//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import ru.server.mock.myTimer.MyTimer;
//
//import java.util.concurrent.atomic.AtomicLong;


import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.server.mock.myTimer.MyTimer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
public class GreetingController {
    private int timerMin;
    private int timerMax;


    @Autowired
    public GreetingController(@Value("${timerGreeting.min}") int timerMin, @Value("${timerGreeting.max}") int timerMax) {
        this.timerMin = timerMin;
        this.timerMax = timerMax;
        log.warn(" timerGreetingMin = " + timerMin + "  timerGreetingMax = " + timerMax);
    }

   // Logger logger = LoggerFactory.getLogger(GreetingController.class);
   // private static final String templateName = "%s";
   // private static final String templateGreeting = "Your greeting - %s!";

    //Считает количество запросов
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Oleg") String name,
                             @RequestParam(value = "greeting", defaultValue = "Hello") String greeting,
                             @RequestHeader(value =  "trace", defaultValue = "111111") String trace,
                             HttpServletResponse response) {

        MyTimer.myTimer(timerMin, timerMax);

        log.warn(" timerMin - " + timerMin + " timerMax - " + timerMax);
        response.addHeader("trace", trace);
       // return new Greeting(counter.incrementAndGet(), String.format(templateName, name), String.format(templateGreeting, greeting));
        return new Greeting(counter.incrementAndGet(), " timerMin - " + timerMin, " timerMax - " + timerMax);
    }

    @GetMapping("/timer")
    public Greeting greeting(@RequestParam(value = "timerMin", defaultValue = "timer_Min") int timer_Min,
                             @RequestParam(value = "timerMax", defaultValue = "timer_Max") int timer_Max,
                             HttpServletResponse response) {

        timerMin = timer_Min;
        timerMax = timer_Max;
        MyTimer.myTimer(timer_Min, timer_Max);

        log.warn(" timerMin - " + timer_Min + " timerMax - " + timer_Max);

        // return new Greeting(counter.incrementAndGet(), String.format(templateName, name), String.format(templateGreeting, greeting));
        return new Greeting(counter.incrementAndGet(), " timerMin - " + timer_Min, " timerMax - " + timer_Max);
    }



}