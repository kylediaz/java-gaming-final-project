package com.kylediaz.nameofgame.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.geometry.Point2D;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 *
 * @author kyled
 */
public final class ConvertImageToCodeToImageInator {

    public final static BufferedImage convert(BufferedImage image, File tempText, File tempJava) throws IOException {
        System.setProperty("java.home", "C:\\Program Files\\Java\\jdk1.7.0_21");
        BufferedReader br=new BufferedReader(new FileReader(tempText));
        PrintWriter writer=new PrintWriter(tempJava);
        writer.write("import java.awt.image.BufferedImage;\n");
        writer.write("public class Temp {\n");
        writer.write("public static BufferedImage excute(Graphics g){\n");
        String reader="";
        while((reader=(br.readLine()))!=null){
            writer.write(reader+"\n");
        }
        writer.write("}\n}");

        br.close();
        writer.close();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int b=compiler.run(null, null, null,tempJava.getAbsolutePath());
        Graphics g = image.getGraphics();
        return image;
    }

    private final static String graphicsVar = "g";
    private final static String xVar = "0";
    private final static String yVar = "0";

    private static String generateCode(BufferedImage image) {
        System.out.println("generating");
        String code = "";
        int currentColor;
        HashMap<Integer, ArrayList<Point2D>> map = new HashMap<>();
        ArrayList<Point2D> temp;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                currentColor = image.getRGB(x, y);
                if (!map.containsKey(currentColor)) {
                    temp = new ArrayList<>();
                    temp.add(new Point2D(x, y));
                    map.put(currentColor, temp);
                } else {
                    temp = map.get(currentColor);
                    temp.add(new Point2D(x, y));
                    map.put(currentColor, temp);
                }
            }
        }
        Set<Integer> colors = map.keySet();

        for (int color : colors) {
            if (getAlpha(color) == 0) {
                continue;
            }
            code += generateSwingColorCode(color) + "\n";
            for (Point2D point : map.get(color)) {
                code += (point) + "\n";
            }
        }
        return code;
    }

    private static String generateSwingColorCode(int color) {
        return graphicsVar + ".setColor(new Color(" + getRed(color) + ", "
                + getGreen(color) + ", "
                + getBlue(color) + ", "
                + getAlpha(color) + "));";
    }

    private static int getAlpha(int pixel) {
        return (pixel >> 24) & 0xff;
    }

    private static int getRed(int pixel) {
        return (pixel >> 16) & 0xff;
    }

    private static int getGreen(int pixel) {
        return (pixel >> 8) & 0xff;
    }

    private static int getBlue(int pixel) {
        return (pixel) & 0xff;
    }
}
