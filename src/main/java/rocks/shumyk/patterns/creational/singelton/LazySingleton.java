package rocks.shumyk.patterns.creational.singelton;

import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
public class LazySingleton {
	private static LazySingleton instance;

	private LazySingleton() {
		log.info("initializing a lazy singleton");
	}

	// lazy and thread safe initiation
	// synchronized assures it thread-safe but it impacts performance
//	public static synchronized LazySingleton getInstance() {
//		if (isNull(instance)) {
//			instance = new LazySingleton();
//		}
//		return instance;
//	}

	// lazy and thread safe initiation
	// double-checked locking
	// it is outdated and not used anymore, cause it is not atomicity
	// supposing thread B wants to use the singleton before Thread A has fully constructed it,
	// it cannot create a new instance because the reference is not null, so it just returns the partially constructed object.
	public static LazySingleton getInstance() {
		if (isNull(instance)) {
			synchronized (LazySingleton.class) {
				if (isNull(instance)) {
					instance = new LazySingleton();
				}
			}
		}
		return instance;
	}
}
