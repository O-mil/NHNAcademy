package com.nhnacamemy.student.repository;


import com.nhnacamemy.student.Student;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MapStudentRepository implements StudentRepository {
    private Map<String , Student> studentMap = new ConcurrentHashMap<>();

    // 학생 등록
    @Override
    public void save(Student student) {
        studentMap.put(student.getId(), student);           //put(key, value)
    }

    //학생 수정
    @Override
    public void update(Student student) {
        studentMap.put(student.getId(), student);
    }

    // 학생 삭제
    @Override
    public void deleteById(String id) {
        studentMap.remove(id);
    }

    // 학생 조회 (아이디로)
    @Override
    public Student getStudentById(String id) {
        return studentMap.get(id);
    }

    //학생 리스트
    @Override
    public List<Student> getStudents() {
        return studentMap.values().stream().collect(Collectors.toList());
    }

    // 학생 아이디 존재 여부
    @Override
    public boolean existById(String id) {
        return studentMap.containsKey(id);
    }
}

