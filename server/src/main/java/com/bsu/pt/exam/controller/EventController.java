package com.bsu.pt.exam.controller;

import com.bsu.pt.exam.dto.EventDto;
import com.bsu.pt.exam.entity.Event;
import com.bsu.pt.exam.entity.Result;
import com.bsu.pt.exam.service.EventService;
import com.bsu.pt.exam.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    private EventService eventService;
    private ResultService resultService;

    @Autowired
    public EventController(EventService eventService, ResultService resultService) {
        this.eventService = eventService;
        this.resultService = resultService;
    }


    @PostMapping(value = "/{groupName}")
    public ResponseEntity<Event> addEvent(@PathVariable String groupName, @RequestBody EventDto event) {
        return new ResponseEntity<>(eventService.addEvent(groupName, event), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{eventName}")
    public ResponseEntity<Event> getEventById(@PathVariable String eventName) {
        return new ResponseEntity<Event>(eventService.getEventByName(eventName), HttpStatus.OK);
    }

    @GetMapping(value = "/all/{groupName}")
    public ResponseEntity<List<Event>> getAllByGroupName(@PathVariable String groupName) {
        return new ResponseEntity<List<Event>>(eventService.getAllByGroupName(groupName), HttpStatus.OK);
    }


    @PutMapping(value = "/result/{resultId}")
    public ResponseEntity<Result> updateResult(@PathVariable String resultId, @RequestBody Result result) {
        return new ResponseEntity<Result>(resultService.updateResult(result), HttpStatus.OK);
    }

    @PutMapping(value = "/event/{eventName}")
    public ResponseEntity<Event> updateEvent(@PathVariable String eventName, @RequestBody EventDto eventDto) {
        return new ResponseEntity<Event>(eventService.updateEvent(eventName, eventDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{eventName}")
    public ResponseEntity<?> delete(@PathVariable String eventName) {
        eventService.deleteEvent(eventName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{eventName}/result/{groupName}")
    public ResponseEntity<Result> createResult(@PathVariable String groupName, @PathVariable String eventName) {
        return new ResponseEntity<>(resultService.createResult(eventName, groupName), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{eventName}/result")
    public ResponseEntity<Result> getResult(@PathVariable String eventName) {
        return new ResponseEntity<>(resultService.getResultByEventId(eventName), HttpStatus.CREATED);
    }


}
