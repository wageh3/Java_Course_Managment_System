import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Admin extends Person {

  FileManager f = new FileManager();

  Admin(Person p1) {
    this.name = p1.getName();
    this.email = p1.getEmail();
    this.password = p1.getPassword();
    this.id = p1.getId();
    this.role = p1.getRole();
  }

  public void addStudent() {
    Student s1 = Menus.GetStudentInfoForAdmin();
    String row = s1.toString();
    f.Add(row, "users.txt");
  }

  public void updateStudent() {
    Menus.UpdateInStudentAsAdmin();
  }

  public void deleteStudent() {
    int Id;
    Scanner input = new Scanner(System.in);
    while (true) {
      System.out.println("enter the student Id to delete :");
      try {
        Id = input.nextInt();
        break;
      } catch (Exception e) {
        System.out.println("Please enter a valid Integer!");
      }
    }

    // input.close();

    f.deleteById(Id, "users.txt");
  }

  public void showAllStudents() {
    String filePath =
      "D:\\wageh\\java\\Final_project_pl2v2\\yarab tb2a al a5era\\CourseManagement\\data\\users.txt";
    StringBuilder result = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] columns = line.split(","); // Assuming space-separated format, adjust delimiter as needed

        if (
          columns.length > 0 &&
          "student".equals(columns[columns.length - 1].trim().toLowerCase())
        ) {
          result.append(line).append("\n");
        }
      }
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace(); // Handle the exception according to your requirements
    }
  }

  public void addInstructor() {
    Instructor I1 = Menus.AddInstructorDataForAdmin();
    String row = I1.toString();
    f.Add(row, "users.txt");
  }

  public void updateInstructor() {
    Menus.UpdateInstructorAsAdmin();
  }

  public void deleteInstructor() {
    System.out.println("enter the Instructor Id to delete :");
    Scanner input = new Scanner(System.in);
    int Id = input.nextInt();
    // input.close();
    f.deleteById(Id, "users.txt");
  }

  public void showAllInstructor() {
    String filePath =
      "D:\\wageh\\java\\Final_project_pl2v2\\yarab tb2a al a5era\\CourseManagement\\data\\users.txt";
    StringBuilder result = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] columns = line.split(","); // Assuming space-separated format, adjust delimiter as needed

        if (
          columns.length > 0 &&
          "instructor".equals(columns[columns.length - 1].trim().toLowerCase())
        ) {
          result.append(line).append("\n");
        }
      }
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace(); // Handle the exception according to your requirements
    }
  }

  public void addCourse() {
    Course c1 = Menus.getCoursesInfoForAdmin();
    String row = c1.toString();

    f.Add(row, "courses.txt");
  }

  public void updateCourse() {
    Menus.updateCourseAsAdmin();
  }

  public void deleteCourse() {
    System.out.println("enter the Course Id to delete :");
    Scanner input = new Scanner(System.in);
    int Id = input.nextInt();
    // input.close();
    f.deleteById(Id, "courses.txt");
  }

  public void showAllCourses() {
    ArrayList<Course> courses = f.getAllCourses();
    Course.displayCoursesDetails(courses);
  }

  public void coursesWillEnd() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<Course> allCourses = f.getAllCourses();
    ArrayList<Course> coursesWillEnd = new ArrayList<>();

    for (Course course : allCourses) {
      try {
        Date endDate = dateFormat.parse(course.getEndDate());
        Date currentDate = new Date();

        if (currentDate.after(endDate)) {
          coursesWillEnd.add(course);
        }
      } catch (ParseException e) {
        System.err.println("Error parsing date: " + e.getMessage());
      }
    }

    if (coursesWillEnd.size() > 0) {
      Course.displayCoursesDetails(coursesWillEnd);
    } else {
      System.out.println("No Courses will end\n");
    }
  }

  public void coursesWillStart() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<Course> courses = f.getAllCourses();
    ArrayList<Course> coursesWillStart = new ArrayList<>();

    for (Course course : courses) {
      try {
        Date startDate = dateFormat.parse(course.getStartDate());
        Date currentDate = new Date();

        if (currentDate.before(startDate)) {
          coursesWillStart.add(course);
        }
      } catch (ParseException e) {
        System.err.println("Error parsing date: " + e.getMessage());
      }
    }

    if (coursesWillStart.size() > 0) {
      Course.displayCoursesDetails(coursesWillStart);
    } else {
      System.out.println("No Courses will start\n");
    }
  }
}
