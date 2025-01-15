package ubb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.model.Form;
import ubb.model.Question;
import ubb.model.Response;
import ubb.repository.FormRepository;
import ubb.repository.UserRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FormService {
    private List<Question> form;
    private final FormRepository formRepository;
    private final UserRepository userRepository;

    @Autowired
    public FormService(FormRepository formRepository, UserRepository userRepository){
        this.formRepository = formRepository;
        this.userRepository = userRepository;
    }

    public void makeForm(){
        File file = new File("C:\\Users\\flore\\OneDrive\\Documents\\GitHub\\PracticaBack\\Server\\src\\main\\resources\\form.txt");
        // read from file and create the form
        if (file.exists()) {
            try{
                Scanner scanner = new Scanner(new FileReader(file));
                List<Question> questions = new ArrayList<>();
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    Question question = new Question();
                    question.setQuestion(line);
                    line = scanner.nextLine();
                    Integer cnt = Integer.parseInt(line);
                    List<Response> responses = new ArrayList<>();
                    for(int i = 0; i < cnt; i++){
                        line = scanner.nextLine();
                        String[] parts = line.split("@");
                        Response response = new Response();
                        response.setScore(Integer.parseInt(parts[0]));
                        response.setResponse(parts[1]);
                        responses.add(response);
                    }
                    question.setResponses(responses);
                    questions.add(question);
                }
                form = questions;
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else {
            System.out.println("Form not available");
        }
    }

    public List<Question> getFormular(){
        return form;
    }

    public Integer getScore(List<String> responses){
        Integer score = 0;
        makeForm();

        for(int i = 0; i < responses.size(); i++){
            for(int j = 0; j < form.get(i).getResponses().size(); j++){
                if(responses.get(i).equals(form.get(i).getResponses().get(j).getResponse())){
                    score += form.get(i).getResponses().get(j).getScore();
                    break;
                }
            }
        }
        return score;
    }

    public Integer saveUserResponse(Long id, List<String> responses){
        Form form = new Form();
        form.setUserId(id);
        form.setAnswers(responses.toString());
        Integer score = getScore(responses);
        form.setScore(score);
        formRepository.save(form);
        userRepository.findById(id).ifPresent(user -> {
            if(user.getScore()==0) {
                user.setScore(score);
                userRepository.save(user);
            }else if(user.getScore()<score){
                user.setScore(score);
                userRepository.save(user);
            }
        });
        return score;
    }

}
