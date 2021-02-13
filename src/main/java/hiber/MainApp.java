package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User userOne = new User("User1", "Lastname1", "user1@mail.ru");
      User userTwo = new User("User2", "Lastname2", "user2@mail.ru");
      User userThree = new User("User3", "Lastname3", "user3@mail.ru");
      userOne.setCar(new Car("Audi", 3));
      userTwo.setCar(new Car("Mercedes-Benz", 221));
      userThree.setCar(new Car("Lada", 2101));

      userService.add(userOne);
      userService.add(userTwo);
      userService.add(userThree);

      System.out.println("tut_main3");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }
      User userCar = userService.getUserWithCar("Lada", 2101);
      System.out.println("");
      System.out.println("=======================================================");
      System.out.println(userCar);

      context.close();
   }
}
