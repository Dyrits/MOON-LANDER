package com.codegym.games.moonlander;

import com.codegym.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64, HEIGHT = 64;
    private Rocket rocket;
    private GameObject landscape, platform;
    private boolean isUpPressed, isLeftPressed, isRightPressed, isGameStopped;
    private int score;


    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        showGrid(false);
    }

    private void createGame() {
        this.score = 1000;
        this.isGameStopped = false;
        this.isUpPressed = false; this.isLeftPressed = false; this.isRightPressed = false;
        createGameObjects();
        drawScene();
        setTurnTimer(50);
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.BLACK);
            }
        }
        this.landscape.draw(this);
        this.rocket.draw(this);
    }

    private void createGameObjects() {
        this.rocket  = new Rocket(WIDTH / 2, 0);
        this.landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
        this.platform = new GameObject(23, MoonLanderGame.HEIGHT - 1, ShapeMatrix.PLATFORM);
    }

    @Override
    public void onTurn(int step) {
        if (this.score > 0) { this.score --; }
        this.rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        setScore(this.score);
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        boolean indexOutOfBound = x < 0 || y <0 || x >= WIDTH || y >= HEIGHT;
        if (!indexOutOfBound) {
            super.setCellColor(x, y, color);
        }
    }

    private void check() {
        if (this.rocket.isCollision(landscape) && !(this.rocket.isCollision(platform) && this.rocket.isStopped())) {
            gameOver();
        } else if (this.rocket.isCollision(platform) && this.rocket.isStopped()) {
            win();
        };
    }

    private void win() {
        this.rocket.land();
        this.isGameStopped = true;
        showMessageDialog(Color.DARKGREEN, "You successfully landed!", Color.WHITE, 35);
        stopTurnTimer();
    }

    private void gameOver() {
        this.rocket.crash();
        this.isGameStopped = true;
        this.score = 0;
        setScore(this.score);
        showMessageDialog(Color.FIREBRICK, "You crashed!", Color.WHITE, 35);
        stopTurnTimer();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case RIGHT:
                this.isRightPressed = true;
                break;
            case LEFT:
                this.isLeftPressed = true;
                break;
            case UP:
                this.isUpPressed = true;
                break;
            case SPACE:
                if (isGameStopped) { createGame(); }
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case RIGHT:
                this.isRightPressed = false;
                break;
            case LEFT:
                this.isLeftPressed = false;
                break;
            case UP:
                this.isUpPressed = false;
                break;
        }
    }
}


