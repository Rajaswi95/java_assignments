package com.test;

import java.util.Arrays;
import java.util.Scanner;

import static com.test.DashboardDisplay.*;


public class DashboardOpt {
    public static String doctorOption;

    public void waitingHall() throws InterruptedException {
        System.out.println("Enter names of patients for Consultation (enter comma separated values, for multiple patients) : <patient -1>, <patient-2>)");
        Scanner sc = new Scanner(System.in);
        String patient_names = sc.nextLine();
        String[] patients = patient_names.split(",");
        patientLists.addAll(Arrays.asList(patients));
        if (waitingList.size() <= waitingQueSize) {
            for (int i = 0; i < patientLists.size(); i++) {
                if (waitingList.size() < waitingQueSize && i < waitingQueSize) {
                    waitingList.add(patientLists.get(i));
                    System.out.println("patient " + patientLists.get(i) + " is sent to waiting hall");
                } else {
                    System.out.println("waiting halls are full, patient " + patientLists.get(i) + " kindly wait for some time");
                }
            }
        } else {
            System.out.println("waiting halls are full, please wait for some time");
        }
        if (waitingList != null) {
            patientLists.removeAll(waitingList);
        }
    }

    public void consultation() {
        if (!waitingList.isEmpty()) {
                if (!doctorList.isEmpty() || doctorList.iterator().next() == "null") {
                    for (int j = 0; j < doctorQueSize && j < waitingQueSize; j++) {
                        if (doctorList.get(j) == "null") {
                            doctorList.remove(j);
                            if (waitingList.iterator().hasNext()) {
                                doctorList.add(j, waitingList.iterator().next());
                                waitingList.remove(doctorList.get(j));
                                System.out.println("WaitingHall patient " + doctorList.get(j) + " is sent to consultation room " + (j + 1));
                                doctorList.get(j);
                            }
                        }
                        if (waitingList.size() < doctorQueSize) {
                            for (int k = 0; k < patientLists.size() && k < doctorQueSize; k++) {
                                waitingList.add(patientLists.get(k));
                            }
                            patientLists.removeAll(waitingList);
                        }
                    }
                }else if(doctorList.size() > doctorQueSize){
                    System.out.println("doctors are full");
                }
        }else {
            System.out.println("please create consultation with doctor");
        }

    }

    public void doctorDecision() {
            if (!doctorList.isEmpty() && doctorList.iterator().next() != "null") {
                if (bedList.iterator().next() == "null" || !bedList.isEmpty() ) {
                    System.out.println("Enter a value in the format <doctor - option>:");
                    Scanner ss = new Scanner(System.in);
                    String doctorOption = ss.nextLine();
                    if (doctorOption.matches("[1-9]-[1-2]+")) {
                    String[] optionList = doctorOption.split("-");
                    int doctor = Integer.parseInt(optionList[0]);
                    int option = Integer.parseInt(optionList[1]);

                        for (int i = 0; i < bedQueSize && i < doctorList.size(); i++) {
                                if (doctor == i + 1 && option == 1) {
                                    if(bedList.get(i) == "null" )
                                    bedList.remove(i);
                                    bedList.add(i, doctorList.get(i));
                                    System.out.println("patient " + doctorList.get(i) + ", please admit in the hospital");
                                    doctorList.remove(i);
                                    doctorList.add(i, "null");
                                } else if (doctor == i + 1 && option == 2) {
                                    quarantine.add(doctorList.get(i));
                                    System.out.println("patient " + doctorList.get(i) + ", please go for a home quarantine");
                                    doctorList.remove(i);
                                    doctorList.add(i, "null");
                                }
                        }consultation();


                    }else {
                        System.out.println("please enter correct format");
                    }
                    } else  {
                        System.out.println("beds are full,please go for home quarantine");
                        doctorList.iterator().remove();
                        doctorList.add("null");
                    }
            } else  {
                System.out.println("please create consultation with doctor.");
            }
        }

    public void dischargePatient() throws InterruptedException {
        if (!bedList.isEmpty() && bedList.iterator().next() != "null") {
            if(bed_no < bedList.size() && bedList.iterator().next() != "null") {
                for (int i = 0; i <= bedList.size(); i++) {
                    if (bed_no == i) {
                        System.out.println("discharge patient in bed number" + (i) + ": " + bedList.get(i - 1));
                        bedList.remove(i - 1);
                        bedList.add(i-1 , "null");
                    }
                }
            } else  {
                System.out.println("bed is empty");
            }
        } else {
            System.out.println("bed is empty or create consultation with doctor");
        }
    }
}


