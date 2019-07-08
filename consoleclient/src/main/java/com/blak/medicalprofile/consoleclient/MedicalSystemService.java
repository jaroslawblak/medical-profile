package com.blak.medicalprofile.consoleclient;

import com.blak.medicalprofile.dao.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MedicalSystemService {
    private RestTemplate restTemplate;

    public MedicalSystemService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Calendar getCalendar(){
        return restTemplate.getForObject("http://localhost:8080/terms", Calendar.class);
    }
}
