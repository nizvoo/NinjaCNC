package st.tori.cnc.stencil.gerber.statement.aperture;

import java.util.Collection;

import st.tori.cnc.stencil.canvas.Drawable;
import st.tori.cnc.stencil.canvas.PositionXYInterface;
import st.tori.cnc.stencil.gerber.statement.aperture.modifier.ApertureModifier;
import st.tori.cnc.stencil.gerber.parser.Gerber;


public class GerberApertureCircle extends GerberAperture {

	private double diameter;
	
	public GerberApertureCircle(int dcode, double diameter, ApertureModifier modifier, Gerber gerber) {
		super(dcode, modifier, gerber);
		this.diameter = diameter;
	}
	
	public double getDiameter() {
		return diameter;
	}

	@Override
	public String getSimpleName() {
		return "%ADD"+dcode+",C";
	}

	@Override
	public float getStroke(PositionXYInterface lastPosition, PositionXYInterface position) {
		return (float)diameter;
	}

	@Override
	public Collection<Drawable> createDrawableCollection(PositionXYInterface origin) {
		return null;
	}

}
