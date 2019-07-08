package com.blak.medicalprofile.consoleclient;

import com.blak.medicalprofile.dao.Reservation;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
@Component
public class Menu {
    public static final String WELCOME_MESSAGE = "Welcome in medical system." +
            "\n Press [1] to show enable specialist's in our system" +
            "\n Press [2] to check for free term" +
            "\n Press [3] to reserve a term" +
            "\n Press [4] to modify a term" +
            "\n Press [0] to exit program..." +
            "\n Waiting...";

    public Menu() throws InterruptedException {
        this.executeProgram();
    }

    public void executeProgram() throws InterruptedException {
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
                    MedicalSystemService medicalSystemService = new MedicalSystemService(new RestTemplate());
                    Reservation reservation = medicalSystemService.getCalendar();
                    reservation.printCalendar();
                    break;
//        case 3:
//        System.out.println("Enter password to entry ranking:");
//        String password = sc.nextLine();
//        if (examService.veryfyUser(password)) {
//            System.out.println("Access granted!");
//        } else {
//            System.out.println("Access denied!");
//            break;
//        }
//        System.out.println("\n Press [1] to show ranking." +
//                "\n Press [2] to show user." +
//                "\n Press [3] to modyfy note for user." +
//                "\n Press [0] to back to main menu...");
//        switch (sc.nextInt()) {
//            case 1:
//                System.out.println(examRanking.showRanking());
//                break;
//            case 2:
//                sc.nextLine();
//                System.out.println("Enter first and last name of user");
//                String userToSearch = sc.nextLine();
//                System.out.println("User was found. His/Her score is: \n" + examRanking.searchResultForUser(userToSearch));
//                break;
//            case 3:
//                sc.nextLine();
//                System.out.println("Enter user name");
//                String userName = sc.nextLine();
//                if (!examRanking.getRanking().containsKey(userName)) {
//                    System.out.println("No user in history!");
//                } else {
//                    System.out.println("Enter new note for user");
//                    Double newNote = sc.nextDouble();
//                    examRanking.getRanking().put(userName, newNote);
//                    System.out.println("Updated...");
//                }
//                break;
//            case 0:
//                break;
//        }
//        break;
//    }
            }
        }
    }
}
