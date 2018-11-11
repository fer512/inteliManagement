package ar.com.intelimanagement.domain.enumeration;

public enum ErrorEnum {
	
	CANT_APPROVE("error.cant.approve"), STATUS_INVALID("error.cant.approve.status.invalid"), USER_CREATION("error.cant.approve.user.creation"), USER_APPROVE("error.cant.approve.user.approve");

	
	private final String name;       

    private ErrorEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }

}
