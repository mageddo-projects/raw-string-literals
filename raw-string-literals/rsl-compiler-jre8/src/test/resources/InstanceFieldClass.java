import com.mageddo.rawstringliterals.RawString;
import com.mageddo.rawstringliterals.RawStrings;
import com.mageddo.rawstringliterals.Rsl;

import static com.mageddo.rawstringliterals.RawStrings.lateInit;

@Rsl
public class InstanceFieldClass {

	private static final String MSG = lateInit();

	public String sayHello(){
		return MSG;
	}
}
