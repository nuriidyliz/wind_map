import java.util.ArrayList;

/*
    x,y ve yonu olan nokta objesi
 */
class Pointt {
    int x, y, temp, direction, index;

    ArrayList<NeighboringCity> neighboringCities = new ArrayList<>();
    Pointt(){

    }
    Pointt(int x, int y, int temp, int index) {
        this.x = x;
        this.y = y;
        this.temp = temp;
        this.index = index;
    }
}

class NeighboringCity extends Pointt{
    int neighbor_id;
    NeighboringCity(int neighbor_id, int index, int temp){
        this.temp = temp;
        this.index = index;
        this.neighbor_id = neighbor_id;
    }
}
