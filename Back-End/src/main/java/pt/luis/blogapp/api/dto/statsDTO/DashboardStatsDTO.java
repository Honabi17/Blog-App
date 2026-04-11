package pt.luis.blogapp.api.dto.statsDTO;

public record DashboardStatsDTO(
        long pageviews,
        long visitors,
        long posts,
        long comments
) {}
