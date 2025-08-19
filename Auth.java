import java.util.List;

class Auth {
    private UserManager userManager;
    private User currentUser;
    private java.util.Scanner scanner;
    
    public Auth(UserManager userManager) {
        this.userManager = userManager;
        this.scanner = new java.util.Scanner(System.in);
    }
    
    public void start() {
        System.out.println("=== Welcome to Tutoring Session Booking System ===");
        
        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showRoleMenu();
            }
        }
    }
    
    private void showAuthMenu() {
        System.out.println("\n=== Authentication Menu ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");
        
        int choice = getIntInput();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("Thank you for using the system!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    private void login() {
        System.out.println("\n=== Login ===");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();
        
        User user = userManager.findUserById(userId);
        if (user != null && user.password.equals(password)) {
            currentUser = user;
            System.out.println("Login successful! Welcome, " + user.getName());
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }
    
    private void register() {
        System.out.println("\n=== Registration ===");
        System.out.println("Select user type:");
        System.out.println("1. Student");
        System.out.println("2. Tutor");
        System.out.println("3. Admin");
        System.out.print("Choose option: ");
        
        int userType = getIntInput();
        
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();
        
        User newUser = null;
        
        switch (userType) {
            case 1:
                System.out.print("Enter Student Number: ");
                String studentNumber = scanner.nextLine().trim();
                newUser = new Student(userId, name, email, password, studentNumber);
                break;
            case 2:
                System.out.print("Enter Specialization: ");
                String specialization = scanner.nextLine().trim();
                newUser = new Tutor(userId, name, email, password, specialization);
                break;
            case 3:
                System.out.print("Enter Department: ");
                String department = scanner.nextLine().trim();
                newUser = new Admin(userId, name, email, password, department);
                break;
            default:
                System.out.println("Invalid user type.");
                return;
        }
        
        if (userManager.registerUser(newUser)) {
            System.out.println("Registration successful! You can now login.");
        }
    }
    
    private void showRoleMenu() {
        System.out.println("\n=== Welcome, " + currentUser.getName() + " (" + currentUser.getRole() + ") ===");
        
        if (currentUser instanceof Student) {
            showStudentMenu();
        } else if (currentUser instanceof Tutor) {
            showTutorMenu();
        } else if (currentUser instanceof Admin) {
            showAdminMenu();
        }
    }
    
    private void showStudentMenu() {
        System.out.println("Student Options:");
        System.out.println("1. View Available Sessions");
        System.out.println("2. Enroll in Session");
        System.out.println("3. View My Enrolled Sessions");
        System.out.println("4. Unenroll from Session");
        System.out.println("5. View My Profile");
        System.out.println("6. Logout");
        System.out.print("Choose option: ");
        
        int choice = getIntInput();
        Student student = (Student) currentUser;
        
        switch (choice) {
            case 1:
                viewAvailableSessions();
                break;
            case 2:
                enrollInSession(student);
                break;
            case 3:
                viewEnrolledSessions(student);
                break;
            case 4:
                unenrollFromSession(student);
                break;
            case 5:
                student.displayProfile();
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    private void showTutorMenu() {
        System.out.println("Tutor Options:");
        System.out.println("1. Create New Session");
        System.out.println("2. View My Sessions");
        System.out.println("3. Update Session");
        System.out.println("4. Delete Session");
        System.out.println("5. View Session Participants");
        System.out.println("6. View My Profile");
        System.out.println("7. Logout");
        System.out.print("Choose option: ");
        
        int choice = getIntInput();
        Tutor tutor = (Tutor) currentUser;
        
        switch (choice) {
            case 1:
                createSession(tutor);
                break;
            case 2:
                viewTutorSessions(tutor);
                break;
            case 3:
                updateSession(tutor);
                break;
            case 4:
                deleteSession(tutor);
                break;
            case 5:
                viewSessionParticipants(tutor);
                break;
            case 6:
                tutor.displayProfile();
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    private void showAdminMenu() {
        System.out.println("Admin Options:");
        System.out.println("1. View All Users");
        System.out.println("2. View All Sessions");
        System.out.println("3. Generate User Report");
        System.out.println("4. Generate Session Report");
        System.out.println("5. View System Statistics");
        System.out.println("6. View My Profile");
        System.out.println("7. Logout");
        System.out.print("Choose option: ");
        
        int choice = getIntInput();
        Admin admin = (Admin) currentUser;
        
        switch (choice) {
            case 1:
                viewAllUsers();
                break;
            case 2:
                viewAllSessions();
                break;
            case 3:
                admin.generateUserReport(userManager.getAllUsers());
                break;
            case 4:
                admin.generateSessionReport(userManager.getAllSessions());
                break;
            case 5:
                viewSystemStatistics();
                break;
            case 6:
                admin.displayProfile();
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    // Helper methods for menu operations
    private void viewAvailableSessions() {
        List<Session> sessions = userManager.getAllSessions();
        if (sessions.isEmpty()) {
            System.out.println("No sessions available.");
            return;
        }
        
        System.out.println("\n=== Available Sessions ===");
        for (int i = 0; i < sessions.size(); i++) {
            System.out.println((i + 1) + ". " + sessions.get(i).toString());
            System.out.println("   Capacity: " + sessions.get(i).getCurrentEnrollment() + "/" + sessions.get(i).getMaxCapacity());
        }
    }
    
    private void enrollInSession(Student student) {
        viewAvailableSessions();
        List<Session> sessions = userManager.getAllSessions();
        if (sessions.isEmpty()) return;
        
        System.out.print("Enter session number to enroll: ");
        int sessionNum = getIntInput();
        
        if (sessionNum > 0 && sessionNum <= sessions.size()) {
            student.enrollInSession(sessions.get(sessionNum - 1));
        } else {
            System.out.println("Invalid session number.");
        }
    }
    
    private void viewEnrolledSessions(Student student) {
        List<Session> enrolled = student.getEnrolledSession();
        if (enrolled.isEmpty()) {
            System.out.println("You are not enrolled in any sessions.");
            return;
        }
        
        System.out.println("\n=== Your Enrolled Sessions ===");
        for (int i = 0; i < enrolled.size(); i++) {
            System.out.println((i + 1) + ". " + enrolled.get(i).toString());
        }
    }
    
    private void unenrollFromSession(Student student) {
        viewEnrolledSessions(student);
        List<Session> enrolled = student.getEnrolledSession();
        if (enrolled.isEmpty()) return;
        
        System.out.print("Enter session number to unenroll: ");
        int sessionNum = getIntInput();
        
        if (sessionNum > 0 && sessionNum <= enrolled.size()) {
            student.unenrollFromSession(enrolled.get(sessionNum - 1));
        } else {
            System.out.println("Invalid session number.");
        }
    }
    
    private void createSession(Tutor tutor) {
        System.out.println("\n=== Create New Session ===");
        System.out.print("Enter Session ID: ");
        String sId = scanner.nextLine().trim();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
        System.out.print("Enter Start Time (HH:MM): ");
        String startTime = scanner.nextLine().trim();
        System.out.print("Enter End Time (HH:MM): ");
        String endTime = scanner.nextLine().trim();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine().trim();
        
        Session session = tutor.createSession(sId, title, date, startTime, endTime, location);
        userManager.addSession(session);
    }
    
    private void viewTutorSessions(Tutor tutor) {
        List<Session> sessions = tutor.getTaughtSession();
        if (sessions.isEmpty()) {
            System.out.println("You have no sessions.");
            return;
        }
        
        System.out.println("\n=== Your Sessions ===");
        for (int i = 0; i < sessions.size(); i++) {
            System.out.println((i + 1) + ". " + sessions.get(i).getSessionDetails());
        }
    }
    
    private void updateSession(Tutor tutor) {
        List<Session> sessions = tutor.getTaughtSession();
        if (sessions.isEmpty()) {
            System.out.println("You have no sessions to update.");
            return;
        }
        
        viewTutorSessions(tutor);
        System.out.print("Enter session number to update: ");
        int sessionNum = getIntInput();
        
        if (sessionNum > 0 && sessionNum <= sessions.size()) {
            Session session = sessions.get(sessionNum - 1);
            System.out.println("Update session: " + session.getTitle());
            System.out.print("Enter new title (or press Enter to skip): ");
            String title = scanner.nextLine().trim();
            if (!title.isEmpty()) session.setTitle(title);
            
            System.out.print("Enter new date (or press Enter to skip): ");
            String date = scanner.nextLine().trim();
            if (!date.isEmpty()) session.setDate(date);
            
            System.out.print("Enter new start time (or press Enter to skip): ");
            String startTime = scanner.nextLine().trim();
            if (!startTime.isEmpty()) session.setStartTime(startTime);
            
            System.out.print("Enter new end time (or press Enter to skip): ");
            String endTime = scanner.nextLine().trim();
            if (!endTime.isEmpty()) session.setEndTime(endTime);
            
            System.out.print("Enter new location (or press Enter to skip): ");
            String location = scanner.nextLine().trim();
            if (!location.isEmpty()) session.setLocation(location);
            
            System.out.println("Session updated successfully!");
        } else {
            System.out.println("Invalid session number.");
        }
    }
    
    private void deleteSession(Tutor tutor) {
        List<Session> sessions = tutor.getTaughtSession();
        if (sessions.isEmpty()) {
            System.out.println("You have no sessions to delete.");
            return;
        }
        
        viewTutorSessions(tutor);
        System.out.print("Enter session number to delete: ");
        int sessionNum = getIntInput();
        
        if (sessionNum > 0 && sessionNum <= sessions.size()) {
            Session session = sessions.get(sessionNum - 1);
            tutor.deleteSession(session);
            userManager.removeSession(session);
        } else {
            System.out.println("Invalid session number.");
        }
    }
    
    private void viewSessionParticipants(Tutor tutor) {
        List<Session> sessions = tutor.getTaughtSession();
        if (sessions.isEmpty()) {
            System.out.println("You have no sessions.");
            return;
        }
        
        System.out.println("\n=== Sessions and Participants ===");
        for (Session session : sessions) {
            System.out.println("Session: " + session.getTitle());
            List<Student> participants = session.getParticipants();
            if (participants.isEmpty()) {
                System.out.println("  No participants enrolled.");
            } else {
                System.out.println("  Participants (" + participants.size() + "):");
                for (Student student : participants) {
                    System.out.println("    - " + student.getName() + " (" + student.getUserId() + ")");
                }
            }
            System.out.println();
        }
    }
    
    private void viewAllUsers() {
        List<User> users = userManager.getAllUsers();
        System.out.println("\n=== All Users ===");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
    
    private void viewAllSessions() {
        List<Session> sessions = userManager.getAllSessions();
        if (sessions.isEmpty()) {
            System.out.println("No sessions in the system.");
            return;
        }
        
        System.out.println("\n=== All Sessions ===");
        for (Session session : sessions) {
            System.out.println(session.getSessionDetails());
        }
    }
    
    private void viewSystemStatistics() {
        List<User> users = userManager.getAllUsers();
        List<Session> sessions = userManager.getAllSessions();
        
        System.out.println("\n=== System Statistics ===");
        System.out.println("Total Users: " + users.size());
        System.out.println("Total Students: " + userManager.getAllStudents().size());
        System.out.println("Total Tutors: " + userManager.getAllTutors().size());
        System.out.println("Total Sessions: " + sessions.size());
        
        int totalEnrollments = 0;
        for (Session session : sessions) {
            totalEnrollments += session.getParticipants().size();
        }
        System.out.println("Total Enrollments: " + totalEnrollments);
    }
    
    private void logout() {
        System.out.println("Logged out successfully. Goodbye, " + currentUser.getName() + "!");
        currentUser = null;
    }
    
    private int getIntInput() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}