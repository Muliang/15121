/**
 * 
 * @author Yichao Xue <yichaox>
 * @section B
 * 
 */

public class Fractals {
	public static Curve generateFractal(Fractal fractal, int step) {
		/* illegal step */
		if (step < 0) {
			System.out.println("Illegal step: " + step
					+ ", step should equal or be greater than 0.");
			System.out.println("Set step to 0.");
			step = 0;
		}

		Curve result = fractal.step0();
		double offset = 0.1;

		if (step == 0) {
			/* move 0.1 from the bottom of the sketch pad */
			result.translate(0, offset);
			return result;
		}
		for (int i = 0; i < step; i++) {
			result = fractal.transform(result);
		}
		/* move 0.1 from the bottom of the sketch pad */
		result.translate(0, offset);
		return result;
	}

	public static void main(String[] args) {
		SketchPad pad = new SketchPad();

		Fractal sierpinski = new Sierpinski();
		Curve curve = generateFractal(sierpinski, 5);
		curve.draw(pad);

	}
}