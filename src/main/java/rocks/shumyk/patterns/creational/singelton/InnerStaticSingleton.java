package rocks.shumyk.patterns.creational.singelton;

// implementation of thread-safe singleton pattern
// we going to have only one instance of this, cause inner class couldn't be seen from outside
public class InnerStaticSingleton {

	private InnerStaticSingleton() {}

	private static class Impl {
		private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();

		public static InnerStaticSingleton getInstance() {
			return Impl.INSTANCE;
		}
	}
}
