public class Student {
        int id;
        String studentName;
        int grade;
        AttendanceRecord[][] attendanceRecord;
        int recordCount = 0;
    
        public Student(int id, String studentName, int grade) {
            this.id = id;
            this.studentName = studentName;
            this.grade = grade;
            this.attendanceRecord = new AttendanceRecord[30][10];
            this.recordCount = 0;
        }
    
        public void markAttendance(String date, String status, String reason) {
            attendanceRecord[recordCount][0] = new AttendanceRecord(date, status, reason);
            recordCount++;
        }
    
        public double calculateAttendancePercentage() {
            int totalClasses = recordCount;
            if (totalClasses == 0) {
                return 0;
            }
            int presentClasses = 0;
            for (int i = 0; i < recordCount; i++) {
                if (attendanceRecord[i][0].getStatus().equals("Present")) {
                    presentClasses++;
                }   
            }
            return (presentClasses / (double) totalClasses) * 100;
        }
        
        public AttendanceRecord[][] getAttendanceRecords() {
            return attendanceRecord;
        }
    
        public String getName() {
            return studentName;
        }
}
