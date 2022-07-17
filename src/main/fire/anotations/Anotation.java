package main.fire.anotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

public class Anotation {
	Class<?> an;
	String pack;

	public Anotation(Class<?> class1, String pack) {
		this.an = class1;
		this.pack = pack;
	}

	public void invokeMethods() {
		Reflections find = new Reflections(pack, new MethodAnnotationsScanner());
		Set<Method> toCall = find.getMethodsAnnotatedWith((Class<? extends Annotation>) an);

		for (Iterator iterator = toCall.iterator(); iterator.hasNext();) {
			Method method = (Method) iterator.next();
			try {

				method.invoke(null, null);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
