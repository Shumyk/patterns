package rocks.shumyk.patterns.structural.composite;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

interface ValueContainer extends Iterable<Integer> {}

class SingleValue implements ValueContainer {
	public final int value;

	// please leave this constructor as-is
	public SingleValue(int value) {
		this.value = value;
	}

	@Override
	public Iterator<Integer> iterator() {
		return Collections.singleton(value).iterator();
	}

	@Override
	public void forEach(Consumer<? super Integer> action) {
		action.accept(value);
	}

	@Override
	public Spliterator<Integer> spliterator() {
		return Collections.singleton(value).spliterator();
	}
}

class ManyValues extends ArrayList<Integer> implements ValueContainer {}

class MyList extends ArrayList<ValueContainer> {
	// please leave this constructor as-is
	public MyList(Collection<? extends ValueContainer> c) {
		super(c);
	}

	public int sum() {
		final var sum = new AtomicInteger();
		for (ValueContainer container : this) {
			container.forEach(sum::addAndGet);
		}
		return sum.get();
	}
}
