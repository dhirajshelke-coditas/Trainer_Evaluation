package Week1Evaluation;

public class PayrollSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		        Employee dev = new Developer("Dhiraj", 60000, 28, 5, 10);
		        Employee mgr = new Manager("Ram", 80000, 30, 4, 5);
		        Employee intern = new Intern("Shyam", 30000, 18, 3);

		        dev.generateSalarySlip();
		        mgr.generateSalarySlip();
		        intern.generateSalarySlip();
	}

}
