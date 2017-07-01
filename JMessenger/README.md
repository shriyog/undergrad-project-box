JMessenger is a java chat application consisting :

1. A server
2. Multiple clients
3. A database to store user credentials & chats  

Contents of source code:
Uback is server
Uend is client
ReceiveMessageInterface is an contract for RMI methods.
A database(MS Access file).
Steps to setup:

    Compile all 3 files.
    Setup database connectivity-

    see in attached mhtml doc. (I have shown d steps using recorder)

      3. Run server using java Uback.java and

      ( If u get Exception then only run=>  rmic Uback )

      4. Run Uend file which is GUI client on seperate terminal.....when it

      starts, it asks for server IP,  enter "Localhost" and then signup first.

      5. U can see db updated.....now run 2 clients and show chat betwn them.

