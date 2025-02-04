package qrameesh;

import java.util.HashMap;
import javax.swing.SwingUtilities;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

    JPanel startPage;
    JPanel loginPage;
    JPanel signinPage;
    JPanel homePage;
    JPanel mealsPage;
    JPanel adminPage;
    JPanel customerPage;
    JPanel logPage;
    JPanel notificationsPage;
    
    static double dailyFunds = 0;
    static int dailyOrders = 0;

    static HashMap<String, Person> users = SerializationUtil.loadFromFile("src/data/users.ser");
    static HashMap<String, Order> allOrders = SerializationUtil.loadFromFile("src/data/orders.ser");
    static HashMap<String, Meal> meals = SerializationUtil.loadFromFile("src/data/meals-log.ser");

    public Main() {
        // كود الانقاذ
//        allOrders = new HashMap<>();
//        users = new HashMap<>();
//        Customer c= new Customer("a", "aaaaaaaa", "a@gmail.com", "Tartous, new Akkaria");
//        Order order = new Order(c, EnOrderType.SPECIAL_ORDER, 500, 200);
//        order.setMeals(new ArrayList(Arrays.asList(meals.get("M001"), meals.get("M002"))));
//        c.addOrder(order);
//        Employee e= new Employee("b", "bbbbbbbb");
//        allOrders.put("O001", order);
//        users.put("C001", c);
//        users.put("E001", e);
//        SerializationUtil.saveToFile("src/data/orders.ser", allOrders);
//        SerializationUtil.saveToFile("src/data/users.ser", users);
//
//        allOrders.remove("O001");
//        ((Customer) users.get("C001")).getOrders().remove(0);
        //================================================================================
            //THERE IS AN IDEA WHEN ADDING PICTURE AND CHOOSING FILE
            //CREATE A COPY OF IT AND PUT IT IN IMAGES FOLDER
        
        createGUI();
    }

    private void createGUI() {
        startPage = new StartPage();
        loginPage = new LoginPage();
        signinPage = new SigninPage();
        homePage = new HomePage();
        mealsPage = new MealsPage();
        logPage = new LogPage();
        adminPage = new AdminPage();
        customerPage = new CustomerPage();
        notificationsPage = new NotificationsPage();
        
        Card.container = getContentPane();
        Card.container.setLayout(Card.card);

        add(startPage);
        add(loginPage);
        add(signinPage);
        add(homePage);
        add(mealsPage);
        add(logPage);
        add(adminPage);
        add(customerPage);
        add(notificationsPage);

        Card.container.getLayout().addLayoutComponent("startPage", startPage);
        Card.container.getLayout().addLayoutComponent("signinPage", signinPage);
        Card.container.getLayout().addLayoutComponent("loginPage", loginPage);
        Card.container.getLayout().addLayoutComponent("homePage", homePage);
        Card.container.getLayout().addLayoutComponent("mealsPage", mealsPage);
        Card.container.getLayout().addLayoutComponent("logPage", logPage);
        Card.container.getLayout().addLayoutComponent("adminPage", adminPage);
        Card.container.getLayout().addLayoutComponent("customerPage", customerPage);
        Card.container.getLayout().addLayoutComponent("notificationsPage", notificationsPage);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(new Logo(30, 30, "src/images/appIcon.png").getScaledImage());

        //SAVE WHEN SHUT DOWN
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SerializationUtil.saveToFile("src/data/users.ser", users);
            SerializationUtil.saveToFile("src/data/orders.ser", allOrders);
            SerializationUtil.saveToFile("src/data/meals.ser", meals);
        }));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {  // SEARCH OUT ABOUT !!!
            @Override
            public void run() {
                new Main();
            }
        });
    }

    static Person CurrentUser;

    public static boolean searchUsers(String name, String password) {
        Person user = getPersonByName(name);
        CurrentUser = user;
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public static Person getPersonByName(String name) {
        for (Person user : users.values()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    static void addUser(Customer c) {
        String key = keyGenerator(users, "C");
        CurrentUser = c;
        users.put(key, c);
    }

    static void addOrder(Order o) {
        String key = keyGenerator(allOrders, "O");
        allOrders.put(key, o);
    }

    static void addMeal(Meal m) {
        String key = m.getName();
        meals.put(key, m);
    }

    public static String keyGenerator(HashMap<String, ?> map, String prefix) {
        int maxNumber = 0;
        for (String key : map.keySet()) {
            if (key.startsWith(prefix)) {
                try {
                    int number = Integer.parseInt(key.substring(prefix.length()));
                    if (number > maxNumber) {
                        maxNumber = number;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        return prefix + String.format("%03d", maxNumber + 1);
    }
    
    public static String getMostWanted(){
        Set set = meals.entrySet();
        Iterator it = set.iterator();
        int max=0;
        String mostWanted = "";
        while (it.hasNext()) {
            Map.Entry mEntry = (Map.Entry) it.next();
            Meal m = (Meal) mEntry.getValue();
            if(m.getTimesOrdered() > max){
                max = m.getTimesOrdered();
                mostWanted = m.getName();
            }
        }
        return mostWanted;
    }
    
    public static String getBestCustomer(){
        Set set = users.entrySet();
        Iterator it = set.iterator();
        int max=0;
        String bestCustomer = "";
        while (it.hasNext()) {
            Map.Entry mEntry = (Map.Entry) it.next();
            Person p = (Person) mEntry.getValue();
            if(!p.isCellEditable()){
                Customer c = (Customer) p;
                if(c.getOrders().size() > max){
                    max = c.getOrders().size();
                    bestCustomer = c.getName();
                }
            }
        }
        return bestCustomer;
    }
}
