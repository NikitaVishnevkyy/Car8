package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "lastname")
   private String lastName;

   @Column(name = "email")
   private String email;

   @OneToOne(cascade = CascadeType.ALL)
   private Car car;

   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public User(String firstName, String lastName, String email, Car car) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.car = car;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Car getCar(){
      return car;
   }

   public void setCar(Car car){
      this.car = car;
   }

   @Override
   public String toString() {
      return "firstName: " + firstName + ", " +
              "lastName: " + lastName + ", " +
              "email: " + email + ", " +
              car.toString();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      User user = (User) o;

      if (!Objects.equals(id, user.id)) return false;
      if (!Objects.equals(firstName, user.firstName)) return false;
      if (!Objects.equals(lastName, user.lastName)) return false;
      if (!Objects.equals(email, user.email)) return false;
      return Objects.equals(car, user.car);
   }

   @Override
   public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      result = 31 * result + (email != null ? email.hashCode() : 0);
      result = 31 * result + (car != null ? car.hashCode() : 0);
      return result;
   }
}
