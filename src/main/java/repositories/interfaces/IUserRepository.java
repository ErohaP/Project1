package repositories.interfaces;

import domain.UserLoginData;
import domain.User;

public interface IUserRepository extends IEntityRepository<User> {
    void remove(User entity);

    User getUserByID(long id);

    User findUserByLogin(UserLoginData data);

    User getUserByUsername(String username);
}
