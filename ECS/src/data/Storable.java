package data;


public interface Storable {
	public int store();
	public void setId(int id);
	public int getId();
	public String getName();
	public void setName(String name);
	public String getDesignation();
	public void setDesignation(String designation);
	public String getEmail();
	public void setEmail(String email);
	public long getContact();
	public void setContact(long contact);
}
