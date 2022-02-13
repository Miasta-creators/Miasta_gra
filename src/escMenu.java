import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class escMenu {
    public static int select = 2;
    public static void show() {
        render_menu();
//        screen.fill_square(33, 8 * 32, 543, 11 * 32 - 1, "#000000", true);
    }
    public static void keyboard_in(int key_code) {
        if (key_code == KeyEvent.VK_UP) {
            if(select > 0){
                select--;
                render_menu();
                screen.update();
            }
        }
        else if (key_code == KeyEvent.VK_DOWN) {
            if(select < 2){
                select++;
                render_menu();
                screen.update();
            }
        }
        else if (key_code == KeyEvent.VK_ENTER) {
            if(select==0){
                map.path = "";
                main.x_text_pos = 43;
                main.Esc_menu =false;
                screen.reset_foreground();
                Graphics2D g2d = screen.foreground.createGraphics();
                g2d.setPaint(Color.white);
                g2d.setFont(new Font("Consolas", Font.PLAIN, 10));
                screen.fill_square(32, 8 * 32 - 10, 543, 9 * 32 - 1, "#000000", true);
                g2d.drawString("Save map", 43, 8 * 32);
                g2d.dispose();
                screen.update();
                main.lock = true;
                main.operation = "save";
            }
            else if(select==1){
                map.path = "";
                main.x_text_pos = 43;
                main.Esc_menu =false;
                screen.reset_foreground();
                Graphics2D g2d = screen.foreground.createGraphics();
                g2d.setPaint(Color.white);
                g2d.setFont(new Font("Consolas", Font.PLAIN, 10));
                screen.fill_square(32, 8 * 32 - 10, 543, 9 * 32 - 1, "#000000", true);
                g2d.drawString("Load map (random generation - noise; {map name})", 43, 8 * 32);
                g2d.dispose();
                screen.update();
                screen.update();
                main.lock = true;
                main.operation = "load";
            }
            else if(select==2){
                if (main.start) main.close();
                System.exit(0);
            }
        }
        else if (key_code == KeyEvent.VK_N || key_code == KeyEvent.VK_ESCAPE) {
            main.Esc_menu =false;
            screen.reset_foreground();
            screen.update();
        }
    }
    private static void render_menu() {
        int y_pos_up = select*32;
        int y_pos_down = y_pos_up+32;
        screen.fill_square(33, 8 * 32, 543, 11 * 32 - 1, "#000000", true);
        screen.fill_square(33, 8 * 32+y_pos_up, 543, 8 * 32+y_pos_down, "#AAAAAA", true);
        Graphics2D g2d = screen.foreground.createGraphics();
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Consolas", Font.PLAIN, 20));
        g2d.drawString("save", 5*32, 9 * 32 - 10); //option 0
        g2d.drawString("load", 5*32, 10 * 32 - 10); //option 1
        g2d.drawString("exit", 5*32, 11 * 32 - 10); //option 2
        g2d.dispose();
        screen.update();
    }
}
