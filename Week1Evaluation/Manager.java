package Week1Evaluation;

 class Manager extends Employee {
	int teamSize;

    public Manager(String name, double baseSalary, int attendanceDays,
                   int performanceRating, int teamSize) {
        super(name, baseSalary, attendanceDays, performanceRating);
        this.teamSize = teamSize;
    }

    @Override
    double calculateGrossSalary() {
        return baseSalary + (teamSize * 1000);
    }
}
