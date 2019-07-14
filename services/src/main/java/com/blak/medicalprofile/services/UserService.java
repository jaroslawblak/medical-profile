package com.blak.medicalprofile.services;

import com.blak.medicalprofile.dao.Patient;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public Patient generateUserKey(Patient patient) {
        return (Patient) patient.userKey(String.valueOf(patient.hashCode() & 0xfffffff).substring(0, 4));
    }
}
