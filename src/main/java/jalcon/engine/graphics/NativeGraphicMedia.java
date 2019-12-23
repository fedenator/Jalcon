package jalcon.engine.graphics;

import java.awt.Color;

import jalcon.engine.Renderer;
import jalcon.engine.math.Matrix;
import jalcon.engine.math.Position;
import jalcon.engine.math.Shape;
import jalcon.engine.math.Shape.Circle;
import jalcon.engine.math.Shape.Triangle;

public class NativeGraphicMedia implements IGraphicMedia
{
	private final Shape shape;
	
	public Color color;

	public NativeGraphicMedia(Shape shape) 
	{
		this.shape = shape;
		this.color = Color.CYAN;
	}

	@Override
	public void render(Position position, double angle, Renderer renderer) 
	{
		if (shape instanceof Circle)
		{
			float radius = ((Circle)this.shape).radius;
			renderer.draw_circle(position.x - radius / 2, position.y - radius / 2, radius, this.color);
		}
		else if (shape instanceof Triangle)
		{
			Triangle triangle = (Triangle) shape;
			Matrix rotateMx = new Matrix(
				new double[] { Math.cos(angle), -Math.sin(angle) }, 
				new double[] { Math.sin(angle),  Math.cos(angle) }
			);
			Matrix pointsMx = new Matrix(
				new double[] {triangle.points[0].x, triangle.points[0].y},
				new double[] {triangle.points[1].x, triangle.points[1].y},
				new double[] {triangle.points[2].x, triangle.points[2].y}
			);
			Matrix result = Matrix.multiply(pointsMx, rotateMx);
			Position centerOfTriangle = new Position(
				(float)(result.data[0][0] + result.data[1][0] + result.data[2][0]) / 3,
				(float)(result.data[0][1] + result.data[1][1] + result.data[2][1]) / 3
			);
			Position translateVector = Position.sum(position, Position.negative(centerOfTriangle));
			Matrix translateToCenter = new Matrix(
				new double[] { translateVector.x, translateVector.y },
				new double[] { translateVector.x, translateVector.y },
				new double[] { translateVector.x, translateVector.y }
			);
			result = Matrix.sum(result, translateToCenter);
			renderer.draw_circle(position.x - 10 / 2, position.y - 10 / 2, 10, this.color);
			renderer.draw_triangle(
				result.data[0][0], result.data[0][1],
				result.data[1][0], result.data[1][1],
				result.data[2][0], result.data[2][1],
				this.color
			);
		}
		else
		{
			throw new RuntimeException("WTF");
		}
	}
}
