import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;

    public Server(int porta) throws IOException {
        this.porta = porta;
        serverSocket = new ServerSocket(porta);
        clientSocket = new serverSocket.accept();
    }

    public Socket attendi() {
        try {
            socket = serverSocket.accept();
        }catch (IOException e) {
            //Server non riesce ad instaurare la connessione
        }
    }

    public void scrivi() {

    }

    public void leggi() {

    }

    public void chiudi() {

    }

    public void termina() {

    }
}