package CompitoTpsit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
class ServerThread extends Thread {
  ServerSocket server = null;
  Socket client = null;
  String stringaRicevuta = null;
  String StringaModificata = null;
  BufferedReader inDalClient;
  DataOutputStream outVersoClient;
  public int numberRandom;

  public ServerThread(Socket socket, int numeroRandom) {
    this.client = socket;
    this.numberRandom = numeroRandom;
  }

  public void run() {
    try {
      System.out.println("numero " + numberRandom);
      comunica(numberRandom);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void comunica(int randomNumber) throws IOException {

    inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    outVersoClient = new DataOutputStream(client.getOutputStream());
    stringaRicevuta = inDalClient.readLine();
    int numero = Integer.parseInt(stringaRicevuta);
    if (numero == randomNumber)
    outVersoClient.writeBytes(stringaRicevuta + " Hai vinto " + "\n");
    else
      outVersoClient.writeBytes(stringaRicevuta + " Hai perso " + "\n");
  }
}




    /*for (;;) {
      stringaRicevuta = inDalClient.readLine();
      /*if (stringaRicevuta == null || stringaRicevuta.equals("FINE")) {
        outVersoClient.writeBytes(stringaRicevuta + " SERVER IN CHIUSURA " + "\n");
        System.out.println("Echo su server in chiusura: " + stringaRicevuta);
        break;
      } else { */







     /*outVersoClient.close();
    inDalClient.close();
    System.out.println("Chiusura socket " + client);
    client.close();
  }*/
//}