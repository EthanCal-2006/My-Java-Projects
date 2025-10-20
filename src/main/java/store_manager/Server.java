package store_manager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server class listens for incoming client connections and creates a new ClientHandler thread for each connection.
 * It also registers a shutdown hook to handle cleanup when the server is stopped.
 */
public class Server {

    private ServerSocket serverSocket;
    private final StoreFront storeFront = new StoreFront();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        // Register a shutdown hook to handle resource cleanup.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("\nShutdown hook triggered. Cleaning up...");
                try {
                    cleanup();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));

        // Run the server indefinitely until CTRL-C is pressed
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept(); // Wait for connection
                ClientHandler clientHandler = new ClientHandler(clientSocket, storeFront);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            } catch (IOException e) {
                if (serverSocket.isClosed()) {
                    System.out.println("Server socket closed, stopping server.");
                    break;
                }
                e.printStackTrace();
            }
        }
    }

    /**
     * Cleans up server resources by closing the server socket.
     * This is called by the shutdown hook when the server is stopped.
     */
    public void cleanup() throws IOException {
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
            System.out.println("Server is shut down");
        }
    } 
}
