
package ui;

import datatype.Account;
import javax.swing.*;
import java.util.regex.Pattern;


public class RegisterFrame extends JFrame
{
    private static RegisterFrame self;
    public static RegisterFrame getInstance()
    {
        synchronized(RegisterFrame.class)
	{	
            if(self == null) self = new RegisterFrame();
            return self;
        }
    }
    
    private RegisterFrame()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        editId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        editPw = new javax.swing.JPasswordField();
        editName = new javax.swing.JTextField();
        editPhone = new javax.swing.JFormattedTextField();
        editEmail = new javax.swing.JTextField();
        editUniv_comp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registration");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosed(java.awt.event.WindowEvent evt)
            {
                formWindowClosed(evt);
            }
        });

        jLabel27.setText("ID");

        jLabel29.setText("Name");

        jLabel31.setText("Phone");

        jLabel32.setText("E-mail");

        jLabel33.setText("University or Company");

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSubmitActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel1.setText("Password");

        try
        {
            editPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-####-####")));
        } catch (java.text.ParseException ex)
        {
            ex.printStackTrace();
        }

        jLabel2.setText("Type");

        comboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "User", "Staff", "Manager" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(editUniv_comp)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel33)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel29)
                                .addComponent(jLabel31)
                                .addComponent(jLabel32))
                            .addGap(78, 78, 78)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(editPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(editEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(editName)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel27))
                            .addGap(90, 90, 90)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(editId)
                                .addComponent(comboType, 0, 130, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(48, 48, 48)
                            .addComponent(editPw, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(editId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(editPw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(editName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(editPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(editEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editUniv_comp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSubmitActionPerformed
    {//GEN-HEADEREND:event_btnSubmitActionPerformed
        Account inf = new Account(  editId.getText(),
                                    new String(editPw.getPassword()),
                                    comboType.getSelectedIndex(),
                                    editName.getText(),
                                    editEmail.getText(),
                                    editPhone.getText(),
                                    editUniv_comp.getText() );
        if(!validateAccountForm(inf))
        {
            showDialog(STR.NOTI_INVALID_FORM);
            return;
        }
            
        this.setEnabled(false);
        control.PacketBuilder.getInstance().register(inf);
    }//GEN-LAST:event_btnSubmitActionPerformed

    
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelActionPerformed
    {//GEN-HEADEREND:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosed
    {//GEN-HEADEREND:event_formWindowClosed
        LoginFrame.getInstance().setEnabled(true);
        LoginFrame.getInstance().setAlwaysOnTop(true);
        LoginFrame.getInstance().setAlwaysOnTop(false);
    }//GEN-LAST:event_formWindowClosed

    public void showDialog(String msg)
    {
        JOptionPane.showMessageDialog(new JFrame(), msg);
    }
    
    /*** check if it's valid form of Account information ***/
    // valid	: true
    // invalid	: false
    private boolean validateAccountForm(Account inf)
    {
            Pattern p = Pattern.compile("[^a-zA-Z0-9]");
            if(p.matcher(inf.getId()).find())		return false; // check if ID's alphanumeric
            if(p.matcher(inf.getPw()).find())		return false; // check if PW's alphanumeric

            int type = inf.getType();
            if(type > 3 || type < 1)				return false; // check type's range

            String nametemp = inf.getName();
            nametemp = nametemp.replace(" ", "");
            if(nametemp.equals(""))					return false; // check if name's just blank

            if(	!inf.getEmail().contains("@") ||
                    !inf.getEmail().contains("."))		return false; // check if email contains @, .

            p = Pattern.compile("/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/");
            if(p.matcher(inf.getPhonenum()).find())	return false; // check if Phonenum's numeric
            String univ_comptemp = inf.getUniv_comp();				
            univ_comptemp.replace(" ", "");
            if(univ_comptemp.equals(""))			return false; // check if univ_comp's just blank

            return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox comboType;
    private javax.swing.JTextField editEmail;
    private javax.swing.JTextField editId;
    private javax.swing.JTextField editName;
    private javax.swing.JFormattedTextField editPhone;
    private javax.swing.JPasswordField editPw;
    private javax.swing.JTextField editUniv_comp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    // End of variables declaration//GEN-END:variables
}
