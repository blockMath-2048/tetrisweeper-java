import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.print("Enter the name of the file to process: ");
        String filename = stdin.nextLine();
        try {
            Runtime.getRuntime().exec(String.format("cp %s %s-summary.txt", filename, filename));
            Runtime.getRuntime().exec(String.format("sed '/^($|[0-9])/d' %s", filename));
        } catch (Exception ignored) {

        }
    }
}
