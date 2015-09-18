package pro.pmmc.genarview.uml;

import java.util.function.BinaryOperator;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;

public class UmlReducer implements BinaryOperator<Package> {

	@Override
	public Package apply(Package t, Package u) {
		Package p = t;
		if (u instanceof Model) {
			p = u;
		}
		return p;
	}

}
