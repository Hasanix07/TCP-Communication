public class MainServer {
    public static void main(String[] args) {
        Server s = new Server(2000);
        if (s.attendi() != null) {
            s.leggi();
            s.scrivi();
            s.chiudi();
        }
        s.termina();
    }
}