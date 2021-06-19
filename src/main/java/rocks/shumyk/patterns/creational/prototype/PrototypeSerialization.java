package rocks.shumyk.patterns.creational.prototype;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

@ToString
@AllArgsConstructor
class Foo implements Serializable {
	public int stuff;
	public String whatever;
}

// prototype pattern
// copy object through serialization (could be slow)
public class PrototypeSerialization {
	public static void main(String[] args) {
		final var hereIsText = new Foo(45, "here is text");
		final var deserializedFoo = (Foo) SerializationUtils.deserialize(SerializationUtils.serialize(hereIsText));

		deserializedFoo.whatever = "xyz";

		System.out.println(hereIsText);
		System.out.println(deserializedFoo);
	}
}
