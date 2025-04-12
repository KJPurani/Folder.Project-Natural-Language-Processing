/**
 * The SentenceRaterRunner class serves as the entry point for the Sentence Rater application.
 * It initializes a SentenceRater with a file containing word difficulty data, 
 * prompts the user to enter a sentence, and prints the analysis summary to the console.
 */
public class SentenceRaterRunner {
    /**
     * Main method that creates a SentenceRater and runs the analysis.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize a SentenceRater with the word difficulty file.
        SentenceRater rater = new SentenceRater("word_difficulty.txt");
        
        // Prompt the user for a sentence and calculate its complexity.
        rater.inputSentence();
        
        // Output the analysis summary: the sentence, score, and complexity level.
        rater.printSummary();
    }
}
