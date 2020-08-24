package com.codegym.games.moonlander;

import com.codegym.engine.cell.*;

public class GameObject {
    public double x;
    public double y;
    public int[][] matrix;
    public int width;
    public int height;

    public GameObject(double x, double y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        this.width = matrix[0].length;
        this.height = matrix.length;
    }

    public void draw(Game game) {
        if(this.matrix == null) {
            return;
        }

        for (int offsetX = 0; offsetX < this.width; offsetX ++) {
            for (int offsetY = 0; offsetY < this.height; offsetY++) {
                int colorIndex = matrix[offsetY][offsetX];
                game.setCellColor((int) x + offsetX, (int) y + offsetY, Color.values()[colorIndex]);
            }
        }
    }
}
