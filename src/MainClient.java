public class MainClient {
    public static void main(String[] args) {
        Client c = new Client("Pietro");

        String serverIndirizzo = "localhost";
        int porta = 2000;

        if (c.connetti(serverIndirizzo, porta) > 0) {
            c.scrivi();
            c.leggi();
            c.chiudi();
        } else {
            System.out.println("Sistemazione automatica: controlla se il Server è stato avviato PRIMA del Client.");
        }
    }
}