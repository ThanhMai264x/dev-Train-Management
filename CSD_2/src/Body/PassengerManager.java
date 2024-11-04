/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Body;

import BSTree.BSTree;
import BSTree.Node;
import LinkedList.LL_Node;
import LinkedList.LinkedList;
import Model.Booking;
import Model.Passenger;
import Model.Train;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 *
 * @author phank
 */
public class PassengerManager {

    BSTree passengerTree;
    private LinkedList bookingList;
    Node root;

    public PassengerManager() {
        this.bookingList = bookingList;
    }

    public BSTree getPassengerTree() {
        return passengerTree;

    }

    // 2.1 load from file
    public void loadFromFile(String filepath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line = br.readLine();
        String[] parts;
        Passenger passenger = null;

        while (line != null) {
            line = line.trim();
            if (line.isEmpty()) {
                line = br.readLine();
                continue;
            }
            parts = line.split("\\|\\s*");
            if (parts.length < 8) {
                System.out.println("Invalid line: " + line);
                line = br.readLine();
                continue;
            }

            try {
                // Lấy thông tin và chuyển đổi kiểu dữ liệu
                String pcode = parts[0].trim();
                String name = parts[1].trim();
                String phone = parts[2].trim();
                // Kiểm tra các trường bắt buộc
                if (pcode.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                    System.out.println("Invalid line (empty values): " + line);
                    line = br.readLine();
                    continue;
                }

                // Tạo đối tượng khach và thêm vào tree
                passenger = new Passenger(pcode, name, phone);
                passengerTree.insert(passenger);

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format in line: " + line);
            }

            line = br.readLine();
        }

        br.close();
        System.out.println("Load successfully from " + filepath);
    }

    //2.4 Save passenger tree to file by pre-order traversal
    public void preOrderSave(Node node, BufferedWriter bw) throws IOException {
        if (node == null) {
            return;
        }
        Passenger passenger = (Passenger) node.info;
        bw.write(passenger.getPcode() + "| " + passenger.getName() + "| " + passenger.getPhone());
        bw.newLine();

        preOrderSave(node.left, bw);
        preOrderSave(node.right, bw);
    }

    public Passenger getPassengerByCode(String pcode) {
        Node node = passengerTree.search(pcode); // Tìm kiếm node theo mã khach
        if (node != null) {
            return (Passenger) node.getInfo(); // Ép kiểu sang Passeger
        }
        return null; // Trả về null nếu không tìm thấy
    }

    //2.5 search by pcode
     public void searchbyPcode(String pcode) {
        searchbyPcode(root, pcode);
    }

    private Passenger searchbyPcode(Node root, String pcode) {
        if (root == null) {
            return null;
        }
        Passenger passenger = (Passenger) root.getInfo();
        if (passenger.getPcode().equals(pcode)) {
            return passenger;
        }
        if (pcode.compareTo(passenger.getPcode()) < 0) {
            return searchbyPcode(root.left, pcode);
        } else {
            return searchbyPcode(root.right, pcode);
        }

    }


    //  TEST
    //2.6 delete by pcode
    public void deleteByPcode(String pcode) {
        Node passegerNode = passengerTree.search(pcode);
        if (passegerNode != null) {
            passengerTree.deleteNodeByCopy(passegerNode);
        }
    }

    // 2.7 search by name
    public void searchByName(String name) {

        Node passegerNode = passengerTree.search(name);
        if (passegerNode != null) {
            passengerTree.search(name);
        }
    }

    //2.8 search trains by pcode
    public void searchBookedByTcode(String tcode) {
        LL_Node current = bookingList.getFirst(); // Get the head of the booking list
        boolean found = false;
        System.out.println("Passengers booked for train code " + tcode + ":");

        while (current != null) {
            // Cast the info to Booking type to access its methods
            Booking bookingInfo = (Booking) current.getInfo(); // Assuming the info is of type Booking

            // Check if the booking corresponds to the specified train code
            if (bookingInfo.getBcode().equals(tcode)) {
                System.out.println("Booking Code: " + bookingInfo.getBcode()
                        + ", Passenger Code: " + bookingInfo.getPcode()
                        + ", Order Date: " + bookingInfo.getOdate());
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No bookings found for train code " + tcode);
        }
    }

}
