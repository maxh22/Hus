public class PlayingField {

    Hole[] holes = new Hole[32];

    public PlayingField(){
        for (int i = 0; i < 32; i++){
            holes[i] = new Hole();
        }
    }
}