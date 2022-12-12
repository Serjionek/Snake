package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Controller {
    private View view;
    private List<Point> coordinates = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private Point apple;
    private int score = 0;

    public void setView(View view) {
        this.view = view;
    }

    public void startGame() {
        coordinates.add(new Point(50, 50));
        view.createFragment(coordinates.get(coordinates.size() - 1), false);
        moveApple();
        while (true) {
            if (isColliding()) {
                break;
            }
            step();
            sleep();
        }
    }

    private boolean checkApple() {
        Point point = coordinates.get(coordinates.size() - 1);
        if (apple.x == point.x && apple.y == point.y) {
            return true;
        }
        return false;
    }

    private boolean isColliding() {
        Point point = coordinates.get(coordinates.size() - 1);
        for (int i = 0; i < coordinates.size() - 1; i++) {
            if (coordinates.get(i).x == point.x && coordinates.get(i).y == point.y) {
                return true;
            }
        }
        return false;
    }

    private void moveApple() {
        int x = 0;
        int y = 0;
        boolean isCorrect = false;
        while (!isCorrect) {
            isCorrect = true;
            x = (int) (Math.random() * 11) * 10;
            y = (int) (Math.random() * 11) * 10;
            for (int i = 0; i < coordinates.size(); i++) {
                if (coordinates.get(i).x == x && coordinates.get(i).y == y) {
                    isCorrect = false;
                }
            }
        }
        apple = new Point(x, y);
        view.locateApple(apple);
    }

    public void handleKeyDown(int key) {
        if (key == 39) {
            direction = Direction.RIGHT;
        }
        if (key == 37) {
            direction = Direction.LEFT;
        }
        if (key == 38) {
            direction = Direction.UP;
        }
        if (key == 40) {
            direction = Direction.DOWN;
        }
    }

    public void step() {
        Point point = coordinates.get(coordinates.size() - 1);
        if (direction == Direction.RIGHT) {
            if (point.x + 10 > 180) {
                coordinates.add(new Point(0, point.y));
            } else {
                coordinates.add(new Point(point.x + 10, point.y));
            }
        }
        if (direction == Direction.LEFT) {
            if (point.x - 10 < 0) {
                coordinates.add(new Point(180, point.y));
            } else {
                coordinates.add(new Point(point.x - 10, point.y));
            }
        }
        if (direction == Direction.UP) {
            if (point.y - 10 < 0) {
                coordinates.add(new Point(point.x, 180));
            } else {
                coordinates.add(new Point(point.x, point.y - 10));
            }
        }
        if (direction == Direction.DOWN) {
            if (point.y + 10 > 180) {
                coordinates.add(new Point(point.x, 0));
            } else {
                coordinates.add(new Point(point.x, point.y + 10));
            }
        }
        view.createFragment(coordinates.get(coordinates.size() - 1), false);
        if (checkApple()) {
            moveApple();
            score = score + 1;
            view.setScore(score);
        } else {
            coordinates.remove(0);
            view.removeTail();
        }
    }

    private void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}