package tcp;

import java.io.*;
import java.net.*;

class TCPServer {

	public static void main(String argv[]) throws Exception {

		String[] opcao;

		ServerSocket welcomeSocket = new ServerSocket(7000);

		Webservice ws = new Webservice();

		while(true) {

			Socket connectionSocket = welcomeSocket.accept();

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			opcao = inFromClient.readLine().split(" ");

			switch (opcao[0].toUpperCase()) {
			case "CONCATENAR":
				outToClient.writeBytes(ws.concat(opcao[1], opcao[2]));
				break;
			case "COMPARAR":
				outToClient.writeBytes(ws.comparar(opcao[1], opcao[2]));
				break;
			case "SUBSTRING":
				outToClient.writeBytes(ws.substring(opcao[1], opcao[2], opcao[3]));
				break;
			case "CONTEM":
				outToClient.writeBytes(ws.contem(opcao[1], opcao[2]));
				break;
			case "SUBSTITUIR":
				outToClient.writeBytes(ws.substituir(opcao[1], opcao[2], opcao[3]));
				break;
			default:
				outToClient.writeBytes("ERRO");
				break;
			}
		}
	}
}