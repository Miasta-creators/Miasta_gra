import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


@SuppressWarnings("serial")
public class miasta extends JPanel {
    private static JFrame starting = new JFrame("Miasta");
    public static JFrame errorWindow = new JFrame("ERROR");
    public static String version;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        try {
            JarFile jar = new JarFile("miasta.jar");
            JarEntry entry = (JarEntry) jar.getEntry("version.bin");
            InputStream input = jar.getInputStream(entry);
            Scanner odczyt = new Scanner(input);
            starting.add(new JLabel("Loading data"));
            starting.pack();
            starting.setResizable(false);
            starting.setLocationByPlatform(true);
            starting.setVisible(true);
            try {
                version = odczyt.nextLine();
                if (version_compare()) {
                    starting.dispatchEvent(new WindowEvent(starting, WindowEvent.WINDOW_CLOSING));
                    JFrame Update = new JFrame("Update available");
                    JPanel UpdatePanel = new JPanel();
                    UpdatePanel.setLayout(new BoxLayout(UpdatePanel, BoxLayout.Y_AXIS));
                    JPanel BtnPanel = new JPanel();
                    JPanel TextPanel = new JPanel();
                    BtnPanel.setLayout(new GridLayout(1, 2));
                    JLabel label = new JLabel("Update available. Do you want to update?");
                    TextPanel.add(label);
                    JButton CancelBtn = new JButton("No");
                    JButton Btn = new JButton("Yes");
                    Btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            label.setText("Downloading...");
                            Btn.hide();
                            CancelBtn.hide();
                            try {
                                InputStream in = new URL("https://github.com/Miasta-creators/Miasta_gra/raw/main/miasta.exe").openStream();
                                Files.copy(in, Paths.get("update.exe"), StandardCopyOption.REPLACE_EXISTING);
                                label.setText("Finished, run 'update.exe' to update");
                            } catch (IOException f) {
                                errorWindow.add(new JLabel("ERROR While downloading update"));
                                errorWindow.setVisible(true);
                            }
                        }
                    });
                    CancelBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Update.dispatchEvent(new WindowEvent(Update, WindowEvent.WINDOW_CLOSING));
                            start();
                        }
                    });
                    BtnPanel.add(Btn);
                    BtnPanel.add(CancelBtn);
                    UpdatePanel.add(TextPanel);
                    UpdatePanel.add(BtnPanel);
                    Update.add(UpdatePanel);
                    Update.pack();
                    Update.setResizable(false);
                    Update.setLocationByPlatform(true);
                    Update.setVisible(true);
                } else {
                    start();
                }
            } catch (NoSuchElementException e) {
                odczyt.close();
                JFrame StartupError = new JFrame("Startup error");
                JPanel Error = new JPanel();
                Error.setLayout(new GridLayout(2, 1));
                Error.add(new JLabel("Startup error occurred version read fail, reinstall game."));
                JButton Btn = new JButton("EXIT");
                Btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(566);
                    }
                });
                Error.add(Btn);
                StartupError.add(Error);
                StartupError.setSize(new Dimension(200, 100));
                StartupError.setResizable(false);
                StartupError.setLocationByPlatform(true);
                StartupError.setVisible(true);

            }
        }
        catch(NoSuchFileException e){start();}
    }
    public static void start(){
        starting.setVisible(true);
        initialize();
        lang.loadLang();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGui1();
                }
                catch(IOException e){starting.dispatchEvent(new WindowEvent(starting, WindowEvent.WINDOW_CLOSING));}
            }
        });
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
            renderMap();
            mapPanel.revalidate();
            mapPanel.repaint();
        }
        catch (IOException e) { }
    }

    public static AffineTransform tx = new AffineTransform();

    public static HashMap<Integer, String> textures = new HashMap<Integer, String>();

    public static void initialize() {
        textures.put(0,"grass.png");
        textures.put(1,"dirt.png");
        textures.put(2,"water.png");
        textures.put(3,"concrete.png");

        textures.put(100,"props\\tree.png");

        textures.put(41,"roads\\road1_0.png");
        textures.put(42,"roads\\road2_0.png");
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

        textures.put(200, "train\\platforms\\platform1.png");
        textures.put(201, "train\\platforms\\platform2.png");
        textures.put(202, "train\\platforms\\platform3.png");
        textures.put(203, "train\\platforms\\platform4.png");
        textures.put(204, "train\\platforms\\platform_corner_1.png");
        textures.put(205, "train\\platforms\\platform_corner_2.png");
        textures.put(206, "train\\platforms\\platform_corner_3.png");
        textures.put(207, "train\\platforms\\platform_corner_4.png");
        textures.put(208, "train\\platforms\\platform_double_1.png");
        textures.put(209, "train\\platforms\\platform_double_2.png");
        textures.put(210, "train\\platforms\\platform_end_1.png");
        textures.put(211, "train\\platforms\\platform_end_2.png");
        textures.put(212, "train\\platforms\\platform_end_3.png");
        textures.put(213, "train\\platforms\\platform_end_4.png");

        textures.put(220, "train\\rails\\rail1_1.png");
        textures.put(221, "train\\rails\\rail1_2.png");
        textures.put(222, "train\\rails\\rail1_turn_1.png");
        textures.put(223, "train\\rails\\rail1_turn_2.png");
        textures.put(224, "train\\rails\\rail1_turn_3.png");
        textures.put(225, "train\\rails\\rail1_turn_4.png");
        textures.put(226, "train\\rails\\rail1_crossing_3_1.png");
        textures.put(227, "train\\rails\\rail1_crossing_3_2.png");
        textures.put(228, "train\\rails\\rail1_crossing_3_3.png");
        textures.put(229, "train\\rails\\rail1_crossing_3_4.png");

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
        textures.put(633,"buildings\\block_end_1.png");
        textures.put(634,"buildings\\block_end_2.png");
        textures.put(635,"buildings\\block_end_3.png");
        textures.put(636,"buildings\\block_end_4.png");
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

        textures.put(2000, "special\\arrow_up.png");
        textures.put(2001, "special\\arrow_right.png");
        textures.put(2002, "special\\arrow_down.png");
        textures.put(2003, "special\\arrow_left.png");
    }
    public static Random random = new Random();

    public static Image GetTexture(int id)  throws IOException {
        if(id==41){
            return ImageIO.read(new File("textures\\roads\\road1_" + random.nextInt(5) + ".png"));
        }
        else if(id==42){
            return ImageIO.read(new File("textures\\roads\\road2_" + random.nextInt(5) + ".png"));
        }
        else {
            return ImageIO.read(new File("textures\\" + textures.get(id)));
        }
    }

    private static JLabel[][] labelGrid = new JLabel[map.gameMAP.length][map.gameMAP[0].length];

    public static void renderMap() throws IOException {
        mapPanel.setLayout(new GridLayout(map.gameMAP.length, map.gameMAP[0].length));
        for (int r = 0; r < labelGrid.length; r++) {
            for (int c = 0; c < labelGrid[r].length; c++) {
                try {
                    labelGrid[r][c].setIcon(new ImageIcon(GetTexture(map.gameMAP[r][c])));
                }
                catch(NullPointerException e){labelGrid[r][c].setIcon(new ImageIcon(error_texture()));}
                catch(IIOException f){labelGrid[r][c].setIcon(new ImageIcon(error_texture()));}
            }
        }
    }
    public static void InitMap() throws IOException {
        mapPanel.setLayout(new GridLayout(map.gameMAP.length, map.gameMAP[0].length));
        for (int r = 0; r < labelGrid.length; r++) {
            for (int c = 0; c < labelGrid[r].length; c++) {
                labelGrid[r][c] = new JLabel();
                try {
                    labelGrid[r][c].setIcon(new ImageIcon(ImageIO.read(new File("textures\\"+textures.get(map.gameMAP[r][c])))));
                }
                catch(NullPointerException e){labelGrid[r][c].setIcon(new ImageIcon(error_texture()));}
                catch(IIOException f){labelGrid[r][c].setIcon(new ImageIcon(error_texture()));}
                mapPanel.add(labelGrid[r][c]);
            }
        }
    }

    public static BufferedImage error_texture(){
        try{
            return ImageIO.read(new File("textures\\error.png"));
        }
        catch (IOException e) {
            return error_texture();
        }
    }

    public static JPanel mapPanel = new JPanel();

    public static JPanel framePanel = new JPanel();
    public static JFrame window = new JFrame("Miasta");
    public static JPanel frame = new JPanel();
    private static void createAndShowGui1() throws IOException {
        InitMap();
        idPanel.renderId();
        inputPanel.initialize();

        framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.X_AXIS));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        framePanel.add(mapPanel);
        framePanel.add(idPanel.idPanel);
        frame.add(framePanel);
        frame.add(inputPanel.mainPanel);
        window.add(frame);
        window.pack();
        starting.dispatchEvent(new WindowEvent(starting, WindowEvent.WINDOW_CLOSING));
        window.setResizable(false);
        window.setLocationByPlatform(true);
        window.setVisible(true);
    }
}
