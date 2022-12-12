package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class View {
    private Controller controller;
    private JFrame frame = new JFrame("Snake");
    private List<JLabel> fragments = new ArrayList<>();
    private JLabel score = new JLabel("Score: ");
    private JLabel apple = new JLabel(new ImageIcon("D:\\СИСТЕМА\\Рабочий стол\\Идея\\ООП\\Snake\\Apple.png"));


    public void setScore(int a) {
        score.setText(String.valueOf(a));
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void create() {
        frame.setSize(600, 600);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        score.setBounds(50, 300, 100, 50);
        frame.add(score);

        frame.add(apple);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controller.handleKeyDown(e.getKeyCode());
            }
        });
        frame.repaint();
    }

    public void createFragment(Point point, boolean isApple) {
        String path;
        if (isApple) {
            path = "D:\\СИСТЕМА\\Рабочий стол\\Идея\\ООП\\Snake\\Apple.png";
        } else {
            path = "D:\\СИСТЕМА\\Рабочий стол\\Идея\\ООП\\Snake\\SnakeFragment.png";
        }
        JLabel fragment = new JLabel(new ImageIcon(path));
        fragment.setBounds(point.x, point.y, 10, 10);
        fragments.add(fragment);
        frame.add(fragment);
        frame.repaint();
    }

    public void locateApple(Point point) {
        apple.setBounds(point.x, point.y, 10, 10);
    }

    public void removeTail() {
        frame.remove(fragments.get(0));
        fragments.remove(0);
        frame.repaint();
    }
}