/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import View.viewSystem;
import Utility.validator;
import Body.*;
import Control.Controller;
import java.util.Scanner;

/**
 *
 * @author phank
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static viewSystem view = new viewSystem();
    private static validator vali = new validator();
    private static Controller co = new Controller();
    private static BookingManager bm = new BookingManager();
    private static PassengerManager pm = new PassengerManager();
    private static TrainManager tm = new TrainManager();

    public static void main(String[] args) throws Exception {
        runMenu();
    }

    public static void runMenu() throws Exception {
        while (true) {
            view.mainMenu();
            int choice = vali.getInt("Your's choice: ", 0, 3);
            if (choice == 0) {
                System.out.println("Thannk You!");
                break;
            }
            switch (choice) {
                //=========================================
                //=========== TRAIN MANAGEMENT ============
                //=========================================
                case 1:
                    while (true) {
                        view.menuTrain();
                        int choiceT = vali.getInt("Your's choice: ", 0, 12);
                        if (choiceT == 0) {
                            break;
                        }
                        String tcode;
                        switch (choiceT) {
                            case 1:
                                co.loadTrainFile();
                                break;
                            case 2:
                                System.out.println(">> Add input to tree: ");
                                co.addNewTrain();
                                break;
                            case 3:
                                co.displayTrainTree();
                                break;
                            case 4:
                                co.saveTrainToFile();
                                break;
                            case 5:
                                System.out.println(">> Search by tcode");
                                tcode = sc.nextLine();
                                co.searchTrainByTcode(tcode);
                                break;
                            case 6:
                                tcode = sc.nextLine();
                                co.deleteByCopy(tcode);
                                break;
                            case 7:
                                tcode = sc.nextLine();
                                co.deleteByMerge(tcode);
                                break;
                            case 8:
                                break;

                            case 9:
                                break;
                            case 10:
                                co.countTrain();
                                break;
                            case 11:
                                String name = sc.nextLine();
                                co.searhByName(name);
                                break;
                            case 12:
                                tcode = sc.nextLine();
                                co.searchBookByTcode(tcode);
                                break;

                        }
                    }
                    break;
                //======================================================
                //=============== Passenger management =================
                // =====================================================
                case 2:
                    while (true) {
                        view.menuPassenger();
                        int choiceP = vali.getInt("your's choice: ", 0, 8);
                        if (choiceP == 0) {
                            break;
                        }
                        String pcode;
                        switch (choiceP) {
                            case 1:
                                co.loadPassengerFile();
                                break;
                            case 2:
                                co.addNewPassenger();
                                break;
                            case 3:
                                co.displayPassengerTree();
                                break;
                            case 4:
                                co.savePassengerFile();
                                break;
                            case 5:
                                pcode = sc.nextLine();
                                co.searchByPcode(pcode);
                                break;
                            case 6:
                                pcode = sc.nextLine();
                                co.deleteByPcode(pcode);
                                break;
                            case 7:
                                String name = sc.nextLine();
                                co.searchByName(name);
                                break;
                            case 8:
                                pcode = sc.nextLine();
                                co.searchTrainByPcode(pcode);
                                break;

                        }
                    }
                    break;
                //===========================================================
                //==================== Booking management ===================
                case 3:
                    while (true) {
                        view.menuBooking();
                        int choiceB = vali.getInt("Your's choice: ", 0, 6);
                        if (choiceB == 0) {
                            break;
                        }
                        String tcode, pcode;
                        switch (choiceB) {
                            case 1:
                                co.loadBookingFile();
                                break;
                            case 2:
                                System.out.println("Enter tcode: ");
                                tcode = sc.nextLine();
                                System.out.println("Enter pcode: ");
                                pcode = sc.nextLine();
                                co.bookTrain(tcode, pcode);
                                break;
                            case 3:
                                co.displayAllBookigs();
                                break;
                            case 4:
                                co.saveFileBook();
                                break;
                            case 5:
                                co.sortBookings();
                                break;
                            case 6:
                                System.out.println("Enter tcode");
                                tcode = sc.nextLine();
                                System.out.println("Enter pcode: ");
                                pcode = sc.nextLine();
                                co.payBooking(tcode, pcode);
                                break;

                        }
                    }
                    break;
            }

        }
    }
}
