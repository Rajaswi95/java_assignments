package com.test;


import java.util.ArrayList;
import java.util.Scanner;

public class DashboardDisplay {
    static int bedQueSize ;
    static int doctorQueSize;
    static int waitingQueSize ;

    static ArrayList<String> patientLists = new ArrayList<>();
    static ArrayList<String> doctorList = new ArrayList<>();
    static ArrayList<String> waitingList = new ArrayList<>();
    static ArrayList<String> bedList = new ArrayList<>();
    static ArrayList<String> quarantine = new ArrayList<>();
    public static int bed_no;


        public DashboardDisplay(int waitingQueSize, int doctorQueSize, int bedQueSize) {

            this.bedQueSize = bedQueSize;
            this.doctorQueSize = doctorQueSize;
            this.waitingQueSize = waitingQueSize;

        }

    public void display() throws InterruptedException {

        if (doctorList.isEmpty()) {
            for (int i = 0; i < doctorQueSize; i++) {
                doctorList.add(i, "null");
            }
        }
        if (bedList.isEmpty()) {
            for (int j = 0; j < bedQueSize; j++) {
                bedList.add(j, "null");
            }
        }
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Dashboard");
        System.out.println("-------");

        if (!bedList.isEmpty()) {
            for (int i = 0; i < bedQueSize; i++) {
                if (bedList.get(i) == "null") {
                    System.out.println("Bed " + (i + 1) + ": <patient name>");
                }
                if (bedList.get(i) != "null" && i <= bedQueSize) {
                    System.out.println("Bed " + (i + 1) + " : " + bedList.get(i));
                }
            }
            System.out.println(" ");
        }
        if (!doctorList.isEmpty()) {
            for (int j = 0; j < doctorQueSize; j++) {
                if (doctorList.get(j) == "null") {
                    System.out.println("Doctor " + (j + 1) + ": <patient name>");
                } else if (doctorList.get(j) != "null") {
                    System.out.println("Doctor " + (j + 1) + " : " + doctorList.get(j));

                }
            }
            System.out.println(" ");
        }
        if (waitingList == null) {
            for (int i = 0; i < waitingQueSize; i++) {
                System.out.println("WaitingHall Seat-" + (i + 1) + ": <patient name>");
            }

        } else {
            for (int k = 0; k < waitingQueSize; k++) {
                if (waitingList != null && k < waitingList.size()) {
                    System.out.println("WaitingHall Seat-" + (k + 1) + " : " + waitingList.get(k));
                } else {
                    System.out.println("WaitingHall seat-" + (k + 1) + ": <patient name>");
                }
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println("Menu");
        System.out.println("---------");
        System.out.println("1. Create Consultation -");
        System.out.println("2. Send Patient to a Consultation Room -");
        System.out.println("3. Doctor decision -");
        System.out.println("4. Discharge Patient -");
        System.out.println(" ");
        menuOption();

    }
        public void menuOption() throws InterruptedException {

            DashboardOpt dashboardObj = new DashboardOpt();
            Scanner s = new Scanner(System.in);
            System.out.print("enter menu option:");
            int enter_option = s.nextInt();

            if (enter_option == 1) {
                dashboardObj.waitingHall();
                display();
            }   else if (enter_option == 2) {
                dashboardObj.consultation();
                display();
            }   else if (enter_option == 3) {
                dashboardObj.doctorDecision();
               display();
            }   else if (enter_option == 4) {
                System.out.println("enter bed number of patient: ");
                Scanner cc = new Scanner(System.in);
                bed_no = cc.nextInt();
                if (bed_no <= 3) {

                    dashboardObj.dischargePatient();
                    display();
                }else
                {
                    System.out.println("please enter correct bed number");
                }
            }
            else if(enter_option>5) {
                System.out.println("please enter correct menu option");
                display();
            }

        }
    }




