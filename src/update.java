import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class update {
    public static void download(){
        Runtime rt = Runtime.getRuntime();
        String url = "https://github.com/Miasta-creators/Miasta_gra/releases";
        try {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        main.close();
        System.exit(0);
    }
    public static void start_check() {
        String run = check();
        if (run.equals("Update Avalibe")) {
            main.update_bool = true;
            Graphics2D g2d = screen.foreground.createGraphics();
            g2d.setPaint(Color.white);
            g2d.setFont(new Font("Consolas", Font.PLAIN, 20));
            g2d.drawString("Update Avalibe", 5*32, 7 * 32 - 10);
            g2d.drawString("Update? Y/N", 5*32, 8 * 32 - 10);
            g2d.dispose();
            screen.update();
        }
        else if (!run.equals("OK")) {
            Graphics2D g2d = screen.foreground.createGraphics();
            g2d.setPaint(Color.RED);
            g2d.setFont(new Font("Consolas", Font.PLAIN, 40));
            g2d.drawString(run, 100, 260);
            g2d.dispose();
            screen.update();
            while (true) {

            }
        } else if (run.equals("OK")) main.start = true;

        if(main.start)main.game();
    }
    public static String check() {
        try {
            URL url = new URL("https://page.atcat.repl.co/program_downloads/gra_miasta/user_auth");
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("GET");
            urlCon.setRequestProperty("User-Agent", "Miasta1.6");
            urlCon.setRequestProperty("UserId", "Miasta1.6user" + main.username);
            urlCon.setRequestProperty("UserPassword", "yugfwyqIUGPUIW;E'bsgnowfe2387472");
            urlCon.setRequestProperty("GameVersion", main.version);
            BufferedReader read = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line = read.readLine();
            System.out.println(line);
            read.close();
            return line;


        } catch (UnknownHostException e) {
            main.noInternet = true;
            return "OK";
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Connection Error";
    }
}
