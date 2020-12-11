package vn.techmaster.demobean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import vn.techmaster.demobean.component.Car;


@ComponentScan
public class DemobeanApplication {	
	private static ApplicationContext context;

	@Autowired
	private Car car;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(DemobeanApplication.class);
		System.out.println("------------------------------------");
		for (String beanName : context.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}
		Car car = context.getBean(Car.class);
		/* Lệnh dưới không thể chạy được vì lỗi sau
		Cannot make a static reference to the non-static field zip
		System.out.println(car);
		*/
	}
}
