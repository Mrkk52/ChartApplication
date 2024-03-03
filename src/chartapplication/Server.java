
package chartapplication;

/**
 *
 * @author Kinkar
 */

//========Swing Packages========================================================
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;



//======AWT=====================================================================
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;

//==========Util================================================================

import java.util.Calendar;

//===============other==========================================================
import java.text.SimpleDateFormat;

//===========SocketPackage======================================================
import java.net.ServerSocket;
import java.net.Socket;

//===========IO package=========================================================
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class Server implements ActionListener{
    
    JTextField text;
    static JPanel al;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    
    Server(){
        f.setLayout(null);
        
        //====JPanel=for=Heading================================================
        
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1);
        
        
        //============ScrollBar=================================================
        JScrollPane scrollPane = new JScrollPane(al);
        scrollPane.setBounds(0,75,435,535);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setBackground(Color.GRAY); // Change background color
        scrollPane.getVerticalScrollBar().setForeground(Color.WHITE); // Change foreground color
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        f.add(scrollPane);
        
        //========ImageAdd==and==Scanling=======================================
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                System.exit(0);
            }
        });
        
        //===============Profile==Image=========================================
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/My.png"));
        Image i5 = i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);
        
        //===============Profile==Image=========================================
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i8 = i7.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel call = new JLabel(i9);
        call.setBounds(300,20,25,25);
        p1.add(call);
        
        //===============Profile==Image=========================================
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i11 = i10.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel other1 = new JLabel(i12);
        other1.setBounds(350,20,25,25);
        p1.add(other1);
        
        //===============Profile==Image=========================================
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(25,27,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel other2 = new JLabel(i15);
        other2.setBounds(400,20,25,27);
        p1.add(other2);
        
        //===============NameLabel==============================================

        JLabel name = new JLabel("Kinkar");
        name.setBounds(100,12,250,25);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN SERIF", Font.BOLD, 15));
        p1.add(name);
        
        //===============active now=============================================

        JLabel active = new JLabel("Active now");
        active.setBounds(99,28,100,25);
        active.setForeground(Color.white);
        active.setFont(new Font("SAN SERIF", Font.BOLD, 10));
        p1.add(active);
        
        //===============al=====================================================
        al = new JPanel();
        al.setBounds(0,75,445,535);
        scrollPane.setViewportView(al);
        
        //===============TextField==============================================

        text = new JTextField("Text here...");
        text.setBounds(0,615,325,45);
        //text.setForeground(Color.white);
        text.setFont(new Font("SAN SERIF", Font.BOLD, 15));
        f.add(text);
        
        //===============SendButton=============================================

        JButton send = new JButton("Send");
        send.setBounds(325,615,110,45);
        send.setForeground(Color.black);
        send.setBackground(new Color(7,94,84));
        send.addActionListener(this);
        send.setFont(new Font("SAN SERIF", Font.BOLD, 15));
        f.add(send);
        //===========Addtional==================================================
        f.setTitle("Server");
        f.setSize(450,700);
        f.setLocation(200,60);
        //f.setUndecorated(true);
        f.setVisible(true);
        f.getContentPane().setBackground(Color.white);
        
        
    }
    public void actionPerformed(ActionEvent ae){
        try{
            String out = text.getText();

            JPanel p2 = formatLabel(out);
            al.setLayout (new BorderLayout());

            JPanel right = new JPanel(new BorderLayout()); 
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            al.add(vertical,BorderLayout.PAGE_START);
            
            // output - send messeages
            //dout.writeUTF(out);
            if(dout != null) {
                // output - send messages
                dout.writeUTF(out);
            } else {
                System.out.println("DataOutputStream is null");
            }
            
            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static JPanel formatLabel(String out) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    JLabel output = new JLabel("<html><p style=\"width:150px\">" + out + "</p></html>");
    output.setFont(new Font("Arial", Font.PLAIN, 15));
    output.setBackground(new Color(37, 211, 102));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15, 15, 15, 50));
    panel.add(output);
    
    JScrollPane scrollPane = (JScrollPane) al.getParent().getParent();
    if (scrollPane != null) {
        JViewport viewport = scrollPane.getViewport();
        SwingUtilities.invokeLater(() -> {
            viewport.setViewPosition(new Point(0, viewport.getViewSize().height - viewport.getHeight()));
        });
    }
    
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    JLabel time = new JLabel();
    time.setText(sdf.format(cal.getTime()));
    panel.add(time);
    
    return panel;
}

    
    public static void main(String[] args){
        new Server();
        
        try{
            ServerSocket skt = new ServerSocket(6001);
            while(true){
               Socket s = skt.accept();
               DataInputStream din = new DataInputStream(s.getInputStream());
               dout = new DataOutputStream(s.getOutputStream());
               
               while(true){
                   //Receive messeages
                   String msg = din.readUTF();
                   JPanel panel = formatLabel(msg);
                   
                   JPanel left = new JPanel(new BorderLayout());
                   left.add(panel,BorderLayout.LINE_START);
                   vertical.add(left);
                   f.validate();
               }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
