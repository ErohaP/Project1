package services.interfaces;

import domain.User;

public interface IUserService {
    User getUserByID(long id);
    User getUserByUsername(String username);

    void addUser(User user);

    void updateUser(User user);
}
