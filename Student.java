import java.util.ArrayList;
import java.util.List;

class Student extends User {
  private String studentNumber;
  private List<Session> enrolledSession; //extension for seeing the current student enrolled class

  public Student(String userId, String name, String email, String password, String studentNumber){
    super(userId, name, email, password, "Student");
    this.studentNumber = studentNumber;
    this.enrolledSession = new ArrayList<>(); //extension for seeing the current student enrolled class
  }

  public String getStudentNumber() {
    return studentNumber;
  }

  public boolean enrollInSession(Session session){
    if(session == null){
      System.out.println("Invalid session !");
      return false;
    }

    if(enrolledSession.contains(session)){
      System.out.println("Already enrolled in this session");
      return false;
    }

    enrolledSession.add(session);
    session.addParticipant(this);
    System.out.println("Successfully enrolled in session: " + session.getTitle());
    return true;
  }

  public boolean unenrollFromSession(Session session){
    if(enrolledSession.remove(session)){
      session.removeParticipant(this);
      System.out.println("Successfully unenrolled from session: " + session.getTitle());
      return true;
    }
    System.out.println("Not enrolled in this session.");
    return false;
  }

  public List<Session> getEnrolledSession() {
    return new ArrayList<>(enrolledSession);
  }

  @Override
  public void displayProfile(){
    System.out.println("Student Profile");
    System.out.println(toString());
    System.out.println("Student Number: " + studentNumber);
    System.out.println("Enrolled Session: " + enrolledSession.size());
  }

  @Override
  public String toString(){
    return name + "(ID: " + userId + ")";
  }
}
