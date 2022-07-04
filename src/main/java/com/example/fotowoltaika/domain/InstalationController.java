package com.example.fotowoltaika.domain;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InstalationController {
    @Autowired
    private UserJPARepository userJPARepository;
    @Autowired
    private InstalationJPARepository instalationJPARepository;
    @Autowired
    MeasurementJPARepository measurementJPARepositorye;
    @Autowired
    DailyMeasurementsJPARepository dailyMeasurementsJPARepository;
    @Autowired
    DateTimeFormatter dtf;
    @PostMapping("/users/{id}/instalation")
    public EntityModel<Instalation> addSettings(@PathVariable Long id, @RequestBody String requestBody )
    {
        System.out.println(requestBody);
        User user = userJPARepository.findById(id).get();
        JSONObject ja = new JSONObject(requestBody);
        Instalation instalation = new Instalation();
        instalation.setUser(user);
        instalation.setAmountOfPanels(ja.getInt("amountOfPanels"));
        instalation.setPowerOfPanel(ja.getDouble("powerOfPanel"));
        instalation.setLatitude(ja.getDouble("latitude"));
        instalation.setLongtitude(ja.getDouble("longtitude"));
        instalation.setPrice(ja.getDouble("price"));
        instalation.setInstalationDate(LocalDate.parse(ja.getString("date"),dtf));
        instalationJPARepository.save(instalation);
        //System.out.println(settings.getAutoCalculated());
        user.getInstalationList().add(instalation);
        userJPARepository.save(user);
        System.out.println(user.getSettings().getId());
        return EntityModel.of(instalation,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InstalationController.class).one(id)).withSelfRel(),
                Link.of("/instalations/").withRel("instalations"),
                Link.of("/users/"+instalation.getUser().getId()).withRel("user"));
    }
    @GetMapping("/instalations/{id}")

    public EntityModel<Instalation> one(@PathVariable Long id)
    {
        Instalation instalation = instalationJPARepository.findById(id).get();
        return EntityModel.of(instalation,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InstalationController.class).one(id)).withSelfRel(),
                Link.of("/instalations/").withRel("instalations"),
                Link.of("/users/"+instalation.getUser().getId()).withRel("user"));
    }
    @GetMapping("/users/{id}/instalation")
    CollectionModel<EntityModel<Instalation>> list(@PathVariable Long id) {

        User user = userJPARepository.findById(id).get(); //
        List<EntityModel<Instalation>> instalacje = new ArrayList<EntityModel<Instalation>>();
        for (Instalation install: user.getInstalationList()
             ) {
            instalacje.add(EntityModel.of(install,Link.
                    of("http://localhost:8080/instalations/"+install.getId())));
        }
        return CollectionModel.of(instalacje,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InstalationController.class).one(id)).withSelfRel());
                //Link.of("http://localhost:8080/instalations/").withRel("instalations"));
        //WebMvcLinkBuilder.linkTo()//(WebMvcLinkBuilder.methodOn(SettingsRESTController.class).all()).withRel("employees"));
    }
    @DeleteMapping("/instalations/{id}")
    public  CollectionModel<EntityModel<Instalation>> deleteInstallation(@PathVariable Long id){
        Instalation instalationToDelete = instalationJPARepository.findById(id).get();
        //System.out.println(instalationToDelete.getId());
        User user = instalationToDelete.getUser();

        /*for (Measurement m:instalationToDelete.getMeasurement()) {
            *//*System.out.println(m.getId());
            //instalationToDelete.getMeasurement().remove(m);
            System.out.println("2");*//*
            measurementJPARepositorye.delete(m);
            //System.out.println("3");
        }
        instalationToDelete.getMeasurement().removeAll(instalationToDelete.getMeasurement());

        for (DailyMeasurement dm:instalationToDelete.getDailyMeasurement())
        {
            //System.out.println(dm.getId());

            dailyMeasurementsJPARepository.delete(dm);
        }
        instalationToDelete.getDailyMeasurement().removeAll(instalationToDelete.getDailyMeasurement());
        //System.out.println(instalationToDelete.getMeasurement().);
        user.getInstalationList().remove(instalationToDelete);

        for (DailyMeasurement dm:instalationToDelete.getDailyMeasurement()
             ) {
            System.out.println(dm.getId());
        }*/
        user.getInstalationList().remove(instalationToDelete);
        System.out.println(user.getInstalationList());
        instalationJPARepository.delete(instalationToDelete);
        userJPARepository.save(user);
        List<EntityModel<Instalation>> instalacje = new ArrayList<EntityModel<Instalation>>();
        for (Instalation install: user.getInstalationList()
        ) {
            instalacje.add(EntityModel.of(install,Link.
                    of("http://localhost:8080/instalations/"+install.getId())));
        }
        return CollectionModel.of(instalacje,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InstalationController.class).deleteInstallation(id)).withSelfRel());
    }
}
