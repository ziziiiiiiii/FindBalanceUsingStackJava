import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;

public class StackUtils {
    public static Stack<Character> buildStack(File f) {
        Stack<Character> curly = new Stack<>();
        Scanner fileScan = null;
        try {
            fileScan = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileScan.hasNextLine()) {
            String fileLine = fileScan.nextLine();
            String[] fileLineSplit = fileLine.split(" ");

            for (String loopVar : fileLineSplit) {
                if (loopVar.equals("{") || loopVar.equals("}")) {
                    Character loopChar = loopVar.charAt(0);
                    curly.push(loopChar);
                }
            }
        }

        return curly;
    }

    public static int reduce(File f) {
        Stack<Character> curly = buildStack(f);
        int leftCount = 0;
        int rightCount = 0;

        for (Character loopVar : curly) {
            if (loopVar.equals('}')) {
                rightCount++;
            }
            else if (loopVar.equals('{') && leftCount >= rightCount) {
                leftCount++;
            }
        }

        if (leftCount == rightCount) {
            return 0;
        }
        else {
            return 1;
        }
    }
}