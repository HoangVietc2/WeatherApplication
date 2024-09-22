/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weather_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author OS
 */
public class Server {
    private ServerSocket serverSocket ;
    
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
     public void serverStart(){

        try{
            // check and loop the serverSocket
            while(!serverSocket.isClosed()){
                
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostAddress()+ " connected with IP address ");
                ClientHandler clientHandler = new ClientHandler(socket);
                
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e){

        }
    }
    // this will close the server
    public void closerServer(){
        
        try{
        if(serverSocket != null){
            serverSocket.close();
        }
    } catch(IOException e){
        e.printStackTrace();
    }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2024);
        System.out.println("Server start");
        Server server = new Server(serverSocket);
        server.serverStart();
    }
}
