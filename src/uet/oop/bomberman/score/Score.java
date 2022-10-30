package uet.oop.bomberman.score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {
    public static List<Integer> highScore = new ArrayList<>();

    public static void readHighScore() {
        highScore.clear();
        try {
            FileReader fr = new FileReader("res\\highscore\\Highscore.txt");
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 5; i++) {
                String line = br.readLine();
                int score = Integer.parseInt(line);
                highScore.add(score);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeHighScore() {
        highScore.sort(Collections.reverseOrder());
        while (highScore.size() != 5) {
            int index = highScore.size() - 1;
            highScore.remove(index);
        }
        try {
            FileWriter fr = new FileWriter("res\\highscore\\Highscore.txt");
            BufferedWriter bw = new BufferedWriter(fr);
            for (int i = 0; i < 5; i++) {
                String temp = highScore.get(i).toString();
                bw.write(temp);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
