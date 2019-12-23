package jalcon.engine.math;

public class Matrix
{
	public final double data[][]; // row -> column
	
	public Matrix(double[]... data)
	{
		int n = data[0].length;
		for (double[] f : data) {
			if (f.length != n) throw new RuntimeException("Illegal matrix dimensions.");
		}
		this.data = data;
	}
	public Matrix(int m, int n)
	{
		this.data = new double[m][n];
	}

	public static Matrix multiply(Matrix mx1, Matrix mx2)
	{
		int m1 = mx1.data.length;
		int n1 = mx1.data[0].length;
		int m2 = mx2.data.length;
		int n2 = mx2.data[0].length;
		if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
		Matrix result = new Matrix(m1, n2);
		for (int i = 0; i < m1; i++)
			for (int j = 0; j < n2; j++)
				for (int k = 0; k < n1; k++)
					result.data[i][j] += mx1.data[i][k] * mx2.data[k][j];
		return result;
	}

	public static Matrix sum(Matrix mx1, Matrix mx2)
	{
		int m1 = mx1.data.length;
		int n1 = mx1.data[0].length;
		int m2 = mx2.data.length;
		int n2 = mx2.data[0].length;
		if (n1 != n2 || m1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
		Matrix result = new Matrix(m1, n1);
		for (int i = 0; i < m1; i++)
		{
			for (int j = 0; j < n1; j++)
			{
				result.data[i][j] = mx1.data[i][j] + mx2.data[i][j];
			}
		}
		return result;
	}
}
