import com.mageddo.rawstringliterals.RawString;
import com.mageddo.rawstringliterals.RawStrings;
import com.mageddo.rawstringliterals.Rsl;

import java.util.function.Supplier;

import static com.mageddo.rawstringliterals.RawStrings.lateInit;

@Rsl
public class VariableInsideLambda {
	public String sayHello(){
		return processLambda(c -> {
			/*
				SELECT
					NAME, AGE
				FROM CUSTOMER
			*/
			@RawString
			String word = lateInit();
			return word;
		});
	}

	String processLambda(MyConsumer s){
		return s.apply("hello!");
	}

	public static interface MyConsumer {
		String apply(String arg);
	}

}
