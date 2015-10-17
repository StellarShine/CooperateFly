package com.cooperate.fly.service.operator;

import java.util.List;

import com.cooperate.fly.bo.DataInfo;
import com.cooperate.fly.bo.DataValue;
import com.cooperate.fly.bo.PackageInfo;
import com.cooperate.fly.bo.PackageVersion;

public interface DataOperate {

	int[] getPackagesOwn(int userId);
	void getMessage(String user_name);
	PackageVersion createVersion(int packageId, String parent_version_id);
	List<PackageInfo> getUpPackages(int packageId);//在创建版本草稿前，要选择此版本对应的上游数据包版本
	List<PackageVersion> getPackageVersions(int packageId);//得到数据包所有历史版本
	//List<PackageVersion> getHistoryVersions(int packageId, int versionId);//得到此数据包历史版本
	List<DataValue> editVersion(int[] datainfoId, int[] type, String[] value);
	List<DataInfo> getDataOfPackage(int packageId);//得到数据包所有数据项
	List<DataInfo> getNonLeafData(List<DataInfo> list);//得到目录数据项
	List<DataInfo> getLeafData(List<DataInfo> list);//得到属性数据项
	int commitVersion(PackageVersion pv, List<DataValue> value_list);
	int isModelOver(int packageId);//查看数据包所属的主模型是否已经over
}
