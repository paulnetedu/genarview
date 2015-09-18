package pro.pmmc.genarview.uml;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;

public class UmlModel {

	protected Model model;

	public UmlModel() {
		model = UMLFactory.eINSTANCE.createModel();
	}
	
	public UmlModel(String modelName) {
		this();
		model.setName(modelName);
	}
	
	
}
