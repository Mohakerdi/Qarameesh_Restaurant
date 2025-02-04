package qrameesh;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class HomePage extends JPanel implements ActionListener {

    Set set;
    Iterator it;
    ArrayList mealList;
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    JRadioButton iners = new JRadioButton("Inside The Restaurant");
    JRadioButton delivery = new JRadioButton("Delivery");
    JRadioButton specialOrder = new JRadioButton("Spical Order");

    ButtonGroup group;
    JButton button_confirmOrder;
    JButton button_cancelOrder;
    JTable table;
    JPanel coastDurationPanel;
    int totalCoast = 0;
    int totalDuration = 0;
    JLabel totalPriceLabel;
    JLabel totalDurationLabel;
    JLabel backLabel;
    JScrollPane scroll;
    private JDialog dialog;
    Image backgroundImage;
    Logo logo = new Logo(120, 100, "src/images/logo.png");

    String[] column = {"Meal", "Price", ""};
    Object[][] row;

    Order order;

    HomePage() {
        
        mealList = new ArrayList<Meal>();

        updateItems();

        coastDurationPanel = new JPanel();

        backLabel = new JLabel("back");
        totalDurationLabel = new JLabel("Duration : 0");
        totalPriceLabel = new JLabel("Coast : 0");

        backgroundImage = new ImageIcon("src\\images\\background.png").getImage();

        setLayout(null);

        model.setDataVector(row, column);
        table = new JTable(model) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 2) {
                    return ImageIcon.class;
                }
                return super.getColumnClass(column);
            }
        };
        table.setShowHorizontalLines(false);
        table.setEnabled(false);

        scroll = new JScrollPane(table);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(75);
         table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setRowHeight(100);
        table.setFont(new Font("Arial", Font.BOLD, 15));
        scroll.setBounds(30, 50, 440, 450);

        coastDurationPanel.setBounds(300, 550, 140, 100);
        coastDurationPanel.setBackground(Color.getHSBColor(1, 1f, 0.4f));
        coastDurationPanel.setLayout(null);

        backLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        backLabel.setForeground(Color.red);
        backLabel.setBounds(50, 65, 60, 40);

        table.setBackground(Color.getHSBColor(1, 1f, 0.4f));
        table.setForeground(Color.yellow);
        table.setGridColor(Color.getHSBColor(1, 1f, 0.4f));
        table.setShowGrid(false);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }

        iners.setBounds(30, 500, 160, 20);
        delivery.setBounds(190, 500, 80, 20);
        specialOrder.setBounds(270, 500, 120, 20);
        group = new ButtonGroup();
        group.add(iners);
        group.add(delivery);
        group.add(specialOrder);

        button_confirmOrder = new JButton("Confirm Order");
        button_cancelOrder = new JButton("Cancel Order");
        button_confirmOrder.setBounds(30, 550, 120, 40);
        button_cancelOrder.setBounds(30, 600, 120, 40);
        button_confirmOrder.setBackground(Color.getHSBColor(0.2f, 0.6f, 1f));
        button_cancelOrder.setBackground(Color.getHSBColor(0.2f, 0.6f, 1f));
        button_cancelOrder.setForeground(Color.red);

        totalDurationLabel.setForeground(Color.getHSBColor(0.2f, 0.6f, 1f));
        totalPriceLabel.setForeground(Color.getHSBColor(0.2f, 0.6f, 1f));
        totalDurationLabel.setBounds(10, 30, 100, 30);
        totalPriceLabel.setBounds(10, 10, 100, 30);

        iners.setForeground(Color.getHSBColor(0.2f, 0.6f, 1f));
        delivery.setForeground(Color.getHSBColor(0.2f, 0.6f, 1f));
        specialOrder.setForeground(Color.getHSBColor(0.2f, 0.6f, 1f));
        iners.setBackground(Color.getHSBColor(1, 1f, 0.4f));
        iners.setSelected(true);
        delivery.setBackground(Color.getHSBColor(1, 1f, 0.4f));
        specialOrder.setBackground(Color.getHSBColor(1, 1f, 0.4f));

        logo.getLogoLabel().setBounds(160, 550, 120, 95);

        coastDurationPanel.add(totalDurationLabel);
        coastDurationPanel.add(totalPriceLabel);
        coastDurationPanel.add(backLabel);
        add(button_confirmOrder);
        add(button_cancelOrder);
        add(scroll);
        add(iners);
        add(delivery);
        add(specialOrder);
        add(coastDurationPanel);
        add(logo.getLogoLabel());

        addRequest();

        button_cancelOrder.addActionListener(this);
        button_confirmOrder.addActionListener(this);

        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card.card.show(Card.container, "customerPage");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        this.addComponentListener(new ComponentAdapter(){
            @Override public void componentShown(ComponentEvent e) {
                System.out.println("Panel 1 is shown. Perform update here.");
                updateItems();
            } // Add your update logic here
        });
    }

    public final void addRequest() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int column = table.columnAtPoint(e.getPoint());
                    if (row >= 0 && column >= 0) {
                        showRequestDialog(row);
                    }
                }
            }
        });
    }

    private void showRequestDialog(int rowIndex) {
        dialog = new JDialog();
        dialog.setLocation(300, 50);
        dialog.setSize(400, 500);
        dialog.setLayout(null);

        Map.Entry mEntry = (Map.Entry) it.next();
        for (int i = 0; i < rowIndex; i++) {
            mEntry = (Map.Entry) it.next();
        }
        Meal m = (Meal) mEntry.getValue();
        it = set.iterator();// resetting it 

        JPanel panel = Components.createPanel(0, 0, 400, 500);
        panel.setBackground(Color.getHSBColor(0.2f, 0.2f, 1f));
        JLabel label = Components.createLabel("Number of meals:", 20, 20, 150, 30);
        JSpinner spinner = Components.createSpinner(320, 20, 50, 40);
        JLabel labelPrice = Components.createLabel("Total Price:", 20, 80, 150, 30);
        JLabel labelDuration = Components.createLabel("Total Duration:", 20, 120, 150, 30);
        JTextArea recipee = Components.createTextArea(m.getRecipee());
        JScrollPane recipe = Components.createScrollPane(recipee, 40, 155, 300, 250, false);
        JLabel labelPriceValue = Components.createLabel("0", 150, 80, 150, 30);
        JLabel labelDurationValue = Components.createLabel("0", 150, 120, 150, 30);
        JButton buttonConfirmDialog = Components.createButton("Confirm", 280, 425, 100, 30);

        panel.add(label);
        panel.add(labelPrice);
        panel.add(recipe);
        panel.add(labelPriceValue);
        panel.add(labelDurationValue);
        panel.add(labelDuration);
        panel.add(buttonConfirmDialog);
        panel.add(spinner);
        dialog.add(panel);

        dialog.setVisible(true);

        spinner.addChangeListener(e -> updatePrice(labelPriceValue, labelDurationValue, spinner, m));
        buttonConfirmDialog.addActionListener(e -> confirmOrder(labelPriceValue, labelDurationValue));
    }

    private void updatePrice(JLabel labelPriceValue, JLabel labelDurationValue, JSpinner spinner, Meal m) {
        Components.playSound("src/data/click.wav");
        int quantity = (int) spinner.getValue();
        double price = m.getPrice();
        int time = m.getPrepareTime();
        labelDurationValue.setText(String.valueOf(quantity * time));
        labelPriceValue.setText(String.valueOf(quantity * price));
        if (quantity > 0) {
            mealList.add(m);
            m.updateTimesOrdered(1);
        }
    }

    private void confirmOrder(JLabel labelPriceValue, JLabel labelDurationValue) {
        Components.playSound("src/data/click.wav");
        totalCoast += Double.parseDouble(labelPriceValue.getText());
        totalDuration += Integer.parseInt(labelDurationValue.getText());
        totalPriceLabel.setText("Coast: " + totalCoast);
        totalDurationLabel.setText("Duration: " + totalDuration);
        dialog.setVisible(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_cancelOrder) {
            Components.playSound("src/data/click.wav");
            int x = JOptionPane.showConfirmDialog(null, "Cancel Order?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (x == 0) {
                Components.playSound("src/data/click.wav");
                resetOrder();
            } else {

            }
        }

        if (e.getSource() == button_confirmOrder) {
            Components.playSound("src/data/click.wav");
            int x = JOptionPane.showConfirmDialog(null, "Confirm Order?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (x == 0) {
                Components.playSound("src/data/click.wav");
                EnOrderType type = checkRadio();
                order = new Order((Customer) Main.CurrentUser, type, totalCoast, totalDuration);
                Date date = new Date();
                order.setMeals(mealList);
                order.setDate(date);
                mealList = new ArrayList();//reset
                Main.addOrder(order);
                ((Customer) Main.CurrentUser).addOrder(order);
                showPaymentDialog();
                orderUpdates();

            } else {
            }
        }
    }

    EnOrderType checkRadio() {
        if (iners.isSelected()) {
            return EnOrderType.INSIDE_THE_RESTAURANT;
        } else if (delivery.isSelected()) {
            return EnOrderType.DELIVERY;
        } else {
            return EnOrderType.SPECIAL_ORDER;
        }
    }

    void resetOrder() {
        totalCoast = 0;
        totalDuration = 0;

        totalPriceLabel.setText("Coast : 0");
        totalDurationLabel.setText("Duration : 0");
    }

    public final void updateItems() {
        Main.meals = SerializationUtil.loadFromFile("src/data/meals-log.ser");
        row = new Object[Main.meals.size()][3];
        int i = 0;
        set = Main.meals.entrySet();
        it = set.iterator();
        while (it.hasNext()) {
            Map.Entry mEntry = (Map.Entry) it.next();
            Meal m = (Meal) mEntry.getValue();
            row[i][0] = m.getName();
            row[i][1] = String.valueOf(m.getPrice());
            row[i][2] = new Logo(90, 90, m.getImagePath()).getScaledIcon();
            i++;
        }
        it = set.iterator();

        model.setDataVector(row, column);
    }

    public void orderUpdates() {
        System.out.println("started");
        Loading ls = new Loading(totalDuration * 1000);
        Thread t = new Thread(ls);
        t.start();
        JOptionPane.showMessageDialog(null, "Order is being prepared!", "Info Message", JOptionPane.INFORMATION_MESSAGE);
        order.setStat(EnOrderStat.PREPARING);
        
        Timer timer = new Timer(100, e -> {
            button_confirmOrder.setEnabled(false);
            if (totalCoast == 0) {
                ((Timer) e.getSource()).stop();
                order.setStat(EnOrderStat.CANCELED);
                JOptionPane.showMessageDialog(null, "Order Canceled!", "Warning Message", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("ended");
                t.interrupt();
                Main.dailyOrders += 1;
                button_confirmOrder.setEnabled(true);
            }
            else if (!t.isAlive()) {
                ((Timer) e.getSource()).stop();
                Components.playSound("src/data/billRing.wav");
                order.setStat(EnOrderStat.DELIVERED);
                JOptionPane.showMessageDialog(null, "Order Delivered!", "Info Message", JOptionPane.INFORMATION_MESSAGE);
                resetOrder();
                System.out.println("ended");
                Main.dailyFunds += order.getTotalCoast();
                Main.dailyOrders += 1;
                button_confirmOrder.setEnabled(true);
            }
        });
        timer.start();
    }
    
    public void showPaymentDialog() {
        JTextField cardNumberField = new JTextField(16);
        JTextField tip = new JTextField(3);
        tip.setText("0");

        JComboBox<String> paymentMethods = new JComboBox<>(new String[]{"Credit Card", "Debit Card", "PayPal", "cash"});
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Select Payment Method:"));
        panel.add(paymentMethods);
        panel.add(new JLabel("Card Number:"));
        panel.add(cardNumberField);
        panel.add(new JLabel("tip:"));
        panel.add(tip);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Payment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try{
                Components.playSound("src/data/payment.wav");
                Main.dailyFunds += Double.parseDouble(tip.getText());
                JOptionPane.showMessageDialog(null, "Payment is being processed!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }catch(NumberFormatException | MatchException e){
                JOptionPane.showMessageDialog(null, "InValid Input!!!.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Payment canceled.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
   

}
