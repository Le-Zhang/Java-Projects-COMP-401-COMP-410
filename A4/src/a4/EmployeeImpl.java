package a4;

abstract public class EmployeeImpl implements Employee {

	private String firstName;
	private String lastName;
	private String jobTitle;
	private int id;
	
	public EmployeeImpl(String first_name, String last_name, String job_title, int id){
		this.firstName = first_name;
		this.lastName = last_name;
		this.jobTitle = job_title;
		this.id = id;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getJobTitle() {
		return jobTitle;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	abstract public double getGrossYearlyIncome();

	@Override
	public double getNetYearlyIncome() {
		return (this.getGrossYearlyIncome()-this.getTaxesWithheld());
	}

	@Override
	abstract public double getTaxableIncome();

	@Override
	public double getTaxesWithheld() {
		if(this.getTaxableIncome()<0)
			throw new RuntimeException("Taxable Income should not be negetive.");
		else if(this.getTaxableIncome()>=0 && this.getTaxableIncome()<=50000)
			return this.getTaxableIncome() * 0.1;
		else if(this.getTaxableIncome()>50000 && this.getTaxableIncome()<=100000)
			return 5000 + (this.getTaxableIncome()-50000)*0.15;
		else 
			return 5000 + 7500 + (this.getTaxableIncome()-100000)*0.25;
	}
	
	

}
