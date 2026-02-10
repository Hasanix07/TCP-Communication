import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;

    public Server(int porta) {
        this.porta = porta;
    }

    public Socket attendi() {
        try {
            // Scenario 3: Se la porta è già occupata, qui scatterà un'eccezione
            serverSocket = new ServerSocket(porta);
            System.out.println("Server avviato. In attesa di connessioni sulla porta " + porta + "...");

            clientSocket = serverSocket.accept();
            System.out.println("Client connesso: " + clientSocket.getInetAddress());
            return clientSocket;
        } catch (BindException e) {
            System.err.println("ERRORE: Porta " + porta + " già in uso! (Scenario 3)");
        } catch (IOException e) {
            System.err.println("Errore di avvio server: " + e.getMessage());
        }
        return null;
    }

    public void leggi() {
        try {
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            String msg = in.readUTF();
            System.out.println("Richiesta ricevuta: " + msg);
        } catch (IOException e) {
            System.err.println("Errore in lettura: " + e.getMessage());
        }
    }

    public void scrivi() {
        try {
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeUTF("Risposta dal Server: Messaggio ricevuto con successo.");
            out.flush();
        } catch (IOException e) {
            System.err.println("Errore in scrittura: " + e.getMessage());
        }
    }

    public void chiudi() {
        try {
            if (clientSocket != null) clientSocket.close();
            System.out.println("Comunicazione con il client chiusa.");
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void termina() {
        try {
            if (serverSocket != null) serverSocket.close();
            System.out.println("Servizio server terminato.");
        } catch (IOException e) { e.printStackTrace(); }
    }
}