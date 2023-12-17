package com.example.arkanoidsujavafx;

public class Ball extends GameObject {
    private int xDirection;
    private int yDirection;
    public Ball(){
        xDirection = 1;
        yDirection = -1;
        loadImage("src/main/resources/ball.png");
        resetBall();
    }

    public int getXDirection() {
        return xDirection;
    }

    public void setXDirection(int x) {
        xDirection = x;
    }

    public void setYDirection(int y) {
        yDirection = y;
    }
    public int getYDirection(){
        return yDirection;
    }


    private void resetBall() {
        x = DefaultValues.ballX;
        y = DefaultValues.ballY;
    }
    public void move(){
        x += xDirection;
        y += yDirection;
        if(x == 0){
            setXDirection(1);
        }
        if(x == DefaultValues.mapWidth - imageWidth){
            setXDirection(-1);
        }
        if(y == 0){
            setYDirection(1);
        }
    }

}
