package com.microservice.example1.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.microservice.example1.configuration.Pages;
import com.microservice.example1.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




// Si falla el devolver la vista verificar que esta la dependecia
//spring-boot-starter-thymeleaf junton con el sufix en aplication 
// properties
@Controller
@RequestMapping("/home")
public class ControllerBasic {

    public List<Post> getPosts(){
        ArrayList<Post> post = new ArrayList<>();

        post.add(new Post(1, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","http://localhost/img/1.jpg", new Date(),"Desarrollo Web"));

        post.add(new Post(2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","http://localhost/img/1.jpg", new Date(),"Desarrollo Web Front"));

        post.add(new Post(3, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","http://localhost/img/1.jpg", new Date(),"Desarrollo Web Backend"));

        post.add(new Post(4, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s","http://localhost/img/1.jpg", new Date(),"Desarrollo Web Full Stack"));

        return post;
    }

    @GetMapping(path = {"/posts", "/"})
    public String saludar(Model model){
        model.addAttribute("posts", getPosts());
        return "index";
    }

    @GetMapping(path = "/public")
    public ModelAndView post(){
        ModelAndView modelAndView = new ModelAndView(Pages.HOME);
        modelAndView.addObject("posts",getPosts());
        return modelAndView;
    }

    @GetMapping(path = {"/post", "/post/p/{post}"})
    // Si se requiere pasar variable por PATH se utilizar PathVariable
    // @RequestParam(defaultValue = "1", name = "id", required = false)
    public ModelAndView getPostIndividual(@PathVariable(required = true, name = "post") int id){
        ModelAndView modelAndView = new ModelAndView(Pages.POST);

        List<Post> posFiltrado = this.getPosts().stream().filter((p) -> {return p.getId() == id;}).collect(Collectors.toList());

        modelAndView.addObject("post", posFiltrado.get(0));
        return modelAndView;
    }
    
}
