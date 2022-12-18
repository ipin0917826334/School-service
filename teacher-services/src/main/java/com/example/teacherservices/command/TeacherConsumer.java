package com.example.teacherservices.command;

import com.example.teacherservices.query.rest.TeacherRestModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherConsumer {
    @RabbitListener(queues = "Queue1")
    public void TeacherConsumer(byte[] bytes) throws IOException {
        List<String> subjectName = new ArrayList<String>();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(bais);
        while (in.available() > 0) {
            String element = in.readUTF();
            subjectName.add(element);
        }
        TeacherRestModel teacherRestModel = new TeacherRestModel();
        teacherRestModel.setSubjects(subjectName);
        System.out.println(subjectName);
    }
}
