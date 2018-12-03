package Helper;

public class HoleMappingHelper {

    public static int[] getIdOfOppositeHole(int holeID) {
        switch (holeID) {
            // player 1
            case 8: return new int[]{23, 24};
            case 9: return new int[]{22, 25};
            case 10: return new int[]{21, 26};
            case 11: return new int[]{20, 27};
            case 12: return new int[]{19, 28};
            case 13: return new int[]{18, 29};
            case 14: return new int[]{17, 30};
            case 15: return new int[]{16, 31};
            // player 2
            case 16: return new int[]{15, 0};
            case 17: return new int[]{14, 1};
            case 18: return new int[]{13, 2};
            case 19: return new int[]{12, 3};
            case 20: return new int[]{11, 4};
            case 21: return new int[]{10, 5};
            case 22: return new int[]{9, 6};
            case 23: return new int[]{8, 7};
        }
        return null;
    }
}
