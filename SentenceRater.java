import java.util.ArrayList;
import java.util.Scanner;

/**
 * The SentenceRater class processes a user-provided sentence to calculate a complexity score.
 * It uses word lists (easy, medium, and hard) loaded from a text file to evaluate each word.
 */
public class SentenceRater {
    private ArrayList<String> easyWords;
    private ArrayList<String> mediumWords;
    private ArrayList<String> hardWords;
    private String userSentence;
    private int complexityScore;

    /**
     * Constructs a SentenceRater by reading word difficulty data from the specified file.
     * <p>
     * Preconditions:
     * <ul>
     *   <li>The provided filename must refer to a valid text file formatted appropriately.</li>
     * </ul>
     * Postconditions:
     * <ul>
     *   <li>The easyWords, mediumWords, and hardWords lists are populated with the appropriate words.</li>
     * </ul>
     * </p>
     *
     * @param filename the name of the file containing word difficulty data.
     */
    public SentenceRater(String filename) {
        // Create a TextProcessor to read the difficulty data from the file.
        TextProcessor processor = new TextProcessor(filename);
        easyWords = new ArrayList<>();
        mediumWords = new ArrayList<>();
        hardWords = new ArrayList<>();
        // Populate the difficulty lists based on the data in textList.
        checkWordDifficulty(processor.getTextList());
    }

    /**
     * Parses each line of the provided text list and populates the appropriate difficulty lists.
     * <p>
     * Preconditions:
     * <ul>
     *   <li>textList must contain non-null strings that start with "easy: ", "medium: ", or "hard: ".</li>
     * </ul>
     * Postconditions:
     * <ul>
     *   <li>The easyWords, mediumWords, and hardWords lists are updated with words from the corresponding lines.</li>
     * </ul>
     * </p>
     *
     * @param textList the list of lines read from the word difficulty file.
     */
    private void checkWordDifficulty(ArrayList<String> textList) {
        for (String line : textList) {
            // Determine which difficulty list to update based on the line's prefix.
            if (line.startsWith("easy: ")) {
                addWordsToList(line.substring(6), easyWords);
            } else if (line.startsWith("medium: ")) {
                addWordsToList(line.substring(8), mediumWords);
            } else if (line.startsWith("hard: ")) {
                addWordsToList(line.substring(6), hardWords);
            }
        }
    }

    /**
     * Splits a comma-separated string of words, trims and normalizes each word, and adds them to the specified list.
     * <p>
     * Preconditions:
     * <ul>
     *   <li>wordsStr must be a non-null comma-separated string.</li>
     *   <li>list must be a valid ArrayList.</li>
     * </ul>
     * Postconditions:
     * <ul>
     *   <li>Each word is trimmed, converted to lowercase, and added to the list.</li>
     * </ul>
     * </p>
     *
     * @param wordsStr the string containing words separated by commas.
     * @param list the ArrayList to store the processed words.
     */
    private void addWordsToList(String wordsStr, ArrayList<String> list) {
        // Split the string by comma and space.
        String[] words = wordsStr.split(", ");
        // Process each word before adding to the list.
        for (String word : words) {
            // Trim any extra spaces and normalize to lowercase.
            list.add(word.trim().toLowerCase());
        }
    }

    /**
     * Prompts the user to enter a sentence, reads the input, and then calculates its complexity score.
     * <p>
     * Preconditions:
     * <ul>
     *   <li>System.in must be available for input.</li>
     * </ul>
     * Postconditions:
     * <ul>
     *   <li>userSentence is set with the entered sentence and complexityScore is computed based on the sentence.</li>
     * </ul>
     * </p>
     */
    public void inputSentence() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a sentence to analyze:");
        // Read the user's sentence from the console.
        userSentence = input.nextLine();
        input.close();
        // Calculate the complexity score based on word difficulty.
        calculateComplexity();
    }

    /**
     * Calculates the complexity score of the user's sentence by comparing each word against the difficulty lists.
     * <p>
     * Scoring system:
     * <ul>
     *   <li>Hard words add 3 points.</li>
     *   <li>Medium words add 2 points.</li>
     *   <li>All other words add 1 point.</li>
     * </ul>
     * Postconditions:
     * <ul>
     *   <li>The complexityScore field is updated with the calculated score.</li>
     * </ul>
     * </p>
     */
    private void calculateComplexity() {
        String[] words = userSentence.split(" ");
        complexityScore = 0;
        // Evaluate each word in the sentence.
        for (String word : words) {
            // Normalize the word: convert to lowercase and remove non-alphabetic characters.
            String lowerWord = word.toLowerCase().replaceAll("[^a-z]", "");
            if (hardWords.contains(lowerWord)) {
                complexityScore += 3;
            } else if (mediumWords.contains(lowerWord)) {
                complexityScore += 2;
            } else {
                complexityScore += 1;
            }
        }
    }

    /**
     * Prints the summary of the analysis, including the original sentence, complexity score, and a descriptive complexity level.
     * <p>
     * Postconditions:
     * <ul>
     *   <li>The analysis summary is output to the console.</li>
     * </ul>
     * </p>
     */
    public void printSummary() {
        System.out.println("Sentence: " + userSentence);
        System.out.println("Complexity Score: " + complexityScore);
        System.out.println("Complexity Level: " + getComplexityLevel());
    }

    /**
     * Determines the complexity level based on the computed complexity score.
     * <p>
     * Returns:
     * <ul>
     *   <li>"Very Complex" if the score is greater than 20.</li>
     *   <li>"Moderately Complex" if the score is greater than 10 but not exceeding 20.</li>
     *   <li>"Simple" otherwise.</li>
     * </ul>
     * </p>
     *
     * @return a String representing the complexity level.
     */
    private String getComplexityLevel() {
        if (complexityScore > 20) {
            return "Very Complex";
        } else if (complexityScore > 10) {
            return "Moderately Complex";
        } else {
            return "Simple";
        }
    }
}