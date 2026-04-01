package com.take_home_test.tht.controller;

import com.take_home_test.tht.dto.WebResponse;
import com.take_home_test.tht.service.reportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class reportController {

    private final reportService reportService;

    @GetMapping("/summary")
    public WebResponse<Map<String, Object>> getSummary() {
        return WebResponse.<Map<String, Object>>builder()
                .status("success").data(reportService.getGeneralSummary()).build();
    }

    @GetMapping("/event/{id}")
    public WebResponse<Map<String, Object>> getEventReport(@PathVariable Long id) {
        return WebResponse.<Map<String, Object>>builder()
                .status("success").data(reportService.getReportByEvent(id)).build();
    }
}
