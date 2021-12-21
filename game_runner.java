import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Scanner;

public class game_runner {
    public static void RUN() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "bat.bat").inheritIO().start().waitFor();
    }
    public static void RUNinstall_exe() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "next_update.exe").inheritIO().start().waitFor();
    }
    public static String version;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner odczyt = new Scanner(new File("version.bin"));
        version = odczyt.nextLine();
        if(version_compare()){
            System.out.println("Dostępna jest nowsza wersja. Czy chcesz zaktualizować? Y/N");
            if(scanner.nextLine().toLowerCase(Locale.ROOT).equals("y")){
                update_download();
            }
            else RUN();
        }
        else RUN();
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
    public static void update_download() throws IOException {
        try {
            System.out.println("Sprawdzam dane...");

            URL url = new URL("https://page.atcat.repl.co/program_downloads/gra_miasta/update_info");

            URLConnection urlCon = url.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String info = read.readLine();
            read.close();
            version = info;
            File f = new File("version.bin");
            PrintWriter zapis = new PrintWriter(f);
            zapis.print(version);

            if(info.toLowerCase(Locale.ROOT).equals("jar")) {
                System.out.println("Pobieranie...");
                try {
                    InputStream in = new URL("https://github.com/ATcat-pl/Miasta_gra/raw/main/miasta.jar").openStream();
                    Files.copy(in, Paths.get("new_miasta.jar"), StandardCopyOption.REPLACE_EXISTING);
                }
                catch (IOException e) {
                    System.out.println("ERROR While downloading updates ");
                }


                File old_game = new File("miasta.jar");
                old_game.delete();

                File file = new File("new_miasta.jar");
                File file2 = new File("miasta.jar");

                if (file2.exists()) {
                    throw new java.io.IOException("ERROR deleting old game file");
                }
                else{
                    file.renameTo(file2);
                }
            }
            else {
                System.out.println("Pobieranie...");
                try{
                    InputStream in = new URL("https://github.com/ATcat-pl/Miasta_gra/raw/main/miasta.exe").openStream();
                    Files.copy(in, Paths.get("next_update.exe"), StandardCopyOption.REPLACE_EXISTING);
                }
                catch (IOException e) {
                    System.out.println("ERROR While downloading updates ");
                }
            }
            RUNinstall_exe();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.exit(0);
    }
}
