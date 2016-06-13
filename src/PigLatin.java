import java.util.Scanner;

public class PigLatin {

	// method testing a letter for vowels
	public static boolean isVowel(char s) {
		if (s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u')
			return true;
		else
			return false;
	}

	public static boolean hasVowel(String word) {
		boolean hasVowel = false;

		for (int i = 0; i < word.length(); i++) {
			if (isVowel(word.charAt(i))) {
				hasVowel = true;
			}
		}
		return hasVowel;
	}

	public static String translate(String word) {

		boolean isUpper = false;

		if (Character.isUpperCase(word.charAt(0)))
			isUpper = true;

		// convert word to lower case
		word = word.toLowerCase();

		// test first character of word for vowels
		if (isVowel(word.charAt(0))) {

			word += "way";

			// if not a vowel
		} else {
			int index = 0;
			boolean done = false;
			if (hasVowel(word)) {

				do {

					if (isVowel(word.charAt(index))) {
						// add all consonants to end of string
						word += word.substring(0, index);

						// chop all consonants up to the first vowel
						word = word.substring(index);

						// exit loop
						done = true;
					}
					index++;

				} while (done == false);

				// if word has no vowels, treat y as vowel
			} else {
				word += word.substring(0, word.indexOf('y'));
				word = word.substring(word.indexOf('y'));
			}
			word += "ay";
		}
		// if first character was upper case, new first letter gets converted to
		// upper case
		if (isUpper) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			return word;
		} else
			// if not, return word as is
			return word;
	}

	public static String createSentence(String sentence) {

		String newSentence = "";

		for (int i = 0; i < sentence.length(); i++) {
			if (i == sentence.length() - 1) {
				// flip the last word
				newSentence += translate(sentence);
			} else if (sentence.charAt(i) == ' ') {
				// flip 0-this word
				newSentence += translate(sentence.substring(0, i)) + " ";
				// substring starting at next letter
				sentence = sentence.substring(i + 1);
				// reset i
				i = 0;
			}
		}
		return newSentence;
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out
				.println("Welcome to the Pig Latin Translator!\nEnter a line to be translated");
		System.out.println(createSentence(scan.nextLine()));
		scan.close();

	}
}
