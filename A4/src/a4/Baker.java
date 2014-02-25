package a4;

public class Baker extends HourlyEmployeeImpl {

	public Baker(String first_name, String last_name, int id,
			double hourly_rate, double hours_per_week) {
		super(first_name, last_name, "Baker", id, hourly_rate, hours_per_week);
	}

	
	public double getGrossYearlyIncome() {
		if(getHoursPerWeek()<=30 && getHoursPerWeek()>0)
			return this.getHourlyRate() * this.getHoursPerWeek() * 52 ;
		else
			return (this.getHourlyRate() * 30 + this.getHourlyRate()*(this.getHoursPerWeek()-30)*1.5)*52;
	}

}
