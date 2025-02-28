import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Class {
    String className;
    String teacher;
    Student[] studentList; // Changed from List<Student> to Student[]
    int studentCount; // To keep track of the number of students

    public Class(String className, String teacher, int maxStudents) {
        this.className = className;
        this.teacher = teacher;
        this.studentList = new Student[maxStudents]; // Initialize the array with a maximum size
        this.studentCount = 0; // Start with zero students
    }

    public void addStudent(Student student) {
        if (studentCount < studentList.length) {
            studentList[studentCount] = student; // Add student to the array
            studentCount++;
        } else {
            System.out.println("Cannot add more students, class is full.");
        }
    }

    public void markDailyAttendance(String date, Map<Integer, String[]> attendanceData) {
        for (Student student : studentList) {
            if (student != null && attendanceData.containsKey(student.id)) { // Corrected to use student.id
                String[] data = attendanceData.get(student.id);
                String status = data[0];
                String reason = data.length > 1 ? data[1] : null;
                student.markAttendance(date, status, reason); // Corrected to call the instance method
            }
        }
    }

    public Map<String, Double> generateMonthlyReport() {
        Map<String, Double> report = new HashMap<>();
        for (Student student : studentList) {
            if (student != null) { // Check for null to avoid NullPointerException
                double attendancePercentage = student.calculateAttendancePercentage(); // Corrected to call the instance method
                report.put(student.getName(), attendancePercentage); // Corrected to call the instance method
            }
        }
        return report;
    }

    public String[] getAlerts() { // Changed from List<String> to String[]
        List<String> alerts = new ArrayList<>();
        for (Student student : studentList) {
            if (student != null) { // Check for null to avoid NullPointerException
                AttendanceRecord[][] records = student.getAttendanceRecords(); // Corrected to call the instance method
                for (int i = 0; i < student.recordCount; i++) { // Use recordCount to iterate
                    AttendanceRecord record = records[i][0]; // Access the record
                    if (record != null && record.getStatus().equals("Absent")) {
                        alerts.add("Alert: " + student.getName() + " was absent on " + record.getDate() + ". Reason: " + record.getReason());
                    }
                }
            }
        }
        return alerts.toArray(new String[0]); // Convert List to Array
    }
}