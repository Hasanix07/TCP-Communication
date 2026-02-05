import java.io.IOException;
import java.net.Socket;

public class MainServer {
    private static Throwable e;

    public static void main(String[] args) {
        // Qui istanzierai la classe Server
        int porta = 12345;
        Server server = new Server(porta);

        try{
            server = new Server(porta);

            Socket socket = server.attendi();
            catch(IOException e){
                e.printStackTrace();
                int status = 1;
                System.exit(status);


            }
        }
    }
}