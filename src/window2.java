import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class window2 extends JPanel {
    public static JFrame selectframe = new JFrame("menu");
    public static int[] TEXTUREset = new int[miasta.textures.size()-17];

    public static void createAndShowGui2() throws IOException {
        int j = 0;
        for (int i : miasta.textures.keySet()) {
            if(i<1000) {
                TEXTUREset[j] = i;
                j++;
            }
        }
        window2 mainPanel = new window2();
        selectframe.setPreferredSize(new Dimension(100, 400));
        selectframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        selectframe.pack();
        selectframe.setLocationByPlatform(true);
        selectframe.setVisible(true);
    }

    private JLabel[] labelGrid = new JLabel[TEXTUREset.length];

    public window2() throws IOException {
        selectframe.setLayout(new GridLayout(1, TEXTUREset.length));
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                try {
                    selectframe.add(new JScrollPane(new TestPane()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                selectframe.setLocationRelativeTo(null);
                selectframe.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() throws IOException {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            int x = 0;
            for (int index = 0; index < labelGrid.length; index++) {
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("textures\\" + miasta.textures.get(TEXTUREset[index]))));
                    JLabel label = new JLabel(String.valueOf(TEXTUREset[index]), icon, JLabel.CENTER);
                    add(label, gbc);
                    JLabel nulllabel = new JLabel(" ");
                    add(nulllabel, gbc);
                } catch (NullPointerException e) {
                    labelGrid[index].setIcon(new ImageIcon(miasta.error_texture()));
                    System.out.println(e + " (unknown id)");
                } catch (IIOException f) {
                    labelGrid[index].setIcon(new ImageIcon(miasta.error_texture()));
                    System.out.println(f);
                }

            }
        }

    }
}
