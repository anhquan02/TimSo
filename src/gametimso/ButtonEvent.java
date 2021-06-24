/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametimso;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Mypc
 */
public class ButtonEvent extends JPanel implements ActionListener {

    Point p;
    private int col, row;
    private int bound = 2;
    private int size = 50;
    private JButton[][] btn;
    private Controller controller;
    private Color background = Color.LIGHT_GRAY;
    int score = 0;
    int vitri = 1;
    GameFrame frame;

    public ButtonEvent(int col, int row, GameFrame frame) {
        this.col = col;
        this.row = row;
        this.frame = frame;

        this.setLayout(new GridLayout(row, col));
        this.setBackground(background);
        this.setPreferredSize(new Dimension((size + bound) * col, (size + bound) * row));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        newGame();
    }

    public void newGame() {
        isGameOver = false;
        controller = new Controller(row, col);
        createArrayButton();

    }

    public void createArrayButton() {
        btn = new JButton[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                btn[i][j] = createButton(i + "," + j);
                btn[i][j].setText(controller.getMatrix()[i][j] + "");
                this.add(btn[i][j]);
            }
        }
    }

    public JButton createButton(String action) {
        JButton btn = new JButton();
        btn.setActionCommand(action);
        btn.setBorder(null);
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnIndex = e.getActionCommand();
        int indexDot = btnIndex.lastIndexOf(",");
        int x = Integer.parseInt(btnIndex.substring(0, indexDot));
        int y = Integer.parseInt(btnIndex.substring(indexDot + 1,
                btnIndex.length()));
        p = new Point(x, y);
        if (btn[p.x][p.y].getText().equals(vitri + "")) {
            btn[p.x][p.y].setBackground(Color.red);
            p = null;
            score = (int) score + (vitri * frame.time);
            vitri++;
            frame.lblScore.setText("" + score);
        } else {
            gameOver(this.getGraphics());
            isGameOver = true;
        }

    }
    public boolean isGameOver = false;

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean x) {
        this.isGameOver = x;
    }

    public void gameOver(Graphics g) {
        g.fillRect(0, 0, (size + bound) * col, (size + bound) * row);
        this.removeAll();
        g.setColor(Color.white);
        g.drawString("Game Over\n", 250, 200);
        g.drawString("Score: " + score , 250, 240);

    }

}
