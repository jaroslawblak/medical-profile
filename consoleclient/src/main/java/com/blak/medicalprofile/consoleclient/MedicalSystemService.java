package com.blak.medicalprofile.consoleclient;

import com.blak.medicalprofile.dao.Doctor;
import com.blak.medicalprofile.dao.Reservation;
import com.blak.medicalprofile.services.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.List;

@Service
public class MedicalSystemService {
    private RestTemplate restTemplate;

    static {
        MockService mockService = new MockService();
        mockService.mockBusyTermsForDoctors();
    }

    public MedicalSystemService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
