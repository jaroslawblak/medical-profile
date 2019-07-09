package com.blak.medicalprofile.consoleclient;

import com.blak.medicalprofile.dao.Doctor;
import com.blak.medicalprofile.dao.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Component
public class Menu {
    private RestTemplate restTemplate;
    private static final String WELCOME_MESSAGE = "Welcome in medical system." +
            "\n Press [1] to show enable specialist's in our system" +
            "\n Press [2] to check and reserve free term" +
            "\n Press [3] to modify a term" +
            "\n Press [0] to exit program..." +
            "\n Waiting...";

    public Menu(@Autowired RestTemplate restTemplate) throws InterruptedException {
        this.restTemplate = restTemplate;
        this.executeProgram();
    }

    private void executeProgram() throws InterruptedException {
        while (true) {
            System.out.println(WELCOME_MESSAGE);
            Scanner sc = new Scanner(System.in);
            int selectedOption = sc.nextInt();
            sc.nextLine();
            switch (selectedOption) {
                case 0:
                    System.exit(-1);
                    break;
                case 1:
                    System.out.println(
                            "Specialist's working in our medical system." +
                                    "\n Cardiologist " +
                                    "\n Surgeon" +
                                    "\n Dentist" +
                                    "\n Orthopaedist"
                    );
                    TimeUnit.SECONDS.sleep(5);
                    break;
                case 2:
                    MedicalSystemService medicalSystemService = new MedicalSystemService(restTemplate);
                    Reservation reservation = new Reservation();
                    List<Doctor> doctors = medicalSystemService.getMockedDoctors();
                    System.out.println("Select doctor to check and reserve a free term: \n");
                    int index = 1;
                    for (Doctor doctor : doctors) {
                        System.out.println(index + ". " + doctor.toShortString());
                        index++;
                    }
                    int chosenDoctor = sc.nextInt();
                    sc.nextLine();
                    Doctor selectedDoctor = doctors.get(chosenDoctor - 1);
                    reservation.printCalendar(selectedDoctor);
                    System.out.println("\n Choose your date");
                    int i = sc.nextInt();

                    //TODO:
//                    if(reservation.checkIfTermIsFree(selectedDoctor, LocalDate.now().withDayOfMonth(i))){
//                        System.out.println("No available terms in this day");
//                    }
                    sc.nextLine();

                    break;
                //TODO:
                case 3:
                    break;
                default:
                    break;
            }
        }
    }
}
