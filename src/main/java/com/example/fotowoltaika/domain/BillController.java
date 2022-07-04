package com.example.fotowoltaika.domain;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BillController {
    @Autowired
    UserJPARepository userJPARepository;

    @Autowired
    BillJPARepository billJPARepository;

    @Autowired
    MonthlyMeasurementJPARepository monthlyMeasurementJPARepository;

    @Autowired
    InstalationJPARepository instalationJPARepositorys;

    @Autowired
    PredictedReturnDateJPARepository predictedReturnDateJPARepository;

    @Autowired
    DateTimeFormatter dtf;

    CollectionModel<EntityModel<Bill>> getCollection(User user,Long id)
    {
        List<EntityModel<Bill>> bills = new ArrayList<EntityModel<Bill>>();
        for (Bill bill: user.getBills()
        ) {
            bills.add(EntityModel.of(bill, Link.
                    of("http://localhost:8080/bills/"+bill.getId()),Link.of("http://localhost:8080/users/"+user.getId()).withRel("user")));
        }
        return CollectionModel.of(bills,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BillController.class).getBills(id)).withSelfRel());

    }
    Double countProducedEnergySum(Long id)
    {
        List<MonthlyMeasurement> pomiary = monthlyMeasurementJPARepository.findAll();
        Double sum = 0.0;
        Integer months = 0;
        for (MonthlyMeasurement mm:pomiary
             ) {
            if(mm.getInstalation().getUser().getId() == id)
            {
                sum += mm.getMonthlysum();
                months++;
            }
        }
        return sum;
    }
    Double countAverageCost(Long id)
    {
        List<Bill> bills = billJPARepository.findAll();
        Double energyUsed = 0.0;
        Double fullPayment = 0.0;
        for (Bill b:bills
        ) {
            if(b.getUser().getId() == id)
            {
                energyUsed += b.getUsage();
                fullPayment += b.getPayment();
            }
        }
        return fullPayment/energyUsed;
    }
    Double savings(Double producedEnergySum, Double averageCost)
    {
        return averageCost * producedEnergySum;
    }
    Double panelCost(Long id)
    {
        List<Instalation> instalations = instalationJPARepositorys.findAll();
        Double sum = 0.0;
        for (Instalation i:instalations
        ) {
            if(i.getUser().getId() == id)
            {
                sum += i.getPrice();
            }
        }
        return sum;
    }
    LocalDate getLastDate(Long id)
    {
        List<Instalation> instalations = instalationJPARepositorys.findAll();
        LocalDate lastDate = LocalDate.MIN;
        for (Instalation i:instalations
        ) {
            if(i.getUser().getId() == id)
            {
                if (i.getInstalationDate().isAfter(lastDate))
                {
                    lastDate = i.getInstalationDate();
                }
            }
        }
        return lastDate;
    }
    LocalDate predictDate(Double savings,Double panelCost, Long id)
    {
        Integer shiftMonths = (int) (panelCost/savings);
        LocalDate lastDate = getLastDate(id);
        return lastDate.plusMonths(shiftMonths);

    }
    LocalDate procedure(Long id)
    {
        Double s = savings(countProducedEnergySum(id),countAverageCost(id));
        Double pc = panelCost(id);
        return predictDate(s,pc,id);
    }
    @GetMapping("/users/{id}/bills")
    public CollectionModel<EntityModel<Bill>> getBills(@PathVariable Long id)
    {
        User user = userJPARepository.findById(id).get();
        return getCollection(user,id);
    }
    @PostMapping("/users/{id}/bills")
    public CollectionModel<EntityModel<Bill>> addBill(@PathVariable Long id, @RequestBody String requestBody)
    {
        User user = userJPARepository.findById(id).get();
        JSONObject ja = new JSONObject(requestBody);
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setDate(LocalDate.parse(ja.getString("date"),dtf));
        bill.setUsage(ja.getDouble("energy"));
        bill.setPayment(ja.getDouble("payment"));
        billJPARepository.save(bill);
        user.getBills().add(bill);
        user.setPredictedReturnDate(new PredictedReturnDate());
        user.getPredictedReturnDate().setDate(procedure(id));
        predictedReturnDateJPARepository.save(user.getPredictedReturnDate());
        userJPARepository.save(user);

        return getCollection(user,id);
    }
}
