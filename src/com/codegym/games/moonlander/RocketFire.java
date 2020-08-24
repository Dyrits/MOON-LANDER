package com.codegym.games.moonlander;

import com.codegym.engine.cell.*;

import java.util.List;

public class RocketFire extends GameObject {
    private List<int[][]> frames;
    private int frameIndex;
    private boolean isVisible;

    public RocketFire(List<int[][]> frameList) {
        super(0, 0, frameList.get(0));
        this.frames = frameList;
        this.frameIndex = 0;
        hide();
    }

    private void nextFrame() {
        this.frameIndex ++;
        if (this.frameIndex >= this.frames.size()) { this.frameIndex = 0; }
        this.matrix = this.frames.get(this.frameIndex);
    }

    @Override
    public void draw(Game game) {
        if (this.isVisible)  {
            nextFrame();
            super.draw(game);
        }
    }

    public void show() {
        this.isVisible = true;
    }

    public void hide() {
        this.isVisible = false;
    }


}
