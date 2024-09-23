/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weather_server;

import api.ApiWeatherService;
import api.ApiWeatherServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import model.Weather;

/**
 *
 * @author OS
 */
public class ClientHandler implements Runnable {

    public Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    public ClientHandler(Socket socket) {
        // Constructors of all the private classes
        try {
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            clientHandlers.add(this);

        } catch (IOException e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }

    @Override
    public void run() {
        String nameCity;
        while (socket.isConnected()) {
            try {
                nameCity = buffReader.readLine();
                System.out.println(nameCity);
                ApiWeatherService i = new ApiWeatherServiceImpl();
                Weather weather = i.getWeatherByCityName(nameCity);
                sendToClient(weather);
            } catch (IOException e) {
                closeAll(socket, buffReader, buffWriter);
                break;
            }
        }
    }

    public void sendToClient(Weather information) {
        try {
            if (information != null) { // Kiểm tra nếu thông tin không phải là null
                String jsonResponse = objectMapper.writeValueAsString(information); // Chuyển đổi đối tượng thành JSON
                buffWriter.write(jsonResponse);
                buffWriter.newLine();
                buffWriter.flush();
//                  boradcastMessage(jsonResponse);
            } else {
                buffWriter.write("No weather  information found!");
                buffWriter.newLine();
                buffWriter.flush();
            }
        } catch (Exception e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        System.out.println("IP address " + socket.getInetAddress().getHostAddress() + " has left Application");
    }

    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter) {

        // handle the removeClient funciton
        removeClientHandler();

        try {
            if (buffReader != null) {
                buffReader.close();
            }
            if (buffWriter != null) {
                buffWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

}
