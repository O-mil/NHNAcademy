package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class CsvStudents implements Students {

    private static final CsvStudents INSTANCE = new CsvStudents();
    private Map<Integer, Student> students = new HashMap<>();


    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        return INSTANCE;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/kimhwajeong/Downloads/springframework-project1/src/main/resources/data/student.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int seq = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                Student student = new Student(seq, name);
                students.put(seq, student);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Student> findAll() {
        return students.values();
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        for (Score score : scores) {
            int seq = score.getStudentSeq();
            if (students.containsKey(seq)) {
                Student student = students.get(seq);
                student.setScore(score);
            }
        }
    }
}
