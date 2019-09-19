package com.example.demo.Form;

import javax.validation.constraints.NotEmpty;

public class UserForm {
	
	@NotEmpty(message="名前を入力してください。")
	String name;
	
	@NotEmpty(message="メールアドレスを入力してください。")
	String email;
	
	@NotEmpty(message="郵便番号を入力してください。")
	String zipcode;
	
	@NotEmpty(message="住所を入力してください。")
	String address;
	
	@NotEmpty(message="電話番号を入力してください。")
	String telephone;
	
	@NotEmpty(message="パスワードを入力してください。")
	String password;
	
	@NotEmpty(message="確認用パスワードを入力してください。")
	String confirmPassword;
	
	String role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", zipcode=" + zipcode + ", address=" + address
				+ ", telephone=" + telephone + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", role=" + role + "]";
	}
	
}
