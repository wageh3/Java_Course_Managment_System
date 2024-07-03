public class Student extends Person {

  private String[] courses;

  // Default constructor
  public Student() {
    super.name = "";
    super.email = "";
    super.password = "";
    super.gender = "";
    super.role = "student";
  }

  public Student(
    int id,
    String name,
    String email,
    String password,
    String gender,
    String role
  ) {
    super(id, name, email, password, gender, role);
  }

  //to get the Student info during Login from object person
  public Student(Person p1) {
    this.id = p1.getId();
    this.name = p1.getName();
    this.email = p1.getEmail();
    this.password = p1.getPassword();
    this.gender = p1.getGender();
    this.role = "student";
  }

  FileManager f = new FileManager();

  // constructor for the first Time of Creating Student Obj so, increases the id by one
  public Student(
    String name,
    String email,
    String password,
    String gender,
    String[] courses
  ) {
    int currentId = f.lastUsedId("users.txt");
    this.id = currentId;
    this.name = name;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.role = "student";
    String row = Integer.toString(currentId);
    for (int i = 0; i < courses.length; i++) {
      row += " , " + courses[i];
    }
    f.Add(row, "student_course.txt");
  }

  public void UpdateName(String NewValue) {
    f.update("users.txt", Integer.toString(getId()), 1, NewValue);
  }

  public void UpdateEmail(String NewValue) {
    f.update("users.txt", Integer.toString(getId()), 2, NewValue);
  }

  public void UpdatePassword(String NewValue) {
    f.update("users.txt", Integer.toString(getId()), 3, NewValue);
  }

  public void StudentShowGrade() {
    f.showGrades(getId(), "Students_grades.txt");
  }

  public void Studentsurvey(String cname, String survey) {
    f.SavesurveytoFile(getId(), getName(), cname, survey, "survey.txt");
  }

  // public void showCourses(String id) {
  //   f.showCoursesForStudent(id, "student_course.txt");
  // }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setCourses(String[] courses) {
    this.courses = courses;
  }

  // Getters
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

  public String[] getCourses() {
    return courses;
  }

  public String searchForCourseName(int id) {
    return f.search(id, "courses.txt", 1);
  }

  public void showCourses() {
    // get the row from the student_course.txt file using the student id
    String row = f.GetIfExists(getId(), "student_course.txt");

    if (row != null && !row.isEmpty()) {
      String[] data = row.split(",");

      // Ensure that data has enough elements before accessing indices
      if (data.length > 5) {
        // search each course id from the row that I got, then search about it in courses.txt and return its name
        String newData =
          searchForCourseName(Integer.parseInt(data[1].trim())) +
          "," +
          searchForCourseName(Integer.parseInt(data[2].trim())) +
          "," +
          searchForCourseName(Integer.parseInt(data[3].trim())) +
          "," +
          searchForCourseName(Integer.parseInt(data[4].trim())) +
          "," +
          searchForCourseName(Integer.parseInt(data[5].trim()));

        System.out.println("Registered Courses: " + newData);
      } else {
        System.out.println("Not enough courses data available.");
      }
    } else {
      System.out.println("No courses data available for the student.");
    }
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
