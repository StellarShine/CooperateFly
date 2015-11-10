package com.cooperate.fly.service.operator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cooperate.fly.bo.DataInfo;
import com.cooperate.fly.bo.DataValue;
import com.cooperate.fly.bo.ModelInfo;
import com.cooperate.fly.bo.PackageInfo;
import com.cooperate.fly.bo.PackageVersion;
import com.cooperate.fly.bo.User;
import com.cooperate.fly.mapper.DataInfoMapper;
import com.cooperate.fly.mapper.DataValueMapper;
import com.cooperate.fly.mapper.ModelInfoMapper;
import com.cooperate.fly.mapper.PackageInfoMapper;
import com.cooperate.fly.mapper.PackageVersionMapper;
import com.cooperate.fly.mapper.UserMapper;
import com.cooperate.fly.service.operator.DataOperate;
import com.cooperate.fly.util.Utils;

public class DataOperateImpl implements DataOperate {

	int version_id = 0;
	
	@Autowired
	UserMapper userinfomapper;
	@Autowired
	PackageVersionMapper packageversionmapper;
	@Autowired
	PackageInfoMapper packageinfomapper;
	@Autowired
	ModelInfoMapper modelinfomapper;
	@Autowired
	DataInfoMapper datainfomapper;
	@Autowired
	DataValueMapper datavaluemapper;
	
	
	@Override
	public int[] getPackagesOwn(int userId) {
		User info = userinfomapper.selectByPrimaryKey(userId);
		String packages = info.getPackageId();
		String[] packages_array = packages.split(",");
		int len = packages_array.length;
		int[] packages_id = new int[len-1];
		for(int i=0;i<len-1;i++){
			packages_id[i] = Utils.StringToInt(packages_array[i+1]);
		}
		return packages_id;
	}

	@Override
	public void getMessage(String user_name) {
		//start GetMessageThread listen for message whether updated in database
		GetMessageThread mythread = new GetMessageThread(user_name);
		mythread.start();
	}

	@Override
	public PackageVersion createVersion(int packageId,String parent_version_id) {
		PackageVersion pv = new PackageVersion();
		pv.setPackageId(packageId);
		pv.setParentId(parent_version_id);
		return pv;
	}

	@Override
	public List<PackageInfo> getUpPackages(int packageId) {
		PackageInfo the_package = packageinfomapper.selectByPrimaryKey(packageId);
		String pids = the_package.getPid();
		String[] pids_array = pids.split(",");
		List<PackageInfo> list = new ArrayList<PackageInfo>();
		for(int i=1;i<pids_array.length;i++){
			int pid = Utils.StringToInt(pids_array[i]);
			PackageInfo the_P_package = packageinfomapper.selectByPrimaryKey(pid);
			list.add(the_P_package);
		}
		return list;
	}

	@Override
	public List<PackageVersion> getPackageVersions(int packageId) {
		List<PackageVersion> list = new ArrayList<PackageVersion>();
		list = packageversionmapper.selectByPackageId(packageId);
		return list;
	}

//	@Override
//	public List<PackageVersion> getHistoryVersions(int packageId, int versionId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<DataValue> editVersion(int[] dataInfoId, int[] type, String[] value) {
		List<DataValue> data_value_list = new ArrayList<DataValue>();
		for(int i=0;i<value.length;i++){
			DataValue dv = new DataValue();
			dv.setDataInfoId(dataInfoId[i]);
			dv.setValue(type[i]+":"+value[i]);
			data_value_list.add(dv);
		}
		return data_value_list;
	}

	@Override
	public int commitVersion(PackageVersion pv, List<DataValue> value_list) {
		if(isModelOver(pv.getPackageId())==1){
			version_id ++;
			pv.setVersionId(version_id); 
			pv.setSubmitTime(Utils.NowTime());
			packageversionmapper.insert(pv);
		}
		for(int i=0;i<value_list.size();i++){
			value_list.get(i).setVersionId(version_id);
			datavaluemapper.insert(value_list.get(i));
		}
		return 0;
	}
	
	@Override
	public int isModelOver(int packageId){ //1 for over
		PackageInfo the_package = packageinfomapper.selectByPrimaryKey(packageId);
		int modelId = the_package.getModelId();
		ModelInfo modelinfo = modelinfomapper.selectByPrimaryKey(modelId);
		int state = modelinfo.getState();
		return ~state;
	}

	@Override
	public List<DataInfo> getDataOfPackage(int packageId) {
		List<DataInfo> list = new ArrayList<DataInfo>();
		list = datainfomapper.selectByPackageId(packageId);
		return list;
	}

	@Override
	public List<DataInfo> getNonLeafData(List<DataInfo> list) {
		List<DataInfo> nonLeafData_list = new ArrayList<DataInfo>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getType()==0){
				nonLeafData_list.add(list.get(i));
			}
		}
		return nonLeafData_list;
	}

	@Override
	public List<DataInfo> getLeafData(List<DataInfo> list) {
		List<DataInfo> leafData_list = new ArrayList<DataInfo>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getType()!=0){
				leafData_list.add(list.get(i));
			}
		}
		return leafData_list;
	}

}
