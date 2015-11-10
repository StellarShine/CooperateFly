package com.cooperate.fly.common.mybatis;

public class Dialect {
	public boolean supportsLimit(){
		return false;
	}
	
	public boolean supportLimitOffset(){
		return supportsLimit();
	}
	
	/**
	 * 将sql变为使用offset,limit的值作为占位符的分页sql语句
	 * @param sql
	 * @param offset
	 * @param limit
	 * @return
	 */
	public String getLimitString(String sql,int offset,int limit){
		return getLimitString(sql, offset, Integer.toString(offset),limit,Integer.toString(limit));
	}
	
	public String getLimitString(String sql,int offset,String offsetPlaceholder,int limit,String limitPlaceholder){
		throw new UnsupportedOperationException("paged queries not supported");		
	}
	
	/**
	 * 将sql转化为总记录数SQL
	 */
	public String getCountString(String sql){
		return "select count(1) from ("+sql+") temp_count";
	}
}