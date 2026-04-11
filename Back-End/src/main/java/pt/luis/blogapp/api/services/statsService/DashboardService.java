package pt.luis.blogapp.api.services.statsService;

import pt.luis.blogapp.api.dto.statsDTO.DashboardStatsDTO;


public interface DashboardService {
    DashboardStatsDTO getStats();
}
