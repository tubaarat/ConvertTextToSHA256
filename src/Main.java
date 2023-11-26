import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File("src/all_words.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
		while ((line = br.readLine()) != null) {
			try {
				String[] array = line.split(",");

				for (String word : array) {
					word = word.trim();
					StringBuilder hexString = getSHA256HashString(word);

					if (hexString.toString().contains("fb80")) {
						System.out.println("Le mot : " + word);
						System.out.println("Le hash : " + hexString.toString());
					}
				}

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

	}

	public static StringBuilder getSHA256HashString(String word) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");

		byte[] hash = md.digest(word.getBytes());

		StringBuilder hexString = new StringBuilder();

		for (byte b : hash) {
			hexString.append(String.format("%02x", b));
		}

		return hexString;
	}
}