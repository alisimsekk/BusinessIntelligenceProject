package com.alisimsek.javabootcamp.finalproject.service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

import com.alisimsek.javabootcamp.finalproject.helper.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

public class DrawChart {
	DatabaseConnection databaseConnection = new DatabaseConnection();

	@Autowired
	public void setDatabaseConnection(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}
	
	public void lineChartRevenueByMonth (String query ) {   //aylık gelir çizgi grafiği metodu
		try {
			JDBCCategoryDataset dataSet = new JDBCCategoryDataset(databaseConnection.getConnection(), query);
			JFreeChart chart = ChartFactory.createLineChart("Ay Bazlı Gelir Grafiği", "Tarih", "Kazanç (USD)", dataSet, PlotOrientation.VERTICAL, false, true, true);
			BarRenderer renderer = null;
			CategoryPlot plot = null;
			renderer = new BarRenderer();	
			ChartFrame frame = new ChartFrame("Ay Bazlı Gelir Grafiği", chart);
			frame.setVisible(true);
			frame.setSize(950,650);
		}
		catch(Exception ex){
			ex.getMessage();
		}
	}
	
	public void pieChartAgency (String query) { //filtreleme verilerine göre acenta bazlı gelir grafiği metodu
		try {
			JDBCPieDataset dataSet = new JDBCPieDataset(databaseConnection.getConnection(), query);
			JFreeChart chart = ChartFactory.createPieChart	("Acenta Bazlı Gelir Grafiği", dataSet, true, true, true);
			PiePlot p = (PiePlot) chart.getPlot();				
			ChartFrame frame = new ChartFrame("Acenta Bazlı Gelir Grafiği", chart);
			frame.setVisible(true);
			frame.setSize(650,650);
		}
		catch(Exception ex){
			ex.getMessage();
		}	
	}

	public void barCartByCustomer (String query) {  //filtreleme verilerine göre ay bazlı işlem yapan müşteri sayısını kıyaslayan grafik
		try {
			JDBCCategoryDataset dataSet = new JDBCCategoryDataset(databaseConnection.getConnection(), query);
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
