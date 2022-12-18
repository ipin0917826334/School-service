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
//        JSONObject jsonObject = new JSONObject((Map<String, ?>) data1.get(0));
//        SubjectRestModel subjectRestModel = (SubjectRestModel) data1.get(0);
//        System.out.println(subjectRestModel.getSubjectName());
//        for(Object obj : data1) {
//
//            if(obj instanceof List) {    //if it is List type then type cast it
//                List<String> str = (List<String>) obj;
//                for(String s : str) {
//                    System.out.println(s);
//                }
//            }
////            System.out.println(element);
//        }
//        System.out.println(data1);
//        System.out.println((ArrayList) data1);
//        JSONObject jsonObject = new JSONObject((Map<String, ?>) data1.get(0));
//       List<String> arrlist = new ArrayList<String>();
//       arrlist.add(subjectRestModel);
//       System.out.println(arrlist);
    }
}
