import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.awt.Graphics2D;

public class main extends JPanel {

    public static String version = "1.6";

    private static String path = "";
    public static Scanner scanner = new Scanner(System.in);
    public static boolean lock = false;

    private static JFrame window = new JFrame("Miasta");
    public static Random random = new Random();

    public static int x_pos = 200;
    public static int y_pos = 50;
    public static int last_x_pos = 200;
    public static int last_y_pos = 50;
    public static boolean start = false;
    public static long username = random.nextLong();

    public static boolean noInternet = false;

    public static boolean Esc_menu = false;

    private static int place_code;

    public static boolean error = false;

    public static int x_select_pos = 1;
    public static int y_select_pos = 1;
    public static String operation = "";
    public static String text = "";
    public static boolean update_bool = false;

    public main() {
        KeyListener listener = new keyboard();
        addKeyListener(listener);
        setFocusable(true);
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    close();
                }
                catch(Exception z){}
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        try {
            textures.init();
            id_list_menu.init();
        } catch (IOException e) {
            error = true;
            Graphics2D g2d = screen.foreground.createGraphics();
            g2d.setPaint(Color.red);
            g2d.setFont(new Font("Consolas", Font.PLAIN, 20));
            g2d.drawString("Critical error loading textures", 5*32, 7 * 32 - 10);
            g2d.drawString("Press any key to exit", 5*32, 8 * 32 - 10);
        }
        main keyboard = new main();
        window.add(keyboard);
        window.add(screen.frame);

        window.pack();
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);
        window.setLocationByPlatform(true);
        window.setVisible(true);

        if(!error)update.start_check();
    }
    public static void game(){

        screen.reset_foreground();


//        screen.fill_square(x_pos,y_pos,x_pos+32,y_pos+32, "#0000E0", true);
//        screen.fill_diagonal_horizontal(x_pos,y_pos,10,32,"#0000FF", true);
//        screen.fill_diagonal_vertical(x_pos+32,y_pos,10,32,"#0000A0", true);

        screen.draw_vertical(545, 0, 543, "#FFFFFF", true);
        screen.update();

        id_list_menu.render();
        screen.render_map();
        screen.update();

    }



    private String removeLastChar(String s) {
//returns the string after removing the last character
        return s.substring(0, s.length() - 1);
    }
    public static void close() {
        if(!noInternet) {
            try {
                URL url = new URL("https://page.atcat.repl.co/program_downloads/gra_miasta/user_auth");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Miasta1.6");
                con.setRequestProperty("UserId", "Miasta1.6user" + username);
                con.setRequestProperty("UserPassword", "yugfwyqIUGPUIW;E'bsgnowfe2387472");
                BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = read.readLine();
                read.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int x_text_pos = 43;

    public class keyboard implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (!error) {
                if (!update_bool) {
                    if (!lock && !Esc_menu) {
                        if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN || e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                            id_list_menu.keyboard_in(e.getKeyCode());
                        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            if (x_select_pos < 16) x_select_pos++;
                            screen.reset_foreground();
                            screen.render_texture2(x_select_pos * 32, y_select_pos * 32, 0.5F, "select", true);
                            screen.update();
                        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            if (x_select_pos > 1) x_select_pos--;
                            screen.reset_foreground();
                            screen.render_texture2(x_select_pos * 32, y_select_pos * 32, 0.5F, "select", true);
                            screen.update();
                        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            if (y_select_pos < 16) y_select_pos++;
                            screen.reset_foreground();
                            screen.render_texture2(x_select_pos * 32, y_select_pos * 32, 0.5F, "select", true);
                            screen.update();
                        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                            if (y_select_pos > 1) y_select_pos--;
                            screen.reset_foreground();
                            screen.render_texture2(x_select_pos * 32, y_select_pos * 32, 0.5F, "select", true);
                            screen.update();
                        } else if (e.getKeyCode() == KeyEvent.VK_W) {
                            try {
                                map.save();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            map.chunk_y++;
                            map.load();
                            screen.render_map();
                            screen.update();
                        } else if (e.getKeyCode() == KeyEvent.VK_S) {
                            try {
                                map.save();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            map.chunk_y--;
                            map.load();
                            screen.render_map();
                            screen.update();
                        } else if (e.getKeyCode() == KeyEvent.VK_A) {
                            try {
                                map.save();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            map.chunk_x++;
                            map.load();
                            screen.render_map();
                            screen.update();
                        } else if (e.getKeyCode() == KeyEvent.VK_D) {
                            try {
                                map.save();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            map.chunk_x--;
                            map.load();
                            screen.render_map();
                            screen.update();
                        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            map.gameMAP[y_select_pos][x_select_pos] = place_code;
                            screen.render_map();
                            screen.update();
                            lock = false;
//                } else if (e.getKeyCode() == KeyEvent.VK_L) {
//                    map.path = "";
//                    x_text_pos = 0;
//                    screen.fill_square(0, 8 * 32, 543, 9 * 32 - 1, "#000000", true);
//                    screen.update();
//                    lock = true;
//                    operation = "load";
//                }else if (e.getKeyCode() == KeyEvent.VK_S) {
//                    map.path = "";
//                    x_text_pos = 0;
//                    screen.fill_square(0, 8 * 32, 543, 9 * 32 - 1, "#000000", true);
//                    screen.update();
//                    lock = true;
//                    operation = "save";
                        } else if (e.getKeyCode() == KeyEvent.VK_P) {
                            operation = "place";
                            text = "";
                            x_text_pos = 43;
                            Graphics2D g2d = screen.foreground.createGraphics();
                            g2d.setPaint(Color.white);
                            g2d.setFont(new Font("Consolas", Font.PLAIN, 10));
                            screen.fill_square(32, 8 * 32 - 10, 543, 9 * 32 - 1, "#000000", true);
                            g2d.drawString("Place", 43, 8 * 32);
                            g2d.dispose();
                            screen.update();
                            lock = true;
                        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            Esc_menu = true;
                            escMenu.show();
                        }
                    } else if (!Esc_menu) {
                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            lock = false;
                            screen.reset_foreground();
                            screen.update();
                        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            if (operation.equals("load")) {
                                map.load();
                                lock = false;
                                screen.render_map();
                                screen.update();
                            } else if (operation.equals("place")) {
                                place_code = Integer.parseInt(text);
                                map.gameMAP[y_select_pos][x_select_pos] = place_code;
                                screen.render_map();
                                screen.update();
                                lock = false;
                            } else if (operation.equals("save")) {
                                try {
                                    map.save();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                lock = false;
                                screen.render_map();
                                screen.update();
                            }
                        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                            if (operation.equals("load") || operation.equals("save")) {
                                map.path = removeLastChar(map.path);
                                x_text_pos = x_text_pos - 20;
                                screen.fill_square(x_text_pos, 9 * 32 - 10, x_text_pos + 20, 8 * 32, "#000000", true);
                                screen.update();
                            } else if (operation.equals("place")) {
                                text = removeLastChar(text);
                                x_text_pos = x_text_pos - 20;
                                screen.fill_square(x_text_pos, 8 * 32, x_text_pos + 20, 9 * 32 - 10, "#000000", true);
                                screen.update();
                            }
                        } else {
                            Graphics2D g2d = screen.foreground.createGraphics();
                            char code = (char) e.getKeyCode();
                            g2d.setPaint(Color.white);
                            g2d.setFont(new Font("Consolas", Font.PLAIN, 20));
                            if (operation.equals("load") || operation.equals("save")) {
                                map.path = map.path + String.valueOf(code).toLowerCase(Locale.ROOT);
                                g2d.drawString(String.valueOf(code), x_text_pos, 9 * 32 - 10);
                                x_text_pos = x_text_pos + 20;
                            } else if (operation.equals("place")) {
                                if (code >= KeyEvent.VK_0 && code <= KeyEvent.VK_9) {
                                    g2d.drawString(String.valueOf(code), x_text_pos, 9 * 32 - 10);
                                    text = text + code;
                                    x_text_pos = x_text_pos + 20;
                                }
                            }
                            g2d.dispose();
                            screen.update();
                        }
                    } else if (Esc_menu) {
                        escMenu.keyboard_in(e.getKeyCode());
                    }
//            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//                x_text_pos=0;
//                if(lock){
//                    screen.fill_square(206, 231, 306, 231+50, "#000000", true);
//                }
//                else {
//                    main.draw_text_box("#0000FF");
//                }
//                lock=!lock;
//            }
                }
                else {
                    if(e.getKeyCode()==KeyEvent.VK_Y){
                        update.download();
                    }
                    else if(e.getKeyCode()==KeyEvent.VK_N){
                        update_bool = false;
                        start = true;
                        game();
                    }
                }
            }
            else{
                System.exit(424);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }
}