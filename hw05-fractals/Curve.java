/**
 * 
 * @author Yichao Xue <yichaox>
 * @section Section B
 * 
 */

public interface Curve {

	/**
	 * draw a curve on the sketch pad
	 * 
	 * @param pad
	 */
	void draw(SketchPad pad);

	/**
	 * translates to the right by tx and up by ty
	 * 
	 * @param tx
	 *            offset in x-axis
	 * @param ty
	 *            offset in y-axis
	 */
	void translate(double tx, double ty);

	/**
	 * scales width by sx and height by of sy
	 * 
	 * @param sx
	 *            factor of x-axis
	 * @param sy
	 *            factor of y-axis
	 */
	void scale(double sx, double sy);

	/**
	 * rotates counter-clockwise by degrees (about the origin)
	 * 
	 * @param degrees
	 *            degree
	 */
	void rotate(double degrees);

	/**
	 * copy a curve
	 * 
	 * @return a deep copy of this Curve
	 */
	Curve copy();

}