package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import tcp.Webservice; 

class UDPServer { 
	
    public static void main(String args[]) throws Exception { 
	
    	DatagramSocket serverSocket = new DatagramSocket(9876); 
	
    	byte[] receiveData = new byte[2048]; 
    	byte[] sendData  = new byte[2048]; 
    	
    	Webservice ws;
	
    	while(true) { 
    		
    		String[] opcao;
        	String response;
        	ws = new Webservice();
		
			DatagramPacket receivePacket =  new DatagramPacket(receiveData, receiveData.length); 
			
			serverSocket.receive(receivePacket); 
			
			String sentence = new String(receivePacket.getData()); 
			
			InetAddress IPAddress = receivePacket.getAddress(); 
			
			int port = receivePacket.getPort(); 
			
			String capitalizedSentence = sentence.toUpperCase();
			
			opcao = capitalizedSentence.split(" ");
			
			for (int i = 0; i < opcao.length; i++) {
				System.out.println(opcao[i]);
			}
			System.out.println(opcao.length);
			
			switch (opcao[0].toUpperCase()) {
			case "CONCATENAR":
				response = ws.concat(opcao[1], opcao[2]);
				break;
			case "COMPARAR":
				response = ws.comparar(opcao[1], opcao[2]);
				break;
			case "SUBSTRING":
				response = ws.substring(opcao[1], opcao[2], opcao[3]);
				break;
			case "CONTEM":
				response = ws.contem(opcao[1], opcao[2]);
				break;
			case "SUBSTITUIR":
				response = ws.substituir(opcao[1], opcao[2], opcao[3]);
				break;
			default:
				response = "ERRO";
				break;
			}
			
			sendData = response.getBytes(); 
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
			
			serverSocket.send(sendPacket); 
		
	    } 
    } 
}
