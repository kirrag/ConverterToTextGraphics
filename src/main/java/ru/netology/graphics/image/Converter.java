package ru.netology.graphics.image;

import java.awt.*;
import java.io.IOException;
//import java.net.MalformedURLException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
//import java.io.File;
import java.awt.image.WritableRaster;

public class Converter implements TextGraphicsConverter {
    private int maxWidth;
    private int maxHeight;
    private double maxRatio;
    private TextColorSchema schema;

    public Converter() {
        maxWidth = 0;
        maxHeight = 0;
        maxRatio = 0;
        schema = new Schema();
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));

        if (maxRatio > 0) {
            double ratio = Math.max(img.getWidth() / img.getHeight(), img.getHeight() / img.getHeight());
            if (ratio > maxRatio) {
                throw new BadImageSizeException(ratio, maxRatio);
            }
        }

        double scalingFactor = 1;

        if (maxWidth > 0 && maxHeight > 0) {
            if (img.getWidth() > maxWidth || img.getHeight() > maxHeight) {
                scalingFactor = Math.max(img.getWidth() / maxWidth, img.getHeight() / maxHeight);
            }
        }

        if (maxWidth > 0 && maxHeight == 0) {
            if (img.getWidth() > maxWidth) {
                scalingFactor = img.getWidth() / maxWidth;
            }
        }

        if (maxWidth == 0 && maxHeight > 0) {
            if (img.getHeight() > maxHeight) {
                scalingFactor = img.getHeight() / maxHeight;
            }
        }

        int newWidth = (int) ((int) img.getWidth() / scalingFactor);
        int newHeight = (int) ((int) img.getHeight() / scalingFactor);

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);

        WritableRaster bwRaster = bwImg.getRaster();

        String charImage = "";
        for (int h = 0; h < bwImg.getHeight(); h++) {
            for (int w = 0; w < bwImg.getWidth(); w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                charImage = charImage + c + c;
            }
            charImage = charImage + "\n";
        }

        return charImage; 
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
