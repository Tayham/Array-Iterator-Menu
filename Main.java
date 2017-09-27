package com.COSC436;

import java.util.Scanner;

import static com.COSC436.Menu.*;

public class Main {

    public static void main(String[] args) {
        Menu eatAtJoesMenu = new Menu();
        eatAtJoesMenu.add("Lobster Dinner", MAIN_DISH, NOT_HEART_HEALTHY, 24.99);
        eatAtJoesMenu.add("Rice Pudding", DESSERT, NOT_HEART_HEALTHY, 3.50);
        eatAtJoesMenu.add("Nachos", APPETIZERS, NOT_HEART_HEALTHY, 2.99);
        eatAtJoesMenu.add("Spaghetti", MAIN_DISH, HEART_HEALTHY, 15.06);
        eatAtJoesMenu.add("Hamburger", MAIN_DISH, NOT_HEART_HEALTHY, 7.99);
        eatAtJoesMenu.add("Ice Cream", DESSERT, NOT_HEART_HEALTHY, 4.89);
        eatAtJoesMenu.add("Salad", APPETIZERS, HEART_HEALTHY, 4.00);

        String name;
        int category;
        boolean heartHealthy;
        double price;
        double priceThreshold = 0.0;

        String menuPrompt = "1 – Display appetizers\n" + "2 – Display main dishes\n" + "3 – Display desserts\n" + "4 – Display heart healthy items\n" + "5 – Display items under a specified price\n" + "6 – Display all menu items\n" + "7 – Add menu item\n" + "8 – Remove menu item\n" + "0 – EXIT";
        String inputPrompt = "Please enter your option:";
        String allItems = "ALL MENU ITEMS";
        String appDisp = "APPETIZERS";
        String heartHealthyDisp = "ALL HEART HEALTHY MENU ITEMS";
        String mainDishDisp = "MAIN DISHES";
        String dessertItem = "ALL DESSERT MENU ITEMS";
        String underPrice = "ALL ITEMS UNDER $" + priceThreshold;

        String addItemName = "Input Item Name";
        String addItemCategory = "Input Item Category:\n" + "1 - Appetizers\n" + "2 - Main Dish\n" + "3 - Dessert";
        String addItemHeartHealth = "Is this item heart healthy? Y/N";
        String addItemPrice = "How much does the item cost (no $ sign)";

        String maxPrice = "What is upper price threshold?";
        String deletePrompt = "Press [d] to delete item or [Enter] to continue";


        Scanner console = new Scanner(System.in);
        boolean exit = false;
        MenuIterator iterator;
        do {
            int selection = menu(menuPrompt, inputPrompt, 0, 8);
            switch (selection) {

                case 1: //1 – Display appetizers

                    iterator = eatAtJoesMenu.getItemIterator(APPETIZERS);
                    printItr(appDisp, iterator);

                    break;

                case 2: //2 – Display main dishes

                    iterator = eatAtJoesMenu.getItemIterator(MAIN_DISH);
                    printItr(mainDishDisp, iterator);

                    break;

                case 3: //3 – Display desserts

                    iterator = eatAtJoesMenu.getItemIterator(DESSERT);
                    printItr(dessertItem, iterator);

                    break;

                case 4: //4 – Display hearty healthy items

                    iterator = eatAtJoesMenu.getHeartHealthyIterator(HEART_HEALTHY);
                    printItr(heartHealthyDisp, iterator);

                    break;

                case 5: //5 – Display items under a specified price

                    priceThreshold = getDouble(console, maxPrice);
                    iterator = eatAtJoesMenu.getPriceIterator(priceThreshold);
                    printItr(underPrice, iterator);

                    break;

                case 6: //6 – Display all menu items

                    iterator = eatAtJoesMenu.getAllItemsIterator();
                    printItr(allItems, iterator);

                    break;

                case 7: //7 – Add menu item

                    name = getString(console, addItemName);
                    category = getIntRange(console, addItemCategory, 3, 1);
                    heartHealthy = twoOptions(console, addItemHeartHealth, "Y", "N");
                    price = getDouble(console, addItemPrice);
                    eatAtJoesMenu.add(name, category, heartHealthy, price);

                    break;

                case 8: //8 – Remove menu item

                    iterator = eatAtJoesMenu.getAllItemsIterator();
                    MenuItem item;
                    while (iterator.hasNext()) {
                        item = iterator.next();
                        System.out.println(item.getName() + " $" + item.getPrice());
                        if (enterOptions(console, deletePrompt, "D"))
                            System.out.println("Kept: " + item.getName());
                        else {
                            System.out.println("Deleted: " + item.getName());
                            eatAtJoesMenu.delete(iterator);
                        }
                    }

                    break;

                case 0: //EXIT MENU
                    exit = true;

                    break;
            }
        } while (exit == false);
    }

    public static String getString(Scanner input, String prompt) {
        System.out.println(prompt);
        while (!input.hasNext()) {
            input.next();//consume
        }
        return input.next();
    }

    public static int getInt(Scanner input, String prompt) {
        System.out.println(prompt);
        while (!input.hasNextInt()) {
            input.next();//consume
            System.out.println("Not an integer. Try again!");
            System.out.println(prompt);
        }
        return input.nextInt();
    }

    public static double getDouble(Scanner input, String prompt) {
        System.out.println(prompt);
        while (!input.hasNextDouble()) {
            input.next();//consume
            System.out.println("Not a number. Try again!");
            System.out.println(prompt);
        }
        return input.nextDouble();
    }

    public static boolean twoOptions(Scanner input, String prompt, String yes, String no) {
        System.out.println(prompt);
        String letter = input.next();
        while (!letter.equalsIgnoreCase(yes) && !letter.equalsIgnoreCase(no)) {
            System.out.println("Not " + yes + " or " + no + ". Try again!");
            System.out.println(prompt);
            letter = input.next();
        }
        return letter.equalsIgnoreCase(yes);
    }

    public static boolean enterOptions(Scanner input, String prompt, String no) {
        System.out.println(prompt);
        String key = input.nextLine();
        while (!key.equals("") && !key.equalsIgnoreCase(no)) {
            System.out.println("Not [Enter] or " + no + ". Try again!");
            System.out.println(prompt);
            key = input.nextLine();
        }
        return key.equals("");
    }

//System.out.println(readString);
//if (readString.equals(""))
//        System.out.println("Enter Key pressed.");
//if (scanner.hasNextLine())
//    readString = scanner.nextLine();
//else
//    readString = null;

    public static void printItr(String display, MenuIterator itr) {
        System.out.println(display);
        MenuItem item;
        while (itr.hasNext()) {
            item = itr.next();
            System.out.println(item.getName() + " $" + item.getPrice());
        }
    }

    public static int getIntRange(Scanner input, String prompt, int MAX, int LOW) {
        int x = getInt(input, prompt);
        while (x > MAX || x < LOW) {

            System.out.println("INPUT ERROR!!! Invalid size.");
            x = getInt(input, prompt);
        }
        return x;
    }

    public static int menu(String menuOptions, String inputPrompt, int min, int max) {
        System.out.println("\nYour options are:\n-----------------");
        System.out.println(menuOptions);
        Scanner console = new Scanner(System.in);
        int input = getInt(console, inputPrompt);
        while (min > input || input > max) {
            System.out.println(input + " is not a valid option");
            input = getInt(console, inputPrompt);
        }
        return input;
    }
}
