import java.util.ArrayList;
import java.util.List;

public class Tutor extends User{
  private String specialization;
  private List<Session> taughtSession;
  
  public Tutor(String userId, String name, String email, String password, String specialization){
    super(userId, name, email, password, "Tutor");
    this.specialization = specialization;
    this.taughtSession = new ArrayList<>();
  }

  public String getSpecialization() {
    return specialization;
  }

  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }

  public Session createSession(String sId, String title, String date, String startTime, String endTime, String location){
    Session session = new Session(sId, title, date, startTime, endTime, location, this);
    taughtSession.add(session);
    System.out.println("Session created successfully: " + title);
    return session;
  }

  public boolean deleteSession(Session session){
    if (taughtSession.remove(session)){
      System.out.println("Session deleted: " + session.getTitle());
      return true;
    }
    System.out.println("Session not found or not authorized to delete.");
    return false;
  }

  public List<Session> getTaughtSession() {
    return taughtSession;
  }

  @Override
  public void displayProfile(){
    System.out.println("Tutor Profile: ");
    System.out.println(toString());
    System.out.println("Specialization: " + specialization);
    System.out.println("Session Teaching: " + taughtSession.size());
  }
}


