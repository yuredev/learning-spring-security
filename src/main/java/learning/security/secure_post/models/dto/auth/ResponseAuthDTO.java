package learning.security.secure_post.models.dto.auth;

import learning.security.secure_post.models.User;

public record ResponseAuthDTO(User user, String token) {
}