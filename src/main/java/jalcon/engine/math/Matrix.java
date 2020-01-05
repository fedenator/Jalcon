package jalcon.engine.math;

import java.util.Arrays;

public class Matrix
{
	public double data[][]; // row -> column
	
	public Matrix(double[][] data)
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

	public Matrix multiply(Matrix mx)
	{
		int m1 = this.data.length;
		int n1 = this.data[0].length;
		int m2 = mx.data.length;
		int n2 = mx.data[0].length;
		if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
		double [][]result = new double[m1][n2];
		for (int i = 0; i < m1; i++)
			for (int j = 0; j < n2; j++)
				for (int k = 0; k < n1; k++)
					result[i][j] += this.data[i][k] * mx.data[k][j];
		this.data = result;
		return this;
	}

	public Matrix sum(Matrix mx1, Matrix mx2)
	{
		int m1 = mx1.data.length;
		int n1 = mx1.data[0].length;
		int m2 = mx2.data.length;
		int n2 = mx2.data[0].length;
		if (n1 != n2 || m1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
		double [][]result = new double[m1][n1];
		for (int i = 0; i < m1; i++)
		{
			for (int j = 0; j < n1; j++)
			{
				result[i][j] = mx1.data[i][j] + mx2.data[i][j];
			}
		}
		this.data = result;
		return this;
	}

	public Matrix clone()
	{
		double [][]copyArray = new double[this.data.length][this.data[0].length];
		for (int i = 0; i < this.data.length; i++)
		{
			copyArray[i] = Arrays.copyOf(this.data[i], this.data[i].length);
		}
		return new Matrix(copyArray);
	}
}
