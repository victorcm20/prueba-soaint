package com.aquintero.soaint.prueba.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rol")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name = "log_file")
	private Boolean logToFile;
	
	@Column(name = "log_console")
	private Boolean logToConsole;
	
	@Column(name = "log_message")
	private Boolean logMessage;
	
	@Column(name = "log_warning")
	private Boolean logWarning;
	
	@Column(name = "log_error")
	private Boolean logError;
	
	@Column(name = "log_database")
	private Boolean logToDatabase;
	
	@OneToMany(mappedBy="rol")
    private List<User> users;
	
}
