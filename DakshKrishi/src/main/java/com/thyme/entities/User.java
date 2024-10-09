package com.thyme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USER")

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Username validation: cannot be empty, and size must be between 5 and 14 characters
    @NotNull
//    @NotBlank(message = "Username cannot be empty")
    @Size(min = 5, max = 14, message = "Username must be between 5 and 14 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must contain only alphanumeric characters")
    private String name;

    // Email validation: cannot be empty, must be unique, and must be a valid email format
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(unique = true)  // Ensures email is unique in the database
    private String email;


	// Password validation: cannot be empty, must be between 7 and 14 characters, and must include at least one uppercase letter, one lowercase letter, and one digit
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 7, max = 14, message = "Password must be between 7 and 14 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;

    // Role validation: cannot be empty and must match specified roles (for example, ADMIN, USER)
    @NotBlank(message = "Role is required")
    private String role;

    // Enabled status (e.g., active user or not)
    private boolean enabled;

    // Optional image URL (no validation necessary)
    private String imageURL;

    // About section with a max length of 500 characters
    @Size(max = 500, message = "About section cannot exceed 500 characters")
    @Column(length = 500) // To enforce the length at the database level
    private String about;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

    // One-to-Many relationship with the Contact entity
   
}
