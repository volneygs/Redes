package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class HTTPServer404 {

	public static void main(String[] args) throws Exception{
		String requestMessageLine, fileName;

		String statusLine, contentTypeLine, entityBody, CRLF="\r\n";

		ServerSocket listenSocket = new ServerSocket(8080);

		while (true) {
			
			Socket connectionSocket = listenSocket.accept();

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			requestMessageLine = inFromClient.readLine();

			StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);

			if(tokenizedLine.nextToken().equals("GET")){
				fileName = tokenizedLine.nextToken();
				if(fileName.startsWith("/"))
					fileName = fileName.substring(1);

				File arquivo = new File("/home/volney/eclipse-workspace/Redes/src/tcp/" + fileName);



				if(arquivo.exists()) {


					byte[] data = Files.readAllBytes(Paths.get("/home/volney/eclipse-workspace/Redes/src/tcp/" + fileName));

					statusLine = "HTTP/1.0 200 OK" + CRLF;
					contentTypeLine = "Content-Type: text/html" + CRLF;

					outToClient.writeBytes(statusLine);
					outToClient.writeBytes(contentTypeLine);
					outToClient.writeBytes(CRLF);
					outToClient.write(data);

					connectionSocket.close();

				}else {

					statusLine = "HTTP/1.0 404 Not Found" + CRLF;
					contentTypeLine = "Content-Type: text/html" + CRLF;
					entityBody = "<HTML>" +
							"<HEAD><TITLE> Não encontrado.</TITLE></HEAD>" +
							"<BODY> Arquivo " + fileName + " não encontrado.</BODY></HTML>";

					outToClient.writeBytes(statusLine);
					outToClient.writeBytes(contentTypeLine);
					outToClient.writeBytes(CRLF);
					outToClient.writeBytes(entityBody);

					connectionSocket.close();

				}
				
				connectionSocket.close();

			}

		}

	}	

}

