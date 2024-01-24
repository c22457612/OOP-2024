package ie.tudublin;

public class Cat {
    String name;
	public int numLives;

	public void setName(String name)
	{
		this.name = name;
	}

	// A constructor. Same name as the file. 
	// No return value
	public Cat()
	{
		this.name = "rescue puppy";
        
	}
	
	// A constructor that takes name
	public Cat(String name)
	{
		this.name = name;
        this.numLives=9;
	}

	// A method
	public void speak()
	{
		System.out.println("Meow. I am " + name);
	}

    public void kill()
	{
        if(this.numLives>0)
        {
            numLives=numLives-1; 
            System.out.println("ouch!");
        }

        
        
        
	}
}
