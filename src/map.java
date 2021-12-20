import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class map {

    public static Random random = new Random();
    private static final byte[] BUFFER = new byte[4096 * 1024];

    private static void copy(InputStream input, OutputStream output) throws IOException {
        int bytesRead;
        while ((bytesRead = input.read(BUFFER)) != -1) {
            output.write(BUFFER, 0, bytesRead);
        }
    }

    public static int[][] gameMAP = {
            {1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012, 1013, 1014, 1015, 1016},
            {1001, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1002, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1003, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1004, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1005, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1006, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1007, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1008, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1009, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1010, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1011, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1012, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1013, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1014, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1015, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {1016, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
    };
    public static int chunk_y = 0;
    public static int chunk_x = 0;
    public static String path = String.valueOf(random.nextInt());
    private static File zipFilePath;

    public static void save() throws IOException {
        File f1 = new File("saves\\" + path);
        f1.mkdir();
        File f = new File("saves\\" + path + "\\" + chunk_x + "," + chunk_y + ".citymap");

        PrintWriter zapis = new PrintWriter("saves\\" + path + "\\" + chunk_x + "," + chunk_y + ".citymap");
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                zapis.print(gameMAP[i][j] + ",");
            }
            zapis.print(";");
        }
        zapis.close();


    }
    public static void load() {
        if (path.equals("null")) {
            System.out.println("Ustawiono chunk na 0,0");
        } else if (path.equals("grass")) {
            for (int j = 0; j < gameMAP.length - 1; j++) {
                for (int x = 0; x < gameMAP[j].length - 1; x++) {
                    map.gameMAP[j + 1][x + 1] = 0;
                }
            }
            path = String.valueOf(random.nextInt());
        } else if (path.equals("dirt")) {
            for (int j = 0; j < gameMAP.length - 1; j++) {
                for (int x = 0; x < gameMAP[j].length - 1; x++) {
                    map.gameMAP[j + 1][x + 1] = 1;
                }
            }
            path = String.valueOf(random.nextInt());
        } else if (path.equals("water")) {
            for (int j = 0; j < gameMAP.length - 1; j++) {
                for (int x = 0; x < gameMAP[j].length - 1; x++) {
                    map.gameMAP[j + 1][x + 1] = 2;
                }
            }
            path = String.valueOf(random.nextInt());
        } else if (path.equals("concrete")) {
            for (int j = 0; j < gameMAP.length - 1; j++) {
                for (int x = 0; x < gameMAP[j].length - 1; x++) {
                    map.gameMAP[j + 1][x + 1] = 3;
                }
            }
            path = String.valueOf(random.nextInt());
        } else {
            try {
                Scanner odczyt = new Scanner(new File("saves\\" + path + "\\" + chunk_x + "," + chunk_y + ".citymap"));
                String line = odczyt.nextLine();
                String[] line_split = line.split(";");
                for (int j = 0; j < line_split.length; j++) {
                    String[] id_number = line_split[j].split(",");
                    for (int x = 0; x < id_number.length; x++) {
                        map.gameMAP[j + 1][x + 1] = Integer.parseInt(id_number[x]);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Mapa nie istnieje");
                for (int j = 0; j < gameMAP.length - 1; j++) {
                    for (int x = 0; x < gameMAP[j].length - 1; x++) {
                        map.gameMAP[j + 1][x + 1] = 0;
                    }
                }
            }
            }
        }
}
