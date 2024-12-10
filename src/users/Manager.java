package users;

import enums.ManagerType;

//доделать
public class Manager extends Employee{
	private ManagerType managerType;

	public Manager(String id, String firstName, String lastName, String email, String password, int salary, ManagerType managerType) {
		super(id, firstName, lastName, email, password, salary);
		this.managerType = managerType;
	}

	public ManagerType getManagerType() {
		return managerType;
	}

	public void setManagerType(ManagerType managerType) {
		this.managerType = managerType;
	}
	
	@Override
	public void displayFunct() {
		// TODO Auto-generated method stub
		
	}
	
	
}
