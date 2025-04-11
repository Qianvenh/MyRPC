package org.example;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("Client query user by id: " + id);
        Random random = new Random();
        return User.builder().userName(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean())
                .build();

    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("Insert data successfully");
        return 1;
    }
}
