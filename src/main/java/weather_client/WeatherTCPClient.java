/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weather_client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author OS
 */
public class WeatherTCPClient {
    
    private String cityname;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Socket socket;

    public WeatherTCPClient(String cityname, Socket socket) {
        this.cityname = cityname;
        this.socket = socket;
        
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            writer.write(cityname);
            writer.newLine();
            writer.flush();
            
            readCityName();
        } catch (IOException e) {
            e.printStackTrace();
            //closeAll(socket, reader, writer);
        }
        
    }

    private void readCityName() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGroupChat;

                while(socket.isConnected()) {
                    try {
                            messageFromGroupChat = reader.readLine();
                            System.out.println(messageFromGroupChat);;
                    }catch(IOException e) {
                        //closeAll(socket, reader, writer);
                    }
                }
            }
        }).start();
    }
 
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2024);
        Scanner sc = new Scanner(System.in);
        String nameCity = sc.nextLine();
        WeatherTCPClient w = new WeatherTCPClient(nameCity, socket);
 
    }
    
}
