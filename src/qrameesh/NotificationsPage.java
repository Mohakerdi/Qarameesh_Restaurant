package qrameesh;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NotificationsPage extends JPanel{
    
    Customer c;
    JLabel pageLabel = new JLabel("Past Orders");
    JPanel overlay;
    JLabel backLabel;
    Image backgroundImage;
    JList list;
    DefaultListModel model;
    JScrollPane sp;
    
    Logo logo = new Logo(120, 100, "src/images/logo.png");

    NotificationsPage() {
        model = new DefaultListModel();
        list = new JList(model);
        sp = new JScrollPane(list);
        
        list.setBackground(Color.getHSBColor(0.2f, 0.2f, 1f));
        list.setForeground(Color.getHSBColor(1, 1f, 0.4f));
        
        sp.setBounds(30, 80, 400, 400);
        
        backgroundImage = new ImageIcon("src\\images\\background.png").getImage();
        
        backLabel = new JLabel("back");
        backLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        backLabel.setForeground(Color.red);
        backLabel.setBounds(400, 600, 100, 40);
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
                c = (Customer) Main.CurrentUser;
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
        model.clear();
        List<Order> orders = c.getOrders();
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
                                 "Order state: " + order.getStat() + "\n" +
                                 "Order type: " + order.getType()+ "\n" +
                                 "Total Cost: " + order.getTotalCoast() + "$\n" +
                                 "Total Duration: " + order.getTotTime() + "\n" +
                                 "Meals: " + orderMeals;

            JOptionPane.showMessageDialog(this, orderDetails, "Order Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
