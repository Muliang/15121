/**
 * Koch class will draw a Koch's snow fake;
 * 
 * @author yichaox
 * 
 */
public class Koch implements Fractal {

	private static final double Width = 1;

	@Override
	public Curve step0() {
		Curve line = new Line(0, 0, Width, 0);
		return line;
	}

	@Override
	public Curve transform(Curve curve) {
		/* scale the curve */
		curve.scale(1.0 / 3, 1.0 / 3);
		/* copy */
		Curve curve1 = curve.copy();
		Curve curve2 = curve.copy();
		Curve curve3 = curve.copy();
		/* rotate */
		curve2.rotate(60);
		curve3.rotate(300);
		/* move */
		curve1.translate(Width * 2 / 3, 0);
		curve2.translate(Width / 3, 0);
		curve3.translate(Width / 2, Width / 3 * Math.sin(Math.PI / 3));

		CompositeCurve newCurve = new CompositeCurve();
		newCurve.add(curve);
		newCurve.add(curve1);
		newCurve.add(curve2);
		newCurve.add(curve3);
		return newCurve;
	}

}
