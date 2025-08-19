import java.util.ArrayList;
import java.util.List;

class UserManager {
    private List<User> users;
    private List<Session> sessions;
    
    public UserManager() {
        this.users = new ArrayList<>();
        this.sessions = new ArrayList<>();
    }
    
    public boolean registerUser(User user) {
        // Check if user ID already exists
        for (User existingUser : users) {
            if (existingUser.getUserId().equals(user.getUserId())) {
                System.out.println("User ID already exists.");
                return false;
            }
        }
        users.add(user);
        System.out.println("User registered successfully: " + user.getName());
        return true;
    }
    
    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    public void addSession(Session session) {
        sessions.add(session);
    }
    
    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    public List<Session> getAllSessions() {
        return new ArrayList<>(sessions);
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
    }
    
    public List<Tutor> getAllTutors() {
        List<Tutor> tutors = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Tutor) {
                tutors.add((Tutor) user);
            }
        }
        return tutors;
    }
}