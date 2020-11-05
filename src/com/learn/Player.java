package com.learn;

class Player {

    private int score = 0;
    private int fours = 0;
    private int sixs = 0;
    private String name;
    private int balls = 0;
    private boolean notOut = false;

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixs() {
        return sixs;
    }

    public void setSixs(int sixs) {
        this.sixs = sixs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public boolean isNotOut() {
        return notOut;
    }

    public void setNotOut(boolean notOut) {
        this.notOut = notOut;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
