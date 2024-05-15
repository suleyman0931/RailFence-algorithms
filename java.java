import java.util.Arrays;
import java.util.Scanner;

class RailFence {

	// Placeholder character used to mark empty spaces in the rail array
	private static final char PLACEHOLDER = '\n';

	// Encrypts a given text using the Rail Fence Cipher with the given key
	public static String encryptRailFence(String text, int key) {
		// Initialize a 2D array with the given key and text length
		char[][] rail = new char[key][text.length()];
		for (int i = 0; i < key; i++) {
			Arrays.fill(rail[i], PLACEHOLDER);
		}

		// Initialize variables to keep track of the current row and column
		// in the rail array
		boolean isDirectionDown = false;
		int currentRow = 0, currentColumn = 0;

		// Iterate through the text and fill the rail array
		for (int i = 0; i < text.length(); i++) {
			if (currentRow == 0 || currentRow == key - 1) {
				isDirectionDown =!isDirectionDown;
			}

			// Place the current character in the rail array
			rail[currentRow][currentColumn++] = text.charAt(i);

			// Update the current row based on the direction
			if (isDirectionDown) {
				currentRow++;
			} else {
				currentRow--;
			}
		}

		// Build the encrypted cipher by concatenating the characters in the rail array
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < key; i++) {
			for (int j = 0; j < text.length(); j++) {
				if (rail[i][j]!= PLACEHOLDER) {
					result.append(rail[i][j]);
				}
			}
		}

		return result.toString();
	}

	// Decrypts a given cipher using the Rail Fence Cipher with the given key
	public static String decryptRailFence(String cipher, int key) {
		// Initialize a 2D array with the given key and cipher length
		char[][] rail = new char[key][cipher.length()];
		for (int i = 0; i < key; i++) {
			Arrays.fill(rail[i], PLACEHOLDER);
		}

		// Initialize variables to keep track of the current row and column
		// in the rail array
		boolean isDirectionDown = true;
		int currentRow = 0, currentColumn = 0;

		// Iterate through the cipher and fill the rail array
		for (int i = 0; i < cipher.length(); i++) {
			if (currentRow == 0) {
				isDirectionDown = true;
			}
			if (currentRow == key - 1) {
				isDirectionDown = false;
			}

			// Place the current character in the rail array
			rail[currentRow][currentColumn++] = '*';

			// Update the current row based on the direction
			if (isDirectionDown) {
				currentRow++;
			} else {
				currentRow--;
			}
		}

		// Replace the placeholders in the rail array with the actual characters
		// from the cipher
		int index = 0;
		for (int i = 0; i < key; i++) {
			for (int j = 0; j < cipher.length(); j++) {
				if (rail[i][j] == '*' && index < cipher.length()) {
					rail[i][j] = cipher.charAt(index++);
				}
			}
		}

		// Build the decrypted message by iterating through the rail array
		StringBuilder result = new StringBuilder();

		currentRow = 0;
		currentColumn = 0;
		for (int i = 0; i < cipher.length(); i++) {
			if (currentRow == 0) {
				isDirectionDown = true;
			}
			if (currentRow == key - 1) {
				isDirectionDown = false;
			}

			// Add the current character to the result string
			if (rail[currentRow][currentColumn]!= '*') {
				result.append(rail[currentRow][currentColumn++]);
			}

			// Update the current row based on the direction
			if (isDirectionDown) {
				currentRow++;
			} else {
				currentRow--;
			}
		}

		return result.toString();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter 1 for encryption, 2 for decryption:");
		int choice = scanner.nextInt();

		switch (choice) {
			case 1:
				System.out.println("Enter the text to encrypt:");
				String text = scanner.next();
				System.out.println("Enter the key:");
				int key = scanner.nextInt();
				System.out.println("Encrypted Message: " + encryptRailFence(text, key));
				break;
			case 2:
				System.out.println("Enter the cipher to decrypt:");
				String cipher = scanner.next();
				System.out.println("Enter the key:");
				key = scanner.nextInt();
				System.out.println("Decrypted Message: " + decryptRailFence(cipher, key));
				break;
			default:
				System.out.println("Invalid choice");
		}
	}
}