package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {

    private static final CsvScores INSTANCE = new CsvScores();
    private List<Score> scores = new ArrayList<>();

    private CsvScores(){}

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Scores getInstance() {
        return INSTANCE;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/kimhwajeong/Downloads/springframework-project1/src/main/resources/data/score.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int seq = Integer.parseInt(fields[0].trim());
                int scoreValue = Integer.parseInt(fields[1].trim());
                Score score = new Score(seq, scoreValue);
                scores.add(score);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Score> findAll() {
        return scores;
    }
}
