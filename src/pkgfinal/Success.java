/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopyOptions;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author beytu
 */
public class Success extends javax.swing.JFrame {
public String shownTable;
private String userName,password,dbName;
public  String url;
    public void yazdir (){
        
        System.out.println(url);
    }

   
    public String getShownTable() {
        return shownTable;
    }

    public void setShownTable(String shownTable) {
        this.shownTable = shownTable;
        System.out.println("success içinde shown"+this.shownTable);
        getShown(shownTable);
    }
    public String getShown(String yolla){
        String a;
        a = yolla;
       this.shownTable=a;
        return a;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

  
    /**
     * Creates new form success
     */
    public Success() {
        initComponents();
        show_Data();
    }
    public ArrayList<Data> dataList (){
        Login Login = new Login();      
        url= Login.getUrl();
       
      
        
        System.out.println("bu combo için url"+url);
              Connection con = null;
    try {
        con = DriverManager.getConnection(url);
    } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }

        String[] types = {"TABLE"};
DatabaseMetaData metadata = null;
    try {
        metadata = con.getMetaData();
    } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
ResultSet resultSet = null;
    try {
        resultSet = metadata.getTables(null, null, "%", types);
    } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      
       
        while (resultSet.next())
        {
            String tableName = resultSet.getString(3);
            System.out.println(tableName);
            System.out.println(tableName);
            tablesCombo.addItem(tableName);
            jComboBox1.addItem(tableName);
        }   // TODO add your handling code here:
    } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
    
        
        
        
        
        
        
        
        
        
        
        
        shownTable=tablesCombo.getSelectedItem().toString();
               
        System.out.println("datalist içinde --success "+ shownTable+"    "+url);
            ArrayList<Data> dataList = new ArrayList();
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            con = DriverManager.getConnection(url);
            String query1= "SELECT * FROM "+shownTable;
            Statement st =con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Data data;
            while(rs.next()){
               
                data = new Data(rs.getString("Sirket"),rs.getString("Organizasyon Adi"),rs.getString("Depo Kodu"),
                rs.getString("Depo Statusu"),rs.getString("Stok Adresi"),
                rs.getString("Adres Statusu"),rs.getString("Kalem Kodu"),rs.getString("Kalem Tanimi"),rs.getString("Eldeki Miktar"),
                        rs.getString("Rezervasyon Miktari"),rs.getString("Kullanilabilir Miktar"),
                rs.getString("Olcu Birimi"),rs.getString("Seri No"),rs.getString("Proje"),rs.getString("Sabit Kiymet")
                        ,rs.getString("Maliyet Grubu"),rs.getString("Maliyet Grubu Tanimi"),
                rs.getString("Birim Maliyet"),rs.getString("Tutar"),rs.getString("Rezervasyon No"),rs.getString("Sayim Kesit Tarihi"));
            dataList.add(data);
          
            }
            
            }
            catch(Exception e) {
        JOptionPane.showMessageDialog(null,e);
       }
            
            return dataList;
            
            
            }

   

    public void setUrl(String url) {
        this.url = url;
    }
    public void show_Data(){
        
        ArrayList<Data> list = dataList(); 
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        
        Object[] row = new Object [21];
        for(int i = 0;i<list.size();i++){
            row[0] = list.get(i).getSirket();
              row[1] = list.get(i).getOrganizasyonAdi();
                row[2] = list.get(i).getDepoKodu();
                  row[3] = list.get(i).getDepoStatusu();
                    row[4] = list.get(i).getStokAdresi();
                      row[5] = list.get(i).getAdresStatusu();
                        row[6] = list.get(i).getKalemKodu();
                          row[7] = list.get(i).getKalemTanimi();
                            row[8] = list.get(i).getEldekiMiktar();
                              row[9] = list.get(i).getRezervasyonMiktari();
                                row[10] = list.get(i).getKullanilabilirMiktar();
                                  row[11] = list.get(i).getOlcuBirimi();
                                    row[12] = list.get(i).getSeriNo();
                                      row[13] = list.get(i).getProje();
                                        row[14] = list.get(i).getSabitKiymet();
                                          row[15] = list.get(i).getMaliyetGrubu();
                                            row[16] = list.get(i).getMaliyetGrubuTanimi();
                                              row[17] = list.get(i).getBirimMaliyet();
                                                row[18] = list.get(i).getTutar();
                                                  row[19] = list.get(i).getRezervasyonNo();
                                                    row[20] = list.get(i).getSayimKesitTarihi();
                                                    model.addRow(row);
                                                    
            
            
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        sirketBtn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tutarBtn = new javax.swing.JTextField();
        depoStatusuBtn = new javax.swing.JTextField();
        sabitKiymetBtn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        eldekiMiktarBtn = new javax.swing.JTextField();
        kalemKoduBtn = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        rezervasyonMiktariBtn = new javax.swing.JTextField();
        resetBtn = new javax.swing.JButton();
        stokAdresiBtn = new javax.swing.JTextField();
        organizasyonAdiBtn = new javax.swing.JTextField();
        adresStatusuBtn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        seriNoBtn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        maliyetGrubuBtn = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rezervasyonNoBtn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        birimMaliyetBtn = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        kullanılabilirMiktarBtn = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        depoKoduBtn = new javax.swing.JTextField();
        projeBtn = new javax.swing.JTextField();
        maliyetGrubuTanimiBtn = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        createBtn = new javax.swing.JButton();
        createName = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        excelToDbBtn = new javax.swing.JButton();
        excelText = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tablesCombo = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        olcuBirimiBtn = new javax.swing.JTextField();
        sayimKesitTarihiBtn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        kalemTanimiBtn = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(377, 377, 377))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(696, 696, 696)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        jLabel18.setText("Birim Maliyet ");

        sirketBtn.setText("jTextField1");
        sirketBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sirketBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Adres Statusu ");

        jLabel1.setText("Sirket");

        jLabel17.setText("Maliyet Grubu Tanimi ");

        tutarBtn.setText("jTextField4");

        depoStatusuBtn.setText("jTextField4");

        sabitKiymetBtn.setText("jTextField5");

        jLabel3.setText("Depo Kodu ");

        eldekiMiktarBtn.setText("jTextField4");

        kalemKoduBtn.setText("jTextField2");

        jLabel13.setText("Seri No ");

        rezervasyonMiktariBtn.setText("jTextField5");

        resetBtn.setText("Reset");

        stokAdresiBtn.setText("jTextField5");

        organizasyonAdiBtn.setText("jTextField2");

        adresStatusuBtn.setText("jTextField1");
        adresStatusuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adresStatusuBtnActionPerformed(evt);
            }
        });

        jLabel8.setText("Kalem Tanimi ");

        jLabel9.setText("Eldeki Miktar ");

        seriNoBtn.setText("jTextField3");

        jLabel2.setText("Organizasyon Adi ");

        jLabel15.setText("Sabit Kiymet ");

        maliyetGrubuBtn.setText("jTextField1");
        maliyetGrubuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maliyetGrubuBtnActionPerformed(evt);
            }
        });

        jLabel10.setText("Rezervasyon Miktari ");

        jLabel20.setText("Rezervasyon No ");

        rezervasyonNoBtn.setText("jTextField5");

        jLabel12.setText("Olcu Birimi ");

        birimMaliyetBtn.setText("jTextField3");

        jLabel11.setText("Kullanilabilir Miktar ");

        jLabel21.setText("Sayim Kesit Tarihi ");

        jLabel7.setText("Kalem Kodu ");

        jLabel19.setText("Tutar ");

        jLabel4.setText("Depo Statusu ");

        kullanılabilirMiktarBtn.setText("jTextField1");
        kullanılabilirMiktarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kullanılabilirMiktarBtnActionPerformed(evt);
            }
        });

        jLabel14.setText("Proje ");

        depoKoduBtn.setText("jTextField3");

        projeBtn.setText("jTextField4");

        maliyetGrubuTanimiBtn.setText("jTextField2");

        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sirket ", "Organizasyon Adi ", "Depo Kodu ", "Depo Statusu ", "Stok Adresi ", "Adres Statusu ", "Kalem Kodu ", "Kalem Tanimi ", "Eldeki Miktar ", "Rezervasyon Miktari ", "Kullanilabilir Miktar ", "Olcu Birimi ", "Seri No ", "Proje ", "Sabit Kiymet ", "Maliyet Grubu ", "Maliyet Grubu Tanimi ", "Birim Maliyet ", "Tutar ", "Rezervasyon No ", "Sayim Kesit Tarihi "
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable.setViewportView(jTable1);

        jScrollPane3.setViewportView(jTable);

        createBtn.setText("Create Table");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });

        createName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNameActionPerformed(evt);
            }
        });

        jLabel22.setText("Oluşturulacak tablo adını giriniz");

        excelToDbBtn.setText("excelToDb");
        excelToDbBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelToDbBtnActionPerformed(evt);
            }
        });

        excelText.setText("Veri eklenecek tabloyu yazın ve dosyayı seçin");

        jLabel23.setText("Gösterilen Tabloyu Değiştir");

        tablesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablesComboActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(createName)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(excelText)))
                        .addGap(290, 290, 290)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tablesCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 2069, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(createBtn)
                        .addGap(172, 172, 172)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(excelToDbBtn)
                            .addComponent(jButton3))))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tablesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(excelText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createBtn)
                    .addComponent(excelToDbBtn))
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        olcuBirimiBtn.setText("jTextField2");

        sayimKesitTarihiBtn.setText("jTextField21");

        jLabel5.setText("Stok Adresi ");

        jLabel16.setText("Maliyet Grubu ");

        kalemTanimiBtn.setText("jTextField3");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addGap(22, 22, 22)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stokAdresiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(depoStatusuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(depoKoduBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(organizasyonAdiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sirketBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(rezervasyonMiktariBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eldekiMiktarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(kalemTanimiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(saveBtn))
                            .addComponent(kalemKoduBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adresStatusuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sabitKiymetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(olcuBirimiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(kullanılabilirMiktarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(resetBtn))
                            .addComponent(projeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seriNoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sayimKesitTarihiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rezervasyonNoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birimMaliyetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maliyetGrubuTanimiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maliyetGrubuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tutarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(626, 626, 626)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sirketBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(organizasyonAdiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(depoKoduBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(depoStatusuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(26, 26, 26))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(stokAdresiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adresStatusuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kalemKoduBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kalemTanimiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(saveBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eldekiMiktarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rezervasyonMiktariBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kullanılabilirMiktarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resetBtn)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(olcuBirimiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seriNoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(projeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sabitKiymetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maliyetGrubuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maliyetGrubuTanimiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(birimMaliyetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tutarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rezervasyonNoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sayimKesitTarihiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sirketBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sirketBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sirketBtnActionPerformed

    private void adresStatusuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adresStatusuBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adresStatusuBtnActionPerformed

    private void kullanılabilirMiktarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kullanılabilirMiktarBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kullanılabilirMiktarBtnActionPerformed

    private void maliyetGrubuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maliyetGrubuBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maliyetGrubuBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
       try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=testdb;user=admin;password=12345";
            Connection con = DriverManager.getConnection(url);
            String query = "insert into TBL_FDATA_TCTS$(Sirket,[Organizasyon Adi],[Depo Kodu],[Depo Statusu],"
                    + "[Stok Adresi],[Adres Statusu],[Kalem Kodu],[Kalem Tanimi],[Eldeki Miktar],[Rezervasyon Miktari],"
                    + "[Kullanilabilir Miktar],[Olcu Birimi],[Seri No],Proje,[Sabit Kiymet],[Maliyet Grubu],[Maliyet Grubu Tanimi],"
                    + "[Birim Maliyet],Tutar,[Rezervasyon No],[Sayim Kesit Tarihi])values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, sirketBtn.getText());
            pst.setString(2, organizasyonAdiBtn.getText());
            pst.setString(3, depoKoduBtn.getText());
            pst.setString(4, depoStatusuBtn.getText());
            pst.setString(5, stokAdresiBtn.getText());
            pst.setString(6, adresStatusuBtn.getText());
            pst.setString(7, kalemKoduBtn.getText());
            pst.setString(8, kalemTanimiBtn.getText());
            pst.setString(9, eldekiMiktarBtn.getText());
            pst.setString(10, rezervasyonMiktariBtn.getText());
            pst.setString(11, kullanılabilirMiktarBtn.getText());
            pst.setString(12, olcuBirimiBtn.getText());
            pst.setString(13, seriNoBtn.getText());
            pst.setString(14, projeBtn.getText());
            pst.setString(15, sabitKiymetBtn.getText());
            pst.setString(16, maliyetGrubuBtn.getText());
            pst.setString(17, maliyetGrubuTanimiBtn.getText());
            pst.setString(18, birimMaliyetBtn.getText());
            pst.setString(19, tutarBtn.getText());
            pst.setString(20, rezervasyonNoBtn.getText());
            pst.setString(21, sayimKesitTarihiBtn.getText());
            pst.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            show_Data();
            JOptionPane.showMessageDialog(null,"Başarıyla Kaydedildi");
       }
       catch(Exception e) {
           JOptionPane.showMessageDialog(null,e);
       }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        
       
                     
        
        
        
    }//GEN-LAST:event_jTableMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    
        int i = jTable1.getSelectedRow();
        TableModel model =jTable1.getModel();
        sirketBtn.setText(model.getValueAt(i, 0).toString());
         organizasyonAdiBtn.setText(model.getValueAt(i, 1).toString());
          depoKoduBtn.setText(model.getValueAt(i, 2).toString());
           depoStatusuBtn.setText(model.getValueAt(i, 3).toString());
            stokAdresiBtn.setText(model.getValueAt(i, 4).toString());
             adresStatusuBtn.setText(model.getValueAt(i, 5).toString());
              kalemKoduBtn.setText(model.getValueAt(i, 6).toString());
               kalemTanimiBtn.setText(model.getValueAt(i, 7).toString());
                eldekiMiktarBtn.setText(model.getValueAt(i, 8).toString());
                 rezervasyonMiktariBtn.setText(model.getValueAt(i, 9).toString());
                  kullanılabilirMiktarBtn.setText(model.getValueAt(i, 10).toString());
                   olcuBirimiBtn.setText(model.getValueAt(i, 11).toString());
                    seriNoBtn.setText(model.getValueAt(i, 12).toString());
                     projeBtn.setText(model.getValueAt(i, 13).toString());
                      sabitKiymetBtn.setText(model.getValueAt(i, 14).toString());
                       maliyetGrubuBtn.setText(model.getValueAt(i, 15).toString());
                        maliyetGrubuTanimiBtn.setText(model.getValueAt(i, 16).toString());
                         birimMaliyetBtn.setText(model.getValueAt(i, 17).toString());
                          tutarBtn.setText(model.getValueAt(i, 18).toString());
                           rezervasyonNoBtn.setText(model.getValueAt(i, 19).toString());
                            sayimKesitTarihiBtn.setText(model.getValueAt(i, 20).toString());
                          
    }//GEN-LAST:event_jTable1MouseClicked

    private void excelToDbBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelToDbBtnActionPerformed
 Login Login = new Login();

       url = Login.getUrl();

        JFileChooser chooser = new JFileChooser();
       chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        
        String[][] dataTable = null;
	File file = new File(fileName);
        try{
            
            
            // Create a file input stream to read Excel workbook and worksheet
		FileInputStream xlfile = new FileInputStream(file);
		HSSFWorkbook xlwb = new HSSFWorkbook(xlfile);
		HSSFSheet xlSheet = xlwb.getSheetAt(0);
            
            // Get the number of rows and columns
		int numRows = xlSheet.getLastRowNum() + 1;
		int numCols = xlSheet.getRow(0).getLastCellNum();
                System.out.println("numrows :="+ numRows);
                // Create double array data table - rows x cols
		// We will return this data table
		dataTable = new String[numRows][numCols];
                // For each row, create a HSSFRow, then iterate through the "columns"
		// For each "column" create an HSSFCell to grab the value at the specified cell (i,j)
		for (int i = 0; i < numRows; i++) {
			HSSFRow xlRow = xlSheet.getRow(i);
			for (int j = 0; j < numCols; j++) {
				HSSFCell xlCell = xlRow.getCell((short) j);
				dataTable[i][j] = xlCell.toString();
                            
            
             
            
         
			}
		}
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                  
            Connection con = DriverManager.getConnection(url);
            FileInputStream fileIn = new FileInputStream (new File(fileName));
                for (int i = 1; i < numRows; i++) { 
                    System.out.println("dosya atma çalışıyor"+i);
                       
     String query = "insert into "+jComboBox1.getSelectedItem().toString() +"(Sirket,[Organizasyon Adi],[Depo Kodu],[Depo Statusu],"
                    + "[Stok Adresi],[Adres Statusu]"
                    + ",[Kalem Kodu],[Kalem Tanimi],[Eldeki Miktar],[Rezervasyon Miktari],[Kullanilabilir Miktar],"
                    + "[Olcu Birimi],[Seri No],Proje,[Sabit Kiymet],"
                    + "[Maliyet Grubu],[Maliyet Grubu Tanimi],[Birim Maliyet],Tutar,[Rezervasyon No],"
                    + "[Sayim Kesit Tarihi])values"
                    + "("+"'"+dataTable[i][0].toString()+"'"+","+"'"+dataTable[i][1].toString()+"'"+","+"'"+dataTable[i][2].toString()+"'"+","
                    +"'"+dataTable[i][3].toString()+"'"
                    +","+"'"+dataTable[i][4].toString()+"'"+","+"'"+dataTable[i][5].toString()+"'"+","+"'"+dataTable[i][6]+"'"+","+"'"+dataTable[i][7]+"'"+","
                           +"'" +dataTable[i][8].toString()+"'"+","+"'"+dataTable[i][9].toString()+"'"+","+"'"+dataTable[i][10]+"'"+","+"'"+dataTable[i][11].toString()
                                   +"'" +","+"'"+dataTable[i][12]+"'"+","+"'"+dataTable[i][13].toString()+"'"+","+"'"+dataTable[i][14]+"'"+","
                                           +"'" +dataTable[i][15].toString()+"'"+","+"'"+dataTable[i][16]+"'"+","
                                                 +"'"   +dataTable[i][17].toString()+"'"+","+"'"+dataTable[i][18]+"'"+","+
                                                "'"+    dataTable[i][19].toString()+"'"+","+"'"+"10/08/2010"+"'"+")"; 
      PreparedStatement pst = con.prepareStatement(query);
         pst.execute();
        
            show_Data();
                
                }
            
            
      
      }
      
       catch(Exception e) {
           JOptionPane.showMessageDialog(null,e);
       } 
            
    }//GEN-LAST:event_excelToDbBtnActionPerformed

   

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
       Login Login = new Login();
       url = Login.getUrl();
                 
            System.out.println("create içinde:"+url);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            Connection con = DriverManager.getConnection(url);
 String query = "CREATE TABLE [dbo].["+createName.getText()+"]( [Sirket] [nvarchar](255) NULL,[Organizasyon Adi] [nvarchar](255) NULL,[Depo Kodu] [nvarchar](255) NULL,[Depo Statusu] [nvarchar](255) NULL,[Stok Adresi] [nvarchar](255) NULL,[Adres Statusu] [nvarchar](255) NULL,[Kalem Kodu] [nvarchar](255) NULL,[Kalem Tanimi] [nvarchar](255) NULL,[Eldeki Miktar] [float] NULL,[Rezervasyon Miktari] [float] NULL,[Kullanilabilir Miktar] [float] NULL,[Olcu Birimi] [nvarchar](255) NULL,[Seri No] [nvarchar](255) NULL,[Proje] [nvarchar](255) NULL,[Sabit Kiymet] [nvarchar](255) NULL,[Maliyet Grubu] [nvarchar](255) NULL,[Maliyet Grubu Tanimi] [nvarchar](255) NULL,[Birim Maliyet] [float] NULL,[Tutar] [float] NULL,[Rezervasyon No] [nvarchar](255) NULL,[Sayim Kesit Tarihi] [datetime] NULL) ;";            PreparedStatement pst = con.prepareStatement(query);
            pst.execute();
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            show_Data();
            
        } catch (Exception e) {
       JOptionPane.showMessageDialog(null,e);
        } 
                   
            
            
            
    }//GEN-LAST:event_createBtnActionPerformed

    private void createNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createNameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try {                                         
            SQLServerBulkCSVFileRecord fileRecord = null;
            
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String fileName = f.getAbsolutePath();
            fileRecord = new SQLServerBulkCSVFileRecord(fileName, true);
            
            System.out.println("filename: "+fileName );
            
            Login Login = new Login();
            url = Login.getUrl();
           
                System.out.println("record çalışıyor");
                long startTime = System.currentTimeMillis();
                
                fileRecord = new SQLServerBulkCSVFileRecord(fileName, true);
                fileRecord.addColumnMetadata(1, null, java.sql.Types.NVARCHAR, 255, 1);
                fileRecord.addColumnMetadata(2, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(3, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(4, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(5, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(6, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(7, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(8, null, java.sql.Types.NVARCHAR, 255, 0);
                
                fileRecord.addColumnMetadata(9, null, java.sql.Types.FLOAT, 0, 0);
                fileRecord.addColumnMetadata(10, null, java.sql.Types.FLOAT, 0, 0);
                fileRecord.addColumnMetadata(11, null, java.sql.Types.FLOAT, 0, 0);
                fileRecord.addColumnMetadata(12, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(13, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(14, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(15, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(16, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(17, null, java.sql.Types.NVARCHAR, 255, 0);
                
                fileRecord.addColumnMetadata(18, null, java.sql.Types.FLOAT, 0, 0);
                fileRecord.addColumnMetadata(19, null, java.sql.Types.FLOAT, 0, 0);
                fileRecord.addColumnMetadata(20, null, java.sql.Types.NVARCHAR, 255, 0);
                fileRecord.addColumnMetadata(21, null, java.sql.Types.DATE, 0, 0);
                
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection destinationConnection = DriverManager.getConnection(url);
                SQLServerBulkCopyOptions copyOptions = new SQLServerBulkCopyOptions();
                
// Depending on the size of the data being uploaded, and the amount of RAM, an optimum can be found here. Play around with this to improve performance.
copyOptions.setBatchSize(300000); 

// This is crucial to get good performance
copyOptions.setTableLock(true);  

SQLServerBulkCopy bulkCopy =  new SQLServerBulkCopy(destinationConnection);
bulkCopy.setBulkCopyOptions(copyOptions);  
bulkCopy.setDestinationTableName(this.jComboBox1.getSelectedItem().toString());
bulkCopy.writeToServer(fileRecord);

long endTime   = System.currentTimeMillis();
long totalTime = endTime - startTime;
System.out.println(totalTime + "ms");
            } catch (SQLServerException ex) {
                Logger.getLogger(Success.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Success.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Success.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablesComboActionPerformed
      DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            show_Data();
    }//GEN-LAST:event_tablesComboActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 Login Login = new Login();

       url = Login.getUrl();

        JFileChooser chooser = new JFileChooser();
       chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        
        String[][] dataTable = null;
	File file = new File(fileName);
        try{
            
            
            // Create a file input stream to read Excel workbook and worksheet
		FileInputStream xlfile = new FileInputStream(file);
		HSSFWorkbook xlwb = new HSSFWorkbook(xlfile);
		HSSFSheet xlSheet = xlwb.getSheetAt(0);
            
            // Get the number of rows and columns
		int numRows = xlSheet.getLastRowNum() + 1;
		int numCols = xlSheet.getRow(0).getLastCellNum();
                System.out.println("numrows :="+ numRows);
                // Create double array data table - rows x cols
		// We will return this data table
		dataTable = new String[numRows][numCols];
                // For each row, create a HSSFRow, then iterate through the "columns"
		// For each "column" create an HSSFCell to grab the value at the specified cell (i,j)
		for (int i = 0; i < numRows; i++) {
			HSSFRow xlRow = xlSheet.getRow(i);
			for (int j = 0; j < numCols; j++) {
				HSSFCell xlCell = xlRow.getCell((short) j);
				dataTable[i][j] = xlCell.toString();
                            
            
             
            
         
			}
		}
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                  
            Connection con = DriverManager.getConnection(url);
            FileInputStream fileIn = new FileInputStream (new File(fileName));
                for (int i = 1; i < numRows; i++) { 
                    System.out.println("dosya atma çalışıyor");
                       
     String query = "insert into "+jComboBox1.getSelectedItem().toString() +"(Sirket,[Organizasyon Adi],[Depo Kodu],[Depo Statusu],"
                    + "[Stok Adresi],[Adres Statusu]"
                    + ",[Kalem Kodu],[Kalem Tanimi],[Eldeki Miktar],[Rezervasyon Miktari],[Kullanilabilir Miktar],"
                    + "[Olcu Birimi],[Seri No],Proje,[Sabit Kiymet],"
                    + "[Maliyet Grubu],[Maliyet Grubu Tanimi],[Birim Maliyet],Tutar,[Rezervasyon No],"
                    + "[Sayim Kesit Tarihi])values"
                    + "("+"'"+dataTable[i][0].toString()+"'"+","+"'"+dataTable[i][1].toString()+"'"+","+"'"+dataTable[i][2].toString()+"'"+","
                    +"'"+dataTable[i][3].toString()+"'"
                    +","+"'"+dataTable[i][4].toString()+"'"+","+"'"+dataTable[i][5].toString()+"'"+","+"'"+dataTable[i][6]+"'"+","+"'"+dataTable[i][7]+"'"+","
                           +"'" +dataTable[i][8].toString()+"'"+","+"'"+dataTable[i][9].toString()+"'"+","+"'"+dataTable[i][10]+"'"+","+"'"+dataTable[i][11].toString()
                                   +"'" +","+"'"+dataTable[i][12]+"'"+","+"'"+dataTable[i][13].toString()+"'"+","+"'"+dataTable[i][14]+"'"+","
                                           +"'" +dataTable[i][15].toString()+"'"+","+"'"+dataTable[i][16]+"'"+","
                                                 +"'"   +dataTable[i][17].toString()+"'"+","+"'"+dataTable[i][18]+"'"+","+
                                                "'"+    dataTable[i][19].toString()+"'"+","+"'"+"10/08/2010"+"'"+")"; 
      PreparedStatement pst = con.prepareStatement(query);
         pst.execute();
        
            show_Data();
                
                }
            
            
      
      }
      
       catch(Exception e) {
          
       } 
            
                                                

   

   
                   
            
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
JFileChooser chooser = new JFileChooser();
       chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        SQLServerBulkCSVFileRecord fileRecord = null;
        try {                                         
    Login Login = new Login();
    
    url = Login.getUrl();
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    
    fileRecord = new SQLServerBulkCSVFileRecord("F:/test/test1.csv", true);    

        // Set the metadata for each column to be copied.
        for(int i = 0;i < 17;i++)
        {
            fileRecord.addColumnMetadata(i+1, null, java.sql.Types.VARCHAR, 10, 0);
        }

        // Open a destinationConnectio to the AdventureWorks database. 
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try (Connection destinationConnection = DriverManager.getConnection(url))
        {
            try (Statement stmt = destinationConnection.createStatement())
            {
                // Perform an initial count on the destination table.
                long countStart = 0;
                try (ResultSet rsRowCount = stmt.executeQuery(
                        "SELECT COUNT(*) FROM dbo.BulkCopyDemoDifferentColumns1;"))
                {
                    rsRowCount.next();
                    countStart = rsRowCount.getInt(1);
                    System.out.println("Starting row count = " + countStart);
                }

                // Set up the bulk copy object.  
                // Note that the column positions in the source 
                // data reader match the column positions in  
                // the destination table so there is no need to 
                // map columns. 
                try (SQLServerBulkCopy bulkCopy =
                           new SQLServerBulkCopy(destinationConnection))
                {
                    bulkCopy.setDestinationTableName("dbo.BulkCopyDemoDifferentColumns1");

                    try
                    {
                        // Write from the source to the destination.
                        bulkCopy.writeToServer(fileRecord);
                    }
                    catch (Exception e)
                    {
                        // Handle any errors that may have occurred.
                        e.printStackTrace();
                    }
                }

                // Perform a final count on the destination  
                // table to see how many rows were added.
                try (ResultSet rsRowCount = stmt.executeQuery(
                        "SELECT COUNT(*) FROM dbo.BulkCopyDemoDifferentColumns1;"))
                {
                    rsRowCount.next();
                    long countEnd = rsRowCount.getInt(1);
                    System.out.println("Ending row count = " + countEnd);
                    System.out.println((countEnd - countStart) + " rows were added.");
                }
            }
        }
    }
    catch (Exception e)
    {
        // Handle any errors that may have occurred.
        e.printStackTrace();
    }
    finally
    {
        if (fileRecord != null) try { fileRecord.close(); } catch(Exception e) {}
    }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Success.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Success.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Success.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Success.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Success().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresStatusuBtn;
    private javax.swing.JTextField birimMaliyetBtn;
    private javax.swing.JButton createBtn;
    private javax.swing.JTextField createName;
    private javax.swing.JTextField depoKoduBtn;
    private javax.swing.JTextField depoStatusuBtn;
    private javax.swing.JTextField eldekiMiktarBtn;
    private javax.swing.JLabel excelText;
    private javax.swing.JButton excelToDbBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kalemKoduBtn;
    private javax.swing.JTextField kalemTanimiBtn;
    private javax.swing.JTextField kullanılabilirMiktarBtn;
    private javax.swing.JTextField maliyetGrubuBtn;
    private javax.swing.JTextField maliyetGrubuTanimiBtn;
    private javax.swing.JTextField olcuBirimiBtn;
    private javax.swing.JTextField organizasyonAdiBtn;
    private javax.swing.JTextField projeBtn;
    private javax.swing.JButton resetBtn;
    private javax.swing.JTextField rezervasyonMiktariBtn;
    private javax.swing.JTextField rezervasyonNoBtn;
    private javax.swing.JTextField sabitKiymetBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField sayimKesitTarihiBtn;
    private javax.swing.JTextField seriNoBtn;
    private javax.swing.JTextField sirketBtn;
    private javax.swing.JTextField stokAdresiBtn;
    public javax.swing.JComboBox<String> tablesCombo;
    private javax.swing.JTextField tutarBtn;
    // End of variables declaration//GEN-END:variables
}
