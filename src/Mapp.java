import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Mapp{
    Scanner scan;
    static int a=0;
    public ArrayList<Pointt> cityList = new ArrayList<>();

    Mapp(){

        File dataFile = new File("data_temp.txt");

        if (dataFile.isFile()) {
            int index=0;
            try {
                FileReader fr = new FileReader("data_temp.txt");
                BufferedReader br = new BufferedReader(fr);

                String cityStr = null;
                do {
                    cityStr = br.readLine();
                    if (cityStr != null) {
                        scan = new Scanner(cityStr);
                        cityList.add(new Pointt(scan.nextInt(), scan.nextInt(), scan.nextInt(), index));

                        index++;
                    }
                } while (cityStr != null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        getNeighbors();
        setDirection();
    }

    public void rewind(){
        int rand = (int)(Math.random()*10);
        int col_row = (int)(Math.random()*2);
        int change;
        if(a%2==0){
            change =-5;
        }
        else{
            change = 5;
        }
        System.out.println("rand "+rand + " colrow " + col_row);
        if(col_row==1){
            for(int i = 0; i < 10; i++){
                cityList.get(rand).temp +=change;
                rand+=10;
            }
        }
        else{
            for(int i = 0; i < 10; i++){
                cityList.get(rand).temp +=change;
                rand++;
            }
        }
        a++;
    }
    public void getNeighbors(){
        Pointt pt;
        for(int i = 0;i < cityList.size(); i++) {
            pt = cityList.get(i);
            int sag = i + 1;
            int sol = i - 1;
            int yukari = i - 10;
            int asagi = i + 10;
            int sag_asagi = i + 11;
            int sol_asagi = i + 9;
            int sag_yukari = i - 9;
            int sol_yukari = i - 11;
            if (isInRange(sag, cityList.size())&&(i%10)!=9) {
                NeighboringCity east = new NeighboringCity(1, i + 1, cityList.get(sag).temp);
                east.temp = cityList.get(sag).temp;
                pt.neighboringCities.add(east);

            }
            if (isInRange(sol, cityList.size())&&(i%10)!=0) {
                NeighboringCity west = new NeighboringCity(5, i - 1, cityList.get(sol).temp);
                west.temp = cityList.get(sol).temp;
                pt.neighboringCities.add(west);
            }
            if (isInRange(yukari, cityList.size())) {
                NeighboringCity north = new NeighboringCity(7, i - 10, cityList.get(yukari).temp);
                north.temp = cityList.get(yukari).temp;
                pt.neighboringCities.add(north);
            }
            if (isInRange(asagi, cityList.size())) {
                NeighboringCity south = new NeighboringCity(3, i + 10, cityList.get(asagi).temp);
                south.temp = cityList.get(asagi).temp;
                pt.neighboringCities.add(south);
            }
            if (isInRange(sag_asagi, cityList.size())&&(i%10)!=9) {
                NeighboringCity south_east = new NeighboringCity(2, i + 11, cityList.get(sag_asagi).temp);
                south_east.temp = cityList.get(sag_asagi).temp;
                pt.neighboringCities.add(south_east);
            }
            if (isInRange(sol_asagi, cityList.size())&&(i%10)!=0) {
                NeighboringCity south_west = new NeighboringCity(4, i + 9, cityList.get(sol_asagi).temp);
                south_west.temp = cityList.get(sol_asagi).temp;
                pt.neighboringCities.add(south_west);
            }
            if (isInRange(sag_yukari, cityList.size())&&(i%10)!=9) {
                NeighboringCity north_east = new NeighboringCity(8, i - 9, cityList.get(sag_yukari).temp);
                north_east.temp = cityList.get(sag_yukari).temp;
                pt.neighboringCities.add(north_east);
            }
            if (isInRange(sol_yukari, cityList.size())&&(i%10)!=0) {
                NeighboringCity north_west = new NeighboringCity(6, i - 11, cityList.get(sol_yukari).temp);
                north_west.temp = cityList.get(sol_yukari).temp;
                pt.neighboringCities.add(north_west);
            }

        }

        for(int i = 0;i < cityList.size(); i++) {
            pt = cityList.get(i);
            System.out.print(" Sehir: " + i+ " Komşuları: ");
            for (int h = 0; h < pt.neighboringCities.size(); h++) {
                System.out.print(pt.neighboringCities.get(h).index + " ");
            }
            System.out.println(" ");
        }
    }
    void setNeighborsTemp(){
        Pointt pt;
        for(int i = 0; i < cityList.size(); i++){
            pt = cityList.get(i);
            for(int h = 0; h < pt.neighboringCities.size(); h++){
                pt.neighboringCities.get(h).temp = cityList.get(pt.neighboringCities.get(h).index).temp;
            }
        }
    }

    public boolean isInRange(int point, int range) {
        return point >= 0 && point < range;
    }

    void setDirection(){  //ruzgar soguktan sıcaga eser
        NeighboringCity hottest = new NeighboringCity(0,-1,0);
        Pointt pt;
        for(int i = 0; i < cityList.size(); i++){
            pt = cityList.get(i);
            for (int h = 0; h < pt.neighboringCities.size(); h++){
                if(hottest.temp < pt.neighboringCities.get(h).temp){
                    hottest = pt.neighboringCities.get(h);
                }
            }

            if(hottest.temp <= cityList.get(i).temp){
                cityList.get(i).direction = 0;
            }
            else{
                cityList.get(i).direction = hottest.neighbor_id;
            }

            hottest.temp = 0;
        }
    }
}
