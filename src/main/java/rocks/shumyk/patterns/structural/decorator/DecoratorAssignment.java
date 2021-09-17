package rocks.shumyk.patterns.structural.decorator;

class Bird {
	public int age;

	public String fly() {
		return age < 10 ? "flying" : "too old";
	}
}

class Lizard {
	public int age;

	public String crawl() {
		return (age > 1) ? "crawling" : "too young";
	}
}

class Dragon {
	private final Bird bird = new Bird();
	private final Lizard lizard = new Lizard();

	public void setAge(int age) {
		bird.age = age;
		lizard.age = age;
	}

	public String fly() {
		return bird.fly();
	}

	public String crawl() {
		return lizard.crawl();
	}
}

public class DecoratorAssignment {
}
