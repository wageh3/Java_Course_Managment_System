public class Instructor extends Person {

  Instructor() {}

  FileManager f = new FileManager();

  Instructor(String name, String email, String password, String gender) {
    super.id = f.lastUsedId("users.txt");
    super.name = name;
    super.email = email;
    super.password = password;
    super.gender = gender;
    super.role = "instructor";
  }

  public int getId() {
    return this.id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  public String getPassword() {
    return this.password;
  }

  public String getGender() {
    return this.gender;
  }

  public String getRole() {
    return this.role;
  }

  @Override
  public String toString() {
    return (
      getId() +
      "," +
      getName() +
      "," +
      getEmail() +
      "," +
      getPassword() +
      "," +
      getGender() +
      "," +
      getRole()
    );
  }
}
