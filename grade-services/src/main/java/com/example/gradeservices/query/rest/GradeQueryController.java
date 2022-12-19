package com.example.gradeservices.query.rest;

import com.example.gradeservices.core.data.GradeRepository;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

    @GetMapping
    List<GradeRestModel> findGradeByStudentId(@RequestBody GradeRestModel model) throws IOException {
        List<GradeRestModel> grades = gradeRepository.findByStudentId(model.getStudentId());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        for (GradeRestModel element : grades) {
            out.writeUTF(element.getGrade());
        }
        byte[] bytes = baos.toByteArray();
        rabbitTemplate.convertAndSend("gradeExchange","grade", bytes);
        return grades;
    }

//    @GetMapping
//    public List<GradeRestModel> getGrades(){
//        FindGradeQuery findGradeQuery = new FindGradeQuery();
//        List<GradeRestModel> grade = queryGateway
//                .query(findGradeQuery, ResponseTypes.multipleInstancesOf(GradeRestModel.class)).join();
//        return grade;
//    }
}
