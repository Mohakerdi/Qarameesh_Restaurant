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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LogPage extends JPanel implements ActionListener{

    JLabel pageLabel = new JLabel("Log Page");
    JPanel overlay;
    JLabel backLabel;
    JLabel mostWanted;
    JLabel bestCustomer;
    JButton dailyReport;
    String mostWantedMeal = "";
    String bestCustomerPerson = "";
    
    Image backgroundImage;
    JList list;
    DefaultListModel model;
    JScrollPane sp;
    
    Logo logo = new Logo(120, 100, "src/images/logo.png");

    LogPage() {
        model = new DefaultListModel();
        list = new JList(model);
        sp = new JScrollPane(list);
        
        list.setBackground(Color.getHSBColor(0.2f, 0.2f, 1f));
        list.setForeground(Color.getHSBColor(1, 1f, 0.4f));
        
        sp.setBounds(30, 80, 400, 320);

        backgroundImage = new ImageIcon("src\\images\\background.png").getImage();
        
        dailyReport = new JButton("Daily Report");
        dailyReport.setBounds(320, 420, 110, 30);
        dailyReport.setForeground(Color.getHSBColor(1, 1f, 0.4f));
        dailyReport.setFocusable(false);
        dailyReport.addActionListener(this);

        mostWanted = new JLabel("Most Wanted: "+mostWantedMeal);
        mostWanted.setFont(new Font("Arial", Font.BOLD, 14));
        mostWanted.setForeground(Color.yellow);
        mostWanted.setBounds(25, 410, 220, 40);
        
        bestCustomer = new JLabel("Best Customer: "+bestCustomerPerson);
        bestCustomer.setFont(new Font("Arial", Font.BOLD, 14));
        bestCustomer.setForeground(Color.yellow);
        bestCustomer.setBounds(25, 455, 250, 40);
        
        backLabel = new JLabel("back");
        backLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        backLabel.setForeground(Color.red);
        backLabel.setBounds(400, 600, 100, 40);
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card.card.show(Card.container, "adminPage");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        overlay = new JPanel();
        overlay.setBounds(25, 30, 450, 500);
        overlay.setLayout(null);
        overlay.setBackground(Color.getHSBColor(1, 1f, 0.4f));

        pageLabel.setBounds(150, 25, 250, 80);
        pageLabel.setFont(new Font("gabriola", Font.BOLD, 50));
        pageLabel.setForeground(Color.yellow);

        logo.getLogoLabel().setBounds(160, 550, 120, 95);

        overlay.add(pageLabel);
        overlay.add(sp);
        overlay.add(bestCustomer);
        overlay.add(mostWanted);
        overlay.add(dailyReport);
        add(overlay);
        add(logo.getLogoLabel());
        add(backLabel);

        setSize(500, 700);

        setLayout(null);
        
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = list.getSelectedIndex();
                    if (row != -1) {
                        showOrderDetails((Order) list.getSelectedValue()); 
                    }
                }
            }
        });
        
        this.addComponentListener(new ComponentAdapter(){
            @Override public void componentShown(ComponentEvent e) {
                updateList();
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    private void updateList() {
        mostWantedMeal = Main.getMostWanted();
        mostWanted.setText("Most Wanted: "+mostWantedMeal);
        bestCustomerPerson = Main.getBestCustomer();
        bestCustomer.setText("Best Customer: " + bestCustomerPerson);
        model.clear();
        List<Order> orders = new ArrayList();
        orders.addAll(Main.allOrders.values());
        if (orders != null) {
            for (Order order : orders) {
                model.addElement(order);
            }
        }
    }

    private void showOrderDetails(Order order) {
        if (order != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
            String orderMeals = "";
            int mealsCnt = order.getMeals().size();
            for(int i=0; i<mealsCnt; i++){
                orderMeals += order.getMeals().get(i).getName()+", ";
            }
            String orderDetails = "Order Date: " + dateFormat.format(order.getDate()) + "\n" +
                                 "Customer: " + order.getCustomer().getName()+ "\n" +
                                 "Order state: " + order.getStat() + "\n" +
                                 "Total Cost: " + order.getTotalCoast() + "$\n" +
                                 "Total Duration: " + order.getTotTime() + "\n" +
                                 "Meals: " + orderMeals;

            JOptionPane.showMessageDialog(this, orderDetails, "Order Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == dailyReport){
            Components.playSound("src/data/click.wav");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String report =     "Date :  " + dateFormat.format(new Date()) + "\n\n" +
                                 "Today's Funds: " + Main.dailyFunds + "$\n" +
                                 "Today's orders count: " + Main.dailyOrders + "\n";
            JOptionPane.showMessageDialog(this, report, "Order Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
