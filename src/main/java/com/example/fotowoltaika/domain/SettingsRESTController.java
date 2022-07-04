package com.example.fotowoltaika.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingsRESTController {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private SettingsJPARepository settingsJPARepository;
    @GetMapping("/users/{id}/settings")
    EntityModel<Settings> one(@PathVariable Long id) {

        User user = userJPARepository.findById(id).get(); //
                //.orElseThrow(() -> new EmployeeNotFoundException(id));
        //Link link = new Link();
        //link.of("/settinges/");
        System.out.println(user.getSettings().getId()+"//Id");
        return EntityModel.of(user.getSettings(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SettingsRESTController.class).one(id)).withSelfRel(),
                Link.of("/settingses/").withRel("settings"),
                Link.of("/users/"+id).withRel("user"));
                //WebMvcLinkBuilder.linkTo()//(WebMvcLinkBuilder.methodOn(SettingsRESTController.class).all()).withRel("employees"));
    }
}
