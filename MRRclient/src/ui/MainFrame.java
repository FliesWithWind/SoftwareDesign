package ui;

import control.*;
import datatype.*;
        
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.*;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.Color;
import java.util.Date;

public class MainFrame extends javax.swing.JFrame
{
    private static MainFrame self;
    private static int accesstype;
    private boolean reserving;
    
    private PacketBuilder packetbuilder;
    MyCalendar calendar;
    
    ArrayList<Account> reglist;
    
    
    public static MainFrame getInstance()
    {
        synchronized(MainFrame.class)
        {	
            if(self == null) self = new MainFrame();
            return self;
        }
    }
    
    public static void setAccessType(int accesstype)
    {
        MainFrame.accesstype = accesstype;
    }
    
    public void freeze()
    {
        labelStatus.setText(STR.LABEL_STATE_BUSY);
        progBar.setIndeterminate(true);
        setEnabled(false);
    }
    
    public void unfreeze()
    {
        labelStatus.setText(STR.LABEL_STATE_IDLE);
        progBar.setIndeterminate(false);
        setEnabled(true);
    }
    
    public void showDialog(String msg)
    {
        JOptionPane.showMessageDialog(new JFrame(), msg);
    }

    private void refreshMyAccount()
    {
        freeze();
        if(!editMan.getText().equals(""))
            packetbuilder.myAccount(editMan.getText());
        packetbuilder.myAccount(packetbuilder.getLoginId());
    }
    
    public void updateAccountSection(Account inf)
    {
        labelIdAcc.setText(inf.getId());
        editPwAcc.setText(inf.getPw());
        editNameAcc.setText(inf.getName());
        editPhoneAcc.setText(inf.getPhonenum());
        editEmailAcc.setText(inf.getEmail());
        editUniv_compAcc.setText(inf.getUniv_comp());
    }
    
    private void refreshMyRoomlist()
    {
        freeze();
        if(!editMan.getText().equals(""))
            packetbuilder.myRooms(editMan.getText());
        packetbuilder.myRooms(packetbuilder.getLoginId());
    }
    
    public void updateMyRoomlist(ArrayList<Room> list)
    {
        String[] temp = new String[list.size()];
        
        int i = 0;
        for(Room iter : list) temp[i++] = iter.getName();
        
        listRoom.setListData(temp);
    }
    
    private void refreshMyReservationlist()
    {
        freeze();
        if(!editMan.getText().equals(""))
            packetbuilder.myRooms(editMan.getText());
        packetbuilder.myRooms(packetbuilder.getLoginId());
    }
    
    public void updateMyReservationlist(ArrayList<Reservation> list)
    {
        String[][] temp = new String[list.size()][2];
        
        int i = 0;
        for(Reservation iter : list)
        {
            temp[i][0] = DateTeller.longtoString(iter.getDate());
            temp[i++][1] = iter.getRoom().getName();
        }
        
        listRoom.setListData(temp);
    }
    
    private void refreshRegList()
    {
        freeze();
        packetbuilder.queryRegistrations();
    }
    
    public void updateRegList(ArrayList<Account> list)
    {
        reglist = list;
        String[] temp = new String[list.size()];
        
        int i = 0;
        for(Account iter : list) temp[i++] = iter.getName();
        
        listReg.setListData(temp);
    }
    
    public void updateRegSection(Account inf)
    {
        if(inf == null)
        {
            labelIdReg.setText("");
            labelNameReg.setText("");
            labelPhoneReg.setText("");
            labelEmailReg.setText("");
            labelUniv_compReg.setText("");
            listReg.setSelectedIndex(-1);
            return;
        }
            
        labelIdReg.setText(inf.getId());
        labelNameReg.setText(inf.getName());
        labelPhoneReg.setText(inf.getPhonenum());
        labelEmailReg.setText(inf.getEmail());
        labelUniv_compReg.setText(inf.getUniv_comp());
    }
    
    public void updateResultTable(ArrayList<Room> list)
    {
        String[] columns = {"City", "RoomName", "MaxCap", "Cost"};
        
        DefaultTableModel model = new DefaultTableModel(null, columns)
                                        {
                                            @Override
                                            public boolean isCellEditable(int row, int col)
                                            {
                                                return false; 
                                            }
                                        };
        for(Room iter : list)
        {
            String[] item = new String[4];
            switch(iter.getCity())
            {
            case 0:
                item[0] = "Seoul";
                break;
                
            case 1:
                item[0] = "Daejeon";
                break;
                
            case 2:
                item[0] = "Daegu";
                break;
                
            case 3:
                item[0] = "Jeonju";
                break;
            }
            item[1] = iter.getName();
            item[2] = Integer.toString(iter.getMaxcapacity());
            item[3] = Float.toString(iter.getDefault_rentcost());
            
            model.addRow(item);
        }
        tableResult.setModel(model);
    }
    
    public void updateCalendar(int year, int month)
    {
        String[] strmonth = {   "January", "Febrary", "March", "April", 
                                "May", "June", "July", "August", 
                                "September", "October", "Novenber", "December"};
        
        labelYear.setText(Integer.toString(year));
        labelMonth.setText(strmonth[month]);
        
        calendar.setYM(year, month);
        calendar.updateCalendar();
    }
    
    void updateRoomInf(Room inf)
    {
        if(inf == null)
        {
            editRoomNameRoom.setText("Room Name");
            comboCityRoom.setSelectedIndex(0);
            editMaxcapRoom.setText("");
            editLocationRoom.setText("");
            labelNameOwner.setText("");
            labelCompanyOwner.setText("");
            labelPhoneOwner.setText("");
            labelEmailOwner.setText("");
            
            editDefaultcostRoom.setText("");
            editRentcostRoom.setText("");
            //btnProcRoom
        }
    }
    
    void setRoomInfEnabled(boolean e)
    {
        
    }
    
    void updateRsrvInf(float rentcost, boolean reserved, boolean reqcalcel)
    {
        editRentcostRoom.setText(Float.toString(rentcost));
        if(!reserved)
        {
            btnProcRoom.setText("Close Reservation");
            btnProcRoom.setEnabled(true);
        }
        else
        {
            btnProcRoom.setText("Cancel Reservation");
            if(reqcalcel) btnProcRoom.setEnabled(true);
            else btnProcRoom.setEnabled(false);
        }
    }
    
    private MainFrame()
    {
        packetbuilder = PacketBuilder.getInstance();
        initComponents();
        jDateChooser.setLocale(Locale.US);
        calendarBean.setLocale(Locale.US);
        refreshMyAccount();
        btnProcRoom.setEnabled(false);
        calendar = (MyCalendar)calendarBean;
        
        Date today = DateTeller.getTodayDate();
        updateCalendar(today.getYear(), today.getMonth());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panelSearch = new javax.swing.JPanel();
        panelOption = new javax.swing.JPanel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        comboCity = new javax.swing.JComboBox();
        editLocation = new javax.swing.JTextField();
        editRoomname = new javax.swing.JTextField();
        chkboxReserved = new javax.swing.JCheckBox();
        editMaxcap = new javax.swing.JFormattedTextField();
        editRentcost = new javax.swing.JFormattedTextField();
        labelMaxcap = new javax.swing.JLabel();
        labelRentcost = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        panelRoomInfo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        editRentcostRoom = new javax.swing.JFormattedTextField();
        editDefaultcostRoom = new javax.swing.JFormattedTextField();
        editMaxcapRoom = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        comboCityRoom = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        editLocationRoom = new javax.swing.JTextArea();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelNameOwner = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelCompanyOwner = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        labelPhoneOwner = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        labelEmailOwner = new javax.swing.JLabel();
        btnProcRoom = new javax.swing.JButton();
        editRoomNameRoom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        panelAux = new javax.swing.JTabbedPane();
        tabMyAcc = new javax.swing.JPanel();
        panelMyAcc = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        labelIdAcc = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        editNameAcc = new javax.swing.JTextField();
        editUniv_compAcc = new javax.swing.JTextField();
        editPwAcc = new javax.swing.JPasswordField();
        editPhoneAcc = new javax.swing.JFormattedTextField();
        editEmailAcc = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btnSubmitAcc = new javax.swing.JButton();
        btnCancelAcc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listRoom = new javax.swing.JList();
        jLabel24 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnSubmitRoom = new javax.swing.JButton();
        btnCancelRoom = new javax.swing.JButton();
        btnNewRoom = new javax.swing.JButton();
        btnEditRoom = new javax.swing.JButton();
        BtnRemoveRoom = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        panelSearchResult1 = new javax.swing.JScrollPane();
        tableRsrv = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listReg = new javax.swing.JList();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        labelIdReg = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        labelNameReg = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        labelUniv_compReg = new javax.swing.JLabel();
        labelPhoneReg = new javax.swing.JLabel();
        labelEmailReg = new javax.swing.JLabel();
        btnAcceptReg = new javax.swing.JButton();
        btnRejectReg = new javax.swing.JButton();
        panelSearchResult = new javax.swing.JScrollPane();
        tableResult = new javax.swing.JTable();
        panelCalendar = new javax.swing.JPanel();
        calendarBean = new MyCalendar()
        ;
        labelMonth = new javax.swing.JLabel();
        btnNextMonth = new javax.swing.JButton();
        btnPrevMonth = new javax.swing.JButton();
        labelYear = new javax.swing.JLabel();
        progBar = new javax.swing.JProgressBar();
        labelStatus = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        sep2 = new javax.swing.JSeparator();
        editMan = new javax.swing.JTextField();
        sep1 = new javax.swing.JSeparator();
        labelMan = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Room Agent v0.5");
        setResizable(false);

        panelSearch.setMaximumSize(new java.awt.Dimension(300, 705));
        panelSearch.setMinimumSize(new java.awt.Dimension(300, 705));
        panelSearch.setPreferredSize(new java.awt.Dimension(300, 705));

        panelOption.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Options"));
        panelOption.setPreferredSize(new java.awt.Dimension(250, 300));

        jDateChooser.setDateFormatString("yyyy - M - d");

        comboCity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seoul", "Daejeon", "Daegu", "Jeonju" }));

        editLocation.setText("Location");
        editLocation.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editLocationActionPerformed(evt);
            }
        });

        editRoomname.setText("Room Name");

        chkboxReserved.setSelected(true);
        chkboxReserved.setText("Exclude Reserved");
        chkboxReserved.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                chkboxReservedActionPerformed(evt);
            }
        });

        editMaxcap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        editMaxcap.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editMaxcapActionPerformed(evt);
            }
        });

        editRentcost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.##"))));

        labelMaxcap.setText("People or above");

        labelRentcost.setText("\\ or below");

        javax.swing.GroupLayout panelOptionLayout = new javax.swing.GroupLayout(panelOption);
        panelOption.setLayout(panelOptionLayout);
        panelOptionLayout.setHorizontalGroup(
            panelOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editRoomname, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboCity, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editLocation)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOptionLayout.createSequentialGroup()
                        .addComponent(editMaxcap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelMaxcap))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOptionLayout.createSequentialGroup()
                        .addComponent(editRentcost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelRentcost))
                    .addGroup(panelOptionLayout.createSequentialGroup()
                        .addComponent(chkboxReserved)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelOptionLayout.setVerticalGroup(
            panelOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editRoomname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkboxReserved)
                .addGap(11, 11, 11)
                .addGroup(panelOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMaxcap)
                    .addComponent(editMaxcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRentcost)
                    .addComponent(editRentcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSearch.setText("Search Rooms");
        btnSearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSearchLayout = new javax.swing.GroupLayout(panelSearch);
        panelSearch.setLayout(panelSearchLayout);
        panelSearchLayout.setHorizontalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelSearchLayout.setVerticalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRoomInfo.setBackground(new java.awt.Color(255, 255, 255));
        panelRoomInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("People");

        jLabel5.setText("Renting Cost");

        jLabel6.setText("Default Cost");

        jLabel9.setText("\\");

            jLabel10.setText("\\");

                editRentcostRoom.setEditable(false);
                editRentcostRoom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.##"))));

                editDefaultcostRoom.setEditable(false);
                editDefaultcostRoom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.##"))));

                editMaxcapRoom.setEditable(false);
                editMaxcapRoom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
                editMaxcapRoom.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        editMaxcapRoomActionPerformed(evt);
                    }
                });

                jLabel7.setText("Max");

                comboCityRoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seoul", "Daejeon", "Daegu", "Jeonju" }));
                comboCityRoom.setEnabled(false);

                editLocationRoom.setEditable(false);
                editLocationRoom.setColumns(20);
                editLocationRoom.setFont(new java.awt.Font("굴림", 0, 18)); // NOI18N
                editLocationRoom.setRows(3);
                jScrollPane3.setViewportView(editLocationRoom);

                jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Owner Infromation"));

                jLabel2.setText("Name");

                labelNameOwner.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

                jLabel11.setText("Company");

                labelCompanyOwner.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

                jLabel14.setText("Phone");

                labelPhoneOwner.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

                jLabel16.setText("Email");

                labelEmailOwner.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelEmailOwner, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelPhoneOwner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelCompanyOwner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelNameOwner, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(labelNameOwner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(labelCompanyOwner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(labelPhoneOwner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(labelEmailOwner))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                btnProcRoom.setText("Choose a Reservation");

                editRoomNameRoom.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
                editRoomNameRoom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                editRoomNameRoom.setText("Room Name");
                editRoomNameRoom.setToolTipText("");
                editRoomNameRoom.setEnabled(false);
                editRoomNameRoom.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        editRoomNameRoomActionPerformed(evt);
                    }
                });

                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/sample.png"))); // NOI18N

                javax.swing.GroupLayout panelRoomInfoLayout = new javax.swing.GroupLayout(panelRoomInfo);
                panelRoomInfo.setLayout(panelRoomInfoLayout);
                panelRoomInfoLayout.setHorizontalGroup(
                    panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(editRoomNameRoom)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRoomInfoLayout.createSequentialGroup()
                                .addComponent(comboCityRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editMaxcapRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProcRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(37, 37, 37)
                                .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                        .addComponent(editRentcostRoom)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                        .addComponent(editDefaultcostRoom)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)))))
                        .addContainerGap())
                );
                panelRoomInfoLayout.setVerticalGroup(
                    panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8)
                            .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel6)
                                            .addComponent(editDefaultcostRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(editRentcostRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5)))
                                        .addGap(6, 6, 6)
                                        .addComponent(btnProcRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(editRoomNameRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(comboCityRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)
                                            .addComponent(editMaxcapRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(9, 9, 9))
                );

                panelAux.addChangeListener(new javax.swing.event.ChangeListener()
                {
                    public void stateChanged(javax.swing.event.ChangeEvent evt)
                    {
                        panelAuxStateChanged(evt);
                    }
                });

                jLabel17.setText("ID");

                jLabel18.setText("Password");

                jLabel20.setText("Name");

                jLabel21.setText("E-mail");

                jLabel22.setText("Phone");

                jLabel23.setText("University or Company");

                try
                {
                    editPhoneAcc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-####-####")));
                } catch (java.text.ParseException ex)
                {
                    ex.printStackTrace();
                }

                btnSubmitAcc.setText("Submit");
                btnSubmitAcc.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        btnSubmitAccActionPerformed(evt);
                    }
                });

                btnCancelAcc.setText("Cancel");
                btnCancelAcc.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        btnCancelAccActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelMyAccLayout = new javax.swing.GroupLayout(panelMyAcc);
                panelMyAcc.setLayout(panelMyAccLayout);
                panelMyAccLayout.setHorizontalGroup(
                    panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMyAccLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(panelMyAccLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelIdAcc))
                            .addGroup(panelMyAccLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editPwAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMyAccLayout.createSequentialGroup()
                                .addGroup(panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editNameAcc)
                                    .addComponent(editPhoneAcc, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                            .addComponent(editUniv_compAcc)
                            .addComponent(editEmailAcc)
                            .addComponent(jSeparator3)
                            .addGroup(panelMyAccLayout.createSequentialGroup()
                                .addGroup(panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel23))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelMyAccLayout.createSequentialGroup()
                                .addComponent(btnSubmitAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(btnCancelAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );
                panelMyAccLayout.setVerticalGroup(
                    panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMyAccLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(labelIdAcc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(editPwAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(editNameAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(editPhoneAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addGap(3, 3, 3)
                        .addComponent(editEmailAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addGap(3, 3, 3)
                        .addComponent(editUniv_compAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                        .addGroup(panelMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSubmitAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );

                javax.swing.GroupLayout tabMyAccLayout = new javax.swing.GroupLayout(tabMyAcc);
                tabMyAcc.setLayout(tabMyAccLayout);
                tabMyAccLayout.setHorizontalGroup(
                    tabMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMyAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                tabMyAccLayout.setVerticalGroup(
                    tabMyAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMyAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                panelAux.addTab("My Account", tabMyAcc);

                listRoom.addMouseListener(new java.awt.event.MouseAdapter()
                {
                    public void mouseClicked(java.awt.event.MouseEvent evt)
                    {
                        listRoomMouseClicked(evt);
                    }
                });
                listRoom.addKeyListener(new java.awt.event.KeyAdapter()
                {
                    public void keyPressed(java.awt.event.KeyEvent evt)
                    {
                        listRoomKeyPressed(evt);
                    }
                });
                jScrollPane1.setViewportView(listRoom);

                jLabel24.setText("My Room List");

                btnSubmitRoom.setText("Submit");

                btnCancelRoom.setText("Cancel");
                btnCancelRoom.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        btnCancelRoomActionPerformed(evt);
                    }
                });

                btnNewRoom.setText("New Room");
                btnNewRoom.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        btnNewRoomActionPerformed(evt);
                    }
                });

                btnEditRoom.setText("Edit Room");
                btnEditRoom.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        btnEditRoomActionPerformed(evt);
                    }
                });

                BtnRemoveRoom.setText("Remove Room");
                BtnRemoveRoom.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        BtnRemoveRoomActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNewRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSubmitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(btnCancelRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnEditRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnRemoveRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnRemoveRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSubmitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );

                panelAux.addTab("My Room", jPanel2);

                jLabel26.setText("My Reservation List");

                tableRsrv.setAutoCreateRowSorter(true);
                tableRsrv.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][]
                    {

                    },
                    new String []
                    {
                        "Date", "Room Name"
                    }
                )
                {
                    boolean[] canEdit = new boolean []
                    {
                        false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex)
                    {
                        return canEdit [columnIndex];
                    }
                });
                tableRsrv.getTableHeader().setReorderingAllowed(false);
                tableRsrv.addMouseListener(new java.awt.event.MouseAdapter()
                {
                    public void mouseClicked(java.awt.event.MouseEvent evt)
                    {
                        tableRsrvMouseClicked(evt);
                    }
                });
                tableRsrv.addKeyListener(new java.awt.event.KeyAdapter()
                {
                    public void keyPressed(java.awt.event.KeyEvent evt)
                    {
                        tableRsrvKeyPressed(evt);
                    }
                });
                panelSearchResult1.setViewportView(tableRsrv);
                tableRsrv.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                if (tableRsrv.getColumnModel().getColumnCount() > 0)
                {
                    tableRsrv.getColumnModel().getColumn(0).setResizable(false);
                    tableRsrv.getColumnModel().getColumn(1).setResizable(false);
                }

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(jSeparator5)
                            .addComponent(panelSearchResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelSearchResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(237, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                panelAux.addTab("My Reservation", jPanel3);

                listReg.addMouseListener(new java.awt.event.MouseAdapter()
                {
                    public void mouseClicked(java.awt.event.MouseEvent evt)
                    {
                        listRegMouseClicked(evt);
                    }
                });
                listReg.addKeyListener(new java.awt.event.KeyAdapter()
                {
                    public void keyPressed(java.awt.event.KeyEvent evt)
                    {
                        listRegKeyPressed(evt);
                    }
                });
                jScrollPane2.setViewportView(listReg);

                jLabel25.setText("Registration Request List");

                jLabel27.setText("ID");

                jLabel29.setText("Name");

                jLabel31.setText("Phone");

                jLabel32.setText("E-mail");

                jLabel33.setText("University or Company");

                btnAcceptReg.setText("Accept");
                btnAcceptReg.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        btnAcceptRegActionPerformed(evt);
                    }
                });

                btnRejectReg.setText("Reject");
                btnRejectReg.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        btnRejectRegActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelIdReg))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelNameReg))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelPhoneReg))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelEmailReg))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnAcceptReg, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRejectReg, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(labelUniv_compReg))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel25)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(labelIdReg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(labelNameReg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(labelPhoneReg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(labelEmailReg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelUniv_compReg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAcceptReg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRejectReg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                panelAux.addTab("Registration Request Management", jPanel5);

                tableResult.setAutoCreateRowSorter(true);
                tableResult.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][]
                    {

                    },
                    new String []
                    {
                        "City", "RoomName", "MaxCap", "Cost"
                    }
                )
                {
                    Class[] types = new Class []
                    {
                        java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
                    };
                    boolean[] canEdit = new boolean []
                    {
                        false, false, false, false
                    };

                    public Class getColumnClass(int columnIndex)
                    {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex)
                    {
                        return canEdit [columnIndex];
                    }
                });
                tableResult.setColumnSelectionAllowed(true);
                tableResult.getTableHeader().setReorderingAllowed(false);
                tableResult.addMouseListener(new java.awt.event.MouseAdapter()
                {
                    public void mouseClicked(java.awt.event.MouseEvent evt)
                    {
                        tableResultMouseClicked(evt);
                    }
                });
                tableResult.addKeyListener(new java.awt.event.KeyAdapter()
                {
                    public void keyPressed(java.awt.event.KeyEvent evt)
                    {
                        tableResultKeyPressed(evt);
                    }
                });
                panelSearchResult.setViewportView(tableResult);
                tableResult.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                if (tableResult.getColumnModel().getColumnCount() > 0)
                {
                    tableResult.getColumnModel().getColumn(0).setResizable(false);
                    tableResult.getColumnModel().getColumn(1).setResizable(false);
                    tableResult.getColumnModel().getColumn(2).setResizable(false);
                    tableResult.getColumnModel().getColumn(3).setResizable(false);
                }

                panelCalendar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                calendarBean.setFont(new java.awt.Font("굴림", 1, 20)); // NOI18N

                labelMonth.setFont(new java.awt.Font("굴림", 0, 36)); // NOI18N
                labelMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                labelMonth.setText("March");

                btnNextMonth.setText("▶");
                btnNextMonth.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        btnNextMonthActionPerformed(evt);
                    }
                });

                btnPrevMonth.setText("◀");
                btnPrevMonth.setActionCommand("");
                btnPrevMonth.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        btnPrevMonthActionPerformed(evt);
                    }
                });

                labelYear.setFont(new java.awt.Font("굴림", 1, 20)); // NOI18N
                labelYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                labelYear.setText("2014");

                javax.swing.GroupLayout panelCalendarLayout = new javax.swing.GroupLayout(panelCalendar);
                panelCalendar.setLayout(panelCalendarLayout);
                panelCalendarLayout.setHorizontalGroup(
                    panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendarBean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCalendarLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnPrevMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(labelYear, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNextMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                panelCalendarLayout.setVerticalGroup(
                    panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCalendarLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnPrevMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNextMonth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCalendarLayout.createSequentialGroup()
                                .addGroup(panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelYear))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(calendarBean, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                labelStatus.setText("Idle State");

                jLabel12.setText("Current version is released at 2014-12-14");

                sep2.setOrientation(javax.swing.SwingConstants.VERTICAL);

                editMan.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        editManActionPerformed(evt);
                    }
                });

                sep1.setOrientation(javax.swing.SwingConstants.VERTICAL);

                labelMan.setText("Managing Account ID");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(panelRoomInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sep1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelMan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                        .addComponent(editMan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3))
                                    .addComponent(panelCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sep2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(progBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelAux, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelAux, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(panelRoomInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panelSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(panelCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(progBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sep2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sep1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(editMan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelMan))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                );

                pack();
                setLocationRelativeTo(null);
            }// </editor-fold>//GEN-END:initComponents

    private void editLocationActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editLocationActionPerformed
    {//GEN-HEADEREND:event_editLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editLocationActionPerformed

    private void chkboxReservedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chkboxReservedActionPerformed
    {//GEN-HEADEREND:event_chkboxReservedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkboxReservedActionPerformed

    private void editMaxcapActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editMaxcapActionPerformed
    {//GEN-HEADEREND:event_editMaxcapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editMaxcapActionPerformed

    private void editManActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editManActionPerformed
    {//GEN-HEADEREND:event_editManActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editManActionPerformed

    private void btnCancelRoomActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelRoomActionPerformed
    {//GEN-HEADEREND:event_btnCancelRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelRoomActionPerformed

    private void btnCancelAccActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelAccActionPerformed
    {//GEN-HEADEREND:event_btnCancelAccActionPerformed
        refreshMyAccount();
    }//GEN-LAST:event_btnCancelAccActionPerformed

    private void btnEditRoomActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEditRoomActionPerformed
    {//GEN-HEADEREND:event_btnEditRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditRoomActionPerformed

    private void btnNewRoomActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnNewRoomActionPerformed
    {//GEN-HEADEREND:event_btnNewRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewRoomActionPerformed

    private void BtnRemoveRoomActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BtnRemoveRoomActionPerformed
    {//GEN-HEADEREND:event_BtnRemoveRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRemoveRoomActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSearchActionPerformed
    {//GEN-HEADEREND:event_btnSearchActionPerformed
        int maxcap = editMaxcap.getText().equals("") ? -1 : Integer.parseInt(editMaxcap.getText());
        float rentcost = editRentcost.getText().equals("") ? -1.0f : Integer.parseInt(editRentcost.getText());
        
        Room temproom = new Room(   null, 
                                    editRoomname.getText(),
                                    comboCity.getSelectedIndex(),
                                    editLocation.getText(),
                                    maxcap,
                                    0.0f);
        
        Reservation temprsrv = new Reservation( null,
                                                temproom,
                                                DateTeller.Datetolong(jDateChooser.getDate()),
                                                rentcost,
                                                false,
                                                !chkboxReserved.isSelected());
        freeze();
        calendar.setReservations(null);
        updateCalendar(jDateChooser.getDate().getYear(), jDateChooser.getDate().getMonth());
        packetbuilder.searchRooms(temprsrv);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void panelAuxStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_panelAuxStateChanged
    {//GEN-HEADEREND:event_panelAuxStateChanged
        switch(panelAux.getSelectedIndex())
        {
        case 0: // My Account
            refreshMyAccount();
            break;
        case 1: // My Room
            refreshMyRoomlist();
            break;
        case 2: // My Reservation
            refreshMyReservationlist();
            break;
        case 3: // Reg List
            refreshRegList();
            break;
        }
    }//GEN-LAST:event_panelAuxStateChanged

    private void listRegMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_listRegMouseClicked
    {//GEN-HEADEREND:event_listRegMouseClicked
        updateRegSection(reglist.get(listReg.getSelectedIndex()));
    }//GEN-LAST:event_listRegMouseClicked

    private void listRegKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_listRegKeyPressed
    {//GEN-HEADEREND:event_listRegKeyPressed
        listRegMouseClicked(null);
    }//GEN-LAST:event_listRegKeyPressed

    private void btnSubmitAccActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSubmitAccActionPerformed
    {//GEN-HEADEREND:event_btnSubmitAccActionPerformed
        if(JOptionPane.showConfirmDialog(new JFrame(),
               STR.CONFIRM_CONTENT, STR.CONFIRM_TITLE, JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) return;
        freeze();
        Account inf = new Account(  labelIdAcc.getText(),
                                    new String(editPwAcc.getPassword()),
                                    0,
                                    editNameAcc.getText(),
                                    editEmailAcc.getText(),
                                    editPhoneAcc.getText(),
                                    editUniv_compAcc.getText());
        packetbuilder.editAccount(inf);
    }//GEN-LAST:event_btnSubmitAccActionPerformed

    private void btnAcceptRegActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAcceptRegActionPerformed
    {//GEN-HEADEREND:event_btnAcceptRegActionPerformed
        if(listReg.getSelectedIndex() == -1)
        {
            showDialog(STR.NOTI_NOT_SELECTED);
            return;
        }
        
        freeze();
        packetbuilder.acceptRegistration(reglist.get(listReg.getSelectedIndex()).getId());
    }//GEN-LAST:event_btnAcceptRegActionPerformed

    private void btnRejectRegActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnRejectRegActionPerformed
    {//GEN-HEADEREND:event_btnRejectRegActionPerformed
        if(listReg.getSelectedIndex() == -1)
        {
            showDialog(STR.NOTI_NOT_SELECTED);
            return;
        }
        
        freeze();
        packetbuilder.rejectRegistration(reglist.get(listReg.getSelectedIndex()).getId());
    }//GEN-LAST:event_btnRejectRegActionPerformed

    private void tableRsrvMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tableRsrvMouseClicked
    {//GEN-HEADEREND:event_tableRsrvMouseClicked
        freeze();
        int selrow = tableRsrv.getSelectedRow();
        Date targetdate = DateTeller.StringtoDate((String)tableRsrv.getValueAt(selrow, 0));
        if(targetdate == null) targetdate = DateTeller.getTodayDate();
        updateCalendar(targetdate.getYear(), targetdate.getMonth());
        packetbuilder.queryReservations((String)tableRsrv.getValueAt(selrow, 1));
    }//GEN-LAST:event_tableRsrvMouseClicked

    private void tableRsrvKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tableRsrvKeyPressed
    {//GEN-HEADEREND:event_tableRsrvKeyPressed
        tableRsrvMouseClicked(null);                                         
    }//GEN-LAST:event_tableRsrvKeyPressed

    private void editMaxcapRoomActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editMaxcapRoomActionPerformed
    {//GEN-HEADEREND:event_editMaxcapRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editMaxcapRoomActionPerformed

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnNextMonthActionPerformed
    {//GEN-HEADEREND:event_btnNextMonthActionPerformed
        calendar.toNextMonth();
    }//GEN-LAST:event_btnNextMonthActionPerformed

    private void btnPrevMonthActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPrevMonthActionPerformed
    {//GEN-HEADEREND:event_btnPrevMonthActionPerformed
        calendar.toPrevMonth();
    }//GEN-LAST:event_btnPrevMonthActionPerformed

    private void tableResultMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tableResultMouseClicked
    {//GEN-HEADEREND:event_tableResultMouseClicked
        freeze();
        int selrow = tableResult.getSelectedRow();
        packetbuilder.queryReservations((String)tableRsrv.getValueAt(selrow, 1));
    }//GEN-LAST:event_tableResultMouseClicked

    private void tableResultKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_tableResultKeyPressed
    {//GEN-HEADEREND:event_tableResultKeyPressed
        tableResultMouseClicked(null);
    }//GEN-LAST:event_tableResultKeyPressed

    private void listRoomMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_listRoomMouseClicked
    {//GEN-HEADEREND:event_listRoomMouseClicked
        freeze();
        Date today = DateTeller.getTodayDate();
        updateCalendar(today.getYear(), today.getMonth());
        packetbuilder.queryReservations((String)listRoom.getModel().getElementAt(listRoom.getSelectedIndex()));
    }//GEN-LAST:event_listRoomMouseClicked

    private void listRoomKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_listRoomKeyPressed
    {//GEN-HEADEREND:event_listRoomKeyPressed
        listRoomMouseClicked(null);
    }//GEN-LAST:event_listRoomKeyPressed

    private void editRoomNameRoomActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editRoomNameRoomActionPerformed
    {//GEN-HEADEREND:event_editRoomNameRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editRoomNameRoomActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRemoveRoom;
    private javax.swing.JButton btnAcceptReg;
    private javax.swing.JButton btnCancelAcc;
    private javax.swing.JButton btnCancelRoom;
    private javax.swing.JButton btnEditRoom;
    private javax.swing.JButton btnNewRoom;
    private javax.swing.JButton btnNextMonth;
    private javax.swing.JButton btnPrevMonth;
    private javax.swing.JButton btnProcRoom;
    private javax.swing.JButton btnRejectReg;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSubmitAcc;
    private javax.swing.JButton btnSubmitRoom;
    private com.toedter.calendar.JDayChooser calendarBean;
    private javax.swing.JCheckBox chkboxReserved;
    private javax.swing.JComboBox comboCity;
    private javax.swing.JComboBox comboCityRoom;
    private javax.swing.JFormattedTextField editDefaultcostRoom;
    private javax.swing.JTextField editEmailAcc;
    private javax.swing.JTextField editLocation;
    private javax.swing.JTextArea editLocationRoom;
    private javax.swing.JTextField editMan;
    private javax.swing.JFormattedTextField editMaxcap;
    private javax.swing.JFormattedTextField editMaxcapRoom;
    private javax.swing.JTextField editNameAcc;
    private javax.swing.JFormattedTextField editPhoneAcc;
    private javax.swing.JPasswordField editPwAcc;
    private javax.swing.JFormattedTextField editRentcost;
    private javax.swing.JFormattedTextField editRentcostRoom;
    private javax.swing.JTextField editRoomNameRoom;
    private javax.swing.JTextField editRoomname;
    private javax.swing.JTextField editUniv_compAcc;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel labelCompanyOwner;
    private javax.swing.JLabel labelEmailOwner;
    private javax.swing.JLabel labelEmailReg;
    private javax.swing.JLabel labelIdAcc;
    private javax.swing.JLabel labelIdReg;
    private javax.swing.JLabel labelMan;
    private javax.swing.JLabel labelMaxcap;
    private javax.swing.JLabel labelMonth;
    private javax.swing.JLabel labelNameOwner;
    private javax.swing.JLabel labelNameReg;
    private javax.swing.JLabel labelPhoneOwner;
    private javax.swing.JLabel labelPhoneReg;
    private javax.swing.JLabel labelRentcost;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelUniv_compReg;
    private javax.swing.JLabel labelYear;
    private javax.swing.JList listReg;
    private javax.swing.JList listRoom;
    private javax.swing.JTabbedPane panelAux;
    private javax.swing.JPanel panelCalendar;
    private javax.swing.JPanel panelMyAcc;
    private javax.swing.JPanel panelOption;
    private javax.swing.JPanel panelRoomInfo;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JScrollPane panelSearchResult;
    private javax.swing.JScrollPane panelSearchResult1;
    private javax.swing.JProgressBar progBar;
    private javax.swing.JSeparator sep1;
    private javax.swing.JSeparator sep2;
    private javax.swing.JPanel tabMyAcc;
    private javax.swing.JTable tableResult;
    private javax.swing.JTable tableRsrv;
    // End of variables declaration//GEN-END:variables
}


class MyCalendar extends com.toedter.calendar.JDayChooser
{
    ArrayList<Reservation> list;
    
    private int firstbtnindex;
    private int year;
    private int month;
        
    public void updateCalendar()
    {
        clearDayColors();
        
        if(list == null) return;
            
        for(Reservation iter : list)
        {
            if(DateTeller.longtoDate(iter.getDate()).getYear() != year) continue;
            if(DateTeller.longtoDate(iter.getDate()).getMonth() != month) continue;
            if(iter.isReqcancel())
                setDayColor(DateTeller.longtoDate(iter.getDate()).getDay(), Color.YELLOW);
            else if(iter.isReserved())
                setDayColor(DateTeller.longtoDate(iter.getDate()).getDay(), Color.BLUE);
            else setDayColor(DateTeller.longtoDate(iter.getDate()).getDay(), Color.GREEN);
        }
    }
    
    void setYM(int year, int month)
    {
        if(year != -1) this.year = year;
        if(month != -1) this.month = month;
        setYear(year);
        setMonth(year);
    }
    
    public void toNextMonth()
    {
        if(month++ > 11)
        {
            year++;
            month = 0;
        }
        MainFrame.getInstance().updateCalendar(year, month);
    }
    
    public void toPrevMonth()
    {
        if(month-- < 0)
        {
            year--;
            month = 11;
        }
        MainFrame.getInstance().updateCalendar(year, month);
    }
    
    public ArrayList<Reservation> getReservations()
    {
        return list;
    }
    
    public void setReservations(ArrayList<Reservation> list)
    {
        this.list = list;
    }
    
    
    private void setDayColor(int i, Color c)
    {
        days[firstbtnindex+i-1].setForeground(c);
    }

    private void clearDayColors()
    {
        for(int i=7 ; i<days.length ; i++)
            days[i].setForeground(Color.BLACK);
        
        for(int i=7 ; i<days.length ; i++)
            if(days[i].isVisible())
            {
                firstbtnindex = i;
                break;
            }
    }
}