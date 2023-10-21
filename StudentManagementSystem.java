import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String studentID;
    private String name;
    private String branch;
    private double gpa;
    private int totalClasses;
    private int attendedClasses;

    public Student(String studentID, String name, String branch, double gpa, int totalClasses, int attendedClasses) {
        this.studentID = studentID;
        this.name = name;
        this.branch = branch;
        this.gpa = gpa;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
    }


    public String getStudentID() {
        return studentID;
    }
    
    public void setName(String newName) {
      this.name=newName;
    }
    
     public void setBranch(String newBranch) {
      this.branch=newBranch;
    }
    
     public void setGpa(double newGpa) {
      this.gpa=newGpa;
    }
    
     public void setTotalClasses(int newTotalClasses) {
      this.totalClasses=newTotalClasses;
    }
    
     public void setAttendedClasses(int newAttendedClasses) {
      this.attendedClasses=newAttendedClasses;
    }
    
    public String toString() {
        return "      Student ID: " + studentID + "\n      Name: " + name + "\n      Branch: " + branch + "\n      GPA: " + gpa
                + "\n      Total Classes: " + totalClasses + "\n      Attended Classes: " + attendedClasses;
    }
}

class StudentDatabase {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(updatedStudent.getStudentID())) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    public void deleteStudent(String studentID) {
        students.removeIf(student -> student.getStudentID().equals(studentID));
    }

    public List<Student> getAllStudents() {
        return students;
    }
}

public class StudentManagementSystem {
    private StudentDatabase database;

    public StudentManagementSystem() {
        database = new StudentDatabase();
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Find Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    findStudent();
                    break;
                case 5:
                    displayAllStudents();
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        String studentID = scanner.next();
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Branch: ");
        String branch = scanner.next();
        System.out.print("Enter GPA: ");
        double gpa = scanner.nextDouble();
        System.out.print("Enter Total Classes: ");
        int totalClasses = scanner.nextInt();
        System.out.print("Enter Attended Classes: ");
        int attendedClasses = scanner.nextInt();

        Student student = new Student(studentID, name, branch, gpa, totalClasses, attendedClasses);
        database.addStudent(student);
        System.out.println("____________________________");
        System.out.println("|Student added successfully|");
        System.out.println("----------------------------");
    }

    private void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID to update: ");
        String studentID = scanner.next();
        Student student = database.findStudent(studentID);

        if (student != null) {
            System.out.print("Enter new Name: ");
            student.setName(scanner.next());
            System.out.print("Enter new Branch: ");
            student.setBranch(scanner.next());
            System.out.print("Enter new GPA: ");
            student.setGpa(scanner.nextDouble());
            System.out.print("Enter new Total Classes: ");
            student.setTotalClasses(scanner.nextInt());
            System.out.print("Enter new Attended Classes: ");
            student.setAttendedClasses(scanner.nextInt());
            database.updateStudent(student);
            System.out.println("________________________________");
            System.out.println("| Student updated successfully |");
            System.out.println("--------------------------------");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID to delete: ");
        String studentID = scanner.next();
        Student student = database.findStudent(studentID);

        if (student != null) {
            database.deleteStudent(studentID);
            System.out.println("____________________________");
            System.out.println("Student deleted successfully");
            System.out.println("----------------------------");
        } else {
            System.out.println();
            System.out.println("XXXXXXXX......Student not found......XXXXXXXX");
            System.out.println();
        }
    }

    private void findStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID to find: ");
        String studentID = scanner.next();
        Student student = database.findStudent(studentID);

        if (student != null) {
            System.out.println("Student Information:\n" + student);
        } else {
            System.out.println();
            System.out.println("XXXXXXXX......Student not found......xxxxxxxx");
            System.out.println();
        }
    }

    private void displayAllStudents() {
        List<Student> students = database.getAllStudents();
        if (!students.isEmpty()) {
            System.out.println("All Students:");
            for (Student student : students) {
                System.out.println(student);
                System.out.println();
                System.out.println("***********************************************");
                System.out.println();
            }
        } else {
            System.out.println("No students in the database.");
        }
    }
}

