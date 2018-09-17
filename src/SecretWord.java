import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SecretWord {
    String visible, secret;

    /**
     * read text from file
     * @return
     */

    private Map<String, ArrayList<String>> readTextFile() {
        ArrayList<String> listOfWords = new ArrayList<>();
        Map<String, ArrayList<String>> listMap = new HashMap<>();
        String categories = "";
        Scanner in;
        {
            try {
                in = new Scanner(new FileReader("E:\\Hangman\\src\\words.txt"));

                while (in.hasNextLine()) {
                    String str = in.nextLine();

                    if (!str.matches("[^_]*")) {
                        if (!listMap.containsKey(str) && !categories.equals("")) {
                            listMap.put(categories, listOfWords);
                            listOfWords = new ArrayList<>();
                        }
                        categories = str;
                    } else {
                        listOfWords.add(str);
                    }
                }
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        listMap.put(categories, listOfWords);

        return listMap;
    }

    /**
     * hide the word
     * @param category
     */
    public SecretWord(String category) {

        Random rand = new Random();
        int lengOfList = readTextFile().get("_" + category).size();
        int num = rand.nextInt(lengOfList);
        secret = readTextFile().get("_" + category).get(num);

        visible = "";

        for (int i = 0; i < secret.length(); i++) {
            char c = secret.toLowerCase().charAt(i);

            if (c == ' ')
                visible += c;
            else
                visible += "_";
        }
    }

    /**
     * enter white spaces
     * @return
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < secret.length(); i++) {
            str += visible.charAt(i) + " ";
        }
       return str;
    }

    /**
     * update the visibylity of the word
     * @param c
     * @return
     */
    public boolean update(char c) {
        char[] secretArray = new char[secret.length()];
        char[] visibleArray = new char[secret.length()];
        String s = "";
        boolean contains = false;

        //We make the String secret an array
        for (int i = 0; i < secret.length(); i++) {
            secretArray[i] = secret.toLowerCase().charAt(i);
            visibleArray[i] = visible.toLowerCase().charAt(i);
        }

        //uptading our empty visible array
        for (int k = 0; k < secret.length(); k++) {

            if (secretArray[k] == c) {
                visibleArray[k] = c;
                contains = true;
            }
        }
        //hanging our visible array into the visible string
        for (int m = 0; m < secret.length(); m++) {
            s = s + visibleArray[m];
            visible = s;
        }
        visible = s;

        return contains;
    }

    //This is a method to check if the secret word is solved
    public boolean isSolved() {
        return secret.equalsIgnoreCase(visible);
    }
}
