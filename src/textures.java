import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class textures {
    public static HashMap<Integer, BufferedImage> texture = new HashMap<Integer, BufferedImage>();
    public static HashMap<String, BufferedImage> other_texture = new HashMap<String, BufferedImage>();

    public static void init() throws IOException {
        init_file_locations();
        for (int x : file_locations.keySet()) {
            try {
                texture.put(x, ImageIO.read(new File("textures\\" + file_locations.get(x))));
            } catch (IOException e) {
                texture.put(x, ImageIO.read(new File("textures\\error.png")));
                System.out.println("Error loading: " + file_locations.get(x));
//                e.printStackTrace();
            }
        }
        for (String x : file_locations2.keySet()) {
            try {
                other_texture.put(x, ImageIO.read(new File("textures\\" + file_locations2.get(x))));
            } catch (IOException e) {
                other_texture.put(x, ImageIO.read(new File("textures\\error.png")));
                System.out.println("Error loading: " + file_locations2.get(x));
//                e.printStackTrace();
            }
        }
    }

    private static HashMap<Integer, String> file_locations = new HashMap<Integer, String>();
    private static HashMap<String, String> file_locations2 = new HashMap<String, String>();

    private static void init_file_locations() {
        file_locations2.put("select", "tile_select.png");
//        file_locations2.put("car1", "roads\\car1.png");
//        file_locations2.put("car2", "roads\\car2.png");
//        file_locations2.put("car3", "roads\\car3.png");
//        file_locations2.put("car4", "roads\\car4.png");
//        file_locations2.put("car5", "roads\\car5.png");
        file_locations.put(0, "grass.png");
        file_locations.put(1, "dirt.png");
        file_locations.put(2, "water.png");
        file_locations.put(3, "concrete.png");

        file_locations.put(100, "props\\tree.png");

        file_locations.put(41, "roads\\road1_0.png");
        file_locations.put(42, "roads\\road2_0.png");
        file_locations.put(51, "roads\\road_crossing_3_1.png");
        file_locations.put(52, "roads\\road_crossing_3_2.png");
        file_locations.put(53, "roads\\road_crossing_3_3.png");
        file_locations.put(54, "roads\\road_crossing_3_4.png");
        file_locations.put(55, "roads\\road_crossing_4.png");
        file_locations.put(61, "roads\\road_turn_1.png");
        file_locations.put(62, "roads\\road_turn_2.png");
        file_locations.put(63, "roads\\road_turn_3.png");
        file_locations.put(64, "roads\\road_turn_4.png");

        file_locations.put(71, "roads\\path1.png");
        file_locations.put(72, "roads\\path2.png");
        file_locations.put(81, "roads\\path_crossing_3_1.png");
        file_locations.put(82, "roads\\path_crossing_3_2.png");
        file_locations.put(83, "roads\\path_crossing_3_3.png");
        file_locations.put(84, "roads\\path_crossing_3_4.png");
        file_locations.put(85, "roads\\path_crossing_4.png");
        file_locations.put(91, "roads\\path_turn_1.png");
        file_locations.put(92, "roads\\path_turn_2.png");
        file_locations.put(93, "roads\\path_turn_3.png");
        file_locations.put(94, "roads\\path_turn_4.png");

        file_locations.put(200, "train\\platforms\\platform1.png");
        file_locations.put(201, "train\\platforms\\platform2.png");
        file_locations.put(202, "train\\platforms\\platform3.png");
        file_locations.put(203, "train\\platforms\\platform4.png");
        file_locations.put(204, "train\\platforms\\platform_corner_1.png");
        file_locations.put(205, "train\\platforms\\platform_corner_2.png");
        file_locations.put(206, "train\\platforms\\platform_corner_3.png");
        file_locations.put(207, "train\\platforms\\platform_corner_4.png");
        file_locations.put(208, "train\\platforms\\platform_double_1.png");
        file_locations.put(209, "train\\platforms\\platform_double_2.png");
        file_locations.put(210, "train\\platforms\\platform_end_1.png");
        file_locations.put(211, "train\\platforms\\platform_end_2.png");
        file_locations.put(212, "train\\platforms\\platform_end_3.png");
        file_locations.put(213, "train\\platforms\\platform_end_4.png");

        file_locations.put(220, "train\\rails\\rail1_1.png");
        file_locations.put(221, "train\\rails\\rail1_2.png");
        file_locations.put(222, "train\\rails\\rail1_turn_1.png");
        file_locations.put(223, "train\\rails\\rail1_turn_2.png");
        file_locations.put(224, "train\\rails\\rail1_turn_3.png");
        file_locations.put(225, "train\\rails\\rail1_turn_4.png");
        file_locations.put(226, "train\\rails\\rail1_crossing_3_1.png");
        file_locations.put(227, "train\\rails\\rail1_crossing_3_2.png");
        file_locations.put(228, "train\\rails\\rail1_crossing_3_3.png");
        file_locations.put(229, "train\\rails\\rail1_crossing_3_4.png");

        file_locations.put(600, "buildings\\block_top.png");
        file_locations.put(601, "buildings\\block_corner_1.png");
        file_locations.put(602, "buildings\\block_corner_2.png");
        file_locations.put(603, "buildings\\block_corner_3.png");
        file_locations.put(604, "buildings\\block_corner_4.png");
        file_locations.put(611, "buildings\\block_side_1.png");
        file_locations.put(612, "buildings\\block_side_2.png");
        file_locations.put(613, "buildings\\block_side_3.png");
        file_locations.put(614, "buildings\\block_side_4.png");
        file_locations.put(631, "buildings\\block_2side_1.png");
        file_locations.put(632, "buildings\\block_2side_2.png");
        file_locations.put(633, "buildings\\block_end_1.png");
        file_locations.put(634, "buildings\\block_end_2.png");
        file_locations.put(635, "buildings\\block_end_3.png");
        file_locations.put(636, "buildings\\block_end_4.png");
        file_locations.put(640, "buildings\\house1.png");
        file_locations.put(641, "buildings\\house2.png");

        file_locations.put(1000, "text\\null.png");
        file_locations.put(1001, "text\\1.png");
        file_locations.put(1002, "text\\2.png");
        file_locations.put(1003, "text\\3.png");
        file_locations.put(1004, "text\\4.png");
        file_locations.put(1005, "text\\5.png");
        file_locations.put(1006, "text\\6.png");
        file_locations.put(1007, "text\\7.png");
        file_locations.put(1008, "text\\8.png");
        file_locations.put(1009, "text\\9.png");
        file_locations.put(1010, "text\\10.png");
        file_locations.put(1011, "text\\11.png");
        file_locations.put(1012, "text\\12.png");
        file_locations.put(1013, "text\\13.png");
        file_locations.put(1014, "text\\14.png");
        file_locations.put(1015, "text\\15.png");
        file_locations.put(1016, "text\\16.png");
    }
}
