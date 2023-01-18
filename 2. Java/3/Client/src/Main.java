import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter message to send");
		String toSend = scanner.nextLine();

		try {
			Socket s = new Socket("localhost", 1234);
			DataOutputStream out = new DataOutputStream(s.getOutputStream());

			out.writeUTF(toSend);
			out.flush();
			out.close();
			s.close();
		}
		catch (Exception e) {
			System.out.println("Failed");
		}
	}
}