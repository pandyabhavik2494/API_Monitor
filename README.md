# API Monitor

A simple Java based program which can monitor the status of certain configurable third party APIs.

It uses the concepts of Scheduler, Multithreading and Two way Client-Server communication via Socket programming in Java to provide the following functionalities:

•	It can monitor status of upto 3 (Configurable) APIs at a time by establishing a TCP connection.

•	Multiple clients (ClientA.java,ClientB.java) can interact with the API Monitor(Monitor.java) simultaneously.

•	The frequency of polling the APIs is configurable.

•	The monitor does not allow more that 2 hits to the same API in less than a second(Hard Coded but Configurable).

•	The user can also define a No notification period wherein he would not be notified if the APIs are Functional or Down for the no notification period.

•	For now, the number of Users/Clients that can interact with the monitor(Monitor.java) is two (ClientA.java, ClientB.java) but it can be increased as wished.


In order to see a demo of the API Monitor, take a clone and run the Monitor.java from a terminal/cmd and run the ClientA.java and ClientB.java from different terminals/cmd.


Feel free to use the code as per your wish and have some fun!
