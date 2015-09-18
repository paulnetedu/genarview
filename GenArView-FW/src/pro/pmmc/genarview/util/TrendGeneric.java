package pro.pmmc.genarview.util;


public abstract class TrendGeneric extends GraphWordUtil implements TrendLabeler {

	protected double percentage;

	public TrendGeneric(double percentage) {
		this.percentage = percentage;
	}
}
