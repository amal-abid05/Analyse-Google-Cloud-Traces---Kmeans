package machines;

public class Platform 
{
	private String id;
	public Platform(String id) 
	{
		this.id=id;
	}
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Platform)
		{
			Platform plat = (Platform)obj;
			return plat.id.equals(id);
		}
		else 
			return false;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}
	
}
