To Print to the terminal we can use the System class to interact with the terminal

we can refer to the Standard in,out with System.in, System.out

thoese also have methods to print to with .print, .println   
println just prints with a newline \n

To read we can use the Scanner class and when we create it with "new Scanner(System.in)" where we can pass a file descriptor

in my code I did Scanner scanner = new Scanner(System.in);
so to get a string from the "System.in" we call the .nextLine() function from Scanner and it returns a string
so in my case its "String str = scanner.nextLine()"
keeping in mind that .nextLine does block the program until it receves an input

