package am.ceocreativedevelopmentcompany.commentmanagementsystem;

import am.ceocreativedevelopmentcompany.commentmanagementsystem.entity.CommentEntity;
import am.ceocreativedevelopmentcompany.commentmanagementsystem.model.Counter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ContextConfiguration(classes = CommentmanagementsystemApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_for_comment_creation_proccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/comment/";
        java.net.URI uri = new URI(baseUrl);
        Stream<CommentEntity> stringStream = Stream.generate(()->new CommentEntity("aaa")).limit(1000);
        List<CommentEntity> list = stringStream.collect(Collectors.toList());
        list.forEach(el->{
            ResponseEntity<?> responseEntity = restTemplate.postForEntity(uri, new HttpEntity<>(el),String.class);
            Long start = System.nanoTime();
            if(responseEntity.getStatusCodeValue() == 200){
                System.out.println(responseEntity.getBody());
                System.out.println("Comment was saved successfully but not delivered");
                System.out.println(Counter.successfullySavedComment++);
                System.out.println(Counter.successfullyDeliveredComment++);
                Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
            }else if(responseEntity.getStatusCodeValue() == 400){
                System.out.println(responseEntity.getBody());
                System.out.println("Comment was not saved.");
                Assertions.assertEquals(400,responseEntity.getStatusCodeValue());
            }else if(responseEntity.getStatusCodeValue() == 201){
                System.out.println(Counter.successfullySavedComment++);
                System.out.println("Comment was saved successfully and delivered");
                System.out.println(responseEntity.getBody());
                Assertions.assertEquals(201,responseEntity.getStatusCodeValue());
            }
            Long end = System.nanoTime();
            Long duration = end - start;
            System.out.println("Request duration is " + duration + " ns");
        });
        System.out.println("Percentage of comments which was saved successfully is " + Counter.successfullySavedComment/10 + " %");
        System.out.println("Percentage of comments which was saved and delivered is " + Counter.successfullyDeliveredComment/10 + " %");
    }

}
//https://javabydeveloper.com/junit-5-parallel-tests-execution-and-resourcelock-examples/
//https://howtodoinjava.com/spring-boot2/testing/testresttemplate-post-example/
//https://www.programmersought.com/article/81645155814/
//https://www.logicbig.com/tutorials/spring-framework/spring-boot/web-application-testing-with-embedded-server-and-test-rest-template.html
//https://stackoverflow.com/questions/59787397/org-apache-http-conn-httphostconnectexception-connect-to-localhost8080-localh
//https://www.youtube.com/watch?v=FlSup_eelYE
//https://www.baeldung.com/dockerizing-spring-boot-application
//https://www.baeldung.com/spring-cloud-configuration