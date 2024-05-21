import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    //Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    // game logic
    Bird bird;
    int gravity = 1;
    ArrayList<Pipe> pipes;
    Random random = new Random();
    Timer gameLoop, placePipesTimer;
    double score = 0;
    boolean gameOver = false;

    public FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        //bird
        int birdX = boardWidth/8; //start of the screen
        int birdY = boardHeight/2; //center on the y-axis
        bird = new Bird(birdImg, birdX, birdY);

        //pipes
        pipes = new ArrayList<Pipe>();

        //place pipes timer
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        //game timer
        gameLoop = new Timer(1000/60, this); // 60 times per second
        gameLoop.start();
    }

    public void placePipes(){
        Pipe topPipe = new Pipe(topPipeImg, this.boardWidth, 0, false);
        pipes.add(topPipe);

        int topPipeY = topPipe.getYCoordinate();
        Pipe bottomPipe = new Pipe(bottomPipeImg, this.boardWidth, topPipeY, true);
        pipes.add(bottomPipe);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //background
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);

        //bird
        g.drawImage(bird.getImg(), bird.getXCoordinate(), bird.getYCoordinate(), bird.getBirdWidth(), bird.getBirdHeight(), null);

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImg(), pipe.getXCoordinate(), pipe.getYCoordinate(), pipe.getPipeWidth(), pipe.getPipeHeight(), null);
        }

        //score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        }
        else{
            g.drawString( String.valueOf((int) score), 10, 35);
        }
    }

    public void move(){
        //bird
        int birdVelocityY = bird.getVelocityY() + gravity;
        bird.setVelocityY(birdVelocityY);
        int birdY = Math.max(0, bird.getYCoordinate() + birdVelocityY);
        bird.setYCoordinate(birdY);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.updateVelocity();

            if (!pipe.isPassed() && bird.getXCoordinate() > pipe.getXCoordinate() + pipe.getPipeWidth()){
                pipe.togglePassed();
                score += 0.5;
            }

            if (this.collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.getYCoordinate() > boardHeight){
            gameOver = true;
        }
    }

    public boolean collision(Bird a, Pipe b){
        return a.getXCoordinate() < b.getXCoordinate() + b.getPipeWidth() &&
                a.getXCoordinate() + a.getBirdWidth() > b.getXCoordinate() &&
                a.getYCoordinate() < b.getYCoordinate() + b.getPipeHeight() &&
                a.getYCoordinate() + a.getBirdHeight() > b.getYCoordinate();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.move();
        repaint();
        if (gameOver) {
            placePipesTimer.stop();
            gameLoop.stop();
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.setVelocityY(-10);
            if (gameOver){
                bird.setYCoordinate(boardHeight/2);
                bird.setVelocityY(0);
                pipes.clear();
                gameOver = false;
                placePipesTimer.start();
                gameLoop.start();
                score = 0;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
