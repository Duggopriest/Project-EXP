
----------------------------- Server -----------------------------------------------------

here we are going to handle multiple clients at once in a non blocking manner

to do this we use a Selector
this contains all the Socket chennels and later we can go though them and see if they are trying to send data

first we make a ServerSocketChannel for the clients to connect to
but this time we need to configure it a bit more
first we set it to non blocking : 						.configureBlocking(false);
next we bind it to a socket address : 					.bind(new InetSocketAddress("localhost", 1234));
then we register it with the Selector and the mode : 	.register(selector, SelectionKey.OP_ACCEPT);

to select each channel we can use the SelectionKey
we can check the state of the channel its on via :		.isAcceptable(), .isReadable(), .isWritable(), .isConnectable()
we can then grab the SocketChannel with :				.channel()

when we accept a new connection comes in it we check the ServerSocketChannel and use the :	.accept()
to grab the new SocketChannel and regester it just like the ServerSocketChannel but with the "SelectionKey.OP_READ" insted

----------------------------- Client -----------------------------------------------------

almost same deal here only with the original SocketChannel we use the flags : 	SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE

and we have to wait for the connection to be established for each key with : 	while (ksc.isConnectionPending()) {ksc.finishConnect();}