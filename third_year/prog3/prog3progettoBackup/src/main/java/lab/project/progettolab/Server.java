package lab.project.progettolab;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 4445;
    private static final ArrayList<ServerTask> serverTasks = new ArrayList<>();
    private static final ExecutorService pool = Executors.newFixedThreadPool(2);
   // private static final Map<String, ServerThread> clientsMap = new ConcurrentHashMap<String,ServerThread>();
    /*###Concurrent HashMap###*/

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        while(true){
            Socket client = listener.accept();
            ServerTask serverTask = new ServerTask(client);
            serverTasks.add(serverTask);
            pool.submit(serverTask);
        }
    }
}