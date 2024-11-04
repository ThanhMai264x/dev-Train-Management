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
import Utility.validator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author phank
 */
public class TrainManager {

    Node root;

    BSTree trainTree;
    private LinkedList bookingList;

    public TrainManager() {
        this.trainTree = new BSTree();
        this.bookingList = bookingList;
    }

    public BSTree getTrainTree() {
        return trainTree;
    }

    // 1.1 load data from file trains.txt
    public void loadFromFile(String filepath) throws FileNotFoundException, IOException, Exception {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line = br.readLine();
        String[] parts;
        Train train = null;

        while (line != null) {
            line = line.trim();

            // Nếu dòng trống, đọc dòng tiếp theo
            if (line.isEmpty()) {
                line = br.readLine();
                continue;
            }

            parts = line.split("\\|\\s*");
            if (parts.length < 8) {
                // Đóng BufferedReader trước khi ném Exception để giải phóng tài nguyên
                br.close();
                throw new Exception("Invalid line: " + line);
            }

            try {
                // Lấy thông tin và chuyển đổi kiểu dữ liệu
                String tcode = parts[0].trim();
                String name = parts[1].trim();
                String dstation = parts[2].trim();
                String astation = parts[3].trim();
                double dtime = Double.parseDouble(parts[4].trim());
                int seat = Integer.parseInt(parts[5].trim());
                int booked = Integer.parseInt(parts[6].trim());
                double atime = Double.parseDouble(parts[7].trim());

                // Kiểm tra các trường bắt buộc
                if (tcode.isEmpty() || name.isEmpty() || dstation.isEmpty() || astation.isEmpty()) {
                    throw new Exception("Invalid line (empty values): " + line);
                }

                // Tạo đối tượng Train và thêm vào cây
                train = new Train(tcode, name, dstation, astation, dtime, atime, seat, booked);
                trainTree.insert(train);

            } catch (NumberFormatException e) {
                // Đóng BufferedReader trước khi ném Exception để giải phóng tài nguyên
                br.close();
                throw new Exception("Invalid number format in line: " + line, e);
            }

            line = br.readLine();
        }

        br.close();
    }

    //1.4 save to file by in order
    public void inOrderSave(Node node, BufferedWriter bw) throws IOException {
        if (node == null) {
            return;  // Base case: empty node, nothing to save
        }

        // Traverse the left subtree
        inOrderSave(node.left, bw);

        // Process the current node (write train info to file)
        Train train = (Train) node.info;
        bw.write(train.getTcode() + "| " + train.getName() + "| " + train.getDstation()
                + "| " + train.getAstation() + "| " + train.getDtime() + "| " + train.getSeat()
                + "| " + train.getBooked() + "| " + train.getAtime());
        bw.newLine();

        // Traverse the right subtree
        inOrderSave(node.right, bw);
    }

    //1.5 search by tcode
    public void searchbytcode(String tcode) {
        searchbyTcode(root, tcode);
    }

    private Train searchbyTcode(Node root, String tcode) {
        if (root == null) {
            return null;
        }
        Train train = (Train) root.getInfo();
        if (train.getTcode().equals(tcode)) {
            return train;
        }
        if (tcode.compareTo(train.getTcode()) < 0) {
            return searchbyTcode(root.left, tcode);
        } else {
            return searchbyTcode(root.right, tcode);
        }

    }

    //1.6 Delete by tcode by copying
    public void deleteTrainByCopying(String tcode) {
        Node trainNode = trainTree.search(tcode);
        if (trainNode != null) {
            trainTree.deleteNodeByCopy(trainNode);
        }
    }

    //1.7 Delete by pcode by merging
    public void deleteTrainByMerging(String pcode) {
        Node trainNode = trainTree.search(pcode);
        if (trainNode != null) {
            trainTree.deleteNodeByMerging(trainNode);
        }
    }

    //1.8 simple balance
    // 1.10 count number of trains
    public int countTrains() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // 1.11 search train by name
    public void searchByName(String name) {
        searchByName(root, name);
    }

    private void searchByName(Node node, String name) {
        if (node == null) {
            return; // Base case: no train found
        }
        Train t = (Train) node.getInfo();
        // Check if the current node's train name matches
        if (t.getName().equalsIgnoreCase(name)) {
            System.out.println(t.toString());
        }

        // Recursively search in the left and right subtrees
        searchByName(node.left, name);
        searchByName(node.right, name);
    }

    // 1.12 search booked by tcode
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

    //funciton for 3.2
    public Train getTrainByCode(String tcode) {
        Node node = trainTree.search(tcode); // Tìm kiếm node theo mã phòng
        if (node != null) {
            return (Train) node.getInfo();
        }
        return null; // Trả về null nếu không tìm thấy
    }
}
