package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> getAll();

    User getById(Long id);

    void delete(Long id, User user);

    void update(User user);
}
