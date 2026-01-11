/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fees_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author rohan
 */
public class AddFees extends javax.swing.JFrame {

    /**
     * Creates new form AddFees
     */
    public void displayCashFirst()
    {
        lbl_cheque_num.setVisible(false);
        txt_dd_num.setVisible(false);
        txt_cheque_num.setVisible(false);
        lbl_dd_num.setVisible(false);
        lbl_bank_name.setVisible(false);
        txt_bank_name.setVisible(false);
        
        
    }
    public String insertData(){
       int receiptno=Integer.parseInt(txt_receipt_num.getText());
       String sname=txt_rec_name.getText();
       String rollno=txt_rollno.getText();
       String paymentmode=combo_mode_payment.getSelectedItem().toString();
       String chequeno=txt_cheque_num.getText();
       String bankname=txt_bank_name.getText();
       String ddno=txt_dd_num.getText();
       String coursename=combo_mode_payment1.getSelectedItem().toString();
       String gst=jLabel9.getText();
       float total=Float.parseFloat(txt_total.getText());
       SimpleDateFormat sd1= new SimpleDateFormat("YYYY-MM-dd");
       String date = sd1.format(date_c.getDate());
       float amount=Float.parseFloat(txt_amount1.getText());
       float cgst=Float.parseFloat(txt_cgst.getText());
       float sgst=Float.parseFloat(txt_sgst.getText());
       String totalinwords=txt_total_in_words.getText();
       String remark=jTextField1.getText();
       int year1=Integer.parseInt(fromyear.getText());
       int year2=Integer.parseInt(toyear.getText());
       String status="";
       try
       {
         Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/csfms?zeroDateTimeBehavior=CONVERT_TO_NULL";
        Connection con=DriverManager.getConnection(url,"root","rohan@123");
        String sql ="insert into fees_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st= con.prepareStatement(sql);
        st.setInt(1, receiptno);
        st.setString(2, sname);
        st.setString(3, rollno);
        st.setString(4, paymentmode);
        st.setString(5, chequeno);
        st.setString(6, bankname);
        st.setString(7, ddno);
        st.setString(8, coursename);
        st.setString(9, gst);
        st.setFloat(10, total);
        st.setString(11, date);
        st.setFloat(12, amount);
        st.setFloat(13, cgst);
        st.setFloat(14, sgst);
        st.setString(15, totalinwords);
        st.setString(16, remark);
        st.setInt(17, year1);
        st.setInt(18, year2);
        int count=st.executeUpdate();
        
        if(count==1)
        {
            status="sucess";
        }
        else
        {
            status="fail";
        }
        
           
       }
       catch(Exception e1)
       {
           e1.printStackTrace();
       }
       return status;
       
       
       
       
       
       
       
    }
    public AddFees() {
        initComponents();
        displayCashFirst();
        fillcombobox();
        int r=getrno();
        r++;
        txt_receipt_num.setText(Integer.toString(r));
    }
    




public class NumberToWordsConverter {

	public static final String[] units = { "", "One", "Two", "Three", "Four",
			"Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
			"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Nineteen" };

	public static final String[] tens = { 
			"", 		// 0
			"",		// 1
			"Twenty", 	// 2
			"Thirty", 	// 3
			"Forty", 	// 4
			"Fifty", 	// 5
			"Sixty", 	// 6
			"Seventy",	// 7
			"Eighty", 	// 8
			"Ninety" 	// 9
	};

	public static String convert(final int n) {
		if (n < 0) 
                {
			return "Minus " + convert(-n);
		}

		if (n < 20) 
                {
			return units[n];
		}

		if (n < 100) {
			return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
		}

		if (n < 1000) {
			return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 100000) {
			return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 10000000) {
			return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		}

		return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}

	public static void main(final String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the Amount : ");
		int n=sc.nextInt();

		
		System.out.println( convert(n)+ " Only");

	
	}
}
    boolean validation()
    {
        if(txt_rec_name.getText().equals(""))
        {
          JOptionPane.showMessageDialog(this,"Please Enter Receiver name"); 
          return false;
        }
        
        if(date_c.getDate()==null)
        {
           JOptionPane.showMessageDialog(this,"Please Enter Date"); 
           return false;
        }
        if(txt_rollno.getText().equals("") || !txt_rollno.getText().matches("[0-9]+"))
{
    JOptionPane.showMessageDialog(this,"Please Enter valid Roll Number");
    return false;
}
        if(combo_mode_payment.getSelectedItem().toString().equalsIgnoreCase("cheque"))
        {
            if(txt_cheque_num.getText().equals(""))
            {
            JOptionPane.showMessageDialog(this,"Please Enter Cheque Number"); 
            return false;
            }
            if(txt_bank_name.getText().equals(""))
            {
            JOptionPane.showMessageDialog(this,"Please Bank Name"); 
          return false;
            }
            
        }
        if(combo_mode_payment.getSelectedItem().toString().equalsIgnoreCase("dd"))
        {
            if(txt_dd_num.getText().equals(""))
            {
            JOptionPane.showMessageDialog(this,"Please Enter dd Number"); 
            return false;
            }
            if(txt_bank_name.getText().equals(""))
            {
            JOptionPane.showMessageDialog(this,"Please Bank Name"); 
          return false;
            }
            
        }
       return true;
        
    }
   public void fillcombobox(){
       try
       {
           Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/csfms?zeroDateTimeBehavior=CONVERT_TO_NULL";
        Connection con=DriverManager.getConnection(url,"root","rohan@123");
        String sql ="select cname from course";
        PreparedStatement st= con.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);
        while(rs.next())
        {
            combo_mode_payment1.addItem(rs.getString("cname"));
        }
        
        }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }
  public int getrno(){
      int rno=0;
       try
       {
           Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/csfms?zeroDateTimeBehavior=CONVERT_TO_NULL";
        Connection con=DriverManager.getConnection(url,"root","rohan@123");
        String sql ="select max(reciept_no) from fees_details";
        PreparedStatement st= con.prepareStatement(sql);
        ResultSet rs=st.executeQuery(sql);
        if(rs.next()==true)
        {
            
            rno=rs.getInt(1);
        }
        
        }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return rno;
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_mode_payment = new javax.swing.JLabel();
        lbl_dd_num = new javax.swing.JLabel();
        txt_receipt_num = new javax.swing.JTextField();
        lbl_cheque_num = new javax.swing.JLabel();
        lbl_recipt_num = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        date_c = new com.toedter.calendar.JDateChooser();
        txt_cheque_num = new javax.swing.JTextField();
        lbl_gstin = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fromyear = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        toyear = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_rollno = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        txt_sgst = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_total_in_words = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txt_rec_name = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        txt_amount1 = new javax.swing.JTextField();
        combo_mode_payment = new javax.swing.JComboBox<>();
        lbl_bank_name = new javax.swing.JLabel();
        txt_bank_name = new javax.swing.JTextField();
        txt_dd_num = new javax.swing.JTextField();
        lbl_mode_payment1 = new javax.swing.JLabel();
        combo_mode_payment1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(153, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setText("Search Record");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 132, -1));

        jButton2.setBackground(new java.awt.Color(153, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton2.setText("Add Course");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 132, -1));

        jButton3.setBackground(new java.awt.Color(153, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton3.setText("Course List");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 132, -1));

        jButton4.setBackground(new java.awt.Color(153, 255, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton4.setText("View All Record");
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 140, -1));

        jButton5.setBackground(new java.awt.Color(153, 255, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton5.setText("Logout");
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 132, -1));

        jButton7.setBackground(new java.awt.Color(153, 255, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton7.setText("HOME");
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 132, -1));

        jButton6.setBackground(new java.awt.Color(153, 255, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton6.setText("Back");
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 132, -1));

        jButton8.setBackground(new java.awt.Color(153, 255, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton8.setText("Edit Course");
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 132, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 1200));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 0, 438, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, -1));

        lbl_mode_payment.setText("Mode Of Payment");
        getContentPane().add(lbl_mode_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 100, -1));

        lbl_dd_num.setText("dd no");
        getContentPane().add(lbl_dd_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));
        getContentPane().add(txt_receipt_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 60, -1));

        lbl_cheque_num.setText("Cheque No");
        getContentPane().add(lbl_cheque_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 70, -1));

        lbl_recipt_num.setText("Receipt No");
        getContentPane().add(lbl_recipt_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 60, -1));

        jLabel9.setText("AOC13425JR");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 80, -1));
        getContentPane().add(date_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 30, 110, -1));
        getContentPane().add(txt_cheque_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 40, -1));

        lbl_gstin.setText("GSTIN");
        getContentPane().add(lbl_gstin, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 40, -1));

        lbl_date.setText("Date");
        getContentPane().add(lbl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 30, -1));

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(900, 1400));

        jLabel3.setText("Serial No");

        jLabel8.setText("To");

        jLabel14.setText("Received from for the given month");

        jLabel11.setText("Roll NO");

        jSeparator1.setForeground(new java.awt.Color(255, 102, 102));

        jSeparator2.setForeground(new java.awt.Color(255, 102, 102));

        jLabel16.setText("CGST 7%");

        jLabel17.setText("Amount");

        txt_rollno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rollnoActionPerformed(evt);
            }
        });

        jLabel18.setText("Head");

        jLabel19.setText("Total");

        jLabel21.setText("Remark");

        jLabel22.setText("Total in Words");

        jLabel23.setText("SGST 7%");

        jLabel24.setText("Receiver Sign.");

        jSeparator5.setForeground(new java.awt.Color(255, 102, 102));

        jLabel10.setText("Recevier name");

        jButton9.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jButton9.setText("Print");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        txt_amount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amount1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(94, 94, 94)
                            .addComponent(jLabel18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jLabel10)
                            .addGap(35, 35, 35)
                            .addComponent(txt_rec_name, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fromyear, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel17))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toyear, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_rollno, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(77, 77, 77))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(61, 61, 61)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(jButton9))
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel16))
                                .addGap(70, 70, 70)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_cgst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_sgst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(52, 52, 52)
                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_total_in_words, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(148, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(483, Short.MAX_VALUE)
                    .addComponent(txt_amount1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(53, 53, 53)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_rec_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(fromyear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toyear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_rollno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cgst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_sgst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_total_in_words, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel24)
                        .addGap(38, 38, 38)
                        .addComponent(jButton9)))
                .addGap(353, 353, 353))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(125, 125, 125)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(707, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(663, Short.MAX_VALUE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(193, 193, 193)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(200, 200, 200)
                    .addComponent(txt_amount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(637, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 620, 820));

        combo_mode_payment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phonepay", "Cash", "DD", "Cheque" }));
        combo_mode_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_paymentActionPerformed(evt);
            }
        });
        getContentPane().add(combo_mode_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 80, -1));

        lbl_bank_name.setText("Bank name");
        getContentPane().add(lbl_bank_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 60, 20));
        getContentPane().add(txt_bank_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 40, -1));
        getContentPane().add(txt_dd_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 40, -1));

        lbl_mode_payment1.setText("Course");
        getContentPane().add(lbl_mode_payment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 100, -1));

        combo_mode_payment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_payment1ActionPerformed(evt);
            }
        });
        getContentPane().add(combo_mode_payment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_mode_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_paymentActionPerformed
       if(combo_mode_payment.getSelectedIndex()==2)
       {
           lbl_dd_num.setVisible(true);
           txt_dd_num.setVisible(true);
           lbl_bank_name.setVisible(true);
           txt_bank_name.setVisible(true);
           lbl_cheque_num.setVisible(false);
           txt_cheque_num.setVisible(false);
           
           
       }
       if(combo_mode_payment.getSelectedIndex()==1){
           lbl_cheque_num.setVisible(false);
        txt_dd_num.setVisible(false);
        txt_cheque_num.setVisible(false);
        lbl_dd_num.setVisible(false);
        lbl_bank_name.setVisible(false);
        txt_bank_name.setVisible(false);
        
           
       }
       if(combo_mode_payment.getSelectedIndex()==3){
           lbl_cheque_num.setVisible(true);
        txt_dd_num.setVisible(false);
        txt_cheque_num.setVisible(true);
        lbl_dd_num.setVisible(false);
        lbl_bank_name.setVisible(true);
        txt_bank_name.setVisible(true);
        
           
       }
           

// TODO add your handling code here:
    }//GEN-LAST:event_combo_mode_paymentActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed


        if(validation()== true){
            String s=insertData();
            if(s.equals("sucess")){
                JOptionPane.showMessageDialog(this, "Record inserted  sucessfully");
                
            }
            else
            {
               JOptionPane.showMessageDialog(this, "Record not inserted "); 
            }
            
            
        }   
        

// TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txt_rollnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rollnoActionPerformed
      
      // TODO add your handling code here:
    }//GEN-LAST:event_txt_rollnoActionPerformed

    private void combo_mode_payment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_payment1ActionPerformed
jTextField7.setText(combo_mode_payment1.getSelectedItem().toString());        // TODO add your handling code here:
    }//GEN-LAST:event_combo_mode_payment1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_amount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amount1ActionPerformed
String s1=txt_amount1.getText();
      float amt=Float.parseFloat(s1);
      
      float cgst=amt*0.07f;
      float sgst=amt*0.07f;
      txt_cgst.setText(Float.toString(cgst));
       txt_sgst.setText(Float.toString(sgst));
       float total=amt+cgst+sgst;
      txt_total.setText(Float.toString(total));
      txt_total_in_words.setText(NumberToWordsConverter.convert((int)total));        // TODO add your handling code here:
    }//GEN-LAST:event_txt_amount1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddFees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_mode_payment;
    private javax.swing.JComboBox<String> combo_mode_payment1;
    private com.toedter.calendar.JDateChooser date_c;
    private javax.swing.JTextField fromyear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lbl_bank_name;
    private javax.swing.JLabel lbl_cheque_num;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_dd_num;
    private javax.swing.JLabel lbl_gstin;
    private javax.swing.JLabel lbl_mode_payment;
    private javax.swing.JLabel lbl_mode_payment1;
    private javax.swing.JLabel lbl_recipt_num;
    private javax.swing.JTextField toyear;
    private javax.swing.JTextField txt_amount1;
    private javax.swing.JTextField txt_bank_name;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_cheque_num;
    private javax.swing.JTextField txt_dd_num;
    private javax.swing.JTextField txt_rec_name;
    private javax.swing.JTextField txt_receipt_num;
    private javax.swing.JTextField txt_rollno;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_in_words;
    // End of variables declaration//GEN-END:variables
}
