import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class lang {
    public static String[] text;
    public static HashMap<String, String> messages = new HashMap<String, String>();
    public static void loadLang(){
        try{
            Scanner odczyt = new Scanner(new File("lang\\setLang.txt"));
            String setLang = odczyt.nextLine();
            odczyt.close();
            Scanner odczyt_ = new Scanner(new File("lang\\"+setLang+".citieslang"));
            String line = odczyt_.nextLine();
            odczyt_.close();
            text = line.split(";");
            init_lang();
        }
        catch (FileNotFoundException e) {
            miasta.errorWindow.add(new JLabel("Language file not found."));
            miasta.errorWindow.pack();
            miasta.errorWindow.setVisible(true);
        }
    }
    public static void init_lang(){ // dalej / zapis / wyjście / wczytaj / zmień chunk / wypełnij
        try {
            messages.put("opcja1", text[0]);
            messages.put("opcja2", text[1]);
            messages.put("opcja3", text[2]);
            messages.put("opcja4", text[3]);
            messages.put("opcja5", text[4]);
            messages.put("opcja6", text[5]);
            messages.put("row:", text[6]);
            messages.put("col:", text[7]);
            messages.put("code:", text[8]);
            messages.put("map_name:", text[9]);
            messages.put("zapisz", text[10]);
            messages.put("zapis_error", text[11]);
            messages.put("Place", text[12]);
            messages.put("load", text[13]);
            messages.put("poczatkowy", text[14]);
            messages.put("koncowy", text[15]);
        }
        catch(ArrayIndexOutOfBoundsException e){
            miasta.errorWindow.add(new JLabel("Error loading language file"+e));
            miasta.errorWindow.pack();
            miasta.errorWindow.setVisible(true);
        }
    }
}
