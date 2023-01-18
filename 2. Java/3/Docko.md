
----------------------------- Server -----------------------------------------------------

here we are just gona sent a single string to a server using sockets

first we create a ServerSocket

then we use .accept from it that returns a linked Socket to the client that connected
this function is a blocking function so the program will not run until a client connects

we can use DataInputStream to handle the input data and give it the Stream to use: new DataInputStream(s.getInputStream());
we use the .readUTF to format the data into a string

----------------------------- Client -----------------------------------------------------

first we create a Socket and enter the ip and port: new Socket("localhost", 1234);

now we use a DataOutputStream to handle the data and give it the Stream we want to use: new DataOutputStream(s.getOutputStream());

next is pritty simple
we just use .write() from DataOutputStream to put what we want to write in a buffer
then we send it with .flush() to send
