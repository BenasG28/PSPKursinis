package com.example.arkanoidsujavafx;

import javax.swing.*;
import java.awt.*;

public class StartArkanoid extends JFrame{
    public StartArkanoid(){
        add(new Board());
        setTitle("Arkanoid Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
            var game = new StartArkanoid();
            game.setVisible(true);
    }
}