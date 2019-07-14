package com.blak.medicalprofile.consoleclient;

import com.blak.medicalprofile.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Component
public class Menu {
    private RestTemplate restTemplate;
    private MedicalSystemService medicalSystemService;
    private static final String WELCOME_MESSAGE = "\n Welcome in medical system." +
            "\n Press [1] to show enable specialist's in our system" +
            "\n Press [2] to check and reserve free term" +
            "\n Press [3] to cancel your visit" +
            "\n Press [4] to check details about your visit" +
            "\n Press [0] to exit program..." +
            "\n Waiting...";

    public Menu(@Autowired RestTemplate restTemplate) throws InterruptedException {
        this.restTemplate = restTemplate;
        this.medicalSystemService = new MedicalSystemService(restTemplate);
        this.medicalSystemService.getMockedCalendar();
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
                            "\n Specialist's working in our medical system." +
                                    "\n Cardiologist " +
                                    "\n Surgeon" +
                                    "\n Dentist" +
                                    "\n Orthopaedist"
                    );
                    TimeUnit.SECONDS.sleep(5);
                    break;
                case 2:
                    List<Doctor> doctors = medicalSystemService.getMockedDoctors();
                    System.out.println("\n Select doctor to check and reserve a free term: \n");
                    int index = 1;
                    for (Doctor doctor : doctors) {
                        System.out.println(index + ". " + doctor.toString());
                        index++;
                    }
                    int chosenDoctor = sc.nextInt();
                    sc.nextLine();
                    if (doctors.stream().noneMatch(f -> f.getId() == chosenDoctor)) {
                        break;
                    }
                    Doctor selectedDoctor = doctors.get(chosenDoctor - 1);
                    Timetable doctorTimetable = medicalSystemService.getCalendarForDoctor(selectedDoctor.getId());
                    boolean flag = true;
                    int dayOfMonth;
                    do {
                        doctorTimetable.printCalendar();
                        System.out.println("\n Choose your date");
                        dayOfMonth = sc.nextInt();
                        if (!doctorTimetable.checkForFreeTerms(dayOfMonth)) {
                            System.out.println("\n No available terms in this day \n");
                        } else {
                            flag = false;
                        }
                    } while (flag);
                    System.out.println("\n Available terms in this day: \n");
                    for (LocalTime time : doctorTimetable.getAllFreeTermsInDay(dayOfMonth)) {
                        System.out.println(FontColour.ANSI_GREEN.getValue() + time.toString() + FontColour.ANSI_RESET.getValue() + "\n");
                    }
                    sc.nextLine();
                    System.out.println("\n Choose time for your visit");
                    String selectedVisitTime = sc.nextLine().split(":")[0];
                    LocalDateTime chosenVisitTerm = LocalDateTime.now()
                            .withDayOfMonth(dayOfMonth)
                            .withHour(Integer.valueOf(selectedVisitTime))
                            .withMinute(0).withSecond(0).withNano(0);
                    System.out.println("\n Please enter your personal data to reserve visit for " +
                            chosenVisitTerm.format(DateTimeFormatter.ofPattern("dd LLLL yyyy HH:mm")) + "\n");
                    System.out.println("\n Enter your first name:");
                    String firstName = sc.nextLine();
                    System.out.println("\n Enter your last name:");
                    String lastName = sc.nextLine();
                    System.out.println("\n Enter your day of birth (YYYY-MM-DD):");
                    String[] birthdayArray = sc.nextLine().split("-");
                    LocalDate birthday = LocalDate.of(
                            Integer.valueOf(birthdayArray[0]),
                            Integer.valueOf(birthdayArray[1]),
                            Integer.valueOf(birthdayArray[2]));
                    System.out.println("\n Enter your pesel number");
                    String pesel = sc.nextLine();
                    System.out.println("\n Enter your visit type:" +
                            Arrays.toString(VisitType.values()));
                    String visitType = sc.nextLine().toUpperCase();
                    User patient = new Patient().firstName(firstName).lastName(lastName).birthday(birthday).pesel(pesel);
                    patient = medicalSystemService.generateUserKey(patient);
                    System.out.println("\n Your personal key is: " + patient.getUserKey());
                    if (medicalSystemService.reserveTerm(selectedDoctor, chosenVisitTerm, patient, visitType)) {
                        System.out.println("\n The date was successfully booked at " +
                                chosenVisitTerm.format(DateTimeFormatter.ofPattern("dd LLLL yyyy HH:mm")));
                    } else {
                        System.out.println("\n Something went wrong. Try again or choose other date!");
                    }
                    TimeUnit.SECONDS.sleep(5);
                    break;
                case 3:
                    System.out.println("\n Please enter your user key to modify your visit");
                    String userKey = sc.nextLine();
                    medicalSystemService.removeVisitByUserKey(userKey);
                    System.out.println("\n Your visit was successfully canceled.");
                    break;
                case 4:
                    System.out.println("\n Please enter your user key to find your visit");
                    String userKeyChecker = sc.nextLine();
                    Visit visit = medicalSystemService.getVisit(userKeyChecker);
                    if (visit != null) {
                        System.out.println("\n Founded your visits with key: " + userKeyChecker);
                        System.out.println("\n Details..." +
                                "\n Date and time: " + FontColour.ANSI_GREEN.getValue() + visit.getDate() + " " + visit.getTime() +
                                FontColour.ANSI_RESET.getValue() + "\n Doctor: " + FontColour.ANSI_GREEN.getValue() + visit.getDoctor().toString() +
                                FontColour.ANSI_RESET.getValue() + "\n Type of visit: " + FontColour.ANSI_GREEN.getValue() + visit.getVisitType() + FontColour.ANSI_RESET.getValue());
                    }else {
                        System.out.println("Reservation with this key doesn't exists");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
