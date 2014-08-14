package st.tori.cnc.stencil.gerber.statement.function;

import st.tori.cnc.stencil.canvas.PositionXYInterface;
import st.tori.cnc.stencil.gerber.parser.Gerber;

/*
 * Interpolate operation code
 */
public class DStatement01 extends DStatement {

	@Override
	protected int getDIndex() {	return 1;	}

	public DStatement01(PositionXYInterface position, Gerber gerber) {
		super(position, gerber);
	}

}
