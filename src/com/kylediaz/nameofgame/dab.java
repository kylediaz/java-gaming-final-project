
import java.awt.*;//needed for graphics
import javax.swing.*;//needed for JFrame window
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class dab extends
        JFrame
        implements KeyListener, ActionListener {//opens program

    public static Color Tan = new Color(249, 211, 165);
    public static Color RedBrown = new Color(152, 80, 60);
    public static Color SkyBlue = new Color(27, 161, 226);
    public static Color Beige = new Color(238, 197, 169);
    public static Color Brown = new Color(97, 51, 24);
    public static Color Orange = new Color(255, 195, 77);
    public static Color cloudBlue = new Color(110, 255, 155);
    public static Color bushGreen = new Color(185, 255, 77);
    public static Color darkGreen = new Color(18, 173, 42);
    public static Color skinTone = new Color(234, 192, 134);
    public static Color lightRed = new Color(250, 160, 160);
    public static Color navyBlue = new Color(10, 40, 89);
    public static Color aqua = new Color(00, 255, 255);
    public static Color Pink = new Color(255, 192, 203);
    public static Color hotPink = new Color(255, 69, 180);
    public int xperson = 900;
    public int yperson = 500;
    public int lives = 10;
    public int prevLives = 10;
    public int xspikeT = 0;
    public int yspikeT = 10;
    public int xspikeB = 0;
    public int yspikeB = 980;

    public static Timer t;
    boolean right = false;
    boolean left = false;
    boolean reset = false;
    
    boolean firstTimeTheGameRan = true;
    public boolean gameIsRunning = false;

    public Random rand = new Random();

    public int xR = 1900;
    public int yR = (int) (1 + Math.random() * (900 - 1 + 1));
    public int xL = 0;
    public int yL = (int) (1 + Math.random() * (900 - 1 + 1));

    public dab() {//constructor for JPanel
        t = new Timer(100, this);
        t.start();
        add(new JP());

    }//close Jpanel Constructor

    public static void main(String[] args) {//start main method

        dab w = new dab();
        w.setTitle("Final Game");
        //w.setSize(500,500);
        w.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setVisible(true);
        w.addKeyListener(w);
        w.setBackground(Color.blue);
    }//end main

    long theLastTimeWhenGaryGotOofed = System.currentTimeMillis();
    public class JP extends JPanel {//start JPanel CLass

        public JP() {
            Container c = getContentPane();
            setOpaque(false);//allows for setting a color background in JPanel
            c.setBackground(SkyBlue);
        }

        public void paint(Graphics g) {//opens paint method
            if (firstTimeTheGameRan == true) {
                g.setColor(Color.white);
                g.fillRect(1, 1, 4000000, 4000000);
                g.setColor(Color.black);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
                g.drawString("Press Y to begin", 275, 500);
            }
            if (gameIsRunning == false && firstTimeTheGameRan == false) {
                g.setColor(Color.black);
                g.fillRect(1, 1, 40000, 4000);
                g.setColor(Color.white);

                g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
                g.drawString("Play again?,Y for yes, N for no ", 500, 500);

            }
            if (gameIsRunning) {
                super.paint(g);//allows for painting and
                if (facingLeft) {
                    garyButHeIsFacingLeft(g, xperson, yperson);
                } else if (facingRight) {
                    garyButHeIsFacingRight(g, xperson, yperson);
                }

                g.setColor(Color.black);
                g.fillRect(0, 0, 10, 1000);
                g.fillRect(1895, 0, 10, 1000);
                g.fillRect(0, 0, 2000, 10);
                g.fillRect(0, 1000, 2000, 10);

                rightblock(g, xR, yR);
                xR-= 1200 * Time.deltaTimeMilli() / 1000;

                if (xR <= 0) {
                    xR = 1900;

                    yR = (int) (1 + Math.random() * (900 - 1 + 1));

                }
                leftblock(g, xL, yL);
                xL += 1200 * Time.deltaTimeMilli() / 1000;

                if (xL >= 1900) {
                    xL = 0;

                    yL = (int) (1 + Math.random() * (900 - 1 + 1));

                }
                if (xperson <= 0 | xperson >= 1950) {
                    lives = 0;
                }
                if (yperson <= 0 | xperson >= 1000) {
                    lives = 0;
                }

                if (yperson <= yR + 50 && yperson + 100 >= yR && xperson + 50 >= xR && xR + 25 >= xperson - 75 && System.currentTimeMillis() - theLastTimeWhenGaryGotOofed > 1000) {
                    theLastTimeWhenGaryGotOofed = System.currentTimeMillis();
                    if (lives == prevLives) {
                        lives--;
                    } else {
                        lives -= 0;
                    }
                } else {
                    prevLives = lives;
                }

                if (yperson <= yL + 50 && yperson + 100 >= yL && xperson + 50 >= xL && xL + 25 >= xperson - 75 && System.currentTimeMillis() - theLastTimeWhenGaryGotOofed > 1000) {
                    theLastTimeWhenGaryGotOofed = System.currentTimeMillis();
                    if (lives == prevLives) {
                        lives--;

                    } else {
                        lives -= 0;
                    }
                } else {
                    prevLives = lives;
                }
                if (lives <= 0)
                {
                    System.out.println("dameover");
                    gameIsRunning = false;
                }

                g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
                g.setColor(Color.BLACK);
                g.drawString("Lives:" + lives + " Lives", 750, 120);
                g.setColor(Color.black);
            }
            
            Time.refresh();
            repaint();
        }//close paint method

    }//close JPanel Class

    public void actionPerformed(ActionEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
    boolean facingLeft = true;
    boolean facingRight = false;
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_D) {
            xperson += 20;
            facingLeft = false;
            facingRight = true;
        }
        if (e.getKeyCode() == e.VK_A) {
            xperson -= 20;
            facingLeft = true;
            facingRight = false;
        }
        if (e.getKeyCode() == e.VK_W) {
            yperson -= 20;

        }
        if (e.getKeyCode() == e.VK_S) {
            yperson += 20;

        }
        if (e.getKeyCode() == e.VK_Y) {
            firstTimeTheGameRan = false;
            gameIsRunning = true;
            if (lives != 10) {
                lives = 10;
            }
            xperson = 900;
            yperson = 500;
        }
        if (e.getKeyCode() == e.VK_N) {
            System.exit(0);
        }

    }

    public static void garyButHeIsFacingLeft(Graphics g, int x, int y) {
        //left eye
        g.setColor(Color.black);
        g.fillRect(x, y, 5, 5);
        g.fillRect(x + 5, y, 5, 5);
        g.fillRect(x + 10, y, 5, 5);
        g.fillRect(x - 5, y + 5, 5, 5);
        g.fillRect(x - 5, y + 10, 5, 5);
        g.fillRect(x - 5, y + 15, 5, 5);
        g.fillRect(x, y + 20, 5, 5);
        g.fillRect(x + 5, y + 20, 5, 5);
        g.fillRect(x + 10, y + 20, 5, 5);
        g.fillRect(x + 15, y + 5, 5, 5);
        g.fillRect(x + 15, y + 10, 5, 5);
        g.fillRect(x + 15, y + 15, 5, 5);
        g.setColor(Color.white);
        g.fillRect(x, y + 5, 15, 15);
        g.setColor(aqua);
        g.fillRect(x, y + 25, 30, 20);
        g.setColor(Color.green);
        g.fillRect(x - 5, y + 45, 70, 5);
        g.setColor(Color.black);
        g.fillRect(x - 5, y + 40, 5, 5);
        g.fillRect(x - 10, y + 45, 5, 5);
        g.fillRect(x - 5, y + 50, 70, 5);
        g.fillRect(x - 5, y + 50, 70, 5);
        g.fillRect(x + 65, y + 45, 5, 5);
        g.fillRect(x + 20, y + 10, 5, 5);
        g.fillRect(x, y + 25, 5, 5);
        g.fillRect(x + 5, y + 25, 5, 5);
        g.fillRect(x + 10, y + 25, 5, 5);
        g.fillRect(x, y + 30, 5, 5);
        g.fillRect(x + 15, y + 30, 5, 5);
        g.fillRect(x + 15, y + 25, 5, 5);
        g.fillRect(x, y + 35, 5, 5);
        g.fillRect(x, y + 40, 5, 5);
        g.fillRect(x + 10, y + 30, 5, 5);
        g.fillRect(x + 10, y + 35, 5, 5);
        g.fillRect(x + 10, y + 40, 5, 5);
        g.fillRect(x + 15, y + 20, 5, 5);
        g.fillRect(x + 25, y + 30, 5, 5);
        g.fillRect(x + 25, y + 35, 5, 5);
        g.fillRect(x + 20, y + 40, 5, 5);
        g.fillRect(x + 25, y + 40, 5, 5);
        g.fillRect(x + 30, y + 40, 5, 5);
        g.fillRect(x + 30, y + 25, 5, 5);
        g.fillRect(x + 30, y + 30, 5, 5);
        g.fillRect(x + 30, y + 35, 5, 5);
        g.fillRect(x + 40, y, 20, 5);
        g.fillRect(x + 60, y + 5, 5, 5);
        g.fillRect(x + 65, y + 5, 5, 5);
        g.fillRect(x + 70, y + 10, 5, 30);
        g.fillRect(x + 35, y + 40, 35, 5);
        g.setColor(Color.black);
        g.fillRect(x + 20, y, 5, 5);
        g.fillRect(x + 25, y, 5, 5);
        g.fillRect(x + 30, y, 5, 5);
        g.fillRect(x + 15, y + 5, 5, 5);
        g.fillRect(x + 15, y + 10, 5, 5);
        g.fillRect(x + 15, y + 15, 5, 5);
        g.fillRect(x + 20, y + 20, 5, 5);
        g.fillRect(x + 25, y + 20, 5, 5);
        g.fillRect(x + 30, y + 20, 5, 5);
        g.fillRect(x + 35, y + 5, 5, 5);
        g.fillRect(x + 35, y + 10, 5, 5);
        g.fillRect(x + 35, y + 15, 5, 5);

        g.setColor(Color.white);
        g.fillRect(x, y + 5, 15, 15);
        g.setColor(Color.red);
        g.fillRect(x, y + 5, 5, 5);
        g.fillRect(x + 5, y + 10, 5, 5);
        g.fillRect(x + 5, y + 5, 5, 5);

        g.fillRect(x + 20, y + 5, 5, 5);
        g.fillRect(x + 25, y + 10, 5, 5);
        g.fillRect(x + 25, y + 5, 5, 5);
        g.setColor(Color.black);
        g.fillRect(x, y + 10, 5, 5);
        g.setColor(Pink);
        g.fillRect(x + 35, y + 20, 35, 20);
        g.fillRect(x + 40, y + 5, 20, 20);
        g.fillRect(x + 60, y + 10, 10, 10);
        g.setColor(hotPink);
        g.fillRect(x + 45, y + 20, 5, 20);
        g.fillRect(x + 50, y + 15, 10, 5);
        g.fillRect(x + 60, y + 20, 5, 5);
        g.fillRect(x + 60, y + 20, 5, 10);
        g.fillRect(x + 55, y + 30, 5, 5);

    }//end dylans character
    public static void garyButHeIsFacingRight(Graphics g, int x, int y) {
        /*
         * Thanks 💦💦💦💦💦 Kyle 💦💦💦💦💦 for this really easy way to reflect a drawing >:3333 
        */
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(-1, 1);
        g2d.translate(-x - 50, y);
        garyButHeIsFacingLeft(g2d, 0, 0);
    }

    public void rightblock(Graphics g, int xR, int yR) {
        g.setColor(Color.yellow);
        g.fillRect(xR, yR, 50, 50);
    }

    public void leftblock(Graphics g, int xL, int yL) {
        g.setColor(Color.yellow);
        g.fillRect(xL, yL, 50, 50);
    }

}//close program

/**
 * Thanks again 💦💦💦💦💦 Kyle 💦💦💦💦💦 for giving me your very clean code of
 * something that gives you delta time, so the speed of the stuff can be a function
 * of time so they move at a constant rate, just like how my life is constantly
 * getting worse
 * @author kyled
 */
final class Time {

    private Time() {

    }

    public final static void refresh() {
        deltaTime = (System.currentTimeMillis() - lastUpdate);
        lastUpdate = System.currentTimeMillis();
        surpassedTime = (int) (System.currentTimeMillis() - startTime);
    }
    private static long startTime = System.currentTimeMillis();
    private static int surpassedTime = 0;

    public final static int surpassedTimeMilli() {
        return surpassedTime;
    }
    private static long lastUpdate = System.currentTimeMillis();
    private static double deltaTime = 0;

    /**
     * @return time since last refresh in milliseconds
     */
    public final static double deltaTimeMilli() {
        return deltaTime;
    }

    public final static void reset() {
        startTime = System.currentTimeMillis();
    }
}
