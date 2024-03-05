package com.isimm.project.controller;

import com.isimm.project.classes.Schedule;
import com.isimm.project.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;


    @PostMapping("/add")
    public ResponseEntity<String> addSchedule(@RequestParam("className") String className,
                                              @RequestParam("dateSchedule") String dateSchedule,
                                              @RequestParam("imageSchedule") MultipartFile imageSchedule) {
        try {
            Schedule schedule = new Schedule();
            schedule.setClassName(className);
            schedule.setDateSchedule(dateSchedule);

            byte[] imageBytes = imageSchedule.getBytes();

            schedule.setImageSchedule(imageBytes);

            Schedule savedSchedule = scheduleRepository.save(schedule);

            return ResponseEntity.ok("Schedule added successfully with ID: " + savedSchedule.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding schedule: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return ResponseEntity.ok(schedules);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSchedule(@PathVariable Long id, @RequestBody Schedule updatedSchedule) {
        Schedule existingSchedule = scheduleRepository.findById(id).orElse(null);
        if (existingSchedule != null) {
            existingSchedule.setClassName(updatedSchedule.getClassName());
            existingSchedule.setDateSchedule(updatedSchedule.getDateSchedule());

            byte[] decodedImage = Base64.getDecoder().decode(updatedSchedule.getImageSchedule());
            existingSchedule.setImageSchedule(decodedImage);

            scheduleRepository.save(existingSchedule);
            return ResponseEntity.ok("Schedule updated successfully with ID: " + id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
        scheduleRepository.deleteById(id);
        return ResponseEntity.ok("Schedule deleted successfully with ID: " + id);
    }
}
