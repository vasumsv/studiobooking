package com.studiobooking.services;

import com.studiobooking.dto.CalendarDayResponse;
import com.studiobooking.entities.Booking;
import com.studiobooking.repositories.BookingRepository;
import com.studiobooking.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final BookingRepository bookingRepository;
    private final CurrentUser currentUser;

    public List<CalendarDayResponse> getMonthView(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();
        return buildCalendarRange(start, end);
    }

    public List<CalendarDayResponse> getWeekView(LocalDate fromDate) {
        LocalDate start = fromDate;
        LocalDate end = start.plusDays(6);
        return buildCalendarRange(start, end);
    }

    public CalendarDayResponse getDayView(LocalDate date) {
        UUID studioId = currentUser.getCurrentStudioId();

        List<Booking> bookings = bookingRepository.findByStudioIdAndStartBetween(
                studioId,
                date.atStartOfDay(),
                date.atTime(23, 59)
        );

        return new CalendarDayResponse(
                date,
                bookings,
                determineCalendarStatus(bookings)
        );
    }

    private List<CalendarDayResponse> buildCalendarRange(LocalDate start, LocalDate end) {
        UUID studioId = currentUser.getCurrentStudioId();
        List<CalendarDayResponse> result = new ArrayList<>();

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            List<Booking> bookings = bookingRepository.findByStudioIdAndStartBetween(
                    studioId,
                    date.atStartOfDay(),
                    date.atTime(23, 59)
            );

            String status = determineCalendarStatus(bookings);
            result.add(new CalendarDayResponse(date, bookings, status));
        }

        return result;
    }

    private String determineCalendarStatus(List<Booking> bookings) {
        if (bookings.isEmpty()) return "EMPTY";

        long confirmedCount = bookings.stream()
                .filter(b -> "CONFIRMED".equalsIgnoreCase(b.getStatus()))
                .count();

        long tentativeCount = bookings.stream()
                .filter(b -> "TENTATIVE".equalsIgnoreCase(b.getStatus()))
                .count();

        if (confirmedCount >= 1) return "GREEN";
        if (tentativeCount >= 1) return "YELLOW";

        return "RED";
    }
}
