import java.util.ArrayList;

/**
 * CompositeCurve is the composition of several curves
 * 
 * @author yichaox
 * 
 */
public class CompositeCurve implements Curve {

	private ArrayList<Curve> curves;

	/**
	 * constructor
	 * 
	 * @param curves
	 *            a curve object
	 */
	public CompositeCurve() {
		this.curves = new ArrayList<Curve>();
	}

	@Override
	public void draw(SketchPad pad) {
		for (int i = 0; i < curves.size(); i++) {
			curves.get(i).draw(pad);
		}
	}

	/**
	 * add a curve to composite curve
	 * 
	 * @param curve
	 *            a curve
	 */
	public void add(Curve curve) {
		curves.add(curve);
	}

	@Override
	public void translate(double tx, double ty) {
		for (int i = 0; i < curves.size(); i++) {
			curves.get(i).translate(tx, ty);
		}
	}

	@Override
	public void scale(double sx, double sy) {
		for (int i = 0; i < curves.size(); i++) {
			curves.get(i).scale(sx, sy);
		}

	}

	@Override
	public void rotate(double degrees) {
		for (int i = 0; i < curves.size(); i++) {
			curves.get(i).rotate(degrees);
		}
	}

	@Override
	public Curve copy() {
		CompositeCurve newCurve = new CompositeCurve();

		for (int i = 0; i < curves.size(); i++) {
			Curve c = curves.get(i).copy();
			newCurve.add(c);
		}
		return newCurve;
	}

}
