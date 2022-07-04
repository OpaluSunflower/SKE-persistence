package com.example.fotowoltaika.domain;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
//TODO Przepisać settingsy do usera []
@RestController
public class SettingsController {
    @Autowired
    private UserJPARepository userJPARepository;
    @Autowired
    private SettingsJPARepository settingsJPARepository;

   // private final EmployeeModelAssembler assembler;

    interface isAutoCalculated
    {
        Boolean autoCalculated(String autocalculated);
    }
    @PostMapping("/users/{id}/settings")
    public EntityModel<Settings> addSettings(@PathVariable Long id, @RequestBody String requestBody )
    {
        User user = userJPARepository.findById(id).get();
        JSONObject ja = new JSONObject(requestBody);
        System.out.println(ja.getString("autoCalculated"));
        Settings settings = new Settings();
        isAutoCalculated iAC = (String autocalculated) -> Objects.equals(autocalculated, "true");
        settings.setAutoCalculated(iAC.autoCalculated(ja.getString("autoCalculated")));
        settings.setUser(user);
        settingsJPARepository.save(settings);
        System.out.println(settings.getAutoCalculated());
        user.setSettings(settings);
        userJPARepository.save(user);
        System.out.println(user.getSettings().getId());

        return EntityModel.of(user.getSettings(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SettingsRESTController.class).one(id)).withSelfRel(),
                Link.of("/settingses/").withRel("settings"),
                Link.of("/users/"+id).withRel("user"));
    }
    @PutMapping("/users/{id}/settings")
    public EntityModel<Settings> updateSettings(@PathVariable Long id, @RequestBody String requestBody)
    {
        User user = userJPARepository.findById(id).get();
        JSONObject ja = new JSONObject(requestBody);
        isAutoCalculated iAC = (String autocalculated) -> (Objects.equals(autocalculated, "true"));
        System.out.println(iAC.autoCalculated(ja.getString("autoCalculated"))+" ___autoCalc");
        user.getSettings().setAutoCalculated(iAC.autoCalculated(ja.getString("autoCalculated")));
        //user.getSettings().setUser(user);
        settingsJPARepository.save(user.getSettings());
        //System.out.println(settings.getAutoCalculated());
        //user.setSettings(settings);
        userJPARepository.save(user);
        System.out.println(user.getSettings().getId());

        return EntityModel.of(user.getSettings(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SettingsRESTController.class).one(id)).withSelfRel(),
                Link.of("/settingses/").withRel("settings"),
                Link.of("/users/"+id).withRel("user"));
    }

}
