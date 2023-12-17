package com.example.arkanoidsujavafx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Board extends JPanel implements KeyListener {
    private Timer timer;
    private String endMessage = "Game Over!";
    private String restartMessage = "Press Enter To Restart";
    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private boolean isPlaying = true;

    public Board() {
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(DefaultValues.mapWidth, DefaultValues.mapHeight));
        initializeGame();
    }

    private void initializeGame() {
        bricks = new Brick[DefaultValues.numberOfBricks];
        ball = new Ball();
        paddle = new Paddle();
        int rows = 5;
        int columns = DefaultValues.numberOfBricks / rows;
        int brickIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                bricks[brickIndex] = new Brick(j * (DefaultValues.brickWidth + DefaultValues.gapBetweenBricks) + 32, i * (DefaultValues.brickHeight + DefaultValues.gapBetweenBricks) + 50);
                brickIndex++;
            }
        }
        timer = new Timer(DefaultValues.gameSpeed, new GameCycle());
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        if (isPlaying) {
            drawObjects(graphics);
        } else {
            gameFinished((Graphics2D) graphics);
        }
    }

    private void drawObjects(Graphics graphics) {
        graphics.drawImage(ball.getImage(), ball.getX(), ball.getY(), ball.getImageWidth(), ball.getImageHeight(), this);
        graphics.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(), paddle.getImageWidth(), paddle.getImageHeight(), this);
        for (int i = 0; i < DefaultValues.numberOfBricks; i++) {
            if (!bricks[i].isDestroyed()) {
                graphics.drawImage(bricks[i].getImage(), bricks[i].getX(), bricks[i].getY(), DefaultValues.brickWidth, DefaultValues.brickHeight, this);
            }
        }
    }

    private void gameFinished(Graphics2D graphics2D) {
        FontMetrics fontMetrics = this.getFontMetrics(new Font("Verdana", Font.BOLD, 20));
        graphics2D.setColor(Color.black);
        graphics2D.setFont(new Font("Verdana", Font.BOLD, 20));
        graphics2D.drawString(endMessage, (DefaultValues.mapWidth - fontMetrics.stringWidth(endMessage)) / 2, DefaultValues.mapHeight / 2);
        graphics2D.drawString(restartMessage, (DefaultValues.mapWidth - fontMetrics.stringWidth(restartMessage)) / 2, (DefaultValues.mapHeight / 2) + 30);
    }

    public void restartGame() {
        isPlaying = true;
        endMessage = "Game Over!";
        if (timer != null && timer.isRunning()) timer.stop();
        initializeGame();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        paddle.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            togglePause();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            restartGame();
        }
        else {
            paddle.keyPressed(e);
        }
    }

    private void togglePause() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    private class GameCycle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ball.move();
            paddle.move();
            checkCollision();
            repaint();
        }
    }

    private void checkCollision() {
        checkMapBottomCollision();
        checkBricksDestroyed();
        checkPaddleCollision();
        checkBrickCollision();
    }

    private void checkBrickCollision() {

        for (int i = 0; i < DefaultValues.numberOfBricks; i++) {
            if ((ball.getRect().intersects(bricks[i].getRect()))) {
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();
                var rightBallPoint = new Point(ballLeft + ballWidth + 1, ballTop);
                var leftBallPoint = new Point(ballLeft - 1, ballTop);
                var topBallPoint = new Point(ballLeft, ballTop - 1);
                var bottomBallPoint = new Point(ballLeft, ballTop + ballHeight + 1);
                if (!bricks[i].isDestroyed()) {
                    if (bricks[i].getRect().contains(rightBallPoint)) {
                        ball.setXDirection(-1);
                    } else if (bricks[i].getRect().contains(leftBallPoint)) {
                        ball.setXDirection(1);
                    }
                    if (bricks[i].getRect().contains(topBallPoint)) {
                        ball.setYDirection(1);
                    } else if (bricks[i].getRect().contains(bottomBallPoint)) {
                        ball.setYDirection(-1 * ball.getYDirection());
                    }
                    bricks[i].setDestroyed(true);
                }
            }
        }
    }


    private void checkPaddleCollision() {
        Random random = new Random();
        if ((ball.getRect()).intersects(paddle.getRect())) {
            if (random.nextBoolean()) {
                ball.setXDirection(-1 * ball.getXDirection());
            }
            ball.setYDirection(-1 * ball.getYDirection());
        }
    }

    private void checkBricksDestroyed() {
        int bricksDestroyed = 0;
        for (int i = 0; i < DefaultValues.numberOfBricks; i++) {
            if (bricks[i].isDestroyed()) {
                bricksDestroyed++;
            }
            if (bricksDestroyed == DefaultValues.numberOfBricks) {
                endMessage = "You Won!";
                stopGame();
            }
        }
    }

    private void checkMapBottomCollision() {
        if (ball.getRect().getMaxY() > DefaultValues.mapHeight) {
            stopGame();
        }
    }

    public void stopGame() {
        isPlaying = false;
        timer.stop();
    }
}
