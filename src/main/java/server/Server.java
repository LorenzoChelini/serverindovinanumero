package server;
import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server in attesa di connessioni...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connessione effettuata.");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                Random random = new Random();
                int numeroDaIndovinare = random.nextInt(100) + 1;
                int tentativi = 0;

                out.println("Benvenuto! Indovina un numero compreso tra 1 e 100.");

                while (true) {
                    tentativi++;
                    int tentativo = Integer.parseInt(in.readLine());

                    if (tentativo < numeroDaIndovinare) {
                        out.println("1"); // Numero troppo piccolo
                    } else if (tentativo > numeroDaIndovinare) {
                        out.println("2"); // Numero troppo grande
                    } else {
                        out.println("3"); // Numero indovinato
                        out.println("4"); // Invia un messaggio per chiudere la connessione
                        break;
                    }
                }
                System.out.println("Numero indovinato in " + tentativi + " tentativi.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

