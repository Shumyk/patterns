package rocks.shumyk.patterns.structural.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human {
	void walk();
	void talk();
}

@Slf4j
class Person implements Human {
	@Override
	public void walk() {
		log.info("I am walking");
	}

	@Override
	public void talk() {
		log.info("I am talking");
	}
}

/*
	Java allows us to create invocation handler which will be intercepting all calls to target object.
	what is handy for implementing proxies
 */
@Slf4j
@RequiredArgsConstructor
class LoggingHandler implements InvocationHandler {
	private final Object target;
	private final Map<String, Integer> calls = new HashMap<>();

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		final String name = method.getName();
		if (name.contains("toString")) {
			return calls.toString();
		}
		calls.merge(name, 1, Integer::sum);
		return method.invoke(target, args);
	}
}

@Slf4j
public class DynamicProxyForLogging {
	/*
	method helps construct proxy for specific object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T withLogging(T target, Class<T> itf) {
		return (T) Proxy.newProxyInstance(
			itf.getClassLoader(),
			new Class<?>[] { itf },
			new LoggingHandler(target)
		);
	}

	public static void main(String[] args) {
		final Person person = new Person();
		final Human loggedPerson = withLogging(person, Human.class);
		loggedPerson.talk();
		loggedPerson.walk();
		loggedPerson.walk();
		log.info("{}", loggedPerson);
	}
}
