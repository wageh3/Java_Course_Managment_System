import java.util.ArrayList;

public class Course {

  private static int counter = 1;
  private int id;
  private String name;
  private int instructorId;
  private int room;
  private double price;
  private String branch;
  private String startDate;
  private String endDate;
  private String daysOftheCourse;
  private String studentsGradesMinimum;
  private int parentCourseId;
  private double parentCoursePrice;
  private int NumberOfStudents;
  FileManager f = new FileManager();

  public Course(int id, String name) {
    this.id = counter++;
    this.name = name;
  }

  public Course(
    String name,
    int instructorId,
    int room,
    double price,
    String branch,
    String startDate,
    String endDate,
    String daysOfTheCourse,
    int NumberOfStudents,
    String parentCourse,
    double parentCoursePrice,
    String StudentGradesMinimum
  ) {
    this.id = f.lastUsedId("courses.txt");
    this.name = name;
    this.instructorId = instructorId;
    this.room = room;
    this.price = price;
    this.branch = branch;
    this.startDate = startDate;
    this.endDate = endDate;
    this.daysOftheCourse = daysOfTheCourse;
    this.NumberOfStudents = NumberOfStudents;
    // this.parentCourseId = parentCourseId;
    this.parentCoursePrice = parentCoursePrice;
    // this.studentsGradesMinimum = studentsGradesMinimum;
  }

  public Course(
    int id,
    String name,
    int instructorId,
    int room,
    double price,
    String branch,
    String startDate,
    String endDate,
    String daysOfTheCourse,
    int NumberOfStudents,
    String parentCourse,
    double parentCoursePrice,
    String StudentGradesMinimum
  ) {
    this.id = f.lastUsedId("courses.txt");
    this.name = name;
    this.instructorId = instructorId;
    this.room = room;
    this.price = price;
    this.branch = branch;
    this.startDate = startDate;
    this.endDate = endDate;
    this.daysOftheCourse = daysOfTheCourse;
    this.NumberOfStudents = NumberOfStudents;
    // this.parentCourseId = parentCourseId;
    this.parentCoursePrice = parentCoursePrice;
    // this.studentsGradesMinimum = studentsGradesMinimum;
  }

  public String getStudentGrades() {
    return this.studentsGradesMinimum;
  }

  public void setStudentGrades(String studentGrades) {
    this.studentsGradesMinimum = studentGrades;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getInstructorId() {
    return instructorId;
  }

  public void setInstructorId(int instructorId) {
    this.instructorId = instructorId;
  }

  public int getRoom() {
    return room;
  }

  public void setRoom(int room) {
    this.room = room;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getDaysOftheCourse() {
    return daysOftheCourse;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public int getParentCourseId() {
    return parentCourseId;
  }

  public double getParentCoursePrice() {
    return parentCoursePrice;
  }

  public int getTotalNumberOfStudents() {
    return this.NumberOfStudents;
  }

  public static void displayCoursesDetails(ArrayList<Course> courses) {
    System.out.printf(
      "%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n",
      "ID",
      "Name",
      "Instructor ID",
      "Room",
      "Price",
      "Branch",
      "Start Date",
      "End Date",
      "Days of the Course",
      "Num of Students",
      "Parent Course ID",
      "Parent Course Price",
      "Total Students"
    );
    System.out.println(
      "--------------------------------------------------------------------------------------------------------------------------------------------------"
    );

    for (Course course : courses) {
      System.out.printf(
        "%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n",
        course.getId(),
        course.getName(),
        course.getInstructorId(),
        course.getRoom(),
        course.getPrice(),
        course.getBranch(),
        course.getStartDate(),
        course.getEndDate(),
        course.getDaysOftheCourse(),
        course.getTotalNumberOfStudents(),
        course.getParentCourseId(),
        course.getParentCoursePrice(),
        ""
      );
    }

    System.out.println(
      "--------------------------------------------------------------------------------------------------------------------------------------------------"
    );
  }

  public static void displayCoursesnameandid(ArrayList<Course> courses) {
    System.out.printf("%-15s%-15s%n", "ID", "Name");
    System.out.println("-------------------------------------------");

    for (Course course : courses) {
      System.out.printf("%-15s%-15s%n", course.getId(), course.getName(), "");
    }

    System.out.println("--------------------------------------------");
  }

  @Override
  public String toString() {
    return (
      getId() +
      "," +
      getName() +
      "," +
      getInstructorId() +
      "," +
      getRoom() +
      "," +
      getPrice() +
      "$" +
      "," +
      getBranch() +
      "," +
      getStartDate() +
      "," +
      getEndDate() +
      "," +
      getDaysOftheCourse() +
      "," +
      getTotalNumberOfStudents() +
      "," +
      getParentCourseId() +
      "," +
      getParentCoursePrice() +
      "$" +
      "," +
      getTotalNumberOfStudents()
    );
  }
}
