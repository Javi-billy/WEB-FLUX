package com.cqrs.pattern.es.events;

//@EqualsAndHashCode(callSuper = false)
public class UserCreatedEvent extends Event {

	private String userId;
	private String firstName;
	private String lastName;

	public UserCreatedEvent(String userId, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof UserCreatedEvent))
			return false;
		UserCreatedEvent other = (UserCreatedEvent) o;
		if (!other.canEqual((Object) this))
			return false;
		if (!super.equals(o))
			return false;
		if (this.userId != other.userId)
			return false;
		if (this.firstName != other.firstName)
			return false;
		if (this.lastName != other.lastName)
			return false;
		return true;
	}

	protected boolean canEqual(Object other) {
		return other instanceof UserCreatedEvent;
	}
}
