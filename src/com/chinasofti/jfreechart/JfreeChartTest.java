package com.chinasofti.jfreechart;

import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import java.awt.Font;

import com.chinasofti.entity.UserHobby;

public class JfreeChartTest {
	public static JFreeChart createPie(String username,List<UserHobby> list) {
		//list为相同名字的集合
		DefaultPieDataset dfp = new DefaultPieDataset();
		for (UserHobby userHobby : list) {
			int count = userHobby.getViewCount();
			String className = userHobby.getClassName();
			dfp.setValue(className, count);
		}
		setTheme();
		JFreeChart a = ChartFactory.createPieChart(username, dfp, true, true, true);
		ChartFrame frame = new ChartFrame("用户喜好 ", a, true);
		return a;
	}
	public static void setTheme() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		standardChartTheme.setLargeFont(new Font("微软雅黑", Font.PLAIN, 16));
		standardChartTheme.setRegularFont(new Font("微软雅黑", Font.PLAIN, 16));
		standardChartTheme.setExtraLargeFont(new Font("微软雅黑", Font.BOLD, 25));
		ChartFactory.setChartTheme(standardChartTheme);
	}
}
