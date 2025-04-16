package org.example.UserService;

public interface UserService {
    User getUserByUserId(Integer id);

    Integer insertUserId(User user);
}
