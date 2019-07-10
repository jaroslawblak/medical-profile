package com.blak.medicalprofile.consoleclient;

import com.blak.medicalprofile.dao.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.List;

@Service
public class MedicalSystemService {
    private RestTemplate restTemplate;

    public MedicalSystemService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Void> getMockedTimetables(){
        return restTemplate.getForEntity("http://localhost:8082/mock/timetables", Void.class);
    }

    public List<Doctor> getMockedDoctors() {
        return restTemplate.exchange("http://localhost:8082/mock/doctors", HttpMethod.GET, null, new ParameterizedTypeReference<List<Doctor>>() {
        }).getBody();
    }

    public List<LocalTime> getFreeTermsInDay(Doctor doctor, int day){
        HttpEntity<Doctor> doctorHttpEntity = new HttpEntity<>(doctor);
        return restTemplate.exchange("http://localhost:8082/terms/free/" + day, HttpMethod.GET, doctorHttpEntity, new ParameterizedTypeReference<List<LocalTime>>() {
        }).getBody();
    }
}
