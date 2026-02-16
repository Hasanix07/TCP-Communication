import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;
    private DataInputStream in;
    private DataOutputStream out;

    public Server(int porta) {
        this.porta = porta;
    }

    public Socket attendi() {
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("[SERVER] In ascolto sulla porta " + porta + "...");
            clientSocket = serverSocket.accept();
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (BindException e) {
            System.err.println("[ERRORE SCENARIO 3] Porta gia' in uso!");
        } catch (IOException e) {
            System.err.println("Errore: " + e.getMessage());
        }
        return clientSocket;
    }

    public void scrivi(String messaggio) {
        try { out.writeUTF(messaggio); } catch (IOException e) { e.printStackTrace(); }
    }

    public String leggi() {
        try { return in.readUTF(); } catch (IOException e) { return "ESCI"; }
    }

    public void chiudi() {
        try { if (clientSocket != null) clientSocket.close(); } catch (IOException e) { e.printStackTrace(); }
    }

    public void termina() {
        try { if (serverSocket != null) serverSocket.close(); } catch (IOException e) { e.printStackTrace(); }
    }
}