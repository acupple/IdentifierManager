package org.mokey.mapping.identifiermanager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.mokey.mapping.Mapper;
import org.mokey.mapping.configurations.Configuration;
import org.mokey.mapping.dal.DataAccessor;
import org.mokey.mapping.dal.DataProvider;
import org.mokey.mapping.identifiermanager.models.Identifier;
import org.mokey.mapping.tuples.Tuple;

public class IdentityMapper {
	private static Mapper mapper = null;
	static{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Configuration conf = new Configuration(loader.getResource("config.xml").getFile());
		DataAccessor dao = new DataAccessor(new DataProvider(){
			@Override
			public List<Tuple<?>[]> getTargetCollection(Tuple<?>[] res) {
				List<Tuple<?>[]> result = new ArrayList<Tuple<?>[]>();
				if(res.length != Convert.LENGTH)
					return result;
				Connection conn = null;
				ResultSet rs = null;
				PreparedStatement statm = null;
				try{
					conn = DataSourceUtils.getConnection();
					statm = conn.prepareStatement("SELECT * FROM identities WHERE ip3 = ?");
					statm.setString(1, res[0].toString());
					rs = statm.executeQuery();
					while(rs.next()){
						Identifier id = Convert.rs2id(rs);
						result.add(Convert.id2tuples(id));
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				return result;
			}
			
		});
		mapper = new Mapper(conf, dao);
	}
	
	public static Identifier map(Identifier id){
		Tuple<?>[] t = Convert.id2tuples(id);
		Tuple<?>[] ret = mapper.map(t);
		return Convert.tp2id(ret);
	}
}
