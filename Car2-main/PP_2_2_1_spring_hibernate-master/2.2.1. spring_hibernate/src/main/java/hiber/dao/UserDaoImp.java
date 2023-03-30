package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Transactional
   @Override
   public void save(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> findAll() {
      return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
   }

   @Transactional
   @Override
   public void deleteAllUsers() {
      List<User> users = findAll();
      for (User user : users) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }

   @Override
   public User findOwner(String car_name, String car_series) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where name = :car_name and series = :car_series")
              .setParameter("car_name", car_name)
              .setParameter("car_series", car_series);
      List<Car> cars = query.getResultList();
      if (cars.isEmpty()) throw new RuntimeException("Cars not found");
      Car car = cars.get(0);
      List<User> ListUser = findAll();
      return ListUser.stream()
              .filter(user -> user.getCar().equals(car))
              .findAny().orElseThrow(() -> new RuntimeException("Owner not found"));

   }
}

