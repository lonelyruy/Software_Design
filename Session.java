import java.util.ArrayList;
import java.util.List;

class Session {
    private String sId;
    private String title;
    private String date;
    private String startTime;
    private String endTime;
    private String location;
    private Tutor tutor;
    private List<Student> participants;
    private int maxCapacity;

    public Session(String sId, String title, String date, String startTime, String endTime, 
                  String location, Tutor tutor) {
        this.sId = sId;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.tutor = tutor;
        this.participants = new ArrayList<>();
        this.maxCapacity = 30; // Default capacity
    }

    public String getSId() { return sId; }
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getLocation() { return location; }
    public Tutor getTutor() { return tutor; }
    public List<Student> getParticipants() { return new ArrayList<>(participants); }
    public int getMaxCapacity() { return maxCapacity; }
    public int getCurrentEnrollment() { return participants.size(); }

    public void setTitle(String title) { this.title = title; }
    public void setDate(String date) { this.date = date; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public void setLocation(String location) { this.location = location; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }

    public boolean addParticipant(Student student) {
        if (participants.size() >= maxCapacity) {
            System.out.println("Session is full. Cannot add more participants.");
            return false;
        }
        
        if (!participants.contains(student)) {
            participants.add(student);
            return true;
        }
        return false;
    }

    public boolean removeParticipant(Student student) {
        return participants.remove(student);
    }

    public boolean isFull() {
        return participants.size() >= maxCapacity;
    }

    public String getSessionDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Session ID: ").append(sId).append('\n')
               .append("Title: ").append(title).append('\n')
               .append("Date: ").append(date).append('\n')
               .append("Time: ").append(startTime).append(" - ").append(endTime).append('\n')
               .append("Location: ").append(location).append('\n')
               .append("Tutor: ").append(tutor.getName()).append('\n')
               .append("Participants: ").append(participants.size()).append("/").append(maxCapacity).append('\n');
        
        if (!participants.isEmpty()) {
            details.append("Enrolled Students: ");
            for (int i = 0; i < participants.size(); i++) {
                if (i > 0) details.append(", ");
                details.append(participants.get(i).getName());
            }
            details.append('\n');
        }
        
        return details.toString();
    }

    @Override
    public String toString() {
        return title + " (" + date + " " + startTime + "-" + endTime + ")";
    }
}