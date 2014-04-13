/**
 * 
 * @author Yichao Xue <yichaox>
 * @section B
 * 
 */

public interface Fractal {

	/**
	 * the first step
	 * 
	 * @return a new Curve representing step 0 of the fractal
	 */
	Curve step0();

	/**
	 * given a Curve representing step n of the fractal, uses it to return a new
	 * Curve representing step n+1 of the fractal
	 * 
	 * @param curve
	 *            a curve
	 * @return a new Curve representing step n+1 of the fractal
	 */
	Curve transform(Curve curve);

}