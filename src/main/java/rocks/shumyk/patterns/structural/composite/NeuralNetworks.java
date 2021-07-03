package rocks.shumyk.patterns.structural.composite;

import java.util.*;
import java.util.function.Consumer;

interface SomeNeurons extends Iterable<Neuron> {
	default void connectTo(final SomeNeurons other) {
		if (this == other) return;
		for (Neuron from : this) {
			for (Neuron to : other) {
				from.out.add(to);
				to.in.add(from);
			}
		}
	}
}

class Neuron implements SomeNeurons {
	public List<Neuron> in;
	public List<Neuron> out;

	@Override
	public Iterator<Neuron> iterator() {
		return Collections.singleton(this).iterator();
	}

	@Override
	public void forEach(Consumer<? super Neuron> action) {
		action.accept(this);
	}

	@Override
	public Spliterator<Neuron> spliterator() {
		return Collections.singleton(this).spliterator();
	}
}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeurons {}

public class NeuralNetworks {
	public static void main(String[] args) {
		final var neuron = new Neuron();
		final var neuron2 = new Neuron();
		final var layer = new NeuronLayer();
		final var layer2 = new NeuronLayer();

		neuron.connectTo(neuron2);
		neuron.connectTo(layer);
		layer.connectTo(neuron);
		layer.connectTo(layer2);
	}
}
