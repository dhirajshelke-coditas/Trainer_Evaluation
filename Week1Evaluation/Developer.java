package Week1Evaluation;

 class Developer extends Employee {
	 int overtimeHours;

	    public Developer(String name, double baseSalary, int attendanceDays,
	                     int performanceRating, int overtimeHours) {
	        super(name, baseSalary, attendanceDays, performanceRating);
	        this.overtimeHours = overtimeHours;
	    }

	    @Override
	    double calculateGrossSalary() {
	        return baseSalary + (overtimeHours * 500);
	    }
}
