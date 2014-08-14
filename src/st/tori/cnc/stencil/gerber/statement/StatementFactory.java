package st.tori.cnc.stencil.gerber.statement;

import java.util.ArrayList;

import st.tori.cnc.stencil.gerber.exception.ApertureMacroNotDefinedException;
import st.tori.cnc.stencil.gerber.exception.ArithmeticExpressionUnsupportedException;
import st.tori.cnc.stencil.gerber.exception.IllegalParameterModifiersException;
import st.tori.cnc.stencil.gerber.exception.IllegalReflectionException;
import st.tori.cnc.stencil.gerber.exception.NoLastStatementExistsException;
import st.tori.cnc.stencil.gerber.exception.UnsupportedApertureException;
import st.tori.cnc.stencil.gerber.exception.UnsupportedIndexException;
import st.tori.cnc.stencil.gerber.exception.UnsupportedParameterCodeException;
import st.tori.cnc.stencil.gerber.parser.Gerber;
import st.tori.cnc.stencil.gerber.statement.aperture.GerberAperture;
import st.tori.cnc.stencil.gerber.statement.aperture.GerberApertureCircle;
import st.tori.cnc.stencil.gerber.statement.aperture.GerberApertureMacro;
import st.tori.cnc.stencil.gerber.statement.aperture.GerberApertureObround;
import st.tori.cnc.stencil.gerber.statement.aperture.GerberApertureRectangle;
import st.tori.cnc.stencil.gerber.statement.aperture.GerberApertureRegularPolygon;
import st.tori.cnc.stencil.gerber.statement.aperture.UnsupportedApertureInterface;
import st.tori.cnc.stencil.gerber.statement.aperture.modifier.ApertureModifier;
import st.tori.cnc.stencil.gerber.statement.aperture.modifier.ApertureModifierHole;
import st.tori.cnc.stencil.gerber.statement.aperture.modifier.ApertureModifierRectanglar;
import st.tori.cnc.stencil.gerber.statement.function.DStatement;
import st.tori.cnc.stencil.gerber.statement.function.DStatement01;
import st.tori.cnc.stencil.gerber.statement.function.DStatement02;
import st.tori.cnc.stencil.gerber.statement.function.DStatement03;
import st.tori.cnc.stencil.gerber.statement.function.DStatement10orHigher;
import st.tori.cnc.stencil.gerber.statement.function.GStatement;
import st.tori.cnc.stencil.gerber.statement.function.GStatement01;
import st.tori.cnc.stencil.gerber.statement.function.GStatement02;
import st.tori.cnc.stencil.gerber.statement.function.GStatement03;
import st.tori.cnc.stencil.gerber.statement.function.GStatement04;
import st.tori.cnc.stencil.gerber.statement.function.GStatement36;
import st.tori.cnc.stencil.gerber.statement.function.GStatement37;
import st.tori.cnc.stencil.gerber.statement.function.GStatement74;
import st.tori.cnc.stencil.gerber.statement.function.GStatement75;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatement;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementAD;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementAM;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementFS;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementIP;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementLP;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementMO;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementOF;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementSR;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementTA;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementTD;
import st.tori.cnc.stencil.gerber.statement.parameter.PStatementTF;

public class StatementFactory {

	public static GStatement createGStatement(int gIndex, Gerber gerber) throws UnsupportedIndexException, NoLastStatementExistsException, IllegalReflectionException {
		GStatement statement = null;
		if(gIndex<0)
			statement = gerber.cloneLastStatement();
		else if(gIndex==1)
			statement = new GStatement01(gerber);
		else if(gIndex==2)
			statement = new GStatement02(gerber);
		else if(gIndex==3)
			statement = new GStatement03(gerber);
		else if(gIndex==4)
			statement = new GStatement04(gerber);
		else if(gIndex==36)
			statement = new GStatement36(gerber);
		else if(gIndex==37)
			statement = new GStatement37(gerber);
		else if(gIndex==74)
			statement = new GStatement74(gerber);
		else if(gIndex==75)
			statement = new GStatement75(gerber);
		if(statement==null || statement instanceof UnsupportedStatementInterface)
			throw new UnsupportedIndexException("G",gIndex);
		return statement;
	}
	
	public static DStatement createDStatement(int dIndex, Gerber gerber) throws UnsupportedIndexException {
		DStatement statement = null;
		if(dIndex==1)
			statement = new DStatement01(gerber);
		else if(dIndex==2)
			statement = new DStatement02(gerber);
		else if(dIndex==3)
			statement = new DStatement03(gerber);
		else if(dIndex>=10)
			statement = new DStatement10orHigher(gerber);
		if(statement==null || statement instanceof UnsupportedStatementInterface)
			throw new UnsupportedIndexException("D",dIndex);
		return statement;
	}

	public static PStatement createPStatement(String parameterCode, String modifiers, Gerber gerber) throws UnsupportedParameterCodeException, IllegalParameterModifiersException, ArithmeticExpressionUnsupportedException {
		PStatement statement = null;
		if("FS".equals(parameterCode))
			statement = new PStatementFS(modifiers, gerber);
		else if("MO".equals(parameterCode))
			statement = new PStatementMO(modifiers, gerber);
		else if("IP".equals(parameterCode))
			statement = new PStatementIP(modifiers, gerber);
		else if("AD".equals(parameterCode))
			statement = new PStatementAD(modifiers, gerber);
		else if("AM".equals(parameterCode))
			statement = new PStatementAM(modifiers, gerber);
		else if("SR".equals(parameterCode))
			statement = new PStatementSR(modifiers, gerber);
		else if("LP".equals(parameterCode))
			statement = new PStatementLP(modifiers, gerber);
		else if("TF".equals(parameterCode))
			statement = new PStatementTF(modifiers, gerber);
		else if("TD".equals(parameterCode))
			statement = new PStatementTD(modifiers, gerber);
		else if("TA".equals(parameterCode))
			statement = new PStatementTA(modifiers, gerber);
		else if("OF".equals(parameterCode))
			statement = new PStatementOF(modifiers, gerber);
		if(statement==null || statement instanceof UnsupportedStatementInterface)
			throw new UnsupportedParameterCodeException(parameterCode,modifiers);
		return statement;
	}

	public static GerberAperture createAperture(int dcode, String type, String modifiersStr, Gerber gerber) throws UnsupportedApertureException, ApertureMacroNotDefinedException {
		GerberAperture aperture = null;
		ModifiersContainer container = new ModifiersContainer("X", modifiersStr);
		if("C".equals(type))
			aperture = new GerberApertureCircle(dcode, container.getAsDouble(0), container.getApertureModifier(1), gerber);
		else if("R".equals(type))
			aperture = new GerberApertureRectangle(dcode, container.getAsDouble(0), container.getAsDouble(1), container.getApertureModifier(2), gerber);
		else if("O".equals(type))
			aperture = new GerberApertureObround(dcode, container.getAsDouble(0), container.getAsDouble(1), container.getApertureModifier(2), gerber);
		else if("P".equals(type))
			aperture = new GerberApertureRegularPolygon(dcode, container.getAsDouble(0), container.getAsInt(1), container.getAsDouble(2), container.getApertureModifier(3), gerber);
		else
			aperture = new GerberApertureMacro(dcode, type, gerber);
		if(aperture==null || aperture instanceof UnsupportedApertureInterface)
			throw new UnsupportedApertureException(dcode, type, modifiersStr);
		return aperture;
	}
	
	public static class ModifiersContainer extends ArrayList<String> {
		
		public ModifiersContainer(String sep, String modifiersStr) {
			String[] array = modifiersStr.split(sep);
			for(String val:array)
				add(val);
		}
		
		public Double getAsDouble(int index) {
			return (size()<index)?null:parseDouble(get(index));
		}
		public Integer getAsInt(int index) {
			return (size()<index)?null:Integer.parseInt(get(index));
		}
		
		public ApertureModifier getApertureModifier(int index) {
			if(index>=size())return null;
			if(index+1==size())
				return new ApertureModifierHole(parseDouble(get(index)));
			return new ApertureModifierRectanglar(parseDouble(get(index)),parseDouble(get(index+1)));
		}
	}
	public static double parseDouble(String val) {
		if(val.startsWith("."))val = "0" + val;
		return Double.parseDouble(val);
	}

}
