package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

        @GetMapping("hello")
        public String hello(Model model){
            model.addAttribute( "data",  "spring!!");
            return "hello";
        }

        @GetMapping("hello-mvc") // 템플릿 방식
        public String helloMvc(@RequestParam(value = "name") String name, Model model){
            model.addAttribute("name", name);
            return "hello-template";
        }

        @GetMapping("hello-string") // API 방식
        @ResponseBody // 그대로 데이타를 내려주는 방식
        public String helloString(@RequestParam("name") String name){
            return "hello" + name; // hello spring
        }

        @GetMapping("hello-api")
        @ResponseBody
        public Hello helloApi(@RequestParam("name") String name){
            Hello hello = new Hello(); // 객체로 Hello 선언
            hello.setName(name);    // JSON 방식으로 출력 key는 name | JSON방식이 기본이 되었음
            return hello;

            // @ResponseBody를 사용 - API 방식 - Default JSON
                // HTTP의 BODY에 문자 내용을 직접 반환
                // viewResolver 대신 HttpMessageConverter가 동작
                // 기본 문자처리 : StringHttpMessageConverter
                // 기본 객체처리 : MappingJackson2HttpMessageConverter
                // byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음

        }

         static class Hello{
            private String name; // 프로퍼티 접근방식? (게터 세터)

             public String getName() {
                 return name;
             }

             public void setName(String name) {
                 this.name = name;
             }
         }
         }


