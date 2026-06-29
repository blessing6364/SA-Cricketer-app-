import java.util.Scanner;

// Interface defining the cricket contract
interface ICricket {
    String getBatsman();
    String getStadium();
    int getRunsScored();
    void printReport();
}

// Abstract class implementing shared logic and enforcing printReport()
abstract class AbstractCricket implements ICricket {

    protected final String batsmanName;
    protected final String stadiumName;
    protected final int runsScored;

    public AbstractCricket(String batsmanName, String stadiumName, int runsScored) {
        if (batsmanName == null || batsmanName.trim().isEmpty())
            throw new IllegalArgumentException("Batsman name cannot be empty.");
        if (stadiumName == null || stadiumName.trim().isEmpty())
            throw new IllegalArgumentException("Stadium name cannot be empty.");
        if (runsScored < 0)
            throw new IllegalArgumentException("Runs scored cannot be negative.");

        this.batsmanName = batsmanName.trim();
        this.stadiumName = stadiumName.trim();
        this.runsScored  = runsScored;
    }

    @Override public String getBatsman()    { return batsmanName; }
    @Override public String getStadium()    { return stadiumName; }
    @Override public int    getRunsScored() { return runsScored;  }
}

// Concrete class — single responsibility: format and print the report
class CricketRunsScored extends AbstractCricket {

    private static final String DIVIDER = "─".repeat(35);

    public CricketRunsScored(String batsmanName, String stadiumName, int runsScored) {
        super(batsmanName, stadiumName, runsScored);
    }

    @Override
    public void printReport() {
        System.out.println("\n" + DIVIDER);
        System.out.println("   BATSMAN RUNS SCORED REPORT");
        System.out.println(DIVIDER);
        System.out.printf("  %-20s %s%n", "Player:",       getBatsman());
        System.out.printf("  %-20s %s%n", "Stadium:",      getStadium());
        System.out.printf("  %-20s %d%n", "Runs Scored:",  getRunsScored());
        System.out.println(DIVIDER);
    }
}

// Driver class — handles all user interaction with full validation
public class CricketApp {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            String batsmanName = promptString(input, "Enter the cricketer name: ");
            String stadiumName = promptString(input, "Enter the stadium: ");
            int runsScored     = promptInt(input,
                "Enter the total runs scored by " + batsmanName + " at " + stadiumName + ": ");

            ICricket cricketer = new CricketRunsScored(batsmanName, stadiumName, runsScored);
            cricketer.printReport();

        } catch (IllegalArgumentException e) {
            System.err.println("Input error: " + e.getMessage());
        }
    }

    // Keeps prompting until a non-blank string is entered
    private static String promptString(Scanner input, String prompt) {
        String value;
        do {
            System.out.print(prompt);
            value = input.nextLine().trim();
            if (value.isEmpty()) System.out.println("  ⚠ This field cannot be blank. Try again.");
        } while (value.isEmpty());
        return value;
    }

    // Keeps prompting until a valid non-negative integer is entered
    private static int promptInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= 0) return value;
                System.out.println("  ⚠ Runs cannot be negative. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Invalid number. Please enter a whole number.");
            }
        }
    }
}
