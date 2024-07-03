import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager implements FileOperations {

  private static final String DATA_PATH =
    "D:\\wageh\\java\\Final_project_pl2v2\\yarab tb2a al a5era\\CourseManagement\\data\\";

  @Override
  public File getFile(String fileName) {
    return new File(DATA_PATH + fileName);
  }

  // Find a person by email in the specified user type file
  @Override
  public Person GetIfExists(String email, String fileName) {
    File usersFile = getFile(fileName);
    try (Scanner scanner = new Scanner(usersFile)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");

        if (data.length >= 6 && data[2].equals(email)) {
          return new Student(
            Integer.parseInt(data[0]),
            data[1],
            data[2],
            data[3],
            data[4],
            data[5]
          );
        }
      }
    } catch (FileNotFoundException e) {
      return null;
    }
    return null;
  }

  //OVER LOAD
  public String GetIfExists(int id, String Filename) {
    File usersFile = this.getFile(Filename);
    try (Scanner scanner = new Scanner(usersFile)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        if (Integer.parseInt(data[0].trim()) == id) {
          return (
            data[0] +
            "," +
            data[1] +
            "," +
            data[2] +
            "," +
            data[3] +
            "," +
            data[4] +
            "," +
            data[5]
          );
        }
      }
      return "";
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return "";
    }
  }

  @Override
  public void Add(String row, String filename) {
    String filePath = DATA_PATH + filename;
    String data = row;
    try (
      BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))
    ) {
      writer.write(data);
      writer.newLine(); // Add a newline to separate rows
      System.out.println("Data has been written to the file.");
    } catch (IOException e) {
      e.printStackTrace(); // Handle the exception according to your requirements
    }
  }

  @Override
  public boolean deleteById(int id, String filename) {
    String filePath = DATA_PATH + filename;
    String content = "";
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        return false;
      }
      FileReader fr = new FileReader(filePath);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        String[] s1 = line.split(",");
        if (s1[0].contains(String.valueOf(id))) {
          continue;
        }
        content = content + line + "\n";
      }
      br.close();
      FileWriter fw = new FileWriter(filePath);
      fw.write(content);
      fw.close();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  @Override
  public int lastUsedId(String fileName) {
    File usersFile = this.getFile(fileName);
    try (Scanner scanner = new Scanner(usersFile)) {
      String line = "";
      while (scanner.hasNext()) {
        line = scanner.nextLine();
      }
      String[] data = line.split(",");
      int id = Integer.parseInt(data[0]);
      return ++id;
    } catch (FileNotFoundException e) {
      return 0;
    }
  }

  @Override
  public void update(
    String filename,
    String TargetId,
    int index,
    String newValue
  ) {
    // Read lines from the file
    String filepath = DATA_PATH + filename;
    try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
      String line;
      StringBuilder updatedContent = new StringBuilder();

      while ((line = br.readLine()) != null) {
        // Split the line by commas
        String[] values = line.split(",");

        // Check if the targetId is present in the current line
        if (values.length > index && values[0].equals(TargetId)) {
          // Replace the value at the specified index with the new value
          values[index] = newValue;
        }

        // Join the updated values back into a line
        String updatedLine = String.join(",", values);

        // Append the updated line to the content
        updatedContent.append(updatedLine).append("\n");
      }

      // Write the updated content back to the file
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
        bw.write(updatedContent.toString());
      }
    } catch (IOException e) {
      e.printStackTrace(); // Handle the exception according to your needs
    }
  }

  // over load
  public boolean Add(int position, String record, String filename) {
    String filepath = DATA_PATH + filename;
    int numberOfRows = 1;
    String content = "";
    try {
      File file = new File(filepath);
      if (!file.exists()) {
        return false;
      }
      FileReader fr = new FileReader(filepath);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        if (numberOfRows == position) {
          content = content + record + "\n" + line + "\n";
          numberOfRows++;
        } else {
          numberOfRows++;
          content = content + line + "\n";
        }
      }
      br.close();
      FileWriter fw = new FileWriter(filepath);
      fw.write(content);
      fw.close();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public static String getStringBeforeEqual(String inputString) {
    // Find the index of the equal sign
    int equalIndex = inputString.indexOf('=');

    // Check if the equal sign is present in the string
    if (equalIndex != -1) {
      // Extract and return the substring before the equal sign
      String resultString = inputString.substring(0, equalIndex);
      return resultString;
    } else {
      // If equal sign is not present, return the original string
      return inputString;
    }
  }

  public static String getStringAfterEqual(String inputString) {
    // Find the index of the equal sign
    int equalIndex = inputString.indexOf('=');

    // Check if the equal sign is present in the string
    if (equalIndex != -1) {
      // Extract and return the substring after the equal sign
      String resultString = inputString.substring(equalIndex + 1).trim();
      return resultString;
    } else {
      // If equal sign is not present, return the original string
      return inputString;
    }
  }

  public void showGrades(int id, String filename) {
    String filepath = DATA_PATH + filename;
    try {
      File file = new File(filepath);
      if (!file.exists()) {
        System.out.println("File does not exist");
        return;
      }
      Scanner CourseIdInput = new Scanner(System.in);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      ArrayList<Course> courses = FileManager.getCoursesnameandid();
      Course.displayCoursesnameandid(courses);
      System.out.print("Enter the Course Id To show Grade:");
      String courseid = CourseIdInput.nextLine();
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] s = line.split(",");
        if (s.length > 1 && s[0].equals(String.valueOf(id))) {
          for (int i = 1; i < s.length; i++) {
            if (getStringBeforeEqual(s[i]).equals(courseid)) {
              System.out.println(
                "Course Name: " +
                getCourseName(courseid, "courses.txt").toUpperCase() +
                "\n" +
                "Course id: " +
                getStringBeforeEqual(s[i]) +
                "\n" +
                "Course grade: " +
                getStringAfterEqual(s[i])
              );
              // System.out.println("course Id :" + getStringBeforeEqual(s[i]));
            }
          }
        }
      }
      // CourseIdInput.close();
      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getCourseName(String id, String filename) {
    String filepath = DATA_PATH + filename;
    try (
      BufferedReader bufferedReader = new BufferedReader(
        new FileReader(filepath)
      )
    ) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] s = line.split(",");
        if (s.length > 1 && s[0].equals(id)) {
          return s[1].trim(); // Trim to remove leading/trailing whitespaces
        }
      }
      System.out.println("ID not found in the file");
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + filepath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null; // Return null if ID is not found or an error occurs
  }

  public void addgrades(String cid, int sid, String grade, String filename) {
    // Specify the file path for the course grade file
    String filepath = DATA_PATH + filename;

    try (
      FileReader fr = new FileReader(filepath);
      BufferedReader br = new BufferedReader(fr);
      FileWriter fw = new FileWriter(filepath, true)
    ) {
      String line;
      boolean gradeExists = false;

      // Check if the grade already exists for the specified student and course
      while ((line = br.readLine()) != null) {
        if (line.startsWith(sid + ",") && line.contains(cid + "=")) {
          gradeExists = true;
          break;
        }
      }

      if (!gradeExists) {
        // Add the new grade
        fw.append(sid + ",");
        fw.append(cid + "=" + grade + System.lineSeparator()); // Add a newline after each entry
        System.out.println("Data has been written to the file.");
      } else {
        // Print an error message if the grade already exists
        System.err.println(
          "Error: Grade already exists for Student ID " +
          sid +
          " and Course ID " +
          cid +
          "."
        );
      }
    } catch (IOException e) {
      e.printStackTrace(); // Handle the exception according to your requirements
    }
  }

  public void SavesurveytoFile(
    int id,
    String name,
    String cname,
    String s2,
    String filename
  ) {
    String filepath = DATA_PATH + filename;

    try {
      // Create a FileWriter object with the file path
      FileWriter fw = new FileWriter(filepath, true);
      fw.write(id + ",");
      fw.write(name + ",");
      fw.write(cname + ",");
      fw.write(s2 + "\n");
      fw.close();
      System.out.println("The survey saved successfully!");
    } catch (IOException e) {
      System.err.println(
        "Error saving the report to the file: " + e.getMessage()
      );
    }
  }

  public void showCoursesForStudent(String studentId, String filename) {
    String filepath = DATA_PATH + filename;

    try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] columns = line.split(",");
        if (columns.length > 0 && columns[0].equals(studentId)) {
          System.out.println("Student ID: " + studentId);
          System.out.println("Courses:");

          for (int i = 1; i < columns.length; i++) {
            System.out.println(columns[i].trim());
          }
          break;
        }
      }
    } catch (IOException | NumberFormatException e) {
      e.printStackTrace();
      System.out.println("Something Went Wrong!");
    }
  }

  public static ArrayList<Course> getAllCourses() {
    ArrayList<Course> courses = new ArrayList<>();
    String filePath = DATA_PATH + "courses.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] columns = line.split(",");
        if (columns.length >= 13) {
          Course course = new Course(
            Integer.parseInt(columns[0].trim()),
            columns[1].trim(),
            Integer.parseInt(columns[2].trim()),
            Integer.parseInt(columns[3].trim()),
            Double.parseDouble(columns[4].replace("$", "").trim()),
            columns[5].trim(),
            columns[6].trim(),
            columns[7].trim(),
            columns[8].trim(),
            Integer.parseInt(columns[9].trim()),
            columns[10].trim(),
            Double.parseDouble(columns[11].replace("$", "").trim()),
            columns[12].trim()
          );
          courses.add(course);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return courses;
  }

  public static ArrayList<Course> getCoursesnameandid() {
    ArrayList<Course> courses = new ArrayList<>();
    String filePath = DATA_PATH + "courses.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] columns = line.split(",");
        if (columns.length >= 13) {
          Course course = new Course(
            Integer.parseInt(columns[0].trim()),
            columns[1].trim()
          );
          courses.add(course);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return courses;
  }

  // Find a person by email in the specified user type file
  public String search(int id, String filename, int ReturnFieldIndex) {
    File usersFile = this.getFile(filename);
    try (Scanner scanner = new Scanner(usersFile)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        if (Integer.parseInt(data[0]) == id) {
          return data[ReturnFieldIndex];
        }
      }
      return "";
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return "";
    }
  }
}
