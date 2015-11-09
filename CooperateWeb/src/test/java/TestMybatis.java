import com.cooperate.fly.bo.*;
import com.cooperate.fly.mapper.CatalogMapper;
import com.cooperate.fly.mapper.DataValueMapper;
import com.cooperate.fly.mapper.PackageInfoMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cooperate.fly.service.model.ModelDesign;
import com.cooperate.fly.service.user.DataExistsException;
import com.cooperate.fly.service.user.RoleMenuService;
import com.cooperate.fly.service.user.SysMenuService;
import com.cooperate.fly.service.user.UserExistsException;
import com.cooperate.fly.service.user.UserGroupService;
import com.cooperate.fly.service.user.UserRoleService;
import com.cooperate.fly.service.user.UserService;
import com.cooperate.fly.util.PwdHelper;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath*:conf/database/applicationContext.xml" })
public class TestMybatis extends AbstractJUnit4SpringContextTests{

	private static Logger logger=Logger.getLogger(TestMybatis.class);
	@Autowired
	private ModelDesign mDesign;
	@Autowired
	private UserGroupService userGroupService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private RoleMenuService roleMenuService;

	@Autowired
	private CatalogMapper mapper;

	@Autowired
	private PackageInfoMapper packageInfoMapper;

	@Autowired
	private DataValueMapper dataValueMapper;
	/*@Test
	public void test1(){
			
		mDesign.createNoneLeafCatalogNode("飞行器主模型", 0);
		mDesign.createNoneLeafCatalogNode("(分类)型号A", 1);
		mDesign.createNoneLeafCatalogNode("(分类)型号B", 2);
	}*/
	@Test
	public void testInsert(){
		Catalog log = new Catalog();
		log.setName("test");
		int id = mapper.insert(log);
		System.out.println(id);
		id = mapper.selectLastInsertId();
		System.out.println(id);
	}

	@Test
	public void testInsertPackage(){
		PackageInfo new_package = new PackageInfo();
		new_package.setId(2);
		new_package.setName("test");
		new_package.setPid("");
		new_package.setSid("");
		new_package.setDirectorId(0);
		new_package.setModelId(1);
		packageInfoMapper.insert(new_package);
	}

	@Test
	public void testUpdate(){
		PackageInfo new_package = new PackageInfo();
		new_package.setId(72);
		new_package.setName("zhongwen");
		new_package.setPid("");
		new_package.setSid("");
		new_package.setDirectorId(0);
		new_package.setModelId(1);
		new_package.setExtraAttributes("属性值");
		packageInfoMapper.updateByPrimaryKey(new_package);
	}

	@Test
	public void testSelectData(){
		DataValue value = dataValueMapper.selectByInfoId(2);
		System.out.print(value.getValue());
	}


	
	/*@Test
	public void testUserGroup(){
		UserGroup ug=new UserGroup();
		ug.setGroupName("游客");
		ug.setGroupNo(4444);
		ug.setRemark("测试添加");
		try {
			userGroupService.save(ug);
		} catch (DataExistsException e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void testUser(){
		User user=new User();
		user.setGroupId(4);
		user.setRoleId(4);
		user.setUserName("visitor");
		user.setPassword(PwdHelper.encryptPassword("123456"));
		user.setPackageId("#");
		try {
			userService.save(user);
		} catch (UserExistsException e) {
			e.printStackTrace();
		}
		/*User user1=new User();
		user1.setGroupId(002);
		user1.setRoleId(2);
		user1.setUserName("dataDesigner");
		user1.setPassword(PwdHelper.encryptPassword("123456"));
		user1.setPackageId("#");
		try {
			userService.save(user1);
		} catch (UserExistsException e) {
			e.printStackTrace();
		}
		User user2=new User();
		user2.setGroupId(003);
		user2.setRoleId(3);
		user2.setUserName("engineer");
		user2.setPassword(PwdHelper.encryptPassword("123456"));
		user2.setPackageId("#001");
		try {
			userService.save(user2);
		} catch (UserExistsException e) {
			e.printStackTrace();
		}*/
	}
	
	/*@Test
	public void testUserRole(){
		UserRole ur=new UserRole();
		ur.setRoleName("系统管理员");
		ur.setRemark("进行系统的人员管理和权限分配等工作");
		UserRole ur1=new UserRole();
		ur1.setRoleName("数据区管理员");
		ur1.setRemark("进行模型策划的工作");
		UserRole ur2=new UserRole();
		ur2.setRoleName("工程师");
		ur2.setRemark("系统工作完成方案模型");
		UserRole ur3=new UserRole();
		ur3.setRoleName("领导查看");
		ur3.setRemark("主要进行视图等的查看工作");
		userRoleService.save(ur);
		userRoleService.save(ur1);
		userRoleService.save(ur2);
		userRoleService.save(ur3);
		
	}*/
	
	/*@Test
	public void testSysMenu(){
		SysMenu sm=new SysMenu();
		sm.setMenuName("系统管理员菜单栏");
		sm.setMenuUrl("/systemAdmin");
		
		SysMenu sm1=new SysMenu();
		sm1.setMenuName("数据区菜单栏");
		sm1.setMenuUrl("/dataAdmin");
		SysMenu sm2=new SysMenu();
		sm2.setMenuName("工程师菜单栏");
		sm2.setMenuUrl("/engineer");
		SysMenu sm3=new SysMenu();
		sm3.setMenuName("浏览菜单栏");
		sm3.setMenuUrl("/visitor");
		
		sysMenuService.save(sm);
		sysMenuService.save(sm1);
		sysMenuService.save(sm2);
		sysMenuService.save(sm3);
	}*/
	
	/*@Test
	public void testRoleMenu(){
		RoleMenu rm=new RoleMenu();
		rm.setId(1);
		rm.setMenuId(1);
		rm.setRoleId(1);
		
		RoleMenu rm2=new RoleMenu();
		rm2.setId(2);
		rm2.setMenuId(2);
		rm2.setRoleId(2);
		
		RoleMenu rm3=new RoleMenu();
		rm3.setId(3);
		rm3.setMenuId(3);
		rm3.setRoleId(3);
		
		RoleMenu rm4=new RoleMenu();
		rm4.setId(4);
		rm4.setMenuId(4);
		rm4.setRoleId(4);
		
		roleMenuService.save(rm);
		roleMenuService.save(rm2);
		roleMenuService.save(rm3);
		roleMenuService.save(rm4);
	}*/
}
