import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class screen {
    public static BufferedImage background = new BufferedImage(544, 544, BufferedImage.TYPE_INT_RGB);
    public static BufferedImage foreground = new BufferedImage(648, 544, BufferedImage.TYPE_INT_RGB);
    public static JLabel frame = new JLabel(new ImageIcon(foreground));
    public static Random random = new Random();


    public static void reset_foreground(){
        float opaque = 1;
        Graphics2D g2d = foreground.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaque));
        g2d.drawImage(background, 0, 0, null);
        g2d.dispose();
    }
    public static void update() {
        ImageIcon icon = new ImageIcon(foreground);
        frame.setIcon(icon);
    }
    public static void draw_line(int x1, int y1, int x2, int y2, String color, boolean foreground){
        Graphics2D g2d;
        if(foreground)g2d=screen.foreground.createGraphics();
        else g2d=screen.background.createGraphics();
        g2d.setPaint(Color.decode(color));
        g2d.drawLine(x1,y1,x2,y2);
        g2d.dispose();
    }
    public static void draw_vertical(int x, int y, int lenght, String color, boolean foreground){
        for(int i=0; i<=lenght; i++) {
            if(foreground) screen.foreground.setRGB(x, i+y, Color.decode(color).getRGB());
            else background.setRGB(x, i+y, Color.decode(color).getRGB());
        }
    }
    public static void draw_horizontal(int x, int y, int lenght, String color, boolean foreground){
        for(int i=0; i<=lenght; i++) {
            if(foreground) screen.foreground.setRGB(i+x, y, Color.decode(color).getRGB());
            else background.setRGB(i+x, y, Color.decode(color).getRGB());
        }
    }
    public static void fill_square(int x1, int y1, int x2, int y2, String color, boolean foreground){
        for(int x=x1; x<=x2; x++){
            for(int y=y1; y<=y2; y++){
                if(foreground) screen.foreground.setRGB(x, y, Color.decode(color).getRGB());
                else background.setRGB(x, y, Color.decode(color).getRGB());
            }
        }
    }
    public static void render_texture(int x, int y, float opaque, int id, boolean foreground){
        Graphics2D g2d;
        if(foreground){g2d = screen.foreground.createGraphics();}
        else{g2d = background.createGraphics();}
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaque));
//        if(id==41){
//            g2d.drawImage(textures.texture.get(id), x, y, null);
//            int lanes = random.nextInt(4);
//            if(lanes == 0){
//
//            }
//            else if(lanes == 1){
//                double angle = 90;
//                BufferedImage car = textures.other_texture.get("car180_"+random.nextInt(6));
//                g2d.drawImage(), x+18, y+4, null);
//            }
//            else if(lanes == 2){
//                g2d.drawImage(textures.other_texture.get("car"+random.nextInt(6)), x, y, null);
//            }
//            else if(lanes == 3){
//                g2d.drawImage(textures.other_texture.get("car"+random.nextInt(6)), x, y, null);
//            }
//        }
//        if(id==42){
//            g2d.drawImage(textures.texture.get(id), x, y, null);
//        }
//        else {
//            g2d.drawImage(textures.texture.get(id), x, y, null);
//        } //future code for random cars
        g2d.drawImage(textures.texture.get(id), x, y, null); //old render

        g2d.dispose();
    }
    public static void render_texture2(int x, int y, float opaque, String id, boolean foreground){
        Graphics2D g2d;
        if(foreground){g2d = screen.foreground.createGraphics();}
        else{g2d = background.createGraphics();}
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaque));
        g2d.drawImage(textures.other_texture.get(id), x, y, null);
        g2d.dispose();
    }
    public static void render_map(){
        for(int i=0; i<map.gameMAP.length; i++){
            for(int j=0; j<map.gameMAP[i].length; j++){
                render_texture(j*32, i*32, 1, map.gameMAP[i][j], false);
            }
        }
        screen.reset_foreground();
        screen.render_texture2(main.x_select_pos * 32, main.y_select_pos * 32, 0.5F, "select", true);
    }
    private static BufferedImage rotateImage(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int width = image.getWidth(), height = image.getHeight();
        int newWidth = (int) Math.floor(width * cos + height * sin), newHeight = (int) Math.floor(height * cos + width * sin);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gs.getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g.rotate(angle, width / 2, height / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }
}
