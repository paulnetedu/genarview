package pro.pmmc.genarview.uml;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Package;
import pro.pmmc.genarview.util.FileUtil;

public class UmlPackage extends UmlModel {

	protected Map<String, org.eclipse.uml2.uml.Package> mapPackages;
	
	public UmlPackage() {
		super();
		mapPackages = new HashMap<String, org.eclipse.uml2.uml.Package>();
	}
	
	public UmlPackage(String modelName) {
		super(modelName);
		mapPackages = new HashMap<String, org.eclipse.uml2.uml.Package>();
	}
	
	public void addPackage(org.eclipse.uml2.uml.Package pack) {
		mapPackages.put(pack.getName(), pack);
	}
	
	public org.eclipse.uml2.uml.Package getPackage(String packageFullName) {
		return mapPackages.get(packageFullName);
	}
	
	public org.eclipse.uml2.uml.Package getOrCreatePackage(String packageFullName) {
		org.eclipse.uml2.uml.Package p = mapPackages.get(packageFullName);
		if (p == null) {
			p = createPackage(packageFullName); 
		}
		return p;
	}
	
	public Package createPackage(String packageFullName) {
		org.eclipse.uml2.uml.Package parent = null;
		if (packageFullName.indexOf(".") == -1) {
			parent = model;
		} else {
			parent = getOrCreatePackage(FileUtil.extractParentFullPackage(packageFullName));
		}
		return createPackage(packageFullName, parent);
	}
	
	public Package createPackage(String packageFullName, Package packParent) {
		Package p = packParent.createNestedPackage(FileUtil.extractLastName(packageFullName));
		mapPackages.put(packageFullName, p);
		return p;
	}
}
