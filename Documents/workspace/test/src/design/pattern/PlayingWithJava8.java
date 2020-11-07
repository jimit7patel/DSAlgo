package design.pattern;

import java.util.Optional;

public class PlayingWithJava8 {

	
	
	public static void main(String[]  args) {
		
		Optional<String> opt = Optional.of("baeldung");
		opt.ifPresent(name -> System.out.println(name.length()));
		
		String nullName = null;
	    String name = Optional.ofNullable(nullName).orElse("john");
	    //assertEquals("john", name);
		
		
	}
}
