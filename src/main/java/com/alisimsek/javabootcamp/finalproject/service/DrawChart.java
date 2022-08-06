package com.alisimsek.javabootcamp.finalproject.service;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.List;

public class DrawChart {

	Session session = HibernateUtil.getSessionFactory().openSession();


	DefaultPieDataset pieDataset = new DefaultPieDataset();

	public void lineChartRevenueByMonth (String sql ) {   //aylık gelir çizgi grafiği metodu
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		try {
			session.beginTransaction();
			Query query = session.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			session.getTransaction().commit();

			for (Object[] objects : list) {
				String transactionDate =  objects[0].toString();
				double price = (double) objects[1];
				dataSet.addValue(price,"revenue",transactionDate );
			}
			JFreeChart chart = ChartFactory.createLineChart("Ay Bazlı Gelir Grafiği", "Tarih", "Kazanç (USD)", dataSet, PlotOrientation.VERTICAL, false, true, true);
			BarRenderer renderer = null;
			CategoryPlot plot = null;
			renderer = new BarRenderer();	
			ChartFrame frame = new ChartFrame("Ay Bazlı Gelir Grafiği", chart);
			frame.setVisible(true);
			frame.setSize(950,650);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	
	public void pieChartAgency (String sql) { //filtreleme verilerine göre acenta bazlı gelir grafiği metodu
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		try {
			session.beginTransaction();
			Query query = session.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			session.getTransaction().commit();

			for (Object[] objects : list) {
				String agencyName =  objects[0].toString();
				double price = (double) objects[1];
				pieDataset.setValue(agencyName, price);
			}

			JFreeChart chart = ChartFactory.createPieChart	("Acenta Bazlı Gelir Grafiği", pieDataset, true, true, true);
			PiePlot p = (PiePlot) chart.getPlot();				
			ChartFrame frame = new ChartFrame("Acenta Bazlı Gelir Grafiği", chart);
			frame.setVisible(true);
			frame.setSize(650,650);
		}
		catch(Exception ex){
			ex.getMessage();
		}	
	}

	public void barCartByCustomer (String sql) {  //filtreleme verilerine göre ay bazlı işlem yapan müşteri sayısını kıyaslayan grafik
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		try {
			session.beginTransaction();
			Query query = session.createNativeQuery(sql);
			List<Object[]> list = query.getResultList();
			session.getTransaction().commit();
			for (Object[] objects : list) {
				dataSet.addValue((Number) objects[1],"transaction", objects[0].toString() );
			}

			JFreeChart chart = ChartFactory.createBarChart	("Ay Bazlı İşlem Yapan Müşteri","Tarih", "İşlem Yapan Müsteri (Adet)", dataSet, PlotOrientation.VERTICAL, false, true, false);
			CategoryPlot plot = chart.getCategoryPlot();
			plot.setRangeGridlinePaint(Color.black);
			ChartFrame frame = new ChartFrame("Ay Bazlı İşlem Yapan Müşteri", chart);
			frame.setVisible(true);
			frame.setSize(950,650);
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
