import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;



@SuppressWarnings("serial")
public class miasta extends JPanel {
    public static String version = "1.3";
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        initialize();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGui1();
                    window2.createAndShowGui2();
                }
                catch(IOException e){}
            }
        });
        if(version_compare()) System.out.println("Dostępna jest nowsza wersja pobierz ją tutaj: https://page.atcat.repl.co/program_downloads/gra_miasta");
        while(true){
            int wiersz=0;
            int kolumna=0;
            int kod=1000;
            System.out.println("d - dalej / s - zapis / e - wyjście / l - wczytaj / z - zmień chunk");
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
                System.out.print("Nazwa mapy: ");
                map.path = scanner.nextLine();
                map.save();
            }
            else if(opcja.equals("e")){
                System.exit(0);
            }
            else if(opcja.equals("l")){
                map.chunk_x = 0;
                map.chunk_y = 0;
                System.out.print("Nazwa mapy: ");
                map.path = scanner.nextLine();
                map.load();
                reloadmap();
            }
            else if(opcja.equals("z")){
                System.out.println("w - UP / s - DOWN / a - LEFT / d - RIGHT");
                opcja = scanner.nextLine().toLowerCase(Locale.ROOT);
                if(opcja.equals("w")){
                    map.save();
                    map.chunk_y++;
                }
                else if(opcja.equals("s")){
                    map.save();
                    map.chunk_y--;
                }
                else if(opcja.equals("a")){
                    map.save();
                    map.chunk_x--;
                }
                else if(opcja.equals("d")){
                    map.save();
                    map.chunk_x++;
                }
                map.load();
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

    public static boolean version_compare(){
        try{
            URL url = new URL("https://page.atcat.repl.co/program_downloads/gra_miasta/lastest_version");

            URLConnection urlCon = url.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String ver = read.readLine();
            read.close();

            if(ver.equals(version)){
                return false;
            }
            else {
                return true;
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
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

        textures.put(100,"props\\tree.png");

        textures.put(41,"roads\\road1.png");
        textures.put(42,"roads\\road2.png");
        textures.put(51,"roads\\road_crossing_3_1.png");
        textures.put(52,"roads\\road_crossing_3_2.png");
        textures.put(53,"roads\\road_crossing_3_3.png");
        textures.put(54,"roads\\road_crossing_3_4.png");
        textures.put(55,"roads\\road_crossing_4.png");
        textures.put(61,"roads\\road_turn_1.png");
        textures.put(62,"roads\\road_turn_2.png");
        textures.put(63,"roads\\road_turn_3.png");
        textures.put(64,"roads\\road_turn_4.png");

        textures.put(71,"roads\\path1.png");
        textures.put(72,"roads\\path2.png");
        textures.put(81,"roads\\path_crossing_3_1.png");
        textures.put(82,"roads\\path_crossing_3_2.png");
        textures.put(83,"roads\\path_crossing_3_3.png");
        textures.put(84,"roads\\path_crossing_3_4.png");
        textures.put(85,"roads\\path_crossing_4.png");
        textures.put(91,"roads\\path_turn_1.png");
        textures.put(92,"roads\\path_turn_2.png");
        textures.put(93,"roads\\path_turn_3.png");
        textures.put(94,"roads\\path_turn_4.png");

        textures.put(600,"buildings\\block_top.png");
        textures.put(601,"buildings\\block_corner_1.png");
        textures.put(602,"buildings\\block_corner_2.png");
        textures.put(603,"buildings\\block_corner_3.png");
        textures.put(604,"buildings\\block_corner_4.png");
        textures.put(611,"buildings\\block_side_1.png");
        textures.put(612,"buildings\\block_side_2.png");
        textures.put(613,"buildings\\block_side_3.png");
        textures.put(614,"buildings\\block_side_4.png");
        textures.put(631,"buildings\\block_2side_1.png");
        textures.put(632,"buildings\\block_2side_2.png");
        textures.put(640,"buildings\\house1.png");
        textures.put(641,"buildings\\house2.png");

        textures.put(1000, "text\\null.png");
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
            System.out.println("ERROR Trying again");
            return error_texture();
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
