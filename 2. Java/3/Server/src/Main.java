import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(1234);
			System.out.println("Started");

			while (true)
			{
				Socket s = ss.accept();
				System.out.println("New Connection");

				DataInputStream input = new DataInputStream(s.getInputStream());
				String str = input.readUTF();

				System.out.println(str);
				s.close();
			}
		}
		catch (Exception e) {
			System.out.println("Failed");
		}
	}
}