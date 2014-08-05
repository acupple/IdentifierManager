package org.mokey.mapping.identifiermanager.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mokey.mapping.identifiermanager.models.Identifier;
import org.mokey.mapping.tuples.IPTuple;
import org.mokey.mapping.tuples.NumberTuple;
import org.mokey.mapping.tuples.StringTuple;
import org.mokey.mapping.tuples.Tuple;

public class Convert {
	
	private static final int LENGTH = 6;
	
	public Tuple<?>[] id2tuples(Identifier id){
		Tuple<?>[] tuples = new Tuple<?>[LENGTH];
		
		IPTuple ipTuple = new IPTuple();
		ipTuple.setValue(id.getIp());
		tuples[0] = ipTuple;
		
		StringTuple uaTuple = new StringTuple();
		uaTuple.setValue(id.getUa());
		tuples[1] = uaTuple;
		
		StringTuple osTuple = new StringTuple();
		osTuple.setValue(id.getOs());
		tuples[2] = osTuple;
		
		NumberTuple osvTuple = new NumberTuple();
		osvTuple.setValue(id.getOsvs());
		tuples[3] = osvTuple;
		
		StringTuple bTuple = new StringTuple();
		bTuple.setValue(id.getBrowser());
		tuples[4] = bTuple;
		
		StringTuple lTuple = new StringTuple();
		lTuple.setValue(id.getLng());
		tuples[5] = lTuple;
		
		return tuples;
	}
	
	public Identifier rs2id(ResultSet rs) throws SQLException{
		Identifier id = new Identifier();
		id.setUuid(rs.getString("uuid"));
		id.setIp(rs.getString("ip"));
		id.setUa(rs.getString("ua"));
		id.setOs(rs.getString("os"));
		id.setOsvs(rs.getInt("osv"));
		id.setBrowser(rs.getString("browser"));
		id.setLng(rs.getString("lng"));
		return id;
	}

}
