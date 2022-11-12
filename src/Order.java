import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    public Order(ArrayList<Cupcake> cupcakeMenu, ArrayList<Drink> drinkMenu) {
        System.out.println("Hello customer. Would you like to place an order? (Y or N)");
        Scanner input = new Scanner(System.in);
        String placeOrder = input.nextLine();

        ArrayList<Object> order = new ArrayList<Object>();

        if (placeOrder.equalsIgnoreCase("Y")) {
            order.add(LocalDate.now());
            order.add(LocalTime.now());
            int itemNumber = 0;
            System.out.println("Here is the menu.\n CUPCAKES\n");
            for (int i = 0; i < cupcakeMenu.toArray().length; i++) {
                System.out.println(i);
                cupcakeMenu.get(i).type();
                System.out.println("Price: $" + cupcakeMenu.get(i).getPrice() + "\n");
                itemNumber++;
            }
            System.out.println("DRINKS:");
            for (int i = 0; i < drinkMenu.toArray().length; i++) {
                System.out.println(i);
                drinkMenu.get(i).type();
                System.out.println("Price: $" + drinkMenu.get(i).getPrice() + "\n");
                itemNumber++;
            }
            System.out.println();
        } else {
            System.out.println("Have a nice day then");
        }
        boolean ordering = true;
        System.out.println("What would you like to order? " +
                "Please use the number associated with each item to order.");
        int orderChoice = input.nextInt();
        input.nextLine();

        while (ordering) {
            System.out.println("What would you like to order? " +
                    "Please use the number associated with each item to order.");
            orderChoice = input.nextInt();
            input.nextLine();
            if ((orderChoice > 0) && (orderChoice < 4)) {
                order.add(cupcakeMenu.get(orderChoice - 1));
                System.out.println("Item added to order");
            } else if ((orderChoice > 3) && (orderChoice < 7)) {
                order.add(drinkMenu.get(orderChoice - 4));
                System.out.println("Item added to order");
            } else {
                System.out.println("Would you like to continue ordering? (Y/N)");
                String continueOrder = input.nextLine();
                if (!continueOrder.equalsIgnoreCase("Y")) {
                    ordering = false;
                }
            }
        }

        //Receipt
        System.out.println(order.get(0));//date
        System.out.println(order.get(1));//time

        Double subTotal = 0.0;

        for (int i = 2; i < order.size(); i++) {
            if (order.get(i).equals(cupcakeMenu.get(0))) {
                cupcakeMenu.get(0).type();
                System.out.println(cupcakeMenu.get(0).getPrice());
                subTotal = subTotal + cupcakeMenu.get(0).getPrice();
            } else if (order.get(i).equals(cupcakeMenu.get(1))) {
                cupcakeMenu.get(1).type();
                System.out.println(cupcakeMenu.get(1).getPrice());
                subTotal = subTotal + cupcakeMenu.get(1).getPrice();
            } else if (order.get(i).equals(cupcakeMenu.get(2))) {
                cupcakeMenu.get(2).type();
                System.out.println(cupcakeMenu.get(2).getPrice());
                subTotal = subTotal + cupcakeMenu.get(2).getPrice();
            } else if (order.get(i).equals(drinkMenu.get(0))) {
                drinkMenu.get(0).type();
                System.out.println(drinkMenu.get(0).getPrice());
                subTotal = subTotal + drinkMenu.get(0).getPrice();
            } else if (order.get(i).equals(drinkMenu.get(1))) {
                drinkMenu.get(1).type();
                System.out.println(drinkMenu.get(1).getPrice());
                subTotal = subTotal + drinkMenu.get(1).getPrice();
            } else if (order.get(i).equals(drinkMenu.get(2))) {
                drinkMenu.get(2).type();
                System.out.println(drinkMenu.get(2).getPrice());
                subTotal = subTotal + drinkMenu.get(2).getPrice();
            }
        }
        System.out.println("$" + subTotal + "\n");
        new CreateFile();
        new WriteToFile(order);
    }
}

class CreateFile {
    public CreateFile() {
        try {
            File salesData = new File("salesData.txt");
            if (salesData.createNewFile()) {
                System.out.println("File created: " + salesData.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e){
            System.out.println("An error occurred");
        }
    }
}

class WriteToFile {
    public WriteToFile(ArrayList<Object> order) {
        try {
            FileWriter fw = new FileWriter("salesData.txt", true);
            PrintWriter salesWriter = new PrintWriter(fw);
            for (int i = 0; i < order.size(); i++) {
                salesWriter.println();
            }
            salesWriter.close();
            System.out.println("Successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }
}