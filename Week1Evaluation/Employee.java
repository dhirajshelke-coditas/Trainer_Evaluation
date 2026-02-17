package Week1Evaluation;

 abstract class Employee {
	 String name;
	    double baseSalary;
	    int attendanceDays;
	    int performanceRating;

	    static final double PF_PERCENT = 0.12; 

	    public Employee(String name, double baseSalary, int attendanceDays, int performanceRating) {
	        this.name = name;
	        this.baseSalary = baseSalary;
	        this.attendanceDays = attendanceDays;
	        this.performanceRating = performanceRating;
	    }


	    abstract double calculateGrossSalary();


	    public double calculateAttendanceDeduction() {
	        double dailySalary = baseSalary / 30;
	        int absentDays = 30 - attendanceDays;
	        return absentDays * dailySalary;
	    }


	    public double calculateBonus(double grossSalary) {
	        double bonusPercent = 0;

	        switch (performanceRating) {
	            case 5: bonusPercent = 0.20; 
	            break;
	            case 4: bonusPercent = 0.15; 
	            break;
	            case 3: bonusPercent = 0.10; 
	            break;
	            case 2: bonusPercent = 0.05; 
	            break;
	            case 1: bonusPercent = 0.0; 
	            break;
	        }

	        return grossSalary * bonusPercent;
	    }


	    public double calculatePF() {
	        return baseSalary * PF_PERCENT;
	    }


	    public double calculateTax(double taxableIncome) {
	        double taxRate;

	        if (taxableIncome <= 50000)
	            taxRate = 0.05;
	        else if (taxableIncome <= 100000)
	            taxRate = 0.10;
	        else if (taxableIncome <= 150000)
	            taxRate = 0.15;
	        else
	            taxRate = 0.20;

	        return taxableIncome * taxRate;
	    }


	    public void generateSalarySlip() {

	        double grossSalary = calculateGrossSalary();
	        double bonus = calculateBonus(grossSalary);
	        double pf = calculatePF();
	        double attendanceDeduction = calculateAttendanceDeduction();
	        double taxableIncome = grossSalary + bonus;
	        double tax = calculateTax(taxableIncome);

	        double netSalary = grossSalary + bonus - tax - pf - attendanceDeduction;

	        System.out.println("------ Salary Slip ------");
	        System.out.println();
	        System.out.println("Employee Name: " + name);
	        System.out.println("Base Salary: " + baseSalary);
	        System.out.println("Gross Salary: " + grossSalary);
	        System.out.println("Bonus: " + bonus);
	        System.out.println("PF Deduction: " + pf);
	        System.out.println("Tax: " + tax);
	        System.out.println("Attendance Deduction: " + attendanceDeduction);
	        System.out.println("Net Salary: " + netSalary);
	        System.out.println("--------------------------");
	        System.out.println();
	    }
}
