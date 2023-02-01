package CompitoTpsit;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    int numeroRandom;
    public void start(int numeroRandom) {
        this.numeroRandom = numeroRandom;
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            for (;;) {
                System.out.println("1 Server in attesa...");
                Socket socket = serverSocket.accept();
                System.out.println(socket);
                System.out.println("3 server socket " + socket);
                ServerThread serverThread = new ServerThread(socket, this.numeroRandom);
                serverThread.start();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        MultiServer tcpServer = new MultiServer();
        int randomNumber = (int)(Math.random() * 10) + 1;
        tcpServer.start(randomNumber);
    }

}
