package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void save(User user);
   List<User> findAll();
   void deleteAllUsers();
   User findOwner(String car_name, String car_series);
}