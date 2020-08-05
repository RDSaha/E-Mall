import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class revenue {
	public double calculate_individual(int sid, LocalDate startdate, LocalDate enddate) {

		Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
				|| date.getDayOfWeek() == DayOfWeek.SUNDAY;
		long daysBetween = ChronoUnit.DAYS.between(startdate, enddate);
		long businessDays = Stream.iterate(startdate, date -> date.plusDays(1)).limit(daysBetween)
				.filter(isWeekend.negate()).count();
		long weekendDays = daysBetween - businessDays;

		connection c = new connection();
		c.loadDriver();
		Connection con = c.getConnection();

		@SuppressWarnings("unused")
		String space = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select space from unitsspace where id = sid");
			while (rs.next()) {
				space = rs.getString("space");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double weekdaycost = 0.0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select cost from businessspace where space = space and duration = \"Week Days\"");
			while (rs.next()) {
				weekdaycost = Double.parseDouble(rs.getString("cost"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double weekendcost = 0.0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select cost from businessspace where space = space and duration = \"Week Ends\"");
			while (rs.next()) {
				weekendcost = Double.parseDouble(rs.getString("cost"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double revenue = (businessDays * weekdaycost) + (weekendDays * weekendcost);

		return revenue;
	}
	
	public double revenue_total(ArrayList<Double> ar) {
		double Sum = 0;
	    for(int i = 0; i < ar.size(); i++)
	    {
	        Sum += ar.get(i);
	    }
	    return Sum;
	}

}
