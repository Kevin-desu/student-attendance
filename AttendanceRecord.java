public class AttendanceRecord {
    String date;
    String status;
    String reason;

    public AttendanceRecord(String date, String status, String reason) {
        this.date = date;
        this.status = status;
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }
}

