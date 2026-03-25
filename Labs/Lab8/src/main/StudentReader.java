import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CS5004 Lab 8 – Reading and Storing Data from a File.
 *
 * Reads student information from students.txt, stores each record in a
 * Student object inside an ArrayList, and provides an interactive menu
 * that lets the user add, remove, or search for students.
 */
public class StudentReader {

    // Path to the data file (resolved relative to the working directory).
    private static final String FILE_PATH = "src/main/students.txt";

    // -------------------------------------------------------------------------
    // Entry point
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        ArrayList<Student> students = loadStudents(FILE_PATH);

        System.out.println("=== Student Database ===");
        printStudents(students);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a student");
            System.out.println("2. Remove a student by ID");
            System.out.println("3. Search for a student by ID");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent(students, scanner);
                    break;
                case "2":
                    removeStudent(students, scanner);
                    break;
                case "3":
                    searchStudent(students, scanner);
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
            }
        }

        scanner.close();
    }

    // -------------------------------------------------------------------------
    // File I/O helpers
    // -------------------------------------------------------------------------

    /**
     * Reads students.txt and returns an ArrayList of Student objects.
     */
    private static ArrayList<Student> loadStudents(String filePath) {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Warning: " + filePath + " not found. Starting with an empty list.");
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Format: FirstName LastName StudentID Email
                String[] parts = line.split("\\s+", 4);
                if (parts.length == 4) {
                    students.add(new Student(parts[0], parts[1], parts[2], parts[3]));
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return students;
    }

    /**
     * Overwrites students.txt with the current contents of the ArrayList.
     */
    private static void saveStudents(ArrayList<Student> students, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student s : students) {
                writer.write(s.getFirstName() + " " + s.getLastName() + " "
                        + s.getStudentId() + " " + s.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // Display helpers
    // -------------------------------------------------------------------------

    /**
     * Prints all students with 1-based line numbers.
     */
    private static void printStudents(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("(No students in the database.)");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }

    // -------------------------------------------------------------------------
    // Menu actions
    // -------------------------------------------------------------------------

    /** Prompts for student info, adds the student, updates the file, and prints the list. */
    private static void addStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        students.add(new Student(firstName, lastName, studentId, email));
        saveStudents(students, FILE_PATH);

        System.out.println("\nStudent added successfully. Updated list:");
        printStudents(students);
    }

    /** Prompts for an ID, removes the matching student, and updates the file. */
    private static void removeStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("Enter student ID to remove: ");
        String targetId = scanner.nextLine().trim();

        Student toRemove = findById(students, targetId);
        if (toRemove == null) {
            System.out.println("Error: No student with ID " + targetId + " found.");
            return;
        }

        students.remove(toRemove);
        saveStudents(students, FILE_PATH);
        System.out.println("Student " + targetId + " removed. Updated list:");
        printStudents(students);
    }

    /** Searches for a student by ID and prints their info or an error message. */
    private static void searchStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("Enter student ID to search: ");
        String targetId = scanner.nextLine().trim();

        Student found = findById(students, targetId);
        if (found == null) {
            System.out.println("Error: No student with ID " + targetId + " exists in the database.");
        } else {
            System.out.println("Found: " + found);
        }
    }

    /** Returns the first Student whose ID matches, or null if not found. */
    private static Student findById(ArrayList<Student> students, String id) {
        for (Student s : students) {
            if (s.getStudentId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}
