package com.weile.webflux.reactor.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.beans.PropertyVetoException;

/**
 * @Auth weile
 * @Time 2019/12/8 17:11
 * @Description TODO
 **/
@Data
@Document(collection = "user")
public class User {

	@Id
	private String id;
	private String name;
	private String pass;
}
