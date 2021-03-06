package st.tori.cnc.stencil.canvas.shape;

import java.awt.Color;

import st.tori.cnc.stencil.canvas.PositionXYInterface;
import st.tori.cnc.stencil.canvas.SimpleXY;

public class Square extends Polygon {

	public Square(double x0, double y0, double x1, double y1, float stroke) {
		super(new PositionXYInterface[]{
			new SimpleXY(x0,y0),
			new SimpleXY(x1,y0),
			new SimpleXY(x1,y1),
			new SimpleXY(x0,y1),
			new SimpleXY(x0,y0),
		},stroke);
	}
	@Override
	protected String getName() {	return "Square";	}
	
}
