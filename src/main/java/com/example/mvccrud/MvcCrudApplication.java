package com.example.mvccrud;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sound.midi.Soundbank;
import java.util.List;

@SpringBootApplication
public class MvcCrudApplication {

    @Bean
    public ApplicationRunner runner(){
        return r->{
            List<String > list= List.of("Apple","Orange","Banana");
//            for (int i=0; i<list.size(); i++){
            for(var fruit: list ){
                System.out.println(fruit);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MvcCrudApplication.class, args);
    }

}
