package com.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.dto.DashboardResponse;
import com.dashboard.service.DashboardService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PostMapping("/log")
    public ResponseEntity<String> logPayload(@RequestBody String payload) {

        dashboardService.addLog(payload);

        return ResponseEntity.ok("Payload Logged Successfully");
    }

    @GetMapping("/logs")
    public ResponseEntity<DashboardResponse> getLogs() {

        return ResponseEntity.ok(
                dashboardService.getDashboardData()
        );
    }
}