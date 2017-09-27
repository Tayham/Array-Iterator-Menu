package com.COSC436;

public class Menu {

    public static final int APPETIZERS = 1;
    public static final int MAIN_DISH = 2;
    public static final int DESSERT = 3;
    public static final boolean HEART_HEALTHY = true;
    public static final boolean NOT_HEART_HEALTHY = false;
    static final int MAX_MENU_ITEMS = 25;
    int itemAmount = 0;
    MenuItem[] items;

    public Menu() {
        items = new MenuItem[MAX_MENU_ITEMS];
    }

    public void add(String name, int category, boolean heartHealthy, double price) {
        MenuItem newItem = new MenuItem(name, category, heartHealthy, price);
        if (itemAmount >= MAX_MENU_ITEMS) {
            System.err.println("ERROR: Menu has reached the max amount of items");
        }
        else {
            items[itemAmount] = newItem;
            itemAmount++;
        }
    }

    public void delete(MenuIterator itr) {

    }

    public MenuIterator getAllItemsIterator() {
        return new AllItemsIterator();
    }

    public MenuIterator getItemIterator(int category) {
        return new ItemIterator(category);
    }

    public MenuIterator getHeartHealthyIterator(boolean healthValue) {
        return new HeartHealthyIterator(healthValue);
    }

    public MenuIterator getPriceIterator(double price) {
        return new PriceIterator(price);
    }

    //    Iterates over all of the items on the menu
    private class AllItemsIterator implements MenuIterator {
        private int i;  // index

        public boolean hasNext() {
            return !(i >= items.length || items[i] == null);
        }

        public MenuItem next() {
            return items[i++];
        }
    }

    //    Iterates over a specified item type (appetizer, main dish, or dessert)
    private class ItemIterator implements MenuIterator {
        private int i;  // index
        private int category; //food category

        public ItemIterator(int category) {
            this.category = category;
        }

        public boolean hasNext() {
            return !(i >= items.length || items[i] == null) && filter();
        }

        public boolean filter() {
            if (items[i].getCategory() == category) {
                return true;
            }
            else {
                i++;
                return hasNext();
            }
        }

        public MenuItem next() {
            return items[i++];
        }
    }

    //    Iterates over the heart healthy items on the menu
    private class HeartHealthyIterator implements MenuIterator {
        private int i;  // index
        private boolean healthValue;

        public HeartHealthyIterator(boolean healthValue) {
            this.healthValue = healthValue;
        }

        public boolean hasNext() {
            return !(i >= items.length || items[i] == null) && filter();
        }

        public boolean filter() {
            if (items[i].isHeartHealthy() == healthValue) {
                return true;
            }
            else {
                i++;
                return hasNext();
            }
        }

        public MenuItem next() {
            return items[i++];
        }
    }

    //    Iterates over the main dishes that are under a specified price
    private class PriceIterator implements MenuIterator {
        double priceThreshold; //the max price value
        private int i;  // index

        public PriceIterator(double priceThreshold) {
            this.priceThreshold = priceThreshold;
        }

        public boolean hasNext() {
            return !(i >= items.length || items[i] == null) && filter();
        }

        public boolean filter() {
            if (items[i].getPrice() <= priceThreshold) {
                return true;
            }
            else {
                i++;
                return hasNext();
            }
        }

        public MenuItem next() {
            return items[i++];
        }
    }
}