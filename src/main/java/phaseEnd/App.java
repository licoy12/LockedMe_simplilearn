package phaseEnd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class App {

	final static String FOLDER = "C:\\tmp";
	static Scanner in = new Scanner(System.in);
	static File[] files = new File(FOLDER).listFiles();
	static Set<String> sortedSet = new TreeSet<>();

	public static void main(String[] args) throws IOException {
		
		//create the file in the directory if it doesn't exist
		File directory = new File(FOLDER);
		if (!directory.exists()) {
			directory.mkdir();
		}
		displayWelcomeScreen();
		showMenu();
	}// end of main

	public static void showMenu() {
		while (true) {
			String option = chooseOption();
			switch (option) {
			case "1":
				showFilesInAscendingOrder();
				break;
			case "2":
				businessLevelOperation();
				break;
			case "3":
				System.out.println("Thank you for using My File Handling Application" + "\nExiting . . . .");
				System.exit(0);
			default:
				System.out.println("Invalid Option");
			}
		}
	}

	public static void showFilesInAscendingOrder() throws NullPointerException {
		if (files.length < 1) {
			System.out.println("This directory doesn't have any file");
			return;
		}
		System.out.println("Showing files in ascending order. . .");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		for (File file : files) {
			if (!file.isFile()) {
				continue;
			}
			sortedSet.add(file.getName());
		}
		sortedSet.forEach(System.out::println);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	}

	public static void addFile() {
		System.out.println("Please provide a file path: ");
		String filePath = in.nextLine().toLowerCase();
		Path path = Paths.get(filePath);

		if (!Files.exists(path)) {
			System.out.println("File does not exist");
			return;
		}
		String newFilePath = FOLDER + "/" + path.getFileName();
		int counter = 0;
		while (Files.exists(Paths.get(newFilePath))) {
			counter++;
			newFilePath = FOLDER + "/" + counter + "_" + path.getFileName();
		}
		try {
			Files.copy(path, Paths.get(newFilePath));
		} catch (IOException e) {
			System.out.println("Unable to copy file to " + newFilePath);
		}
		System.out.println("Successfully copied file to folder!");
	}

	public static void deleteFile() {
		System.out.println("Enter the name of the file to be deleted: ");
		String fileNameToBeRemove = in.nextLine().toLowerCase();
		String fileToBeDelete = FOLDER + "\\" + fileNameToBeRemove;
		File file = new File(fileToBeDelete);

		if (file.delete()) {
			System.out.println(fileNameToBeRemove + " has been sucessfully deleted");
			return;
		}
		System.out.println("Cannot find " + fileNameToBeRemove + " in the directory");
	}

	public static void searchFile() {
		System.out.println("------------------");
		files = new File(FOLDER).listFiles();
		System.out.print("Enter the filename you want to look for: ");
		String searchFor = in.nextLine();
		for (File file : files) {
			if (searchFor.equals(file.getName())) {
				System.out.println(searchFor + " exists in the directory!");
				return;
			}
		}
		System.out.println("Cannot find " + searchFor + " in the directory");
	}

	public static void displayWelcomeScreen() {
		System.out.println("******************************************");
		System.out.printf("*\t%-33s*\n", "MY FILE HANDLING APPLICATION");
		System.out.printf("*%-40s*\n", "");
		System.out.printf("*%-40s*\n", "");
		System.out.printf("*%40s*\n", "Developed by:");
		System.out.printf("*%40s*\n", "Angelico Rodriguez");
		System.out.printf("*%-40s*\n", "");
		System.out.println("******************************************");
	}

	public static String chooseOption() {
		System.out.print("\n\nPlease make a selection\n\n" + "1). Sort the List\n" + "2). Business Level Operations\n"
				+ "3). Exit the program\n" + "Your Selection: ");
		String selection = in.nextLine();
		return selection;
	}

	public static String displayBusinessLevelOperation() {
		System.out.print("\n1). Add a file\n" + "2). Delete a file\n" + "3). Search a file from the List\n"
				+ "4). Go back to main menu\n" + "Your Selection: ");
		return in.nextLine();
	}

	public static void businessLevelOperation() {
		String bloSelection = displayBusinessLevelOperation();

		switch (bloSelection) {
		case "1":
			addFile();
			break;
		case "2":
			deleteFile();
			break;
		case "3":
			searchFile();
			break;
		case "4":
			break;
		default:
			System.out.println("Invalid Selection!");
			bloSelection = displayBusinessLevelOperation();
			break;
		}
	}

}
