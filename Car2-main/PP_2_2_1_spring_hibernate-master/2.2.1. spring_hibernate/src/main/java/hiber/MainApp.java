package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);
      carService.save(new Car("BMW", "320"));
      carService.save(new Car("BMW", "X5"));
      carService.save(new Car("BMW", "750"));
      carService.save(new Car("BMW", "X6"));

      UserService userService = context.getBean(UserService.class);
      userService.deleteAllUsers();
      List<Car> cars = carService.findAll();

      userService.save(new User("Sasha", "Sasha", "Sasha@mail.ru"));
      userService.save(new User("Nikita", "Nikita", "Nikita@mail.ru"));
      userService.save(new User("Vlad", "Vlad", "Vlad@mail.ru"));
      userService.save(new User("Olga", "Olga", "Olga@mail.ru"));


      List<User> users = userService.findAll();
      users.get(0).setCar(cars.get(0));
      users.get(1).setCar(cars.get(1));
      users.get(2).setCar(cars.get(2));
      users.get(3).setCar(cars.get(3));

      users.forEach(t->userService.save(t));


      List<User> uu = userService.findAll();
      for (User user : uu) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      System.out.println("Найти \"BMW X5\"");
      System.out.println(userService.findOwner("BMW", "X5"));
      context.close();
   }
}
