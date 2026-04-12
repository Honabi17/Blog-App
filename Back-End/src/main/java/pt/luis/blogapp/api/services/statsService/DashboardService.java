package pt.luis.blogapp.api.services.statsService;

import pt.luis.blogapp.api.dto.statsDTO.DashboardStatsDTO;
import pt.luis.blogapp.api.dto.statsDTO.EarningStatsDTO;
import pt.luis.blogapp.api.dto.statsDTO.TrafficStatsDTO;

import java.util.List;


public interface DashboardService {

    DashboardStatsDTO getStats();

    List<TrafficStatsDTO> getTrafficStats();

    List<EarningStatsDTO> getEarningStats();
}
