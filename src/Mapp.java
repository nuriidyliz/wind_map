/*
    x, y ve yonu .txt'den okuyarak harita(ArrayList) olusturma
        ornek;
    60	380	1
    100	20	2
        0-7 arasi 8 farkl, yon
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Mapp{
    Scanner scan;
    ArrayList<Pointt> cityList = new ArrayList<>();

    Mapp(){
        File dataFile = new File("data_wind.txt");

        if (dataFile.isFile()) {
            try {
                FileReader fr = new FileReader("data_wind.txt");
                BufferedReader br = new BufferedReader(fr);

                String cityStr = null;
                do {
                    cityStr = br.readLine();
                    if (cityStr != null) {
                        scan = new Scanner(cityStr);
                        cityList.add(new Pointt(scan.nextInt(), scan.nextInt(), scan.nextInt()));
                    }
                } while (cityStr != null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
