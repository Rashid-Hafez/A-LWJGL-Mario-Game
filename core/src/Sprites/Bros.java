package Sprites;

//class

/**
 * Specifically designed class to switch between the two Mario Brothers, this uses the 
 * Object Orientated specific ENUM, allowing me to program so that if the user chooses
 * Mario, then the predetermined Mario values, such as gravity, velocity and size, will be set privately
 * but can be called using the get methods. 
 * 
 * Generally you will use this class by writing the following:
 * 
 * bro = new Bros();
 * bro.type = bro.type.Mario
 * or
 * bro.type = bro.type.Luigi
 * 
 * you can then use the get functions to do the following calculations:
 * 
 * int fixedValue= 2;
 * 
 * CircleShape radius = new CircleShape();
 * radius.setRadius(fixedValue*bro.type.getSize());
 * 
 * I'm smart right?? lol
 * 
 * @author Rashid
 *
 */
public class Bros {
	
	//enum class
	public enum BroType{
		Mario(1,1), Luigi(1.5f, 1.37933f);
		private float size;
		private float grav;
		
		private BroType(float inF, float gra){
			size = inF;
			grav = gra;
		}
		
		public float getSize(){
			return size;
		}
		
		public float getGrav(){
			return grav;
		}
	};
	
	public BroType type = BroType.Mario;
};
