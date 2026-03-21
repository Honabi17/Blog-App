package pt.luis.blogapp.api.dto.personDTO;

public record PersonStatsDTO(
        int categoriesCount,
        int postsCount,
        int commentsCount
) {}
