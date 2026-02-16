import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== JAVA-CRAFT ===");
        System.out.print("Scegli il personaggio: 1) Steve 2) Alex: ");
        int scelta = scanner.hasNextInt() ? scanner.nextInt() : 1;
        scanner.nextLine();

        String nome = (scelta == 2) ? "Alex" : "Steve";
        Client player = new Client(nome, (scelta == 2) ? "F" : "M");

        if (player.connetti("localhost", 5000) == 0) {
            System.out.println(player.leggi());

            while (true) {
                System.out.print("\n[" + nome + "] Azione > ");
                String comando = scanner.nextLine();
                player.scrivi(comando);

                if (comando.equalsIgnoreCase("ESCI")) {
                    System.out.println("Disconnessione dal server...");
                    break;
                }

                String situazione = player.leggi();
                System.out.println(situazione);

                if (situazione.contains("VITTORIA") || situazione.contains("GAME OVER")) {
                    break;
                }
            }
            player.chiudi();
        } else {
            System.err.println("Errore: Server offline (Scenario 2) o Host errato (Scenario 4).");
        }
        scanner.close();
    }
}