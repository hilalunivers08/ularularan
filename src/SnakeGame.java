import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SnakeGame extends JPanel implements ActionListener {
    static final int CELL = 25;
    static final int COLS = 20;
    static final int ROWS = 20;

    ArrayList<Point> snake = new ArrayList<>(); // indeks 0 = kepala
    int dx = 1, dy = 0;                         // arah: sekarang ke kanan

    final Timer timer = new Timer(120, this);

    public SnakeGame() {
        setPreferredSize(new Dimension(COLS * CELL, ROWS * CELL));
        setBackground(new Color(0xDFE8D0));

        snake.add(new Point(10, 10)); // kepala
        snake.add(new Point(9, 10));
        snake.add(new Point(8, 10));  // ekor

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    void move() {
        Point head = snake.get(0);

        // Sementara: tembus dinding. "+ COLS" mencegah hasil negatif saat x = 0 dan dx = -1.
        int nx = (head.x + dx + COLS) % COLS;
        int ny = (head.y + dy + ROWS) % ROWS;

        snake.add(0, new Point(nx, ny));   // 1) tambah kepala baru di depan
        snake.remove(snake.size() - 1);    // 2) buang ekor
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < snake.size(); i++) {
            Point p = snake.get(i);
            // Kepala lebih gelap dari badan
            g.setColor(i == 0 ? new Color(0x1F4D1F) : new Color(0x2F6B2F));
            g.fillRect(p.x * CELL + 1, p.y * CELL + 1, CELL - 2, CELL - 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Snake Apple");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(new SnakeGame());
            f.pack();
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}