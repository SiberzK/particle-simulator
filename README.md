# Running the program.

Program arguments: 

'Force class' 'Algorithm class' 'force parameters' 'particle list' 'trajectory output' 'energy output' 'number of iterations' 'length of timestep' (6 Strings, an integer and a double)

e.g "Gravity Verlet g.dat planets.dat output.xyz energy.dat 10000 0.1"

Particle list input file may take two forms:

- Explicit constructor. Each line must contain a single particle with the format: 

'label' 'mass' 'xPos' 'yPos' 'zPos' 'xVel' 'yVel' 'zVel' (String followed by 7 doubles)

- Default constructor. File must contain a single integer indicating the number of default particles to be created.

# Adding new force and/or algorithm classes.

Force class must implement the following methods from Force interface:

	public Vector computeForce(Particle a, Particle b);
	public double potentialEnergy(Particle a, Particle b);
	public void systemParameters(Scanner parameters);
	public boolean boundaryConditions(Particle particle);
	
Algorithm class must implement the following methods from Algorithm interface:

	public void updateSystem(Vector[] forces);
