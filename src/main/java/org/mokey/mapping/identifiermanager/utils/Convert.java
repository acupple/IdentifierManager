package org.mokey.mapping.identifiermanager.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mokey.mapping.identifiermanager.models.Identifier;
import org.mokey.mapping.tuples.IPTuple;
import org.mokey.mapping.tuples.NumberTuple;
import org.mokey.mapping.tuples.StringTuple;
import org.mokey.mapping.tuples.Tuple;

public class Convert {
	
	public static final int LENGTH = 8;
	
	public static Tuple<?>[] id2tuples(Identifier id){
		Tuple<?>[] tuples = new Tuple<?>[LENGTH];
		
		StringTuple ip3Tuple = new StringTuple();
		ip3Tuple.setValue(id.getIp3());
		tuples[0] = ip3Tuple;
		
		IPTuple ipTuple = new IPTuple();
		ipTuple.setValue(id.getIp());
		tuples[1] = ipTuple;
		
		StringTuple uaTuple = new StringTuple();
		uaTuple.setValue(id.getUa());
		tuples[2] = uaTuple;
		
		StringTuple osTuple = new StringTuple();
		osTuple.setValue(id.getOs());
		tuples[3] = osTuple;
		
		NumberTuple osvTuple = new NumberTuple();
		osvTuple.setValue(id.getOsvs());
		tuples[4] = osvTuple;
		
		StringTuple bTuple = new StringTuple();
		bTuple.setValue(id.getBrowser());
		tuples[5] = bTuple;
		
		StringTuple lTuple = new StringTuple();
		lTuple.setValue(id.getLng());
		tuples[6] = lTuple;
		
		StringTuple uuidTuple = new StringTuple();
		uuidTuple.setValue(id.getUuid());
		tuples[7] = uuidTuple;
		
		return tuples;
	}
	
	public static Identifier rs2id(ResultSet rs) throws SQLException{
		Identifier id = new Identifier();
		id.setUuid(rs.getString("uuid"));
		id.setIp3(rs.getString("ip3"));
		id.setIp(rs.getString("ip"));
		id.setUa(rs.getString("ua"));
		id.setOs(rs.getString("os"));
		id.setOsvs(rs.getInt("osvs"));
		id.setBrowser(rs.getString("browser"));
		id.setLng(rs.getString("lng"));
		return id;
	}
	
	public static Identifier tp2id(Tuple<?>[] ret){
		if(ret == null)
			return null;
		Identifier id = new Identifier();
		id.setIp3(ret[0].toString());
		id.setIp(ret[1].toString());
		id.setUa(ret[2].toString());
		id.setOs(ret[3].toString());
		id.setOsvs(Integer.parseInt(ret[4].toString()));
		id.setBrowser(ret[5].toString());
		id.setLng(ret[6].toString());
		id.setUuid(ret[7].toString());
		
		return id;
	}

}
