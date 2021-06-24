/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametimso;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Mypc
 */
public class GameFrame extends JFrame implements ActionListener, Runnable {

    private int row = 10;
    private int col = 10;
    private int width = 900;
    private int height = 565;
    private ButtonEvent graphicsPanel;
    private JPanel mainPanel;
    int time;
    int MAX_TIME = 60 * 2;
    JLabel lblScore;
    JButton btnNewGame;
    JLabel lblTime;

    public GameFrame() {
        time = MAX_TIME;
        this.add(mainPanel = createMainPanel());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setTitle("Game tìm số từ 1-100");
    }

    public void newGame() {
        time = MAX_TIME;
        isGameOver = false;
        graphicsPanel.setIsGameOver(false);
        time = 120;
        graphicsPanel.removeAll();
        mainPanel.add(createGraphicsPanel(), BorderLayout.CENTER);
        mainPanel.validate();
        mainPanel.setVisible(true);
        lblScore.setText("0");
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createGraphicsPanel(), BorderLayout.CENTER);
        panel.add(createControlPanel(), BorderLayout.EAST);
        return panel;
    }

    private JPanel createGraphicsPanel() {
        graphicsPanel = new ButtonEvent(row, col, this);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.gray);
        panel.add(graphicsPanel);
        return panel;
    }

    public void showDialogNewGame(String message, String title, int t) {
        int select = JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                null, null);
        if (select == 0) {
            newGame();
        } else {
            if (t == 1) {
                System.exit(0);
            }
        }
    }

    private JButton createButton(String buttonName) {
        JButton btn = new JButton(buttonName);
        btn.addActionListener(this);
        return btn;
    }

    private JPanel createControlPanel() {
        //tạo JLable lblScore với giá trị ban đầu là 0
        lblScore = new JLabel("0");
        lblTime = new JLabel("" + time);

        //tạo Panel chứa Score và Time
        JPanel panelLeft = new JPanel(new GridLayout(2, 1, 5, 5));
        panelLeft.add(new JLabel("Score:"));
        panelLeft.add(new JLabel("Time:"));

        JPanel panelCenter = new JPanel(new GridLayout(2, 1, 5, 5));
        panelCenter.add(lblScore);
        panelCenter.add(lblTime);

        JPanel panelScoreAndTime = new JPanel(new BorderLayout(5, 0));
        panelScoreAndTime.add(panelLeft, BorderLayout.WEST);
        panelScoreAndTime.add(panelCenter, BorderLayout.CENTER);

        // tạo Panel mới chứa panelScoreAndTime và nút New Game
        JPanel panelControl = new JPanel(new BorderLayout(10, 10));
        panelControl.setBorder(new EmptyBorder(10, 3, 5, 3));
        panelControl.add(panelScoreAndTime, BorderLayout.CENTER);
        panelControl.add(btnNewGame = createButton("New Game"),
                BorderLayout.PAGE_END);

        // Set BorderLayout để panelControl xuất hiện ở đầu trang
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Good luck"));
        panel.add(panelControl, BorderLayout.PAGE_START);

        return panel;
    }
    boolean isGameOver = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNewGame) {
            showDialogNewGame("Your game hasn't done. Do you want to create a new game?", "Warning", 0);
        }
    }

    @Override
    public void run() {
        if (time == 0) {
            graphicsPanel.gameOver(getGraphics());
        }
        while (time >= 0) {
            if (graphicsPanel.getIsGameOver()) {
                isGameOver = true;
                graphicsPanel.setIsGameOver(true);
            }
            lblTime.setText("" + time);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
