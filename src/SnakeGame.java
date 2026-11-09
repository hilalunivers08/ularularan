import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeGame extends JPanel implements ActionListener {
    static final int CELL = 25;   // 1 kotak = 25 piksel
    static final int COLS = 20;
    static final int ROWS = 20;

    int x = 0;                    // posisi kotak, dalam satuan kotak
    final Timer timer = new Timer(120, this); // detak tiap 120 ms

    public SnakeGame() {
        setPreferredSize(new Dimension(COLS * CELL, ROWS * CELL));
        setBackground(new Color(0xDFE8D0));
        timer.start();
    }

    // Dipanggil Timer tiap detak: UBAH DATA, lalu minta gambar ulang
    @Override
    public void actionPerformed(ActionEvent e) {
        x = (x + 1) % COLS;       // maju 1 kotak; kembali ke 0 setelah 19
        repaint();
    }

    // Dipanggil Swing saat repaint(): GAMBAR dari data
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x2F6B2F));
        g.fillRect(x * CELL, 10 * CELL, CELL, CELL);
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