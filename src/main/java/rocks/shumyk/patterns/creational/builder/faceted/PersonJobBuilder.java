package rocks.shumyk.patterns.creational.builder.faceted;

public class PersonJobBuilder extends PersonBuilder {

	public PersonJobBuilder(final Person person) {
		this.person = person;
	}

	public PersonJobBuilder at(final String companyName) {
		person.setCompanyName(companyName);
		return this;
	}

	public PersonJobBuilder asA(final String position) {
		person.setPosition(position);
		return this;
	}

	public PersonJobBuilder earning(final int annualIncome) {
		person.setAnnualIncome(annualIncome);
		return this;
	}
}
