package com.ty.fabrico.fabrico_springboot.customgeneration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CartCustomId implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = "CT_", suffix = "";
		try {
			Connection connection = session.connection();
			Statement statement = connection.createStatement();
			String sql = "select * from Cart order by cartId desc limit 1";
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				String res = resultSet.getString(1);
				String[] resultid = res.trim().split("_");
				int cartId = Integer.parseInt(resultid[1]);
				int id = cartId + 1;
				if (id <= 9) {
					suffix += "0" + id;
				} else {
					suffix += id;
				}
			} else {
				suffix = "0" + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prefix + suffix;
	}

}
