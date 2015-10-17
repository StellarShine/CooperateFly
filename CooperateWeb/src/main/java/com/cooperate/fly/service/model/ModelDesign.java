package com.cooperate.fly.service.model;

import java.util.List;

import com.cooperate.fly.bo.Catalog;
import com.cooperate.fly.bo.DataInfo;
import com.cooperate.fly.bo.ModelInfo;
import com.cooperate.fly.bo.PackageInfo;

public interface ModelDesign {
//��ô�ڴ����ڵ�ʱ֪���丸�ڵ㣿
	int createNoneLeafCatalogNode(String nodeName, int parentId);//����Ŀ¼����ڵ�
	int createModelNode(String nodeName, int parentId);//������ģ�ͽڵ�
	int createPackageNode(String nodeName, int parentId);//������ݰ�ڵ�
	
	int deleteCatalogNode(int nodeId);//ɾ��Ŀ¼�ڵ�
	int updateCatalogNode(Catalog catalog);//�޸�Ŀ¼�ڵ�
	int createNoneLeafDataNode(String nodeName, int nodeId);//������������ڵ�,�����nodeId��Ŀ¼���Խڵ㣬Ҳ����packageId
	int createLeafDataNode(String nodeName, int type, int nodeId);//������������Խڵ�
	int deleteDataNode(int nodeId);//ɾ�������ڵ�
	int updateDataNode(DataInfo dataInfo);//�޸������ڵ�
	
	void modelStart(ModelInfo modelInfo);//ģ�ͷ���
	void modelOver(ModelInfo modelInfo);//ģ�͹ر�

	List<PackageInfo> getPackages(int modelId);//�õ���ģ����������ݰ����Ϣ
	//PackageInfo[] getOtherPackages(PackageInfo[] packages, int packageId);//�õ���ģ����������ݰ���Ϣ
	//int[] getPackagesId(int modelId);//�õ���ģ����������ݰ��Id
	int checkFiliation(int modelId);//ģ������ݰ���ϵ���,����1��ʾ���ͨ��
	
	//����������ݰ�����ι�ϵ
	int updateSonIds(int modelId);//�޸Ļ򴴽�һ����ݰ���Ϣʱ����
	
}
