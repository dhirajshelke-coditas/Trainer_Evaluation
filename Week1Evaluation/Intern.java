package Week1Evaluation;

public class Intern extends Employee {


	    public Intern(String name, double baseSalary, int attendanceDays,
	                  int performanceRating) {
	        super(name, baseSalary, attendanceDays, performanceRating);
	    }

	    @Override
	    double calculateGrossSalary() {
	        double attendancePercent = (attendanceDays / 30.0) * 100;

	        if (attendancePercent < 70) {
	            return baseSalary - (baseSalary * 0.20);
	        } else {
	            return baseSalary;
	        }
	    }
	}

