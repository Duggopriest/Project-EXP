import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		try {
			Selector selector = Selector.open();
			ServerSocketChannel ssc = ServerSocketChannel.open();

			ssc.configureBlocking(false);
			ssc.bind(new InetSocketAddress("localhost", 1234));
			ssc.register(selector, SelectionKey.OP_ACCEPT);

			SelectionKey key = null;

			while (true) {
				if (selector.select() <= 0)
					continue;

				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();

				while (iter.hasNext()) {
					key = (SelectionKey) iter.next();
					iter.remove();

					if (key.isAcceptable())
					{
						SocketChannel sc = ssc.accept();
						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ);
						System.out.println("Connection Accepted: " + sc.getLocalAddress());
					}

					if (key.isReadable())
					{
						SocketChannel sc = (SocketChannel) key.channel();

						ByteBuffer bb = ByteBuffer.allocate(1024);
						sc.read(bb);

						String result = new String(bb.array()).trim();
						System.out.println("Message :" + result);
					}
				}
			}
		}
		catch (Exception e) {System.out.println("Failed");}
	}
}