import java.util.ArrayList;

/**
 * The TextProcessor class reads and processes text data from a file.
 * It converts the file content into an ArrayList of strings, where each element represents a line of text.
 */
public class TextProcessor {
    private String filename;
    private ArrayList<String> textList;

    /**
     * Constructs a TextProcessor for the given file.
     * <p>
     * Preconditions:
     * <ul>
     *   <li>The filename provided must refer to a valid text file containing the necessary data.</li>
     * </ul>
     * Postconditions:
     * <ul>
     *   <li>textList is populated with lines from the file.</li>
     * </ul>
     * </p>
     *
     * @param filename the name of the file to process.
     */
    public TextProcessor(String filename) {
        this.filename = filename;
        // Populate textList with file content using a helper FileReader utility.
        textList = FileReader.toStringList(filename);
    }

    /**
     * Retrieves the filename associated with this TextProcessor.
     *
     * @return the filename as a String.
     */
    public String getFileName() {
        return filename;
    }

    /**
     * Retrieves the list of lines read from the file.
     *
     * @return an ArrayList of strings where each element is a line from the file.
     */
    public ArrayList<String> getTextList() {
        return textList;
    }

    /**
     * Changes the file being processed and updates the textList accordingly.
     * <p>
     * Preconditions:
     * <ul>
     *   <li>The new filename must refer to a valid text file.</li>
     * </ul>
     * Postconditions:
     * <ul>
     *   <li>textList is updated with the lines from the new file.</li>
     * </ul>
     * </p>
     *
     * @param filename the name of the new file to process.
     */
    public void changeFile(String filename) {
        this.filename = filename;
        textList = FileReader.toStringList(filename);
    }

    /**
     * Converts the file's content into an ArrayList of individual words.
     * <p>
     * Each line in textList is split into words based on spaces and added to a new list.
     * </p>
     *
     * @return an ArrayList of words extracted from the file.
     */
    public ArrayList<String> textToWords() {
        ArrayList<String> wordList = new ArrayList<>();
        // Split each line into words and add them to wordList.
        for (String line : textList) {
            String[] words = line.split(" ");
            for (String word : words) {
                wordList.add(word);
            }
        }
        return wordList;
    }

    /**
     * Returns a formatted String representation of the entire text.
     * <p>
     * All lines from textList are concatenated with newline characters.
     * </p>
     *
     * @return a string containing the full text content.
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        // Append each line from textList with a newline.
        for (String value : textList) {
            text.append(value).append("\n");
        }
        return text.toString();
    }
}