package ui;

import control.*;
import datatype.*;
        
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.toedter.calendar.*;
import java.util.ArrayList;
import java.util.Locale;

public class MainFrame extends javax.swing.JFrame
{
    private static MainFrame self;
    private static int accesstype;
    
    private PacketBuilder packetbuilder;
            
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
        if(!editManagingId.getText().equals(""))
            packetbuilder.myAccount(editManagingId.getText());
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
    
    private MainFrame()
    {
        packetbuilder = PacketBuilder.getInstance();
        initComponents();
        jDateChooser.setLocale(Locale.US);
        jCalendar.setLocale(Locale.US);
        refreshMyAccount();
        btnProcRsrv.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panelSearch = new javax.swing.JPanel();
        panelOption = new javax.swing.JPanel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        comboCity = new javax.swing.JComboBox();
        EditLocation = new javax.swing.JTextField();
        editRoomname = new javax.swing.JTextField();
        chkboxReserved = new javax.swing.JCheckBox();
        editMaxcap = new javax.swing.JFormattedTextField();
        editRentcost = new javax.swing.JFormattedTextField();
        labelMaxcap = new javax.swing.JLabel();
        labelRentcost = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        panelRoomInfo = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnProcRsrv = new javax.swing.JButton();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        comboCity1 = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        listRsrv = new javax.swing.JList();
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
        jTable1 = new javax.swing.JTable();
        panelCalendar = new javax.swing.JPanel();
        jCalendar = new com.toedter.calendar.JCalendar();
        progBar = new javax.swing.JProgressBar();
        labelStatus = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        editManagingId = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel39 = new javax.swing.JLabel();

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

        EditLocation.setText("Location");
        EditLocation.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                EditLocationActionPerformed(evt);
            }
        });

        editRoomname.setText("Room Name");

        chkboxReserved.setSelected(true);
        chkboxReserved.setText("Exclude reserved");
        chkboxReserved.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                chkboxReservedActionPerformed(evt);
            }
        });

        try
        {
            editMaxcap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex)
        {
            ex.printStackTrace();
        }
        editMaxcap.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editMaxcapActionPerformed(evt);
            }
        });

        try
        {
            editRentcost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex)
        {
            ex.printStackTrace();
        }

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
                    .addComponent(EditLocation)
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
                .addComponent(EditLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("굴림", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Room Name");

        jLabel3.setText("Location");

        jLabel4.setText("People");

        jLabel5.setText("Renting Cost");

        jLabel6.setText("Default Cost");

        jLabel9.setText("\\");

            jLabel10.setText("\\");

                jLabel11.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
                jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel11.setText("Status");

                btnProcRsrv.setText("Reserve");

                jFormattedTextField6.setEditable(false);
                try
                {
                    jFormattedTextField6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
                } catch (java.text.ParseException ex)
                {
                    ex.printStackTrace();
                }

                jFormattedTextField7.setEditable(false);
                try
                {
                    jFormattedTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
                } catch (java.text.ParseException ex)
                {
                    ex.printStackTrace();
                }

                jFormattedTextField8.setEditable(false);
                try
                {
                    jFormattedTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
                } catch (java.text.ParseException ex)
                {
                    ex.printStackTrace();
                }
                jFormattedTextField8.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        jFormattedTextField8ActionPerformed(evt);
                    }
                });

                jLabel7.setText("Max");

                comboCity1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seoul", "Daejeon", "Daegu", "Jeonju" }));
                comboCity1.setEnabled(false);

                jTextArea1.setEditable(false);
                jTextArea1.setColumns(20);
                jTextArea1.setFont(new java.awt.Font("굴림", 0, 18)); // NOI18N
                jTextArea1.setRows(3);
                jScrollPane3.setViewportView(jTextArea1);

                javax.swing.GroupLayout panelRoomInfoLayout = new javax.swing.GroupLayout(panelRoomInfo);
                panelRoomInfo.setLayout(panelRoomInfoLayout);
                panelRoomInfoLayout.setHorizontalGroup(
                    panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                .addComponent(comboCity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))
                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnProcRsrv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(jFormattedTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3)
                                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(95, 95, 95)
                                        .addComponent(jFormattedTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)))))
                        .addContainerGap())
                );
                panelRoomInfoLayout.setVerticalGroup(
                    panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoomInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRoomInfoLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelRoomInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(comboCity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnProcRsrv, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSubmitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );

                panelAux.addTab("My Room", jPanel2);

                jLabel26.setText("My Reservation List");

                listRsrv.addMouseListener(new java.awt.event.MouseAdapter()
                {
                    public void mouseClicked(java.awt.event.MouseEvent evt)
                    {
                        listRsrvMouseClicked(evt);
                    }
                });
                listRsrv.addKeyListener(new java.awt.event.KeyAdapter()
                {
                    public void keyPressed(java.awt.event.KeyEvent evt)
                    {
                        listRsrvKeyPressed(evt);
                    }
                });
                jScrollPane4.setViewportView(listRsrv);

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(jSeparator5)
                            .addComponent(jScrollPane4))
                        .addContainerGap())
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(272, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
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

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][]
                    {

                    },
                    new String []
                    {
                        "City", "RoomName", "MaxCap", "Cost"
                    }
                )
                {
                    boolean[] canEdit = new boolean []
                    {
                        false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex)
                    {
                        return canEdit [columnIndex];
                    }
                });
                jTable1.setColumnSelectionAllowed(true);
                jTable1.getTableHeader().setReorderingAllowed(false);
                panelSearchResult.setViewportView(jTable1);
                jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                if (jTable1.getColumnModel().getColumnCount() > 0)
                {
                    jTable1.getColumnModel().getColumn(0).setResizable(false);
                    jTable1.getColumnModel().getColumn(1).setResizable(false);
                    jTable1.getColumnModel().getColumn(2).setResizable(false);
                    jTable1.getColumnModel().getColumn(3).setResizable(false);
                }

                panelCalendar.setBackground(new java.awt.Color(255, 255, 255));
                panelCalendar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                javax.swing.GroupLayout panelCalendarLayout = new javax.swing.GroupLayout(panelCalendar);
                panelCalendar.setLayout(panelCalendarLayout);
                panelCalendarLayout.setHorizontalGroup(
                    panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                panelCalendarLayout.setVerticalGroup(
                    panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                );

                labelStatus.setText("Idle State");

                jLabel12.setText("Current version is released at 2014-12-14");

                jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

                editManagingId.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        editManagingIdActionPerformed(evt);
                    }
                });

                jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

                jLabel39.setText("Managing Account ID");

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
                                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editManagingId, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3))
                                    .addComponent(panelCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(progBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelAux, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelAux))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(panelRoomInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panelSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(panelCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(progBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(editManagingId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel39))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3))
                );

                pack();
                setLocationRelativeTo(null);
            }// </editor-fold>//GEN-END:initComponents

    private void EditLocationActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_EditLocationActionPerformed
    {//GEN-HEADEREND:event_EditLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditLocationActionPerformed

    private void chkboxReservedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chkboxReservedActionPerformed
    {//GEN-HEADEREND:event_chkboxReservedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkboxReservedActionPerformed

    private void editMaxcapActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editMaxcapActionPerformed
    {//GEN-HEADEREND:event_editMaxcapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editMaxcapActionPerformed

    private void editManagingIdActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editManagingIdActionPerformed
    {//GEN-HEADEREND:event_editManagingIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editManagingIdActionPerformed

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

    private void jFormattedTextField8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jFormattedTextField8ActionPerformed
    {//GEN-HEADEREND:event_jFormattedTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField8ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSearchActionPerformed
    {//GEN-HEADEREND:event_btnSearchActionPerformed
        //Room temproom = new Room();
        //Reservation temprsrv = new Reservation();
        packetbuilder.searchRooms(null);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void panelAuxStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_panelAuxStateChanged
    {//GEN-HEADEREND:event_panelAuxStateChanged
        switch(panelAux.getSelectedIndex())
        {
        case 0: // My Account
            refreshMyAccount();
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

    private void listRsrvKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_listRsrvKeyPressed
    {//GEN-HEADEREND:event_listRsrvKeyPressed
        listRsrvMouseClicked(null);
    }//GEN-LAST:event_listRsrvKeyPressed

    private void listRsrvMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_listRsrvMouseClicked
    {//GEN-HEADEREND:event_listRsrvMouseClicked
        freeze();
        Account inf = new Account(  labelIdAcc.getText(),
                                    new String(editPwAcc.getPassword()),
                                    0,
                                    editNameAcc.getText(),
                                    editEmailAcc.getText(),
                                    editPhoneAcc.getText(),
                                    editUniv_compAcc.getText());
        packetbuilder.editAccount(inf);        
    }//GEN-LAST:event_listRsrvMouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRemoveRoom;
    private javax.swing.JTextField EditLocation;
    private javax.swing.JButton btnAcceptReg;
    private javax.swing.JButton btnCancelAcc;
    private javax.swing.JButton btnCancelRoom;
    private javax.swing.JButton btnEditRoom;
    private javax.swing.JButton btnNewRoom;
    private javax.swing.JButton btnProcRsrv;
    private javax.swing.JButton btnRejectReg;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSubmitAcc;
    private javax.swing.JButton btnSubmitRoom;
    private javax.swing.JCheckBox chkboxReserved;
    private javax.swing.JComboBox comboCity;
    private javax.swing.JComboBox comboCity1;
    private javax.swing.JTextField editEmailAcc;
    private javax.swing.JTextField editManagingId;
    private javax.swing.JFormattedTextField editMaxcap;
    private javax.swing.JTextField editNameAcc;
    private javax.swing.JFormattedTextField editPhoneAcc;
    private javax.swing.JPasswordField editPwAcc;
    private javax.swing.JFormattedTextField editRentcost;
    private javax.swing.JTextField editRoomname;
    private javax.swing.JTextField editUniv_compAcc;
    private com.toedter.calendar.JCalendar jCalendar;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelEmailReg;
    private javax.swing.JLabel labelIdAcc;
    private javax.swing.JLabel labelIdReg;
    private javax.swing.JLabel labelMaxcap;
    private javax.swing.JLabel labelNameReg;
    private javax.swing.JLabel labelPhoneReg;
    private javax.swing.JLabel labelRentcost;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelUniv_compReg;
    private javax.swing.JList listReg;
    private javax.swing.JList listRoom;
    private javax.swing.JList listRsrv;
    private javax.swing.JTabbedPane panelAux;
    private javax.swing.JPanel panelCalendar;
    private javax.swing.JPanel panelMyAcc;
    private javax.swing.JPanel panelOption;
    private javax.swing.JPanel panelRoomInfo;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JScrollPane panelSearchResult;
    private javax.swing.JProgressBar progBar;
    private javax.swing.JPanel tabMyAcc;
    // End of variables declaration//GEN-END:variables
}
