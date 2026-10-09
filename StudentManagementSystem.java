package student;
import java.util.Scanner;
public class StudentManagementSystem {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

	        String studentName = "";
	        String kkuId = "";
	        boolean isDataEntered = false;
	        double[] marks = null;

	        int choice;

	        do {
	            displayMenu();
	            System.out.print("Enter your choice (1-6): ");

	            while (!scanner.hasNextInt()) {
	                System.out.println("Invalid input. Please enter a number between 1 and 6.");
	                scanner.next();
	                System.out.print("Enter your choice (1-6): ");
	            }

	            choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {

	                case 1:
	                    studentName = inputStudentName(scanner);
	                    System.out.println("Student Name set to: " + studentName);
	                    break;

	                case 2:
	                    // KKU ID Validation
	                    kkuId = inputValidKKUId(scanner);
	                    isDataEntered = true;
	                    System.out.println("KKU ID saved successfully.");
	                    break;

	                case 3:
	                    if (!isDataEntered) {
	                        System.out.println("Please enter student details first!");
	                    } else {
	                        System.out.println("Feature to enter and calculate marks.");

	                        // Number of subjects validation
	                        int numberOfSubjects = inputNumberOfSubjects(scanner);

	                        System.out.println("Number of subjects accepted: "
	                                + numberOfSubjects);

	                        marks = new double[numberOfSubjects];

	                        // Mark validation
	                        for (int i = 1; i <= numberOfSubjects; i++) {
	                            System.out.print("Enter mark for subject " + i + ": ");
	                            double mark = inputValidMark(scanner);

	                            System.out.println("Valid mark: " + mark);
	                            marks[i - 1] = mark;
	                        }

	                        displayMarksInfo(marks);
	                    }
	                    break;

	                case 4:
	                    if (!isDataEntered) {
	                        System.out.println("No student data available to modify.");
	                    } else {
	                        System.out.println("Feature to modify a mark.");

	                        if (marks == null) {
	                            System.out.println("No marks entered yet. Please choose option 3 first.");
	                        } else {
	                            displayAllMarks(marks);
	                            int subjectNumber = inputSubjectNumber(scanner, marks.length);
	                            System.out.print("New mark for subject " + subjectNumber + " - ");
	                            double newMark = inputValidMark(scanner);
	                            updateMark(marks, subjectNumber, newMark);
	                            System.out.println("Mark updated successfully.");
	                            displayMarksInfo(marks);
	                        }
	                    }
	                    break;

	                case 5:
	                    if (!isDataEntered) {
	                        System.out.println("No student details available.");
	                    } else {
	                        System.out.println("\n--- STUDENT SUMMARY ---");
	                        System.out.println("Name: " + studentName);
	                        System.out.println("ID: " + kkuId);

	                        if (marks == null) {
	                            System.out.println("No marks entered yet.");
	                        } else {
	                            displayMarksInfo(marks);
	                        }
	                    }
	                    break;

	                case 6:
	                    System.out.println("Exiting the program. Goodbye!");
	                    break;

	                default:
	                    System.out.println("Invalid choice. Please choose an option from 1 to 6.");
	            }

	            System.out.println();

	        } while (choice != 6);

	        scanner.close();
	    }

	    public static void displayMenu() {
	        System.out.println("==========================================");
	        System.out.println("    STUDENT GRADE MANAGEMENT SYSTEM       ");
	        System.out.println("==========================================");
	        System.out.println("1. Enter Student Name");
	        System.out.println("2. Enter KKU ID");
	        System.out.println("3. Input Marks & Perform Calculations");
	        System.out.println("4. Modify a Mark");
	        System.out.println("5. Display Summary & Final Grade");
	        System.out.println("6. Exit");
	        System.out.println("==========================================");
	    }

	    public static String formatStudentName(String name) {
	        if (name == null || name.trim().isEmpty()) {
	            return "";
	        }

	        String[] words = name.trim().split("\\s+");
	        StringBuilder formattedName = new StringBuilder();

	        for (String word : words) {
	            if (!word.isEmpty()) {
	                String capitalized = word.substring(0, 1).toUpperCase()
	                        + word.substring(1).toLowerCase();

	                formattedName.append(capitalized).append(" ");
	            }
	        }

	        return formattedName.toString().trim();
	    }

	    public static String inputStudentName(Scanner scanner) {
	        System.out.print("Enter Student Name: ");

	        String input = scanner.nextLine();
	        String formattedName = formatStudentName(input);

	        while (formattedName.isEmpty()) {
	            System.out.print("Invalid name. Please enter a valid Student Name: ");

	            input = scanner.nextLine();
	            formattedName = formatStudentName(input);
	        }

	        return formattedName;
	    }

	    // ==================== DATA VALIDATION ====================

	    // Validate KKU ID
	    public static boolean isValidKKUId(String kkuId) {

	        if (kkuId == null) {
	            return false;
	        }

	        kkuId = kkuId.trim();

	        // KKU ID must contain exactly 9 digits
	        if (kkuId.length() != 9) {
	            return false;
	        }

	        for (int i = 0; i < kkuId.length(); i++) {
	            if (!Character.isDigit(kkuId.charAt(i))) {
	                return false;
	            }
	        }

	        return true;
	    }

	    // Get a valid KKU ID
	    public static String inputValidKKUId(Scanner scanner) {

	        String kkuId;

	        do {
	            System.out.print("Enter KKU ID: ");
	            kkuId = scanner.nextLine().trim();

	            if (!isValidKKUId(kkuId)) {
	                System.out.println(
	                        "Invalid KKU ID. Please enter exactly 9 digits."
	                );
	            }

	        } while (!isValidKKUId(kkuId));

	        return kkuId;
	    }

	    // Validate number of subjects
	    public static int inputNumberOfSubjects(Scanner scanner) {

	        int numberOfSubjects;

	        while (true) {

	            System.out.print("Enter number of subjects: ");

	            while (!scanner.hasNextInt()) {
	                System.out.println(
	                        "Invalid input. Please enter a positive number."
	                );
	                scanner.next();
	            }

	            numberOfSubjects = scanner.nextInt();
	            scanner.nextLine();

	            if (numberOfSubjects <= 0) {
	                System.out.println(
	                        "Number of subjects cannot be 0 or negative."
	                );
	            } else {
	                return numberOfSubjects;
	            }
	        }
	    }

	    // Validate marks from 0 to 100
	    public static double inputValidMark(Scanner scanner) {

	        double mark;

	        while (true) {

	            System.out.print("Enter mark (0-100): ");

	            if (!scanner.hasNextDouble()) {
	                System.out.println(
	                        "Invalid input. Please enter a number between 0 and 100."
	                );
	                scanner.next();
	                continue;
	            }

	            mark = scanner.nextDouble();
	            scanner.nextLine();

	            if (mark < 0 || mark > 100) {
	                System.out.println(
	                        "Invalid mark. Mark must be between 0 and 100."
	                );
	            } else {
	                return mark;
	            }
	        }
	    }

	    public static double calculateAverage(double[] marks) {
	        double sum = 0;
	        for (int i = 0; i < marks.length; i++) {
	            sum += marks[i];
	        }
	        return sum / marks.length;
	    }

	    public static double findMax(double[] marks) {
	        double max = marks[0];
	        for (int i = 1; i < marks.length; i++) {
	            if (marks[i] > max) {
	                max = marks[i];
	            }
	        }
	        return max;
	    }

	    public static double findMin(double[] marks) {
	        double min = marks[0];
	        for (int i = 1; i < marks.length; i++) {
	            if (marks[i] < min) {
	                min = marks[i];
	            }
	        }
	        return min;
	    }

	    public static String getStatus(double average) {
	        if (average >= 60) {
	            return "Pass";
	        } else {
	            return "Fail";
	        }
	    }

	    public static String getGrade(double mark) {
	        if (mark >= 90) return "A";
	        else if (mark >= 80) return "B";
	        else if (mark >= 70) return "C";
	        else if (mark >= 60) return "D";
	        else return "F";
	    }

	    public static void updateMark(double[] marks, int subjectNumber, double newMark) {
	        marks[subjectNumber - 1] = newMark;
	    }

	    public static int inputSubjectNumber(Scanner scanner, int numberOfSubjects) {
	        int subjectNumber;

	        while (true) {
	            System.out.print("Enter subject number to modify (1-" + numberOfSubjects + "): ");

	            if (!scanner.hasNextInt()) {
	                System.out.println("Invalid input. Please enter a number.");
	                scanner.next();
	                continue;
	            }

	            subjectNumber = scanner.nextInt();
	            scanner.nextLine();

	            if (subjectNumber < 1 || subjectNumber > numberOfSubjects) {
	                System.out.println("Invalid subject number.");
	            } else {
	                return subjectNumber;
	            }
	        }
	    }

	    public static void displayAllMarks(double[] marks) {
	        System.out.println("\nCurrent marks:");
	        for (int i = 0; i < marks.length; i++) {
	            System.out.println("Subject " + (i + 1) + ": " + marks[i] + "  Grade: " + getGrade(marks[i]));
	        }
	    }

	    public static void displayMarksInfo(double[] marks) {
	        double average = calculateAverage(marks);

	        System.out.println("Average: " + String.format(java.util.Locale.US, "%.2f", average));	        displayAllMarks(marks);
	       
	        System.out.println("Max Mark: " + findMax(marks));
	        System.out.println("Min Mark: " + findMin(marks));
	        System.out.println("Status: " + getStatus(average));
	    }
}
