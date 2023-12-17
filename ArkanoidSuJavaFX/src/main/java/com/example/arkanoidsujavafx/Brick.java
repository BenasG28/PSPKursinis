package com.example.arkanoidsujavafx;

import lombok.Getter;

@Getter
public class Brick extends GameObject {
    private boolean destroyed;
    public Brick(int x, int y){
        this.x = x;
        this.y = y;
        destroyed = false;
        loadImage("src/main/resources/brick.png");
    }
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

}
