import java.io.*;
import java.net.*;

public class Client {
    private String nome;
    private Socket socket;

    public Client(String nome) {
        this.nome = nome;
    }

    public int connetti(String nomeServer, int portaServer) {
        try {
            socket = new Socket(nomeServer, portaServer);
            System.out.println(nome + ": Connesso al server!");
            return 1;
        } catch (UnknownHostException e) {
            System.err.println("ERRORE: Host sconosciuto '" + nomeServer + "' (Scenario 4)");
            return -1;
        } catch (ConnectException e) {
            System.err.println("ERRORE: Server non raggiungibile. È acceso? (Scenario 2)");
            return -1;
        } catch (IOException e) {
            System.err.println("Errore di connessione: " + e.getMessage());
            return -1;
        }
    }

    public void scrivi() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Richiesta da " + nome);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void leggi() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("Risposta ricevuta: " + in.readUTF());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void chiudi() {
        try {
            if (socket != null) socket.close();
            System.out.println("Connessione client chiusa.");
        } catch (IOException e) { e.printStackTrace(); }
    }
}