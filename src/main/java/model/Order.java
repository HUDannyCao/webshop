package model;

import java.util.ArrayList;

public class Order {
	
	private int id;
	private Account account;
	private Address address;
	
	
	public Order(int id, Account account, Address address) {
		this.id = id;
		this.account = account;
		this.address = address;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object anderObject) {
		boolean isEqual = false;

		if (anderObject instanceof Order) {
			Order anderOrder = (Order) anderObject;

			if (this.id == anderOrder.getId() &&
					this.account.equals(anderOrder.getAccount()) &&
					this.address.equals(anderOrder.getAddress())) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}
