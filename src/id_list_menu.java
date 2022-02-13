import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class id_list_menu {
    private static int[] textures_id = new int[textures.texture.size()-17];
    private static final int num_of_pages = textures_id.length / 17;
    private static int active_page = 0;
    public static void init(){
        int j=0;
        for (int i : textures.texture.keySet()) {
            if(i<1000) {
                textures_id[j] = i;
                j++;
            }
        }
    }
    public static void render(){
        Graphics2D g2d = screen.foreground.createGraphics();
        g2d.setPaint(Color.black);
        g2d.fill(new Rectangle2D.Double(546, 0, 102, 544));
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Consolas", Font.PLAIN, 20));
        int page = active_page*17;
        for(int i=0; i<17||i+page<textures_id.length-1; i++){
            screen.render_texture(546, i*32, 1, textures_id[i+page], true);
            g2d.drawString(String.valueOf(textures_id[i+page]), 546 + 42, i*32 + 20);
        }
    }
    public static void keyboard_in(int key_code){
        if (key_code == KeyEvent.VK_PAGE_UP){
            if(active_page>0){
                System.out.println("PAGE_UP");
                active_page--;
                render();
                screen.update();
            }
        }
        else if (key_code == KeyEvent.VK_PAGE_DOWN){
            if(active_page<num_of_pages-1){
                System.out.println("PAGE_DOWN");
                active_page++;
                render();
                screen.update();
            }
        }
    }
}
