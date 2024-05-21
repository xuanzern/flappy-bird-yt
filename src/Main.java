import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy Bird");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight);

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack(); // doesn't take into account the bar on top
        flappyBird.requestFocus();
        frame.setVisible(true);
    }
}