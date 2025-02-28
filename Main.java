import java.util.*;
import java.io.*;

public class Main {

    public static void saveData(Student[] students) { // Changed List<Student> to Student[]
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance_data.txt"))) {
            for (Student student : students) {
                if (student != null) { // Check for null to avoid NullPointerException
                    writer.write(student.id + "," + student.getName() + "," + student.grade + "\n");
                    AttendanceRecord[][] records = student.getAttendanceRecords(); // Get attendance records
                    for (int i = 0; i < student.recordCount; i++) { // Use recordCount to iterate
                        AttendanceRecord record = records[i][0]; // Access the record
                        if (record != null) {
                            writer.write(student.id + "," + record.getDate() + "," + record.getStatus() + "," + record.getReason() + "\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Student[] loadData() { // Changed return type to Student[]
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("attendance_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) { // Student line
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int grade = Integer.parseInt(parts[2]); // Changed String to int for grade
                    students.add(new Student(id, name, grade));
                } else if (parts.length == 4) { // Attendance record line
                    int id = Integer.parseInt(parts[0]);
                    String date = parts[1];
                    String status = parts[2];
                    String reason = parts[3];
                    for (Student student : students) {
                        if (student.id == id) {
                            student.markAttendance(date, status, reason);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students.toArray(new Student[0]); // Convert List to Array
    }

    public static void main(String[] args) {
          // Create a Math class
          Class mathClass = new Class("Math", "Mr. Smith", 30); // Specify maximum students
          Student student1 = new Student(1, "Alice", 10); // Changed "10th" to 10 for grade
          Student student2 = new Student(2, "Bob", 10); // Changed "10th" to 10 for grade
          Student student3 = new Student(3, "Kevin", 10);
        

  
          // Add students to the class
          mathClass.addStudent(student1);
          mathClass.addStudent(student2);
          mathClass.addStudent(student3);
        
  
          // Mark attendance
          Map<Integer, String[]> attendanceData = new HashMap<>();
          attendanceData.put(1, new String[]{"Present"});
          attendanceData.put(2, new String[]{"Absent", "Sick"});
          attendanceData.put(3, new String[]{"Present", "Interview"});
          mathClass.markDailyAttendance("2023-10-01", attendanceData);
  
          // Generate report
          Map<String, Double> report = mathClass.generateMonthlyReport();
          report.forEach((name, percentage) -> System.out.println(name + ": " + percentage + "%"));
  
          // Generate absence alerts
          String[] alerts = mathClass.getAlerts(); // Changed to call getAlerts() method
          for (String alert : alerts) {
              System.out.println(alert);
          }
  
          // Save data
          Main.saveData(new Student[]{student1, student2, student3}); // Changed to use the array
  
          // Load data
          Student[] loadedStudents = Main.loadData(); // Changed to use the array
          for (Student student : loadedStudents) {
              System.out.println("Loaded student: " + student.getName());
          }
    }
}