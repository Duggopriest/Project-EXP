import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try {
			Selector selector = Selector.open();
			SocketChannel sc = SocketChannel.open();

			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress("localhost", 1234));
			sc.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE);

			while (true) {
				if (selector.select() > 0) {
					SelectionKey key = null;

					Iterator iter = selector.selectedKeys().iterator();
					while (iter.hasNext()) {
						key = (SelectionKey) iter.next();
						iter.remove();
					}
					SocketChannel ksc = (SocketChannel) key.channel();
					while (ksc.isConnectionPending()) {
						ksc.finishConnect();
					}

					if (key.isWritable()) {
						System.out.print("Type a message (type quit to stop): ");
						String msg = new Scanner(System.in).nextLine();
						if (msg.equalsIgnoreCase("quit")) {
							break;
						}
						ByteBuffer bb = ByteBuffer.wrap(msg.getBytes());
						sc.write(bb);
					}
				}
			}

		}
		catch (Exception e) {System.out.println("Failed");}
	}
}