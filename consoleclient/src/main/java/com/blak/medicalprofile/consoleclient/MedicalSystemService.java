package com.blak.medicalprofile.consoleclient;

import com.blak.medicalprofile.dao.Doctor;
import com.blak.medicalprofile.dao.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MedicalSystemService {
    private RestTemplate restTemplate;

    public MedicalSystemService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Timetable getMockedCalendar() {
        return restTemplate.getForEntity("http://localhost:8082/mock/calendar", Timetable.class).getBody();
    }

    public Timetable getCalendarForDoctor(int doctorId) {
        return restTemplate.getForEntity("http://localhost:8082/calendar/doctors/" + doctorId, Timetable.class).getBody();
    }

    public List<Doctor> getMockedDoctors() {
        return restTemplate.exchange("http://localhost:8082/mock/doctors", HttpMethod.GET, null, new ParameterizedTypeReference<List<Doctor>>() {
        }).getBody();
    }

}
