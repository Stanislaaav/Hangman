import java.util.ArrayList;
import java.util.Scanner;

public class KeyboardInput
{
    // Properties
    ArrayList<String> keyList ;
    boolean chosen;

    //Constructor
    public KeyboardInput()
    {
        keyList = new ArrayList<String>();
    }

    // Adds and stores the entered key in the Arraylist
    public void keyChosen( String key )
    {
        keyList.add( key );
    }

    // Checks if the entered key has been chosed before
    public boolean hasBeenChosen( String key)
    {
        chosen = keyList.contains( key );
        return chosen;
    }


    public char getNewLetter()
    {
        Scanner scan=new Scanner( System.in);

        char a;
        boolean chosen;
        String aTmp;    // used to convert a into a String for compatibility

        do
        {
            System.out.println("Enter a Letter");
            a = scan.next().charAt( 0);
            aTmp = "" + a;
            chosen = hasBeenChosen( aTmp);
            if(!chosen) {
                keyChosen(aTmp);
            } else {
                System.out.println("You've already used that one. Please enter a new Letter");

            }


        }
        // we repeat the process until we're out of trials.
        while(chosen);

        return a;
    }
}
