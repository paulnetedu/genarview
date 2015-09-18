package pro.pmmc.genarview.uml;

import java.io.IOException; 
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.resource.UMLResource;

public class UmlXmi implements Consumer<Element> {

	private String filePath;
	
	private String fileName;
	
	protected static final ResourceSet RESOURCE_SET = new ResourceSetImpl();
	
	public UmlXmi(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}

	protected static void registerResourceFactories() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
			UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
	}

	protected static void registerPathmaps(URI uri) {
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP),
			uri.appendSegment("libraries").appendSegment(""));

		URIConverter.URI_MAP.put(URI.createURI(UMLResource.METAMODELS_PATHMAP),
			uri.appendSegment("metamodels").appendSegment(""));

		URIConverter.URI_MAP.put(URI.createURI(UMLResource.PROFILES_PATHMAP),
			uri.appendSegment("profiles").appendSegment(""));
	}
	
	@Override
	public void accept(Element element) {
		registerResourceFactories();
		URI uri = URI.createURI("file:/" + filePath).appendSegment(fileName)
				.appendFileExtension(UMLResource.FILE_EXTENSION);
		Resource resource = RESOURCE_SET.createResource(uri);
		resource.getContents().add(element.getModel());
		try {
			resource.save(null);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
	}

}
