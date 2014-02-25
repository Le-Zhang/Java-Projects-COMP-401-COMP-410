package a4;

public class AccountManager extends SalariedEmployeeImpl {

	public AccountManager(String first_name, String last_name, int id) {
		super(first_name, last_name, "Account Manager",id, 7500);
	}
	
	public double getGrossYearlyIncome() {
		return super.getGrossYearlyIncome() + 10000;
	}

}
