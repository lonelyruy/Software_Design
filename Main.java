class TutoringSystemDemo {
    public static void main(String[] args) {
        UserManager system = new UserManager();
        
        Student student1 = new Student("S001", "Alice Johnson", "alice@email.com", "pass123", "ST2024001");
        Student student2 = new Student("S002", "Bob Smith", "bob@email.com", "pass456", "ST2024002");
        Tutor tutor1 = new Tutor("T001", "Dr. Sarah Wilson", "sarah@email.com", "tutorpass", "Mathematics");
        Admin admin1 = new Admin("A001", "John Admin", "admin@email.com", "adminpass", "IT Department");
        
        system.registerUser(student1);
        system.registerUser(student2);
        system.registerUser(tutor1);
        system.registerUser(admin1);
        
        Session mathSession = tutor1.createSession("SES001", "Calculus I Tutorial", 
                                                  "2024-03-15", "14:00", "16:00", "Room A101");
        system.addSession(mathSession);
        
        Auth auth = new Auth(system);
        
        System.out.println("Test Accounts:");
        System.out.println("Student: S001, pass123");
        System.out.println("Tutor: T001, tutorpass");
        System.out.println("Admin: A001, adminpass");
        System.out.println();
        
        auth.start();
    }
}