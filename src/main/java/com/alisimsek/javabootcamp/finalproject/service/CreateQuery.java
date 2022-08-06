package com.alisimsek.javabootcamp.finalproject.service;

import com.alisimsek.javabootcamp.finalproject.helper.Helper;

public class CreateQuery {

//User ekranında soldaki sorgu kriterlerine göre arama query'sini oluşturur.
	public String search(String startDate, String endDate, String agencyName, String city, String ageMin, String ageMax) {
		String query = "SELECT car_policy.id, customer.id AS customer_id,"
				+ "insurance_agency.id AS insurance_agency_id, car_make, car_year,"
				+ "transaction_date, effective_date, expiration_date, price FROM car_policy "
				+ "INNER JOIN customer ON car_policy.customer_id = customer.id "
				+ "INNER JOIN insurance_agency ON car_policy.insurance_agency_id = insurance_agency.id "
				+ "WHERE (car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') "
				+ "AND (customer.age between {{ageMin}} and {{ageMax}})"
				+ "AND (customer.city like '%{{city}}%')"
				+ "AND (insurance_agency.name like '%{{agencyName}}%')"
				+ "order by car_policy.id asc";
		
		boolean flagAge = false;  //filtrelemede yaş girdileri boş geldiğinde kontrol için
		boolean flagDate = false; //filtrelemede tarih girdileri boş geldiğinde kontrol için
		if (ageMax.equals("") && ageMin.equals("")) {	//Yaş boş bırakılıp rapor çalıştırılırsa hata almamak için
			query = query.replace("AND (customer.age between {{ageMin}} and {{ageMax}})", "");
			flagAge = true;
		}
		else if (ageMax.equals("") || ageMin.equals("")) {
			query = query.replace("AND (customer.age between {{ageMin}} and {{ageMax}})", "");

			//hatalı giriş uyarısı
			Helper.showMsg("Yaş aralığının ikisini de doldurunuz");
			//query'nin doğru oluşması için kontrol
			flagAge = false;
		}
		else { //ageMin ve ageMax değerleri girildiyse query e eklenir
			query = query.replace("{{ageMin}}", ageMin);
			query = query.replace("{{ageMax}}", ageMax);
			flagAge = true;
		}
		
		if (startDate.equals("") && endDate.equals("")) {	//tarih boş bırakılıp rapor çalıştırılırsa hata almamak için
			query = query.replace("(car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND", "");
			flagDate = true;
		}
		else if (startDate.equals("") || endDate.equals("")) {
			query = query.replace("(car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND", "");

			//hatalı giriş uyarısı
			Helper.showMsg("Başlangıç ve bitiş tarihlerinin ikisini de doldurunuz");
			//query'nin doğru oluşması için kontrol
			flagDate = false;
		}
		else {  //startDate ve endDate değerleri girildiyse query e eklenir
			query = query.replace("{{startDate}}", startDate);
			query = query.replace("{{endDate}}", endDate);
			flagDate = true;
		}
		
		if (flagAge && flagDate) { //yaş ve tarih girişleri doğru olması durumunda acenta ve şehir girişleri eklenir
			query = query.replace("{{agencyName}}", agencyName);
			query = query.replace("{{city}}", city);
		}
		else {
			query = "";
		}
		//System.out.println(query);
		return query;
	}
	
//çizgi grafiği için gerekli dataset in sql sorgusunu oluşturur.
	public String revenueByMonthly(String startDate, String endDate, String agencyName, String city, String ageMin, String ageMax) {
		String query = "SELECT  to_char(transaction_date, 'Month YYYY') AS MONTH ,SUM (price) AS \"Monthly Revenue\"\r\n"
				+ "FROM car_policy  \r\n"
				+ "INNER JOIN customer ON car_policy.customer_id = customer.id\r\n"
				+ "INNER JOIN insurance_agency ON car_policy.insurance_agency_id = insurance_agency.id\r\n"
				+ "where (car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND\r\n"
				+ "(customer.age between {{ageMin}} and {{ageMax}}) AND \r\n"
				+ "(customer.city ilike '%{{city}}%') AND\r\n"
				+ "(insurance_agency.name ilike '%{{agencyName}}%')\r\n"
				+ "GROUP BY  to_char(transaction_date, 'Month YYYY'), to_char(transaction_date, 'MM') "
				+ "order by to_char(transaction_date, 'MM')";

		boolean flagAge = false;  //filtrelemede yaş girdileri boş geldiğinde kontrol için
		boolean flagDate = false; //filtrelemede tarih girdileri boş geldiğinde kontrol için

		if (ageMax.equals("") && ageMin.equals("")) {	//Yaş boş bırakılıp tablo oluşturma çalıştırılırsa hata almamak için
			query = query.replace("(customer.age between {{ageMin}} and {{ageMax}}) AND", "");
			flagAge = true;
		}
		else if (ageMax.equals("") || ageMin.equals("")) {
			query = query.replace("(customer.age between {{ageMin}} and {{ageMax}}) AND", "");
			flagAge = false;
		}
		else {
			query = query.replace("{{ageMin}}", ageMin);
			query = query.replace("{{ageMax}}", ageMax);
			flagAge = true;
		}
		
		if (startDate.equals("") && endDate.equals("")) {	//tarih boş bırakılıp tablo oluşturma çalıştırılırsa hata almamak için
			query = query.replace("(car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND", "");
			flagDate = true;
		}
		else if (startDate.equals("") || endDate.equals("")) {
			query = query.replace("(car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND", "");
			flagDate = false;
		}
		else {
			query = query.replace("{{startDate}}", startDate);
			query = query.replace("{{endDate}}", endDate);
			flagDate = true;
		}
		
		if (flagAge && flagDate) {
			query = query.replace("{{agencyName}}", agencyName);
			query = query.replace("{{city}}", city);
			
		}
		else {
			query = "";
		}
		return query;
	}

//pasta grafiği için gerekli dataset in sql sorgusunu oluşturur.
	public String pieChartByAgency(String startDate, String endDate, String agencyName, String city, String ageMin, String ageMax) {
		String query = " SELECT insurance_agency.name\r\n"
				+ ",SUM (price) AS \"Monthly Revenue\"\r\n"
				+ "FROM car_policy\r\n"
				+ "INNER JOIN customer ON car_policy.customer_id = customer.id\r\n"
				+ "INNER JOIN insurance_agency ON car_policy.insurance_agency_id = insurance_agency.id\r\n"
				+ "where (car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND\r\n"
				+ "(customer.age between {{ageMin}} and {{ageMax}}) AND \r\n"
				+ "(customer.city ilike '%{{city}}%') AND\r\n"
				+ "(insurance_agency.name ilike '%{{agencyName}}%')\r\n"
				+ "GROUP BY insurance_agency.name";

		boolean flagAge = false;  //filtrelemede yaş girdileri boş geldiğinde kontrol için
		boolean flagDate = false; //filtrelemede tarih girdileri boş geldiğinde kontrol için
		if (ageMax.equals("") && ageMin.equals("")) {	//Yaş boş bırakılıp tablo oluşturma çalıştırılırsa hata almamak için
			query = query.replace("(customer.age between {{ageMin}} and {{ageMax}}) AND", "");
			flagAge = true;
		}
		else if (ageMax.equals("") || ageMin.equals("")) {
			query = query.replace("(customer.age between {{ageMin}} and {{ageMax}}) AND", "");
			flagAge = false;
		}
		else {
			query = query.replace("{{ageMin}}", ageMin);
			query = query.replace("{{ageMax}}", ageMax);
			flagAge = true;
		}
		
		if (startDate.equals("") && endDate.equals("")) {	//tarih boş bırakılıp tablo oluşturma çalıştırılırsa hata almamak için
			query = query.replace("(car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND", "");
			flagDate = true;
		}
		else if (startDate.equals("") || endDate.equals("")) {
			query = query.replace("(car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND", "");
			flagDate = false;
		}
		else {
			query = query.replace("{{startDate}}", startDate);
			query = query.replace("{{endDate}}", endDate);
			flagDate = true;
		}
		
		if (flagAge && flagDate) {
			query = query.replace("{{agencyName}}", agencyName);
			query = query.replace("{{city}}", city);
		}
		else {
			query = "";
		}
		return query;
	}

//mum grafiği için gerekli dataset in sql sorgusunu oluşturur.
	public String barChart (String startDate, String endDate, String agencyName, String city, String ageMin, String ageMax) {
		String query = "SELECT  to_char(transaction_date, 'Month YYYY') AS Month,COUNT (customer_id) AS \"Monthly Transaction\"\r\n"
				+ "FROM car_policy  \r\n"
				+ "INNER JOIN customer ON car_policy.customer_id = customer.id\r\n"
				+ "INNER JOIN insurance_agency ON car_policy.insurance_agency_id = insurance_agency.id\r\n"
				+ "where (car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND\r\n"
				+ "(customer.age between {{ageMin}} and {{ageMax}}) AND \r\n"
				+ "(customer.city ilike '%{{city}}%') AND\r\n"
				+ "(insurance_agency.name ilike '%{{agencyName}}%')\r\n"
				+ "GROUP BY  to_char(transaction_date, 'Month YYYY'), to_char(transaction_date, 'MM') "
				+ "order by to_char(transaction_date, 'MM')";

		boolean flagAge = false;  //filtrelemede yaş girdileri boş geldiğinde kontrol için
		boolean flagDate = false; //filtrelemede tarih girdileri boş geldiğinde kontrol için
		if (ageMax.equals("") && ageMin.equals("")) {	//Yaş boş bırakılıp tablo oluşturma çalıştırılırsa hata almamak için
			query = query.replace("(customer.age between {{ageMin}} and {{ageMax}}) AND", "");
			flagAge = true;
		}
		else if (ageMax.equals("") || ageMin.equals("")) {
			query = query.replace("(customer.age between {{ageMin}} and {{ageMax}}) AND", "");
			flagAge = false;
		}
		else {
			query = query.replace("{{ageMin}}", ageMin);
			query = query.replace("{{ageMax}}", ageMax);
			flagAge = true;
		}

		if (startDate.equals("") && endDate.equals("")) {	//tarih boş bırakılıp tablo oluşturma çalıştırılırsa hata almamak için
			query = query.replace("(car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND", "");
			flagDate = true;
		}
		else if (startDate.equals("") || endDate.equals("")) {
			query = query.replace("(car_policy.transaction_date between '{{startDate}}' and '{{endDate}}') AND", "");
			flagDate = false;
		}
		else {
			query = query.replace("{{startDate}}", startDate);
			query = query.replace("{{endDate}}", endDate);
			flagDate = true;
		}

		if (flagAge && flagDate) {
			query = query.replace("{{agencyName}}", agencyName);
			query = query.replace("{{city}}", city);
		}
		else {
			query = "";
		}
		return query;
	}

}
