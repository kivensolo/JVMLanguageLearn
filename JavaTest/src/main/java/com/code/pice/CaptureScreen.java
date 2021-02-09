package com.code.pice;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * author: King.Z
 * date:  2016/2/25 10:40
 * description:截屏代码
 */
public class CaptureScreen {
    public static void main(String[] args) throws Exception {
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       Rectangle screenRectangle = new Rectangle(screenSize);
       Robot robot = new Robot();
       BufferedImage image = robot.createScreenCapture(screenRectangle);
       ImageIO.write(image, "png", new File("D://1111.png"));
    }
}
