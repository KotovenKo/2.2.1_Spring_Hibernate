package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("Misha", "Mishev", "Mishev@mail.ru"));
//      userService.add(new User("Kesha", "keshev", "keshev@mail.ru"));
//      userService.add(new User("Trisha", "Trishev", "Trishev@mail.ru"));
      //userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      //userService.clearUsers();


//      User userMisha = new User("Misha", "Mishev", "Mishev@mail.ru");
//      User userKesha = new User("Kesha", "keshev", "keshev@mail.ru");
//      User userTrisha = new User("Trisha", "Trishev", "Trishev@mail.ru");
//      Car carVolvo = new Car("Volvo", 53);
//      Car carMazda = new Car("Mazda", 20);
//      Car carMers = new Car("Mers", 40);
//      userMisha.setCar(carVolvo);
//      userKesha.setCar(carMazda);
//      userTrisha.setCar(carMers);
//      userService.add(userMisha);
//      userService.add(userKesha);
//      userService.add(userTrisha);



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car:  "+ user.getCar());
         System.out.println();
      }

      System.out.println("*****Searching User by Car************");

      User userByCar = userService.getUserByCar("Mers", 40);
      if(userByCar.getFirstName() != null) {
         System.out.println(userByCar);
      } else {
         System.out.println("User not found");
      }


      context.close();
   }
}
