package a4;

public class HourlyEmployeeImpl extends EmployeeImpl implements HourlyEmployee {
	private double hourlyRate;
	private double hoursPerWeek;
	
	public HourlyEmployeeImpl(String first_name, String last_name,
			String job_title, int id) {
		super(first_name, last_name, job_title, id);
	}

	public HourlyEmployeeImpl(String first_name, String last_name,
			String job_title, int id, double hourly_rate, double hours_per_week) {
		super(first_name, last_name, job_title, id);
		hourlyRate = hourly_rate;
		hoursPerWeek = hours_per_week;
	}
	
	
	@Override
	public double getHourlyRate() {
		return hourlyRate;
	}

	@Override
	public void setHourlyRate(double rate) {
		this.hourlyRate = rate;
	}

	@Override
	public double getHoursPerWeek() {
		return hoursPerWeek;
	}

	@Override
	public void setHoursPerWeek(double hours_per_week) {
		this.hoursPerWeek = hours_per_week;
	}

	@Override
	public double getGrossYearlyIncome() {
		return this.getHourlyRate() * this.getHoursPerWeek() * 52 ;
	}
	
	@Override
	public double getTaxableIncome(){
		if(this.getGrossYearlyIncome()<0)
			throw new RuntimeException("Gross Yearly Income should be over 0.");
		else if(this.getGrossYearlyIncome()>=0 && this.getGrossYearlyIncome()<=25000)
			return 0.0;
		else
			return this.getGrossYearlyIncome()-25000;
	}
}
