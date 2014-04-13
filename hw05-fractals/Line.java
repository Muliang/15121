/**
 * Line is a single line in the sketchPad
 * 
 * @author yichao
 * 
 */
public class Line implements Curve {
	private double srcX;
	private double srcY;
	private double desX;
	private double desY;

	/**
	 * constructor method
	 * 
	 * @param srcX
	 *            x of source
	 * @param srcY
	 *            y of source
	 * @param desX
	 *            x of destination
	 * @param desY
	 *            y of destination
	 */
	public Line(double srcX, double srcY, double desX, double desY) {
		super();
		this.srcX = srcX;
		this.srcY = srcY;
		this.desX = desX;
		this.desY = desY;
	}

	/**
	 * draw a line on sketch pad
	 * 
	 * @param pad
	 *            sketch pad
	 */
	public void draw(SketchPad pad) {
		pad.drawLine(srcX, srcY, desX, desY);
	}

	@Override
	public void translate(double tx, double ty) {
		this.srcX += tx;
		this.srcY += ty;
		this.desX += tx;
		this.desY += ty;
	}

	@Override
	public void scale(double sx, double sy) {
		this.srcX *= sx;
		this.srcY *= sy;
		this.desX *= sx;
		this.desY *= sy;
	}

	@Override
	public void rotate(double degrees) {
		/* convert to radiant */
		double radiant = degrees / 180 * Math.PI;

		/* rotate start point */
		double tempX = srcX;
		srcX = srcX * Math.cos(radiant) - srcY * Math.sin(radiant);
		srcY = tempX * Math.sin(radiant) + srcY * Math.cos(radiant);

		/* rotate end point */
		tempX = desX;
		desX = desX * Math.cos(radiant) - desY * Math.sin(radiant);
		desY = tempX * Math.sin(radiant) + desY * Math.cos(radiant);

	}

	@Override
	public Curve copy() {
		Curve newCurve = new Line(srcX, srcY, desX, desY);

		return newCurve;
	}

}
