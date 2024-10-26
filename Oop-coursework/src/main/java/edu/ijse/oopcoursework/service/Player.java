package edu.ijse.oopcoursework.service;

public abstract class Player {
    protected BoardImpl boardImpl;

    //public Player() {}
    public Player(BoardImpl boardImpl) {
        this.boardImpl = boardImpl;
    }

    public abstract void move(int row, int col);
}