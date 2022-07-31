package com.alisimsek.javabootcamp.finalproject.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.alisimsek.javabootcamp.finalproject.helper.Helper;
import com.alisimsek.javabootcamp.finalproject.model.CarPolicy;
import com.alisimsek.javabootcamp.finalproject.model.Users;
import com.alisimsek.javabootcamp.finalproject.service.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class UserGUI extends JFrame {

	private JPanel wrapper;
	private Users user;
	private JTextField fld_age_min;
	private JTextField fld_age_max;

	private JTable tbl_carPolicy_list;
	DefaultTableModel mdl_carPolicy_list;
    private Object[] row_carPolicy_list;

	String revenueByMonthlyQuery;
	String pieChartByAgencyQuery;
	String barChartQuery;
	private CarPolicyService carPolicyService = new CarPolicyService();
	private DrawChart drawChart = new DrawChart();
	private CreateQuery createQuery = new CreateQuery();
	private CustomerService customerService = new CustomerService();
	private InsuranceAgencyService insuranceAgencyService = new InsuranceAgencyService();

	public UserGUI(Users user) {

		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
		setTitle("Anadolubank Java Bootcamp Bitirme Projesi");
		wrapper = new JPanel();
		wrapper.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(wrapper);
		setVisible(true);
		setResizable(false);
		wrapper.setLayout(null);
		JLabel lbl_welcome = new JLabel("user ekranı");
		lbl_welcome.setBounds(5, 5, 1174, 14);
		wrapper.add(lbl_welcome);
		
		lbl_welcome.setText("Hoşgeldin " + user.getName());
		
		JPanel pnl_sh_form = new JPanel();
		pnl_sh_form.setBounds(5, 60, 165, 480);
		wrapper.add(pnl_sh_form);
		pnl_sh_form.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Başlangıç Tarihi");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 20, 100, 20);
		pnl_sh_form.add(lblNewLabel);
	
		JLabel lblBitiTarihi = new JLabel("Bitiş Tarihi");
		lblBitiTarihi.setFont(new Font("Arial", Font.PLAIN, 13));
		lblBitiTarihi.setBounds(10, 70, 100, 20);
		pnl_sh_form.add(lblBitiTarihi);
		
		JLabel lblBitiTarihi_1 = new JLabel("Acenta");
		lblBitiTarihi_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblBitiTarihi_1.setBounds(10, 120, 100, 20);
		pnl_sh_form.add(lblBitiTarihi_1);

		JComboBox cmb_agency_name = new JComboBox();
		cmb_agency_name.setBounds(10, 140, 150, 20);
		pnl_sh_form.add(cmb_agency_name);
		
		JLabel lblBitiTarihi_1_1 = new JLabel("Şehir");
		lblBitiTarihi_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblBitiTarihi_1_1.setBounds(10, 170, 100, 20);
		pnl_sh_form.add(lblBitiTarihi_1_1);

		JComboBox cmb_city = new JComboBox();
		cmb_city.setBounds(10, 190, 150, 20);
		pnl_sh_form.add(cmb_city);

		JLabel lblBitiTarihi_1_1_1 = new JLabel("Yaş Aralığı");
		lblBitiTarihi_1_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblBitiTarihi_1_1_1.setBounds(10, 220, 100, 20);
		pnl_sh_form.add(lblBitiTarihi_1_1_1);
		
		fld_age_min = new JTextField();
		fld_age_min.setBounds(10, 240, 40, 20);
		pnl_sh_form.add(fld_age_min);
		fld_age_min.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setBounds(58, 240, 22, 20);
		pnl_sh_form.add(lblNewLabel_1);
		
		fld_age_max = new JTextField();
		fld_age_max.setColumns(10);
		fld_age_max.setBounds(70, 240, 40, 20);
		pnl_sh_form.add(fld_age_max);
		
		JDateChooser dateChooser_start = new JDateChooser();
		dateChooser_start.setBounds(10, 40, 150, 20);
		pnl_sh_form.add(dateChooser_start);
		
		JDateChooser dateChooser_end = new JDateChooser();
		dateChooser_end.setBounds(10, 90, 150, 20);
		pnl_sh_form.add(dateChooser_end);
		
		JTabbedPane tab_wrapper = new JTabbedPane(JTabbedPane.TOP);
		tab_wrapper.setBounds(180, 60, 975, 480);
		wrapper.add(tab_wrapper);
		
		JPanel pnl_carPolicy_list = new JPanel();
		tab_wrapper.addTab("Sonuç Tablosu", null, pnl_carPolicy_list, null);
		pnl_carPolicy_list.setLayout(null);
		
		JScrollPane scrl_carPolicy_list = new JScrollPane();
		scrl_carPolicy_list.setBounds(0, 0, 970, 454);
		pnl_carPolicy_list.add(scrl_carPolicy_list);
		
		tbl_carPolicy_list = new JTable();
		scrl_carPolicy_list.setViewportView(tbl_carPolicy_list);

//Acenta isimlerinin filtreleme combo box'ına aktarılması
		List<String> agencyNameList = insuranceAgencyService.getAgencyName();
		cmb_agency_name.setModel(new DefaultComboBoxModel<String>(agencyNameList.toArray(new String[0])));

//Müşterilerin şehirlerinin filtreleme combo box'ına aktarılması
		List<String> customersCityNameList = customerService.getCityName();
		cmb_city.setModel(new DefaultComboBoxModel<String>(customersCityNameList.toArray(new String[0])));

//Araç sigortaları tablosu kodları başlangıcı
        mdl_carPolicy_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        
        Object[] col_carInsurance_list = {"id", "İsim Soyisim", "Yaş", "Cinsiyet", "Email", "Şehir", "Araç Markası", "Araç Yılı", 
        		  "Poliçe Başlangıcı", "Poliçe Bitişi", "Fiyat", "İşlem Tarihi", "Acenta"};
        mdl_carPolicy_list.setColumnIdentifiers(col_carInsurance_list);
        row_carPolicy_list = new Object[col_carInsurance_list.length];
        tbl_carPolicy_list.setModel(mdl_carPolicy_list);
        tbl_carPolicy_list.getTableHeader().setReorderingAllowed(false);
//Araç sigortaları tablosu kodları bitişi


//rapor çalıştır butonu kodları başlangıcı
    	JButton btn_searh = new JButton("Raporu Çalıştır");
		btn_searh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  //tarih verileri için format
				String startDate;
				if (dateChooser_start.getDate() == null) {	//tarih boş bırakılıp rapor çalıştırılırsa hata almamak için
					startDate = "";
				}
				else {
					startDate = formatter.format(dateChooser_start.getDate());
				}
				
				String endDate;
				if (dateChooser_end.getDate() == null) {	//tarih boş bırakılıp rapor çalıştırılırsa hata almamak için
					endDate = "";
				}
				else {
					endDate = formatter.format(dateChooser_end.getDate());
				}

				String agencyName = cmb_agency_name.getSelectedItem().toString();
				String city=cmb_city.getSelectedItem().toString();
				String ageMin=fld_age_min.getText().trim();
				String ageMax=fld_age_max.getText().trim();
		
//sorgu algoritması
				//filtreleme girdilerine göre query oluşturulur
				String searchQuery = createQuery.search(startDate, endDate, agencyName, city, ageMin, ageMax );
				if(!searchQuery.equals("")) {

					//filtreleme query si ile databaseden veriler searchCarPolicyList e aktarılır
					List<CarPolicy> searchCarPolicyList = carPolicyService.searchList (searchQuery);

					if(searchCarPolicyList.size() < 1) {
						Helper.showMsg("Aranan kriterlere uygun sonuç bulunamadı");
					}

					loadCarPolicyModel(searchCarPolicyList);  //arama sonuçları tablo olarak kullanıcıya gösterilir
				}
				//filtreleme girdilerine göre aylık kazanç grafiği için query oluşturulur
				revenueByMonthlyQuery = createQuery.revenueByMonthly(startDate, endDate, agencyName, city, ageMin, ageMax); //filtreleme sonucuna göre çizgi grafik data seti

				//filtreleme girdilerine göre gelirdeki acenta oranlarının grafiği için query oluşturulur
				pieChartByAgencyQuery = createQuery.pieChartByAgency(startDate, endDate, agencyName, city, ageMin, ageMax);  //filtreleme sonucuna göre pasta grafik data seti

				//filtreleme girdilerine göre aylık müşteri işlem sayısı grafiği için query oluşturulur
				barChartQuery = createQuery.barChart(startDate, endDate, agencyName, city, ageMin, ageMax);
			}
		});
		btn_searh.setBounds(22, 300, 120, 25);
		pnl_sh_form.add(btn_searh);

		//Kullanıcı ekranındaki aylık kazanç grafiği oluşturma kodları
		JCheckBox check_box = new JCheckBox("Aylık gelir grafiği");
		check_box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(check_box.isSelected()) {		
					drawChart.lineChartRevenueByMonth(revenueByMonthlyQuery);
					check_box.setSelected(false);
				}
			}
		});
		check_box.setBounds(180, 30, 140, 23);
		wrapper.add(check_box);

		//Kullanıcı ekranındaki acenta bazlı gelir grafiği oluşturma kodları
		JCheckBox chckbox_piechart = new JCheckBox("Acenta Gelir Dağılımı Grafiği");
		chckbox_piechart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbox_piechart.isSelected()) {
					drawChart.pieChartAgency(pieChartByAgencyQuery);
					chckbox_piechart.setSelected(false);
				}
			}
		});
		chckbox_piechart.setBounds(320, 30, 190, 23);
		wrapper.add(chckbox_piechart);

		//Ay bazlı işlem yapan müşteri sayısı grafiği oluşturma kodları
		JCheckBox chckbox_barchart = new JCheckBox("Ay Bazlı İşlem Yapan Müşteri Grafiği");
		chckbox_barchart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbox_barchart.isSelected()) {
					drawChart.barCartByCustomer(barChartQuery);
					chckbox_barchart.setSelected(false);
				}
			}
		});
		chckbox_barchart.setBounds(520, 30, 240, 23);
		wrapper.add(chckbox_barchart);
	}

	private void loadCarPolicyModel(List<CarPolicy> list) {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_carPolicy_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (CarPolicy obj : list){
            i = 0;
            row_carPolicy_list[i++] = obj.getId();
			row_carPolicy_list[i++] = obj. getCustomer().getFullName();
            row_carPolicy_list[i++] = obj.getCustomer().getAge();
            row_carPolicy_list[i++] = obj.getCustomer().getGender();
            row_carPolicy_list[i++] = obj.getCustomer().getEmail();
            row_carPolicy_list[i++] = obj.getCustomer().getCity();
            row_carPolicy_list[i++] = obj.getCarMake();
            row_carPolicy_list[i++] = obj.getCarYear();
            row_carPolicy_list[i++] = obj.getEffectiveDate();
            row_carPolicy_list[i++] = obj.getExpirationDate();
            row_carPolicy_list[i++] = obj.getPrice();
            row_carPolicy_list[i++] = obj.getTransactionDate(); 
            row_carPolicy_list[i++] = obj.getInsuranceAgency().getName();
            mdl_carPolicy_list.addRow(row_carPolicy_list);
        }
	}
}
