/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author phank
 */
public class viewSystem {

    public void mainMenu() {
        System.out.println("******************* Train Booking management system ********************");
        String con[] = {"Train", "Passenger", "Booking"};
        for (int i = 0; i < con.length; i++) {
            System.out.println((i + 1) + ". " + con[i]);
        }
        System.out.println("4. Exist");
    }

    public static void menuTrain() {
        String con[] = {
            "Load data", "Add input to the tree", "Display data by pre-order traversal",
            "Save data by in-order traversal", "Search train by tcode", "Delete by copying", "Delete by merging",
            "Simply balancing", "Display data by breath-first traversal",
            "Count the number of trains", "Search train by name", "Search booked by tcode"};
        System.out.println("************ Train management ***************");
        for (int i = 0; i < con.length; i++) {
            System.out.println((i + 1) + ". " + con[i]);
        }
        System.out.println("0. Exist");

    }

    public static void menuPassenger() {
        String con[] = {"Load data", "Add inout to the tree", "Display data by post-order traversal",
            "save data by pre-order traversal", "Search passenger by pcode", "Delete by pcode",
            "Search passenger by name", "Search trains by pcode"};
        System.out.println("************* Passenger management ****************");
        for(int i= 0 ; i<con.length;i++){
            System.out.println((i+1)+". "+con[i]);
        }
        System.out.println("0. exist");

    }

    public static void menuBooking() {
        String con[] = {"Loas data", "Book train", "Display data", "save data", "Sort by tcode and pcode",
            "Pay booking by tcode and pcode"};
        System.out.println("*********** Booking management *****************");
        for(int i = 0 ; i< con.length;i++){
            System.out.println((i+1)+". "+con[i]);
        }
        System.out.println("0. exits");

    }
    public static void loadTrainData(int i){
        String con[]={"tcode","name","dstation","astation","dtime","atime","seat","booked"};
        System.out.print("Enter: "+con[i]+": ");
    }
}
