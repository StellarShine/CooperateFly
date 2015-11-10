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
	List<PackageInfo> getUpPackages(int packageId);//�ڴ����汾�ݸ�ǰ��Ҫѡ��˰汾��Ӧ���������ݰ��汾
	List<PackageVersion> getPackageVersions(int packageId);//�õ����ݰ�������ʷ�汾
	//List<PackageVersion> getHistoryVersions(int packageId, int versionId);//�õ������ݰ���ʷ�汾
	List<DataValue> editVersion(int[] datainfoId, int[] type, String[] value);
	List<DataInfo> getDataOfPackage(int packageId);//�õ����ݰ�����������
	List<DataInfo> getNonLeafData(List<DataInfo> list);//�õ�Ŀ¼������
	List<DataInfo> getLeafData(List<DataInfo> list);//�õ�����������
	int commitVersion(PackageVersion pv, List<DataValue> value_list);
	int isModelOver(int packageId);//�鿴���ݰ���������ģ���Ƿ��Ѿ�over
}
