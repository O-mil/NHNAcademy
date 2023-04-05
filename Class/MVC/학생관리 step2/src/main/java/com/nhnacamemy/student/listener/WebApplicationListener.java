package com.nhnacamemy.student.listener;

import com.nhnacamemy.student.Gender;
import com.nhnacamemy.student.Student;
import com.nhnacamemy.student.repository.MapStudentRepository;
import com.nhnacamemy.student.repository.StudentRepository;
import org.apache.commons.math3.random.RandomDataGenerator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        for (int i = 1; i < 11; i++) {
            //... student 1~10 생성하기
            String id = "학생" + i;
            String name = "아카데미" + i;

            // 나이: random 처리: 20~30
            int age = new RandomDataGenerator().nextInt(20, 30);
            //RandomDataGenerator(): apache.coomons Mateh 사용
            //nextInt(): 0~N 까지의 값 중 랜덤처리

            Gender gender = age % 2 == 0 ? Gender.M : Gender.F;
            Student student = new Student(id, name, gender, age);
            studentRepository.save(student);
        }
        //... application scope에서 studentRepository 객체에 접근할 수 있도록 구현
        context.setAttribute("studentRepository", studentRepository);
    }


}
