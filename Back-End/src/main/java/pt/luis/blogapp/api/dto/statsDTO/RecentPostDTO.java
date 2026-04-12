package pt.luis.blogapp.api.dto.statsDTO;

import java.time.LocalDateTime;

public record RecentPostDTO(
        Long id,
        String title,
        LocalDateTime createdAt
){}
