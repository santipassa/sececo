/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Painter;

public class DesktopPainter implements Painter<JComponent> {

    private Image image;

    public DesktopPainter() {

        try {
            URL iconURL = getClass().getResource("/Imagenes/fondo.jpg");
            image = ImageIO.read(iconURL);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D g, JComponent object, int width, int height) {

        g.drawImage(image, 0, 0, width, height, null);
    }
}
