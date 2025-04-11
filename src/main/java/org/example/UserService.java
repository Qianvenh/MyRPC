package org.example;

public interface UserService {
    User getUserByUserId(Integer id);

    Integer insertUserId(User user);
}
