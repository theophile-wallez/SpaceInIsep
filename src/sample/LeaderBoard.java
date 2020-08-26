package sample;

public class LeaderBoard {
    public int Score1;
    public int Score2;
    public int Score3;
    public int Score4;
    public int Score5;

    public int getScore(int index)
    {
        return switch (index) {
            case 0 -> Score1;
            case 1 -> Score2;
            case 2 -> Score3;
            case 3 -> Score4;
            case 4 -> Score5;
            default -> 0;
        };
    }

    public void setScore(int index, int score)
    {
        switch (index) {
            case 0 -> Score1 = score;
            case 1 -> Score2 = score;
            case 2 -> Score3 = score;
            case 3 -> Score4 = score;
            case 4 -> Score5 = score;
        }
    }
}
