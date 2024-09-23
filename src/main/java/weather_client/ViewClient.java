/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package weather_client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author OS
 */
public class ViewClient extends javax.swing.JFrame {

    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private Socket socket;

    public ViewClient(Socket socket) {
        this.socket = socket;

        try {
            buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            readCityName();
        } catch (IOException e) {
            e.printStackTrace();
            //closeAll(socket, reader, writer);
        }

        initComponents();

    }

    private void readCityName() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGroupChat;

                while (socket.isConnected()) {
                    try {
                        messageFromGroupChat = buffReader.readLine();
                        if (isValidJson(messageFromGroupChat)) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode weatherResponse = objectMapper.readTree(messageFromGroupChat);
                            String locationName = weatherResponse.path("location").path("name").asText();
                            String country = weatherResponse.path("location").path("country").asText();
                            int localtime_epoch = weatherResponse.path("location").path("localtime_epoch").asInt();
                            String local_time = weatherResponse.path("location").path("localtime").asText();
                            String wind_mph = weatherResponse.path("current").path("wind_mph").asText();
                            String precip_mm = weatherResponse.path("current").path("precip_mm").asText();
                            String temperature = weatherResponse.path("current").path("temp_c").asText();
                            String humidity = weatherResponse.path("current").path("humidity").asText();

                            String icon = weatherResponse.path("current").path("icon").asText();
                            String text = weatherResponse.path("current").path("text").asText();
                            text_temp.setText(temperature + "°C");
                            text_namecity.setText(locationName + ", " + country);
                            text_wind.setText(wind_mph + " km/h");
                            text_precip.setText(precip_mm + " mm");
                            text_pressure.setText(humidity + " %");
                            text_info.setText(text);

                            URL url = new URL(icon);
                            Image image = ImageIO.read(url);
                            ImageIcon icon1 = new ImageIcon(image);
                            img_main.setIcon(icon1);
                        } else {
                            JOptionPane.showMessageDialog(null, messageFromGroupChat);
                        }
                    } catch (IOException e) {
                        closeAll(socket, buffReader, buffWriter);
                    }
                }
            }
        }).start();
    }

    private boolean isValidJson(String message) {
        try {
            new ObjectMapper().readTree(message);
            return true;
        } catch (IOException e) {
            return false; // Nếu có lỗi, chuỗi không phải là JSON hợp lệ
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        text_namecity = new javax.swing.JLabel();
        text_temp = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        img_main = new javax.swing.JLabel();
        text_wind = new javax.swing.JLabel();
        text_precip = new javax.swing.JLabel();
        text_pressure = new javax.swing.JLabel();
        tf_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        text_info = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        text_namecity.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N

        text_temp.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        text_temp.setText("0°C");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setText("Wind :");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setText("Precip :");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setText("Humidity :");

        img_main.setIcon(new javax.swing.ImageIcon("C:\\Users\\OS\\Downloads\\sn.jpg")); // NOI18N

        tf_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_searchActionPerformed(evt);
            }
        });

        btn_search.setText("Search");
        btn_search.setActionCommand("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(text_namecity))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(text_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(img_main, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120)
                        .addComponent(text_temp)
                        .addGap(120, 120, 120)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(text_precip, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(text_pressure, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(text_wind, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(text_namecity, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(text_wind))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(text_precip))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(text_pressure)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(img_main, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_temp, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(text_info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_searchActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        sendCityName();

    }//GEN-LAST:event_btn_searchActionPerformed

    private void sendCityName() {
        try {
            String nameToServer = tf_search.getText().trim();
            if (!nameToServer.isEmpty()) {
                buffWriter.write(nameToServer);
                buffWriter.newLine();
                buffWriter.flush();

                tf_search.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "you must enter the city name!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter) {
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("localhost", 2024);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewClient(socket).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_search;
    private javax.swing.JLabel img_main;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel text_info;
    private javax.swing.JLabel text_namecity;
    private javax.swing.JLabel text_precip;
    private javax.swing.JLabel text_pressure;
    private javax.swing.JLabel text_temp;
    private javax.swing.JLabel text_wind;
    private javax.swing.JTextField tf_search;
    // End of variables declaration//GEN-END:variables

}
