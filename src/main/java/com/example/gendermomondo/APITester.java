package com.example.gendermomondo;

import com.example.gendermomondo.dtos.AgeDTO;
import com.example.gendermomondo.dtos.CombinedDTO;
import com.example.gendermomondo.dtos.GenderDTO;
import com.example.gendermomondo.dtos.NationalityDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class APITester implements CommandLineRunner {

    public CombinedDTO getCombinedInfo(String name) {
        /*WebClient client = WebClient.create();*/
        Mono<GenderDTO> gender = getGenderForName(name);
        Mono<AgeDTO> age = getAgeByName(name);
        Mono<NationalityDTO> nationality = getNationalityByName(name);

        var combinedMono = Mono.zip(gender, age, nationality).map(t-> {
            CombinedDTO newCombined = new CombinedDTO();
            // from GenderDTO
            newCombined.setName(t.getT1().getName());
            newCombined.setGender(t.getT1().getGender());
            newCombined.setProbability(t.getT1().getProbability());
            newCombined.setCount(t.getT1().getCount());

            // from AgeDTO
            newCombined.setAge(t.getT2().getAge());

            // from NationalityDTO
            newCombined.setCountry(t.getT3().getCountry().get(0).getCountry_id());
            newCombined.setCountryProbability(t.getT3().getCountry().get(0).getProbability());
            return newCombined;
        });
        return combinedMono.block();
    }

    public Mono<NationalityDTO> getNationalityByName(String name) {
        WebClient client = WebClient.create();
        Mono<NationalityDTO> nationality = client.get()
                .uri("https://api.nationalize.io?name="+name)
                .retrieve()
                .bodyToMono(NationalityDTO.class);
        return nationality;
    }


    public Mono<GenderDTO> getGenderForName(String name) {
        WebClient client = WebClient.create();
        Mono<GenderDTO> gender = client.get()
                .uri("https://api.genderize.io?name="+name)
                .retrieve()
                .bodyToMono(GenderDTO.class);
        return gender;
    }

    public Mono<AgeDTO> getAgeByName(String name) {
        WebClient client = WebClient.create();
        Mono<AgeDTO> age = client.get()
                .uri("https://api.agify.io?name="+name)
                .retrieve()
                .bodyToMono(AgeDTO.class);
        return age;
    }

    @Override
    public void run(String... args) throws Exception {
        /*System.out.println(getGenderForName("kristian").block());
        getCombinedInfo("kristian");*/
        //getCombinedInfo("kristian");

        /*CombinedDTO info = getCombinedInfo("kristian").block();*/
        /*System.out.println(info);*/
    }
}
