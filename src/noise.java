import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class noise {
    public static void mapRender() throws FileNotFoundException {
        Random random = new Random();
        String[] path = map.path.split(";");
        if(path[1]==null) {
            map.path = String.valueOf(random.nextInt());
        }
        else{
            map.path = path[1];
        }
        SimplexNoise simplexNoise=new SimplexNoise(40,0.09, random.nextInt());

        double xStart=0;
        double XEnd=500;
        double yStart=0;
        double yEnd=500;

        int xResolution=256;
        int yResolution=256;

        double[][] result=new double[xResolution][yResolution];

        for(int i=0;i<xResolution;i++){
            for(int j=0;j<yResolution;j++){
                int x=(int)(xStart+i*((XEnd-xStart)/xResolution));
                int y=(int)(yStart+j*((yEnd-yStart)/yResolution));
                result[i][j]=0.5*(1+simplexNoise.getNoise(x,y));
            }
        }

        File f1 = new File("saves\\" + map.path);
        f1.mkdir();

        int lastID = 0;
        for(int chunk_y=0; chunk_y<17; chunk_y++) {
            for(int chunk_x=0; chunk_x<17; chunk_x++) {
                int x = chunk_x-8;
                int y = chunk_y-8;
                File f = new File("saves\\" + map.path + "\\" + y + "," + x + ".citymap");
                PrintWriter zapis = new PrintWriter("saves\\" + map.path + "\\" + y + "," + x + ".citymap");
                for (int i = 1; i < 17; i++) {
                    for (int j = 1; j < 17; j++) {
                        try {
                            int chunk_x16 = chunk_x * 16;
                            int chunk_y16 = chunk_y * 16;
                            if (result[chunk_x16 + i][chunk_y16 + j] < 0.5) {
                                if(lastID==0){
                                    zapis.print(1 + ",");
                                    lastID = 1;
                                }
                                else {
                                    zapis.print(2 + ",");
                                    lastID = 2;
                                }
                            }
                            else if (result[chunk_x16 + i][chunk_y16 + j] < 0.8 && result[chunk_x16 + i][chunk_y16 + j] > 0.4) {
                                if(lastID==2){
                                    zapis.print(1 + ",");
                                    lastID = 1;
                                }
                                else if (result[chunk_x16 + i - 1][chunk_y16 + j] < 0.5) {
                                    zapis.print(1 + ",");
                                    lastID = 1;
                                }
                                else if (result[chunk_x16 + i + 1][chunk_y16 + j] < 0.5) {
                                    zapis.print(1 + ",");
                                    lastID = 1;
                                }
                                else {
                                    zapis.print(0 + ",");
                                    lastID = 0;
                                }
                            }
                            else zapis.print(3 + ",");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(e);
                        }
                    }
                    zapis.print(";");
                }
                zapis.close();
            }
        }
    }
}
