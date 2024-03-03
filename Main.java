import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize the scanner for reading input from the console
        Scanner scanner = new Scanner(System.in);

        // Variables to hold the number of judges and celebrities
        int judges = 0;
        int celebrities = 0;
        String name = "";
        double score = 0.0;

        // Prompt and read the number of judges, ensuring it's between 3 and 6
        do {
            System.out.print("Please enter number of judges between 3 and 6: ");
            judges = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()
            System.out.println("The number of judges you chose is " + judges);
        } while (judges < 3 || judges > 6);

        // Prompt and read the number of celebrities, ensuring it's between 10 and 14
        do {
            System.out.print("Please enter number of celebrities between 10 and 14: ");
            celebrities = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()
            System.out.println("The number of celebrities you chose is " + celebrities);
        } while (celebrities < 10 || celebrities > 14);

        // Array to store the names of all celebrities
        String[] celebritiesName = new String[celebrities];
        System.out.println("Requesting celebrities' names");

        // Loop to read and validate each celebrity's name
        for (int i = 0; i < celebrities; i++) {
            boolean isAlphanumeric = false;
            while (!isAlphanumeric) {
                System.out.print("Please enter celebrity(" + (i + 1) + ") name: ");
                name = scanner.nextLine();
                // Validate the name to ensure it contains only letters and numbers
                isAlphanumeric = name.matches("^[a-zA-Z0-9]+$");
                if (!isAlphanumeric) {
                    System.out.println("You have entered an invalid name. Names can only contain letters and numbers.");
                } else {
                    System.out.println("You have entered " + name);
                }
            }
            celebritiesName[i] = name; // Store the valid name in the array
        }

        // Array to store the average score of each celebrity
        double[] averageScore = new double[celebrities];

        // Loop over each celebrity to collect and process scores from judges
        for (int i = 0; i < celebritiesName.length; i++) {
            System.out.println("Celebrity " + celebritiesName[i] + " has finished dancing");
            double totalScore = 0; // Variable to accumulate scores for averaging
            for (int j = 0; j < judges; j++) {
                boolean isScoreValid = false;
                // Validate each score entered by a judge
                while (!isScoreValid) {
                    System.out.print("Judge (" + (j + 1) + ") please place your score between 0 and 6 with a maximum of one decimal points: ");
                    String input = scanner.nextLine();
                    // Check if the score is in the correct format and range
                    if (input.matches("^\\d+(\\.\\d{1})?$")) {
                        score = Double.parseDouble(input);
                        if (score >= 0 && score <= 6) {
                            isScoreValid = true;
                            totalScore += score; // Add valid score to the total
                        } else {
                            System.out.println("Score must be between 0 and 6 with a maximum of one decimal points.");
                        }
                    } else {
                        System.out.println("Please enter a score with zero or one decimal point.");
                    }
                }
            }
            averageScore[i] = totalScore / judges; // Calculate the average score
        }

        // Variables to determine the winning celebrity based on the highest average score
        double maxScore = 0;
        int winner = -1;
        for (int i = 0; i < averageScore.length; i++) {
            if (averageScore[i] > maxScore) {
                maxScore = averageScore[i];
                winner = i; // Update the winner index if a new max score is found
            }
        }

        // Close the scanner to avoid resource leaks
        scanner.close();

        // Announce the winner with the highest average score
        System.out.println();
        System.out.println("And the winner is...");
        System.out.println("Winning Name: " + celebritiesName[winner]);
        System.out.println("Winning Score: " + String.format("%.2f", averageScore[winner]));
    }
}
