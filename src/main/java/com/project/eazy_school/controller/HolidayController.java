package com.project.eazy_school.controller;

import com.project.eazy_school.model.Holiday;
import com.project.eazy_school.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HolidayController {

    private final HolidaysRepository holidaysRepository;

    @Autowired
    public HolidayController(HolidaysRepository holidaysRepository) {
        this.holidaysRepository = holidaysRepository;
    }

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model) {
        if (display != null && display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (display != null && display.equals("festival")) {
            model.addAttribute("festival", true);
        } else {
            model.addAttribute("federal", true);
        }
        Iterable<Holiday> holidays = holidaysRepository.findAll();
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(), false).toList();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
