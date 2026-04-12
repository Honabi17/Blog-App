package pt.luis.blogapp.api.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.luis.blogapp.api.dto.statsDTO.DashboardStatsDTO;
import pt.luis.blogapp.api.dto.statsDTO.EarningStatsDTO;
import pt.luis.blogapp.api.dto.statsDTO.RecentPostDTO;
import pt.luis.blogapp.api.dto.statsDTO.TrafficStatsDTO;
import pt.luis.blogapp.api.services.statsService.DashboardService;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public DashboardStatsDTO getStats(){
        return dashboardService.getStats();
    }

    @GetMapping("/traffic")
    public List<TrafficStatsDTO> getTrafficStats(){
        return dashboardService.getTrafficStats();
    }

    @GetMapping("/earnings")
    public List<EarningStatsDTO> getEarningStats(){
        return dashboardService.getEarningStats();
    }

    @GetMapping("/recent-posts")
    public List<RecentPostDTO> getRecentPosts(){
        return dashboardService.getRecentPosts();
    }
}
