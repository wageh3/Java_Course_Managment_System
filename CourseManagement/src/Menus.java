import java.util.*;

public class Menus {

  public static void mainMenu() {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter your email: ");
    String email = input.nextLine();
    System.out.print("Enter the password: ");
    String password = input.nextLine();

    Person p1 = null;
    if (Person.login(email, password) != null) {
      p1 = Person.login(email, password);
      System.out.println("=========\nhello " + p1.getName());
      System.out.println("you are " + p1.getRole() + "\n=========");
    } else {
      System.out.println("Login Failed");
      return;
    }

    String Role = p1.getRole();
    switch (Role) {
      case "admin" -> AdminView(p1);
      case "student" -> StudentView(p1);
      case "instructor" -> instructorview(p1);
    }
  }

  public static void AdminView(Person p1) {
    Admin a1 = new Admin(p1);
    Scanner input = new Scanner(System.in);
    boolean exit = false;
    try {
      while (!exit) {
        System.out.println("Choose the Module to perform action:");
        System.out.println("1-Course");
        System.out.println("2-Instructor");
        System.out.println("3-Student");
        System.out.println("4-Exit");
        int choice = input.nextInt();

        // input.close();

        switch (choice) {
          case 1:
            switch (AdminCourseActionsMenu(p1)) {
              case 1:
                a1.addCourse();
                break;
              case 2:
                a1.updateCourse();
                break;
              case 3:
                a1.deleteCourse();
                break;
              case 4:
                a1.showAllCourses();
                break;
              case 5:
                a1.coursesWillEnd();
                break;
              case 6:
                a1.coursesWillStart();
                break;
              case 7:
                exit = true;
                break;
            }
            break;
          case 2:
            switch (AdminToInstructorActionMenu(p1)) {
              case 1:
                a1.addInstructor();
                break;
              case 2:
                a1.updateInstructor();
                break;
              case 3:
                a1.deleteInstructor();
                break;
              case 4:
                a1.showAllInstructor();
                break;
              case 5:
                AdminView(p1);
                break;
            }
            break;
          case 3:
            switch (AdminToStudentActionMenu(p1)) {
              case 1:
                a1.addStudent();
                break;
              case 2:
                a1.updateStudent();
                break;
              case 3:
                a1.deleteStudent();
                break;
              case 4:
                a1.showAllStudents();
                break;
              case 5:
                AdminView(p1);
                break;
            }
            break;
          case 4:
            exit = true;
            break;
          default:
            System.out.println("Invalid choice, please try again.");
        }
      }
    } catch (NoSuchElementException e) {
      System.out.println("Data updated successfully!");
    }
  }

  public static void StudentView(Person p1) {
    // boolean exit = false; // Not needed now

    Student s1 = new Student(p1);
    Scanner input = new Scanner(System.in);
    int choice;
    do {
      System.out.println("1-for update Name");
      System.out.println("2-for update Email");
      System.out.println("3-for update Password");
      System.out.println("4-for Show Grade");
      System.out.println("5-for Show All courses");
      System.out.println("6-survey about sepcific course");
      System.out.println("7-Exit");

      choice = input.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Enter the new Name:");
          String newName = input.nextLine();
          s1.UpdateName(newName);
          break;
        case 2:
          System.out.print("Enter the new Email:");
          String NewEmail = input.nextLine();
          s1.UpdateEmail(NewEmail);
          break;
        case 3:
          System.out.print("Enter the new Password:");
          String NewPassword = input.nextLine();
          s1.UpdatePassword(NewPassword);
          break;
        case 4:
          s1.StudentShowGrade();
          break;
        case 5:
          s1.showCourses();
          break;
        case 6:
          Scanner scanner = new Scanner(System.in);
          System.out.println("Enter cousre name:");
          String cname = scanner.nextLine();
          System.out.println("Enter your survey :");
          String survey = scanner.nextLine();
          s1.Studentsurvey(cname, survey);
      }
    } while (choice != 6); // Repeat the loop while choice is not 5

    System.out.println("Exiting Student View!"); // Optional message after exiting
    // input.close(); // Close input now, after the loop
  }

  public static void instructorview(Person p1) {
    Scanner input = new Scanner(System.in);
    boolean exit = false;
    while (!exit) {
      System.out.println("1-Add Grades");
      System.out.println("2-Exit");
      int choice = input.nextInt();
      input.nextLine(); // Consume the newline character
      // input.close();

      switch (choice) {
        case 1:
          System.out.print("Enter student ID: ");
          int sid = input.nextInt();
          input.nextLine(); // Consume the newline character

          System.out.print("Enter course ID : ");
          String cid = input.nextLine();

          System.out.print("Enter student Grade : ");
          String grade = input.nextLine();
          // input.close();

          FileManager f = new FileManager();
          f.addgrades(cid, sid, grade, "Students_grades.txt");
          break;
        case 2:
          exit = true;
        default:
          break;
      }
    }
    // input.close();
  }

  public static int AdminToStudentActionMenu(Person p1) {
    Scanner input = new Scanner(System.in);
    int option;
    while (true) {
      System.out.println("1-for add");
      System.out.println("2-for update");
      System.out.println("3-for delete");
      System.out.println("4-for show all students");
      System.out.println("5-Back");
      System.out.println("0-Exit");
      System.out.print("Enter your choice: ");
      option = input.nextInt();
      // input.close();
      switch (option) {
        case 1:
          return 1;
        case 2:
          return 2;
        case 3:
          return 3;
        case 4:
          return 4;
        case 5:
          return 5;
        case 0:
          System.out.println("Exiting..");
          System.exit(0);
        default:
          System.out.println("Invalid choice, please try again");
      }
      // input.close();
    }
  }

  public static Student GetStudentInfoForAdmin() {
    Scanner input = new Scanner(System.in);
    System.out.println("enter name :");
    String name = input.nextLine();
    System.out.println("enter email to Set :");
    String email = input.nextLine();
    System.out.println("enter password to Set :");
    String password = input.nextLine();
    System.out.println("enter gender :");
    String gender = input.nextLine();
    String[] courses = new String[5];
    for (int i = 0; i < courses.length; i++) {
      System.out.println("enter course" + i + " :");
      String course = input.nextLine();
      // input.close();
      courses[i] = course;
    }

    Student s1 = new Student(name, email, password, gender, courses);
    // input.close();
    return s1;
  }

  public static void UpdateInStudentAsAdmin() {
    Scanner input = new Scanner(System.in);
    System.out.println("please enter the target Id:");
    String targetedtId = input.nextLine();
    System.out.println("Select 1 for updating email"); //done
    System.out.println("Select 2 for updating id"); //done
    System.out.println("Select 3 for updating name"); //done
    System.out.println("Select 4 for updating password"); //done
    System.out.println("Select 5 for updating course1");
    System.out.println("Select 6 for updating course2");
    System.out.println("Select 7 for updating course3");
    System.out.println("Select 8 for updating course4");
    System.out.println("Select 9 for updating course5");
    System.out.println("Select 10 for updating course6");
    System.out.println("Select 0 for Back");

    int option = input.nextInt();
    // input.close();
    FileManager f = new FileManager();
    Scanner scanner = new Scanner(System.in);
    switch (option) {
      case 1:
        System.out.println("enter the new Email");
        String NewEmail = scanner.nextLine();

        f.update("users.txt", targetedtId, 2, NewEmail);
        break;
      case 2:
        System.out.println("enter the new Id");
        String NewId = scanner.nextLine();
        f.update("users.txt", targetedtId, 0, NewId);
        break;
      case 3:
        System.out.println("enter the new Name");
        String NewName = scanner.nextLine();
        f.update("users.txt", targetedtId, 1, NewName);
        break;
      case 4:
        System.out.println("enter the new password");
        String NewPassword = scanner.nextLine();
        f.update("users.txt", targetedtId, 3, NewPassword);
        break;
    }
    // input.close();
  }

  public static int AdminToInstructorActionMenu(Person p1) {
    Scanner input = new Scanner(System.in);
    int option;
    while (true) {
      System.out.println("Select 1 for add personal info");
      System.out.println("Select 2 for update");
      System.out.println("Select 3 for delete");
      System.out.println("Select 4 for show all Instructors");
      System.out.println("5-Back");
      System.out.print("Enter your choice: ");
      option = input.nextInt();
      // input.close();
      switch (option) {
        case 1:
          return 1;
        case 2:
          return 2;
        case 3:
          return 3;
        case 4:
          return 4;
        case 5:
          return 5; // Go back to the main menu
        default:
          System.out.println("Invalid choice, please try again");
      }
      // input.close();
    }
  }

  public static Instructor AddInstructorDataForAdmin() {
    Scanner input = new Scanner(System.in);
    System.out.println("instructor Name :");
    String instructorName = input.nextLine();

    System.out.println("instructor Email :");
    String instructorEmail = input.nextLine();

    System.out.println("instructor Password :");
    String instructorPassword = input.nextLine();

    System.out.println("instructor gender :");
    String instructorgender = input.nextLine();
    // input.close();

    Instructor I1 = new Instructor(
      instructorName,
      instructorEmail,
      instructorPassword,
      instructorgender
    );
    // input.close();
    return I1;
  }

  public static void UpdateInstructorAsAdmin() {
    Scanner input = new Scanner(System.in);
    System.out.println("please enter the target Id:");
    String targetedtId = input.nextLine();

    System.out.println("Select 1 for updating email"); //done
    System.out.println("Select 2 for updating id"); //done
    System.out.println("Select 3 for updating name"); //done
    System.out.println("Select 4 for updating password"); //done

    int option = input.nextInt();
    FileManager f = new FileManager();
    // input.close();
    switch (option) {
      case 1:
        System.out.println("enter the new Email");
        Scanner newEmail = new Scanner(System.in);
        String NewEmail = newEmail.nextLine();
        f.update("users.txt", targetedtId, 2, NewEmail);
        break;
      case 2:
        System.out.println("enter the new Id");
        String NewId = input.nextLine();
        f.update("users.txt", targetedtId, 0, NewId);
        break;
      case 3:
        System.out.println("enter the new Name");
        Scanner newName = new Scanner(System.in);
        String NewName = newName.nextLine();
        f.update("users.txt", targetedtId, 1, NewName);
        break;
      case 4:
        System.out.println("enter the new Name");
        Scanner newPassword = new Scanner(System.in);
        String NewPassword = newPassword.nextLine();
        f.update("users.txt", targetedtId, 3, NewPassword);
        break;
    }
    // input.close();
  }

  public static int AdminCourseActionsMenu(Person p1) {
    Scanner input = new Scanner(System.in);
    int option;
    while (true) {
      System.out.println("1- add course");
      System.out.println("2- update course");
      System.out.println("3- delete course");
      System.out.println("4- show all courses");
      System.out.println("5- show courses will end");
      System.out.println("6- show courses will start");
      System.out.println("7- Back");
      System.out.print("Enter your choice: ");
      option = input.nextInt();
      if (option == 7) {
        AdminView(p1);
      } else {
        return option;
      }
      // input.close();
    }
  }

  public static Course getCoursesInfoForAdmin() {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter Course Information:");

    System.out.print("Name: ");
    String Name = input.nextLine();

    System.out.print("Instructor ID: ");
    int instructor = input.nextInt();
    input.nextLine();

    System.out.print("Room: ");
    int room = input.nextInt();
    input.nextLine();

    System.out.print("Price: ");
    double price = input.nextDouble();
    input.nextLine();

    System.out.print("Branch: ");
    String branch = input.nextLine();

    System.out.print("Start Date: ");
    String startDate = input.nextLine();

    System.out.print("End Date: ");
    String endDate = input.nextLine();

    System.out.print("Days of the Course: ");
    String daysOfCourse = input.nextLine();

    System.out.print("Total Number of Students: ");
    int students = input.nextInt();
    input.nextLine();

    System.out.print("Parent Course ID: ");
    String parentCourse = input.nextLine();

    System.out.print("Parent Course Price: ");
    double parentCoursePrice = input.nextDouble();
    input.nextLine();

    System.out.print("Grades: ");
    String grades = input.nextLine();
    // input.close();

    Course c1 = new Course(
      Name,
      instructor,
      room,
      price,
      branch,
      startDate,
      endDate,
      daysOfCourse,
      students,
      parentCourse,
      parentCoursePrice,
      grades
    );
    // input.close();
    return c1;
  }

  public static void updateCourseAsAdmin() {
    Scanner input = new Scanner(System.in);
    System.out.println("please enter the target Id:");
    String targetedtId = input.nextLine();
    FileManager f = new FileManager();
    System.out.println("1-for updating CourseName");
    System.out.println("2-for updating CourseId");
    System.out.println("3-for updating InstructorId");
    System.out.println("4-for updating Room NO");
    System.out.println("5-for updating Price");
    System.out.println("6-for updating Branch");
    System.out.println("7-for updating parent course");
    System.out.println("8-for updating price of parent course");
    System.out.println("9-for updating Total Number Of students");
    System.out.println("10-for updating Start Date");
    System.out.println("11-for updating Days of the course");
    System.out.println("12-for updating End Date");

    int option = input.nextInt();
    // input.close();
    try (Scanner scanner = new Scanner(System.in)) {
      switch (option) {
        case 1:
          System.out.println("Enter the new Name");
          String NewName = scanner.nextLine();
          f.update("courses.txt", targetedtId, 1, NewName);
          break;
        case 2:
          System.out.println("Enter the new Id");
          String NewId = scanner.nextLine();
          f.update("courses.txt", targetedtId, 0, NewId);
          break;
        case 3:
          System.out.println("Enter the new Instructor Id");
          String InstructorId = scanner.nextLine();
          f.update("courses.txt", targetedtId, 2, InstructorId);
          break;
        case 4:
          System.out.println("Enter the new Room Number");
          String NewRoomNumber = scanner.nextLine();
          f.update("courses.txt", targetedtId, 3, NewRoomNumber);
          break;
        case 5:
          System.out.println("Enter the new Course Price");
          String NewCoursePrice = scanner.nextLine();
          f.update("courses.txt", targetedtId, 4, NewCoursePrice);
          break;
        case 6:
          System.out.println("Enter the new Branch");
          String NewBranch = scanner.nextLine();
          f.update("courses.txt", targetedtId, 5, NewBranch);
          break;
        case 7:
          System.out.println("Enter the new ParentCourse");
          String NewParentCoure = scanner.nextLine();
          f.update("courses.txt", targetedtId, 10, NewParentCoure);
          break;
        case 8:
          System.out.println("Enter the new price of ParentCourse");
          String NewPriceOfParentCoure = scanner.nextLine();
          f.update("courses.txt", targetedtId, 11, NewPriceOfParentCoure);
          break;
        case 9:
          System.out.println("enter the new Total number of Students");
          String NewTotalNumberOfStudents = scanner.nextLine();
          f.update("courses.txt", targetedtId, 9, NewTotalNumberOfStudents);
          break;
        case 10:
          System.out.println("Enter the new Start Date");
          String NewStartDate = scanner.nextLine();
          f.update("courses.txt", targetedtId, 6, NewStartDate);
          break;
        case 11:
          System.out.println("Enter the new Course Days");
          String NewCourseDays = scanner.nextLine();
          f.update("courses.txt", targetedtId, 8, NewCourseDays);
          break;
        case 12:
          System.out.println("Enter the new End Date");
          String NewEndDate = scanner.nextLine();
          f.update("courses.txt", targetedtId, 7, NewEndDate);
          break;
      }
      // input.close();
    }
  }
}
