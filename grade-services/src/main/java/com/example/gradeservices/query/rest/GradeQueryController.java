package com.example.gradeservices.query.rest;

import com.example.gradeservices.core.data.GradeRepository;
import com.example.gradeservices.query.FindGradeQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeQueryController {
    private final GradeRepository gradeRepository;

    @Autowired
    QueryGateway queryGateway;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public GradeQueryController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/getGradesForStudent")
    List<String> findGradeByStudentId(@RequestBody GradeRestModel model) throws IOException {

        List<String> grade = new ArrayList<String>();
        //List<GradeRestModel> grades = null;
        
        for (int i=0; i<model.getStudent().length; i++){
//            grade.add(model.getStudent()[i]);
//            System.out.println(grade);
            String grades = gradeRepository.findByStudentId(model.getStudent()[i]);
            //System.out.println(grades);
            grade.add(grades);
            //System.out.println("grade"+grade);
            //System.out.println("grade"+ grade.get(0));
            
        }
        //List<GradeRestModel> grades = gradeRepository.findByStudentId(model.getStudent());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);


        for (String element : grade) {
            out.writeUTF(element);
        }
        byte[] bytes = baos.toByteArray();
        rabbitTemplate.convertAndSend("gradeExchange","grade", bytes);
        return grade;
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/getGrades")
    public List<GradeRestModel> getGrades(@RequestBody GradeRestModel model){
        List<GradeRestModel> grades = gradeRepository.findBySubjectName(model.getSubjectName());
        return grades;
    }
}
