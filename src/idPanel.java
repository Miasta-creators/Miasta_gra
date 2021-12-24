import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class idPanel extends JPanel {
    public static JPanel idPanel = new JPanel();
    public static int[] TEXTUREset = new int[miasta.textures.size()-17];

    public static void renderId() throws IOException {
        int j = 0;
        for (int i : miasta.textures.keySet()) {
            if(i<1000) {
                TEXTUREset[j] = i;
                j++;
            }
        }
        idPanel id_list_Panel = new idPanel();
        idPanel.setPreferredSize(new Dimension(100, 400));
        idPanel.setVisible(true);
    }

    private JLabel[] labelGrid = new JLabel[TEXTUREset.length];
    private static JPanel AllIdPanel = new JPanel();

    public idPanel() throws IOException {
        idPanel.setLayout(new GridLayout(1, TEXTUREset.length));
        try {
            TestPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
        idPanel.add(new JScrollPane(AllIdPanel));
        idPanel.setVisible(true);
    }
    private void TestPane() throws IOException {
        try {
            AllIdPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            int x = 0;
            for (int index = 0; index < labelGrid.length; index++) {
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("textures\\" + miasta.textures.get(TEXTUREset[index]))));
                    JLabel label = new JLabel(String.valueOf(TEXTUREset[index]), icon, JLabel.CENTER);
                    AllIdPanel.add(label, gbc);
                    JLabel nulllabel = new JLabel(" ");
                    AllIdPanel.add(nulllabel, gbc);
                } catch (NullPointerException e) {
                    labelGrid[index].setIcon(new ImageIcon(miasta.error_texture()));
                    System.out.println(e + " (unknown id)");
                }

            }
        }
        catch(IOException g){
            System.out.println(g);
        }
    }

}
