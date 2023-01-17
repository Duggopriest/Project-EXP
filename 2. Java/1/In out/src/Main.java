import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");

		System.out.print("Reading input\n");
		Scanner scanner = new Scanner(System.in);

		String str = scanner.nextLine();

		System.out.println("Message : " + str);
	}
}