package com.example.arkanoidsujavafx;

import javax.swing.*;
import java.awt.*;
public class GameObject {
    int x;
    int y;
    int imageWidth;
    int imageHeight;
    Image image;

    public Rectangle getRect(){
        return new Rectangle(x,y,image.getWidth(null), image.getHeight(null));
    }
    public void getImageDimensions(){
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }
    public void loadImage(String photoPath){
        var imageIcon = new ImageIcon(photoPath);
        image = imageIcon.getImage();
        getImageDimensions();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
