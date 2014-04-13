/**
 * Sierpinski class will draw a Sierpinski triangle
 * 
 * @author yichaox
 * 
 */
public class Sierpinski implements Fractal {

	private static final double Width = 1;

	@Override
	public Curve step0() {
		/* create three lines */
		Curve line1 = new Line(0, 0, Width, 0);
		Curve line2 = new Line(0, 0, Width * 0.5, Width * Math.sin(Math.PI / 3));
		Curve line3 = new Line(Width, 0, Width * 0.5, Width
				* Math.sin(Math.PI / 3));

		/* create a triangle with three lines */
		CompositeCurve triAngle = new CompositeCurve();
		triAngle.add(line1);
		triAngle.add(line2);
		triAngle.add(line3);

		return triAngle;
	}

	@Override
	public Curve transform(Curve curve) {
		/* scale the curve */
		curve.scale(0.5, 0.5);
		/* copy */
		Curve curve1 = curve.copy();
		Curve curve2 = curve.copy();
		/* move */
		curve1.translate(Width * 0.5, 0);
		curve2.translate(Width * 0.25, Width * Math.sin(Math.PI / 3) * 0.5);
		/* create a curve */
		CompositeCurve newCurve = new CompositeCurve();
		newCurve.add(curve);
		newCurve.add(curve1);
		newCurve.add(curve2);

		return newCurve;
	}

}
