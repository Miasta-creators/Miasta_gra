import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;


import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;



@SuppressWarnings("serial")
public class miasta extends JPanel {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        initialize();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGui1();
                    window2.createAndShowGui2();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        while(true){
            int wiersz=0;
            int kolumna=0;
            int kod=1000;
            System.out.println("d - dalej / s - zapis / e - wyjście / l - wczytaj");
            String opcja = scanner.nextLine().toLowerCase(Locale.ROOT);
            if(opcja.equals("d")) {
                try {
                    System.out.print("wiersz: ");
                    wiersz = scanner.nextInt();
                    System.out.print("kolumna: ");
                    kolumna = scanner.nextInt();
                    System.out.print("kod: ");
                    kod = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Musi być liczbą");
                }
                if (wiersz > 0 && kolumna > 0) {
                    map.gameMAP[wiersz][kolumna] = kod;
                }
            }
            else if(opcja.equals("s")){
                System.out.print("Naswa mapy: ");
                map.save(scanner.nextLine()+".citymap");
            }
            else if(opcja.equals("e")){
                System.exit(0);
            }
            else if(opcja.equals("l")){
                System.out.print("Naswa mapy: ");
                map.load(scanner.nextLine()+".citymap");
                reloadmap();
            }

            try {
                miasta mainPanel = new miasta();
                mapframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mapframe.getContentPane().add(mainPanel);
                mapframe.pack();
                mapframe.setLocationByPlatform(true);
                mapframe.setVisible(true);
            }
            catch(IllegalComponentStateException e){}
        }
    }

    public static void reloadmap(){
        try {
            miasta mainPanel = new miasta();
            mapframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mapframe.getContentPane().add(mainPanel);
            mapframe.pack();
            mapframe.setLocationByPlatform(true);
            mapframe.setVisible(true);
        }
        catch(IllegalComponentStateException e){} catch (IOException e) {}
    }

    public static AffineTransform tx = new AffineTransform();

    public static HashMap<Integer, String> textures = new HashMap<Integer, String>();

    public static void initialize() {
        textures.put(0,"grass.png");
        textures.put(1,"dirt.png");
        textures.put(2,"water.png");
        textures.put(3,"concrete.png");
        textures.put(41,"roads\\road1.png");
        textures.put(42,"roads\\road2.png");
        textures.put(51,"roads\\road_crossing_3_1.png");
        textures.put(52,"roads\\road_crossing_3_2.png");
        textures.put(53,"roads\\road_crossing_3_3.png");
        textures.put(54,"roads\\road_crossing_3_4.png");
        textures.put(55,"roads\\road_crossing_4.png");
        textures.put(600,"buildings\\block_top.png");
        textures.put(601,"buildings\\block_corner_1.png");
        textures.put(602,"buildings\\block_corner_2.png");
        textures.put(603,"buildings\\block_corner_3.png");
        textures.put(604,"buildings\\block_corner_4.png");
        textures.put(611,"buildings\\block_side_1.png");
        textures.put(612,"buildings\\block_side_2.png");
        textures.put(613,"buildings\\block_side_3.png");
        textures.put(614,"buildings\\block_side_4.png");
        textures.put(621,"buildings\\block_slant_1.png");
        textures.put(622,"buildings\\block_slant_2.png");
        textures.put(623,"buildings\\block_slant_3.png");
        textures.put(624,"buildings\\block_slant_4.png");
        textures.put(631,"buildings\\block_2side_1.png");
        textures.put(632,"buildings\\block_2side_2.png");
        textures.put(640,"buildings\\house1.png");
        textures.put(641,"buildings\\house2.png");

        textures.put(1000,"text\\null.png");
        textures.put(1001,"text\\1.png");
        textures.put(1002,"text\\2.png");
        textures.put(1003,"text\\3.png");
        textures.put(1004,"text\\4.png");
        textures.put(1005,"text\\5.png");
        textures.put(1006,"text\\6.png");
        textures.put(1007,"text\\7.png");
        textures.put(1008,"text\\8.png");
        textures.put(1009,"text\\9.png");
        textures.put(1010,"text\\10.png");
        textures.put(1011,"text\\11.png");
        textures.put(1012,"text\\12.png");
        textures.put(1013,"text\\13.png");
        textures.put(1014,"text\\14.png");
        textures.put(1015,"text\\15.png");
        textures.put(1016,"text\\16.png");
}

    private JLabel[][] labelGrid = new JLabel[map.gameMAP.length][map.gameMAP[0].length];

    public miasta() throws IOException {
        setLayout(new GridLayout(map.gameMAP.length, map.gameMAP[0].length));
        for (int r = 0; r < labelGrid.length; r++) {
            for (int c = 0; c < labelGrid[r].length; c++) {
                labelGrid[r][c] = new JLabel();
                try {
                    labelGrid[r][c].setIcon(new ImageIcon(ImageIO.read(new File("textures\\"+textures.get(map.gameMAP[r][c])))));
                }
                catch(NullPointerException e){labelGrid[r][c].setIcon(new ImageIcon(error_texture()));}
                catch(IIOException f){labelGrid[r][c].setIcon(new ImageIcon(error_texture()));}
                add(labelGrid[r][c]);
            }
        }
        addMouseListener(new MyMouseListener());
    }
    public static int publicrow;
    public static int publiccol;

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent mEvt) {
            Component comp = getComponentAt(mEvt.getPoint());
            for (int row = 0; row < labelGrid.length; row++) {
                for (int col = 0; col < labelGrid[row].length; col++) {
                    if (labelGrid[row][col] == comp) {
                        publicrow = row;
                        publiccol = col;
                    }
                }
            }
        }
    }

    public static BufferedImage error_texture(){
        try{
            return ImageIO.read(new File("textures\\error.png"));
        }
        catch (IOException e) {
            return null;
        }
    }

    public static JFrame mapframe = new JFrame("mapa");
    private static void createAndShowGui1() throws IOException {
        miasta mainPanel = new miasta();

        mapframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapframe.getContentPane().add(mainPanel);
        mapframe.pack();
        mapframe.setLocationByPlatform(true);
        mapframe.setVisible(true);
    }
}
