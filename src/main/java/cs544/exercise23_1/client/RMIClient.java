package cs544.exercise23_1.client;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

import cs544.exercise23_1.server.ICalculator;
import cs544.exercise23_1.server.IGreeting;
import cs544.exercise23_1.server.Person;

public class RMIClient {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"springconfigclient.xml");
		IGreeting remoteServer = context
				.getBean("helloserver", IGreeting.class);
		Person person = new Person("John", "Doe");
		String result = remoteServer.getMessage(person);
		System.out.println("Receiving result: " + result);
		
		StopWatch clock = new StopWatch();
		ICalculator remoteServer2 = context.getBean("calculateServer", ICalculator.class);
		clock.start();
		int calc = remoteServer2.calc('+', 3);
		clock.stop();
		System.out.println("Calculate result: " + calc);
		System.out.println(clock.prettyPrint());
		
		clock.start();
		int calc2 = remoteServer2.calc('+', 6);
		clock.stop();
		System.out.println("Calculate result: " + calc2);
		System.out.println(clock.prettyPrint());
		
		clock.start();
		int calc3 = remoteServer2.calc('*', 4);
		clock.stop();
		System.out.println("Calculate result: " + calc3);
		System.out.println(clock.prettyPrint());
		context.close();
	}

}
