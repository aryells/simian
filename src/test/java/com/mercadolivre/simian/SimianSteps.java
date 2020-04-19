package com.mercadolivre.simian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mercadolivre.simian.domain.Human;
import org.springframework.http.ResponseEntity;

import com.mercadolivre.simian.domain.dto.HumanDTO;
import com.mercadolivre.simian.domain.dto.StatsDTO;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SimianSteps extends CucumberSteps {

    private HumanDTO humanDTO;
    private ResponseEntity<HumanDTO> responseHuman;
    private ResponseEntity<StatsDTO> responseStats;

    @Given("validate and save new simian")
    public void validate_and_save_new_simian(io.cucumber.datatable.DataTable dataTable) {
    	this.humanDTO = new HumanDTO();
        this.humanDTO.setDna(Arrays.asList(String.valueOf(dataTable.asList().get(1)).split(", ")));
    }

    @Given("dna invalid")
    public void dna_invalid() {
        boolean result = false;
        try {
            HumanDTO.getMD5Hash(new HumanDTO());
        } catch (Exception e) {
            result = true;
        }
        assertTrue(result);
    }

    @Given("exist one item in datastore")
    public void exist_one_item_in_datastore() {
        datastoreTemplate.save(Human.builder().dna(Arrays.asList("CTGAGA", "CTAGGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACAG"))
                .isSimian(true).md5("312363015241322881294918712800339134208").build());
    }

    @Given("save humans")
    public void save_humans(io.cucumber.datatable.DataTable dataTable) {
        List<Human> humans = new ArrayList<>();
        dataTable.asLists().stream().skip(1).forEach(
            human -> {
                List<String> dna = Arrays.asList(human.get(0).split(", "));
                boolean isSimian = Boolean.valueOf(human.get(1));
                String md5 = human.get(2);
                humans.add(Human.builder().dna(dna).isSimian(isSimian).md5(md5).build());
            }
        );
        datastoreTemplate.saveAll(humans);
        datastoreTemplate.findAll(Human.class);
    }
    @Given("drop humans")
    public void drop_humans() {
        datastoreTemplate.deleteAll(Human.class);
    }

    @When("the client calls \\/simian")
    public void the_client_calls_simian() {
        this.responseHuman = template.postForEntity(String.join("", host, ":", port.toString(), basePath), humanDTO, HumanDTO.class);
    }

    @When("the client calls \\/simian\\/stats")
    public void the_client_calls_simian_stats() {
        this.responseStats = template.getForEntity(String.join("", host, ":", port.toString(), "/stats"), StatsDTO.class);
    }

    @Then("the client receives response status code of {long}")
    public void the_client_receives_response_status_code_of(Long httpStatus) {
        assertEquals(httpStatus, Long.valueOf(responseHuman.getStatusCodeValue()));
    }
    
    @Then("total simians is equal to {long}")
    public void total_simians_is_equal_to(Long count) {
    	assertEquals(count, Long.valueOf(datastoreTemplate.count(Human.class)));
    }

    @Then("ratio calculator")
    public void ratio_calculator() {
        StatsDTO statsDTO = responseStats.getBody();
        long countHumans = statsDTO.getCountHumanDna();
        long countMutants = statsDTO.getCountMutantDna();
        if(countHumans == 0L) {
            assertEquals(Double.valueOf(0D), statsDTO.getRatio());
        } else {
            final BigDecimal ratio = BigDecimal.valueOf(countMutants).divide(BigDecimal.valueOf(countHumans), 2, BigDecimal.ROUND_UP);
            assertEquals(statsDTO.getRatio(), Double.valueOf(ratio.doubleValue()));
        }
    }

    @After
    public void cleanUp() {
        this.datastoreTemplate.deleteAll(Human.class);
    }
}
