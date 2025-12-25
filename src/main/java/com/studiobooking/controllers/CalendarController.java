package com.studiobooking.controllers;

import com.studiobooking.dto.CalendarDayResponse;
import com.studiobooking.services.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    // Month View
    @GetMapping("/month")
    public ResponseEntity<List<CalendarDayResponse>> getMonth(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(calendarService.getMonthView(year, month));
    }

    // Week View
    @GetMapping("/week")
    public ResponseEntity<List<CalendarDayResponse>> getWeek(
            @RequestParam String fromDate
    ) {
        return ResponseEntity.ok(
                calendarService.getWeekView(LocalDate.parse(fromDate))
        );
    }

    // Day View
    @GetMapping("/day")
    public ResponseEntity<CalendarDayResponse> getDay(
            @RequestParam String date
    ) {
        return ResponseEntity.ok(
                calendarService.getDayView(LocalDate.parse(date))
        );
    }
}
