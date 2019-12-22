package jalcon.models.entities;

/**
 * Tipo que representa el tamaño y la produccion de cada planeta
 */
public enum PlanetType
{
	TYPE_0(0, 1, 50);

	public final int   planet_type_id;
	public final float production_per_second;
	public final float radius;

	private PlanetType(
		int   planet_type_id,
		float production_per_second,
		float radius
	)
	{
		this.planet_type_id        = planet_type_id;
		this.production_per_second = production_per_second;
		this.radius                = radius;
	}
}
