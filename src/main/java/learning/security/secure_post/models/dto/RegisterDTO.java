package learning.security.secure_post.models.dto;

import learning.security.secure_post.models.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
    
}
