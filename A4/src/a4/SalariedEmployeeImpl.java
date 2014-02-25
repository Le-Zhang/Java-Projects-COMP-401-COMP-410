package a4;

public class SalariedEmployeeImpl extends EmployeeImpl implements SalariedEmployee{

	private double monthlySalary;
	
	public SalariedEmployeeImpl(String first_name, String last_name,
			String job_title, int id, double monthly_salary){
		super(first_name, last_name, job_title, id);
		this.monthlySalary = monthly_salary;
	}

	@Override
	public double getMonthlySalary() {
		return monthlySalary;
	}

	@Override
	public void setMonthlySalary(double salary) {
		monthlySalary = salary;
	}

	@Override
	public double getGrossYearlyIncome() {
		return this.getMonthlySalary() * 12;
	}

	@Override
	public double getTaxableIncome() {
		return this.getGrossYearlyIncome();
	}


}
