package CompitoTpsit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
public class MultiClient {

    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    boolean vittoria;
    private String nome;

    public Socket connetti() {
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return miosocket;
    }

    public void vittoria(){
        System.out.println("HO VINTO SZE");
    }


    public void comunica(String nome) {
        this.nome = nome;
            try {
                System.out.println("inserire numero lotteria" + '\n');
                stringaUtente = tastiera.readLine();
                outVersoServer.writeBytes(stringaUtente + '\n');
                stringaRicevutaDalServer = inDalServer.readLine();
                System.out.println(nome +" "+ stringaRicevutaDalServer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Errore durante la connessione");
                System.exit(1);
            }
    }
    public static void main(String args[]) {
        MultiClient cliente1 = new MultiClient();
        System.out.println("CLIENT 1 partito in esecuzione ...");
        cliente1.connetti();
        cliente1.comunica("Client 1");
        MultiClient cliente2 = new MultiClient();
        System.out.println("CLIENT 2 partito in esecuzione ...");
        cliente2.connetti();
        cliente2.comunica("Client 2");

        MultiClient cliente3 = new MultiClient();
        System.out.println("CLIENT 3 partito in esecuzione ...");
        cliente3.connetti();
        cliente3.comunica("Client 3");

        MultiClient cliente4 = new MultiClient();
        System.out.println("CLIENT 4 partito in esecuzione ...");
        cliente4.connetti();
        cliente4.comunica("Client 4");

    }

}