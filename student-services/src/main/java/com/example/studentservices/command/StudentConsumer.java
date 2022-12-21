package com.example.studentservices.command;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentConsumer {
    @RabbitListener(queues = "Queue2")
    public void TeacherConsumer(byte[] bytes) throws IOException {
        List<String> grade = new ArrayList<String>();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(bais);
        while (in.available() > 0) {
            String element = in.readUTF();
            grade.add(element);
        }
        System.out.println(grade);
    }
}
