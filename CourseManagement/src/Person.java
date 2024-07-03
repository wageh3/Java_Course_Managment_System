public abstract class Person {

  protected static int counter = 1;
  protected int id;
  protected String name;
  protected String email;
  protected String password;
  protected String gender;
  protected String role;

  public Person() {}

  public Person(
    int id,
    String name,
    String email,
    String password,
    String gender,
    String role
  ) {
    this.id = counter++;
    this.name = name;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.role = role;
  }

  public static Person login(String email, String password) {
    FileManager f = new FileManager();
    Person p = f.GetIfExists(email, "users.txt");
    if (p != null && p.getPassword().equals(password)) {
      return p;
    }
    return null;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getGender() {
    return gender;
  }

  public String getRole() {
    return role;
  }

  @Override
  public String toString() {
    return (
      "Person{" +
      "id=" +
      id +
      ", name='" +
      name +
      '\'' +
      ", email='" +
      email +
      '\'' +
      ", password=" +
      password +
      ", gender=" +
      gender +
      ", role='" +
      role +
      '\'' +
      '}'
    );
  }
}
