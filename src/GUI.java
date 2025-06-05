import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private int[] array;
    private int highlightedIndex = -1;
    private JButton startButton;
    private boolean sortingStarted = false;

    public GUI(int[] array) {
        this.array = array;
        setTitle("Heap Sort Visualizer");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.LIGHT_GRAY); // Set grey background

        startButton = new JButton("Start Heap Sort");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sortingStarted) {
                    sortingStarted = true;
                    startButton.setEnabled(false);
                    new Thread(() -> {
                        try {
                            App.heapSort(array, GUI.this);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }).start();
                }
            }
        });
        add(startButton, BorderLayout.NORTH); // Place button at the top
        setAlwaysOnTop(true); // Keep window always on top
    }

    public void setArray(int[] array, int highlightedIndex) {
        this.array = array.clone();
        this.highlightedIndex = highlightedIndex;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (array == null) return;
        int width = getWidth();
        int height = getHeight() - 50;
        int barWidth = Math.max(1, width / array.length);
        int max = 1;
        for (int value : array) max = Math.max(max, value);
        for (int i = 0; i < array.length; i++) {
            int barHeight = (int)(((double)array[i] / max) * (height - 50));
            if (i == highlightedIndex) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillRect(i * barWidth, height - barHeight + 50, barWidth, barHeight);
        }
    }
}
