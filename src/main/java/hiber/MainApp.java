package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car(12, "model1");
      Car car2 = new Car(21, "model2");
      Car car4 = new Car(7, "model4");

      user1.setCar(car1);
      user2.setCar(car2);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();

      System.out.println();
      System.out.println("_______ Пользователи с машинами _______");
      System.out.println();

      for (User user : users) {
         try {
            if (user.getCar().getModel() != null) {
               System.out.println("Id = " + user.getId());
               System.out.println("First Name = " + user.getFirstName());
               System.out.println("Last Name = " + user.getLastName());
               System.out.println("Email = " + user.getEmail());
               System.out.println("Series = " + user.getCar().getSeries());
               System.out.println("Model = " + user.getCar().getModel());
               System.out.println();
            }
         } catch (NullPointerException e) { }
      }

      System.out.println("_______ Пользователь по серии и модели машины _______");

      for (User user : users) {
         try {
            if (user.getCar().getSeries() != user2.getCar().getSeries() && user.getCar().getModel() != user2.getCar().getModel()) {
            } else {
               System.out.println("Id = " + user.getId());
               System.out.println("First Name = " + user.getFirstName());
               System.out.println("Last Name = " + user.getLastName());
               System.out.println("Email = " + user.getEmail());
            }
         } catch (NullPointerException e) { }
      }
      System.out.println("_____________________________________________________");

      context.close();
   }
}
