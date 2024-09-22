/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package https;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author OS
 */
public interface HttpHelper {
    HttpResponse get(String URL) throws MalformedURLException, IOException;
}
