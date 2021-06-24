/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametimso;

/**
 *
 * @author Mypc
 */
public class GameTimSo {

    GameFrame frame;

    public GameTimSo() {
        frame = new GameFrame();
        Time time = new Time();
        time.start();
        Thread thread = new Thread(frame);
        thread.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new GameTimSo();
//        Controller controller = new Controller(4, 4);
    }

    class Time extends Thread {

        public void run() {

            while (frame.time > 0) {
                if (!frame.isGameOver) {
                    frame.time--;
                }
                try {
                    Thread.sleep(1050);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
