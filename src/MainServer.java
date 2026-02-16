import java.util.Random;

public class MainServer {
    // Codici ANSI per i colori
    public static final String RESET = "\u001B[0m";
    public static final String ROSSO = "\u001B[31m";
    public static final String VERDE_ACCESO = "\u001B[32;1m"; // Bold Green
    public static final String ORO = "\u001B[33;1m"; // Bold Gold/Yellow
    public static final String VIOLA = "\u001B[35m";
    public static final String CIANO = "\u001B[36m";
    public static final String VITTORIA_COL = "\u001B[32;1;4m"; // Verde, grassetto e sottolineato

    public static void main(String[] args) {
        Server mcServer = new Server(5000);
        Random rand = new Random();

        if (mcServer.attendi() != null) {
            int playerHP = 10;
            int dragonHP = 50;
            int smeraldi = rand.nextInt(31) + 20;
            int meleDoro = 0;
            boolean haSpada = false;
            boolean cristalliVivi = true;
            boolean giocoAttivo = true;

            mcServer.scrivi(CIANO + "--- BENVENUTO NELL'END ---" + RESET + "\nDigita 'HELP' o 'ESCI'.");

            while (giocoAttivo) {
                String azione = mcServer.leggi().toUpperCase();
                String risposta = "";

                switch (azione) {
                    case "ESCI":
                        risposta = "Hai abbandonato la partita.";
                        giocoAttivo = false;
                        break;
                    case "TRADE":
                        if (smeraldi >= 10) {
                            smeraldi -= 10; meleDoro++;
                            risposta = VERDE_ACCESO + "Scambio riuscito con il villager!" + RESET;
                        } else { risposta = ROSSO + "Smeraldi insufficienti!" + RESET; }
                        break;
                    case "CRAFT":
                        haSpada = true;
                        risposta = CIANO + "Spada in diamante forgiata!" + RESET;
                        break;
                    case "CRISTALLI":
                        cristalliVivi = false;
                        risposta = VIOLA + "Cristalli distrutti! Il drago e' debole." + RESET;
                        break;
                    case "MANGIA":
                        if (meleDoro > 0) {
                            playerHP = Math.min(10, playerHP + 5);
                            meleDoro--;
                            risposta = ORO + "Hai mangiato la Mela d'oro! La vita si rigenera." + RESET;
                        } else { risposta = "Non hai mele!"; }
                        break;
                    case "ATTACCA":
                        int danno = haSpada ? 10 : 3;
                        dragonHP -= danno;
                        risposta = "Hai colpito il drago!";
                        break;
                    case "HELP":
                        risposta = "Comandi: TRADE(smeraldi), CRAFT(spada in diamante), CRISTALLI(distruggi), ATTACCA, MANGIA(rigenera vita), ESCI";
                        break;
                    default:
                        risposta = "Azione non riconosciuta.";
                }

                if (giocoAttivo && dragonHP > 0 && !azione.equals("HELP")) {
                    int dannoDrago = rand.nextInt(4);
                    playerHP -= dannoDrago;
                    risposta += " | " + ROSSO + "IL DRAGO ti toglie " + dannoDrago + " HP!" + RESET;
                    if (cristalliVivi && dragonHP < 50) {
                        dragonHP = Math.min(50, dragonHP + 3);
                        risposta += " (Il drago si rigenera)";
                    }
                }

                if (playerHP <= 0) {
                    risposta += "\n" + ROSSO + "--- GAME OVER --- Sei stato sconfitto." + RESET;
                    giocoAttivo = false;
                } else if (dragonHP <= 0) {
                    risposta += "\n" + VITTORIA_COL + "--- VITTORIA! --- Il drago e' caduto!" + RESET;
                    giocoAttivo = false;
                }

                // --- UI GRAFICA ---
                String cuoriUI = ROSSO;
                for (int i = 0; i < 10; i++) {
                    if (i < playerHP) cuoriUI += "❤"; else cuoriUI += "♡";
                }
                cuoriUI += RESET;

                String bossBar = VIOLA + "[";
                int blocchi = dragonHP / 5;
                for (int i = 0; i < 10; i++) {
                    if (i < blocchi) bossBar += "█"; else bossBar += "░";
                }
                bossBar += "]" + RESET;

                String stato = String.format(
                        "\n========================================" +
                                "\nVITA: %s  | " + VERDE_ACCESO + "SMERALDI: %d" + RESET + " | " + ORO + "MELE: %d" + RESET +
                                "\nDRAGO: %s (%d/50)" +
                                "\n----------------------------------------" +
                                "\n%s" +
                                "\n========================================",
                        cuoriUI, smeraldi, meleDoro, bossBar, dragonHP, risposta
                );
                mcServer.scrivi(stato);
            }
            mcServer.chiudi();
            mcServer.termina();
        }
    }
}