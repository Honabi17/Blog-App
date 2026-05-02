package pt.luis.blogapp.api.dto.statsDTO;

public record DashboardStatsDTO(
        long pageviews,
        long visitors,
        long categories,
        long posts,
        long comments
) {}
