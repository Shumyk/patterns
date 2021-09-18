package rocks.shumyk.patterns.structural.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Property<T> {
	private T value;

	// here we can add any additional logic over setting some value
	// it is property proxy
	public void setValue(T value) {
		// logging
		this.value = value;
	}
}

@Data
class Creature {
	private Property<Integer> agility = new Property<>(10);

	public void setAgility(int value) {
		agility.setValue(value);
	}

	public int getAgility() {
		return agility.getValue();
	}
}

public class PropertyProxy {
	public static void main(String[] args) {
		final Creature creature = new Creature();
		creature.setAgility(12);
	}
}
