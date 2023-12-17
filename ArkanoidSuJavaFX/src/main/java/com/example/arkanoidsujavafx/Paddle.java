package com.example.arkanoidsujavafx;

import java.awt.event.KeyEvent;

public class Paddle extends GameObject {
    private int dx;

    public Paddle() {
        loadImage("src/main/resources/paddle.png");
        resetPaddle();
    }

    public void move(){
        x+=dx;
        if(x <= 0){
            x = 0;
        }
        if(x>= DefaultValues.mapWidth -imageWidth){
            x = DefaultValues.mapWidth-imageWidth;
        }
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            dx = -2;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            dx = 2;
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            dx =0;
        }
    }

    private void resetPaddle() {
        x = DefaultValues.paddleX;
        y = DefaultValues.paddleY;
    }
}
