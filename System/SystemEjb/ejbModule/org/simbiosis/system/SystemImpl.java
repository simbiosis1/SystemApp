package org.simbiosis.system;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.simbiosis.system.model.Auth;
import org.simbiosis.system.model.Branch;
import org.simbiosis.system.model.Company;
import org.simbiosis.system.model.Config;
import org.simbiosis.system.model.Currency;
import org.simbiosis.system.model.FastMenu;
import org.simbiosis.system.model.Menu;
import org.simbiosis.system.model.Role;
import org.simbiosis.system.model.RoleMenu;
import org.simbiosis.system.model.Session;
import org.simbiosis.system.model.SubBranch;
import org.simbiosis.system.model.User;

@Stateless
@Remote(ISystem.class)
public class SystemImpl implements ISystem {

	@PersistenceContext(unitName = "SystemEjb", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	OrgHelper orgHelper = new OrgHelper();
	UserHelper userHelper = new UserHelper();

	@SuppressWarnings({ "unchecked" })
	Session findSessionByName(String key) {
		Query sql = em.createNamedQuery("findSessionByName");
		sql.setParameter("name", key);
		List<Session> allSession = sql.getResultList();
		if (allSession.size() == 1) {
			return allSession.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	Session findSessionByUser(long userId) {
		Query sql = em.createNamedQuery("findSessionByUser");
		sql.setParameter("userId", userId);
		List<Session> allSession = sql.getResultList();
		if (allSession.size() == 1) {
			return allSession.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	User getUserMatch(String user, String password) {
		Query sql = em.createNamedQuery("getUserMatch");
		sql.setParameter("user", user);
		sql.setParameter("password", password);
		List<User> users = sql.getResultList();
		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	List<FastMenu> listFastMenuByUser(long userId) {
		Query sql = em.createNamedQuery("listFastMenuByUser");
		sql.setParameter("userId", userId);
		List<FastMenu> userFastMenu = sql.getResultList();
		return userFastMenu;
	}

	@SuppressWarnings("unchecked")
	private List<RoleMenu> listRoleMenu(long roleId) {
		Query sql = em.createNamedQuery("listRoleMenu");
		sql.setParameter("roleId", roleId);
		List<RoleMenu> userMenu = sql.getResultList();
		return userMenu;
	}

	@Override
	public long saveUser(UserDto userDto) {
		User user = userHelper.createUserFromDto(em, userDto);
		if (userDto.getId() == 0) {
			em.persist(user);
		} else {
			em.merge(user);
		}
		return user.getId();
	}

	@Override
	public UserDto getUser(long id) {
		User user = em.find(User.class, id);
		return userHelper.createUserToDto(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> listActiveUsers(long company, long branch) {
		List<UserDto> allUser = new ArrayList<UserDto>();
		String strQuery = branch == 0 ? "listActiveUsers1" : "listActiveUsers2";
		Query sql = em.createNamedQuery(strQuery);
		if (branch == 0) {
			sql.setParameter("company", company);
		} else {
			sql.setParameter("branch", branch);
		}
		List<User> users = sql.getResultList();
		for (User user : users) {
			allUser.add(userHelper.createUserToDto(user));
		}
		return allUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> listUsers(long company, long branch) {
		List<UserDto> allUser = new ArrayList<UserDto>();
		String strQuery = branch == 0 ? "listUsers1" : "listUsers2";
		Query sql = em.createNamedQuery(strQuery);
		if (branch == 0) {
			sql.setParameter("company", company);
		} else {
			sql.setParameter("branch", branch);
		}
		List<User> users = sql.getResultList();
		for (User user : users) {
			allUser.add(userHelper.createUserToDto(user));
		}
		return allUser;
	}

	private static final char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private String byteArray2Hex(byte[] bytes) {
	    StringBuffer sb = new StringBuffer(bytes.length * 2);
	    for(final byte b : bytes) {
	        sb.append(hex[(b & 0xF0) >> 4]);
	        sb.append(hex[b & 0x0F]);
	    }
	    return sb.toString();
	}
	
	private String createHash(String stringToEncrypt){
		try {
			MessageDigest messageDigest;
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(stringToEncrypt.getBytes());
			return byteArray2Hex(messageDigest.digest()); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return stringToEncrypt;
	}
	
	@Override
	public SessionDto login(String userName, String password) {
		// Check validity of this user and get userDto
		User user = getUserMatch(userName, password);
		if (user != null) {
			// Check whether it has already a session
			Session session = findSessionByUser(user.getId());
			// Create session if it don't have
			if (session == null) {
				session = new Session();
				session.setId(0);
				//
				Date sessionBegin = new Date();
				String encryptedString = userName + ":" + password + ":"
						+ sessionBegin;
				//
				session.setName(createHash(encryptedString));
				session.setBegin(sessionBegin);
				session.setValid(1);
				session.setUser(user);
				em.persist(session);
			} else {
				// Use the existing session or
				// discard it (if only one session per user allowed)
			}
			SessionDto sessionDto = new SessionDto();
			sessionDto.setId(session.getId());
			sessionDto.setUserId(session.getUser().getId());
			sessionDto.setName(session.getName());
			sessionDto.setUserName(user.getName());
			sessionDto.setUserRealName(user.getRealName());
			return sessionDto;
		}
		return null;
	}

	@Override
	public void logout(String key) {
		Session session = findSessionByName(key);
		if (session != null) {
			session.setValid(0);
			session.setEnd(new Date());
			em.persist(session);
		}
	}

	@Override
	public long saveMenu(MenuDto menuDto) {
		Menu menu = new Menu();
		menu.setId(menuDto.getId());
		menu.setTitle(menuDto.getTitle());
		menu.setPlace(menuDto.getPlace());
		menu.setLevel(menuDto.getLevel());
		Menu parent = em.find(Menu.class, menuDto.getParentId());
		menu.setParent(parent);
		if (menuDto.getId() == 0) {
			em.persist(menu);
		} else {
			em.merge(menu);
		}
		return menu.getId();
	}

	@Override
	public long saveFastMenu(FastMenuDto fastMenuDto) {
		FastMenu fastMenu = new FastMenu();
		fastMenu.setId(fastMenuDto.getId());
		User user = em.find(User.class, fastMenuDto.getUserId());
		fastMenu.setUser(user);
		Menu menu = em.find(Menu.class, fastMenuDto.getMenuId());
		fastMenu.setMenu(menu);
		if (fastMenuDto.getId() == 0) {
			em.persist(fastMenu);
		} else {
			em.merge(fastMenu);
		}
		return fastMenu.getId();
	}

	@Override
	public void deleteRoleMenu(long roleMenu) {
		RoleMenu toDel = em.find(RoleMenu.class, roleMenu);
		em.remove(toDel);
	}

	@Override
	public long saveRoleMenu(RoleMenuDto roleMenuDto) {
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setId(roleMenuDto.getId());
		Role role = em.find(Role.class, roleMenuDto.getRoleId());
		roleMenu.setRole(role);
		Menu menu = em.find(Menu.class, roleMenuDto.getMenuId());
		roleMenu.setMenu(menu);
		if (roleMenuDto.getId() == 0) {
			em.persist(roleMenu);
		} else {
			em.merge(roleMenu);
		}
		return roleMenu.getId();
	}

	@Override
	public List<FastMenuDto> listUserFastMenu(long userId) {
		List<FastMenuDto> result = new ArrayList<FastMenuDto>();
		List<FastMenu> userFastMenu = listFastMenuByUser(userId);
		for (FastMenu fastMenu : userFastMenu) {
			FastMenuDto menuDto = new FastMenuDto();
			menuDto.setId(fastMenu.getId());
			menuDto.setMenuId(fastMenu.getMenu().getId());
			menuDto.setTitle(fastMenu.getMenu().getTitle());
			menuDto.setLink(createLink(fastMenu.getMenu()));
			menuDto.setUserId(fastMenu.getUser().getId());
			result.add(menuDto);
		}
		return result;
	}

	String createLink(Menu menu) {
		String place = menu.getPlace();
		if (place == null) {
			place = "";
		}
		String link = new String();
		if ((menu.getLevel() == 3) && !place.isEmpty()) {
			String links[] = place.split("\\.");
			link = (links.length > 0) ? "#" + links[links.length - 1] + ":"
					: "";
			link = "/" + menu.getModule() + "/" + link;
		}
		return link;
	}

	@Override
	public List<MenuDto> listUserMenuByRole(long roleId) {
		List<MenuDto> listMenu = new ArrayList<MenuDto>();
		List<RoleMenu> listUserMenu = listRoleMenu(roleId);
		for (RoleMenu userMenu : listUserMenu) {
			MenuDto menuDto = new MenuDto();
			Menu menu = userMenu.getMenu();
			menuDto.setId(menu.getId());
			menuDto.setTitle(menu.getTitle());
			menuDto.setPlace(menu.getPlace());
			menuDto.setLink(createLink(menu));
			Menu parent = menu.getParent();
			if (parent != null) {
				menuDto.setParentTitle(parent.getTitle());
				Menu grandParent = parent.getParent();
				if (grandParent != null) {
					menuDto.setGrandParentTitle(grandParent.getTitle());
				} else {
					menuDto.setGrandParentTitle("");
				}
			} else {
				menuDto.setParentTitle("");
				menuDto.setGrandParentTitle("");
			}
			listMenu.add(menuDto);
		}
		return listMenu;
	}

	@SuppressWarnings("unchecked")
	private Map<Long, MenuDto> listAllMenu() {
		Query sql = em.createNamedQuery("listAllMenu");
		List<Menu> menus = sql.getResultList();
		Map<Long, MenuDto> result = new HashMap<Long, MenuDto>();
		for (Menu menu : menus) {
			MenuDto newMenu = new MenuDto();
			newMenu.setId(menu.getId());
			newMenu.setLevel(menu.getLevel());
			newMenu.setTitle(menu.getTitle());
			newMenu.setParentTitle(menu.getParent().getTitle());
			newMenu.setGrandParentTitle(menu.getParent().getParent().getTitle());
			newMenu.setPlace(menu.getPlace());
			result.put(menu.getId(), newMenu);
		}
		return result;
	}

	@Override
	public List<MenuDto> listAllRoleMenu(long role) {
		Map<Long, MenuDto> mapAllMenu = listAllMenu();
		List<RoleMenu> listRoleMenu = listRoleMenu(role);
		for (RoleMenu roleMenu : listRoleMenu) {
			MenuDto mapped = mapAllMenu.get(roleMenu.getMenu().getId());
			if (mapped != null) {
				mapped.setActive(1);
				mapped.setOtherId(roleMenu.getId());
				mapAllMenu.put(mapped.getId(), mapped);
			}
		}
		List<MenuDto> result = new ArrayList<MenuDto>();
		result.addAll(mapAllMenu.values());
		return result;
	}

	@Override
	public List<MenuDto> listAllRoleMenuByModule(long role, String module) {
		List<MenuDto> result = new ArrayList<MenuDto>();
		List<MenuDto> menus = listAllRoleMenu(role);
		for (MenuDto menu : menus) {
			if (menu.getPlace() != null && !menu.getPlace().isEmpty()) {
				if (menu.getPlace().contains(module)) {
					result.add(menu);
				}
			}
		}
		return result;
	}

	@Override
	public UserDto getUserFromSession(String key) {
		Session session = findSessionByName(key);
		if (session != null) {
			return userHelper.createUserToDto(session.getUser());
		}
		return null;
	}

	@Override
	public SessionDto getSession(String key) {
		Session session = findSessionByName(key);
		if (session != null) {
			SessionDto sessionDto = new SessionDto();
			sessionDto.setId(session.getId());
			sessionDto.setName(session.getName());
			sessionDto.setUserId(session.getUser().getId());
			sessionDto.setUserName(session.getUser().getName());
			sessionDto.setUserRealName(session.getUser().getRealName());
			return sessionDto;
		}
		return null;
	}

	@Override
	public long saveCurrency(CurrencyDto currencyDto) {
		Currency currency = new Currency();
		currency.setId(currencyDto.getId());
		currency.setCode(currencyDto.getCode());
		currency.setName(currencyDto.getName());
		if (currencyDto.getId() == 0) {
			em.persist(currency);
		} else {
			em.merge(currency);
		}
		return currency.getId();
	}

	private CurrencyDto createCurrencyToDto(Currency currency) {
		CurrencyDto currencyDto = new CurrencyDto();
		currencyDto.setId(currency.getId());
		currencyDto.setCode(currency.getCode());
		currencyDto.setName(currency.getName());
		return currencyDto;
	}

	@Override
	public CurrencyDto getCurrency(long id) {
		Currency currency = em.find(Currency.class, id);
		return createCurrencyToDto(currency);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CurrencyDto> listCurrencty() {
		List<CurrencyDto> result = new ArrayList<CurrencyDto>();
		Query sql = em.createNamedQuery("listCurrency");
		List<Currency> listCurrency = sql.getResultList();
		for (Currency currency : listCurrency) {
			result.add(createCurrencyToDto(currency));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDto> listCompany() {
		List<CompanyDto> result = new ArrayList<CompanyDto>();
		Query sql = em.createNamedQuery("listCompany");
		List<Company> listCompany = sql.getResultList();
		for (Company company : listCompany) {
			result.add(orgHelper.createCompanyToDto(company));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BranchDto> listBranchByCompany(long companyId) {
		List<BranchDto> result = new ArrayList<BranchDto>();
		Query sql = em.createNamedQuery("listBranchByCompany");
		sql.setParameter("companyId", companyId);
		List<Branch> listBranch = sql.getResultList();
		for (Branch branch : listBranch) {
			result.add(orgHelper.createBranchToDto(branch));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubBranchDto> listSubBranch(long company, long branch) {
		List<SubBranchDto> result = new ArrayList<SubBranchDto>();
		String strSql = (branch == 0) ? "listSubBranch1" : "listSubBranch2";
		Query sql = em.createNamedQuery(strSql);
		if (branch == 0) {
			sql.setParameter("company", company);
		} else {
			sql.setParameter("branch", branch);
		}
		List<SubBranch> listSubBranch = sql.getResultList();
		for (SubBranch subBranch : listSubBranch) {
			result.add(orgHelper.createSubBranchToDto(subBranch));
		}
		return result;
	}

	@Override
	public CompanyDto getCompany(long id) {
		Company company = em.find(Company.class, id);
		return orgHelper.createCompanyToDto(company);
	}

	@Override
	public BranchDto getBranch(long id) {
		Branch branch = em.find(Branch.class, id);
		if (branch == null) {
			branch = em.find(Branch.class, 3L);
		}
		return orgHelper.createBranchToDto(branch);
	}

	MenuDto createMenuToDto(Menu menu) {
		MenuDto menuDto = new MenuDto();
		menuDto.setId(menu.getId());
		menuDto.setTitle(menu.getTitle());
		menuDto.setPlace(menu.getPlace());
		menuDto.setLevel(menu.getLevel());
		if (menu.getParent() != null) {
			Menu parent = menu.getParent();
			menuDto.setParentId(parent.getId());
			menuDto.setParentTitle(parent.getTitle());
			if (parent.getParent() != null) {
				Menu grandParent = parent.getParent();
				menuDto.setGrandParentTitle(grandParent.getTitle());
			}
		}
		return menuDto;
	}

	@Override
	public MenuDto getMenu(long id) {
		Menu menu = em.find(Menu.class, id);
		return createMenuToDto(menu);
	}

	@SuppressWarnings("unchecked")
	@Override
	public MenuDto getMenuByPlace(String place) {
		Query sql = em.createNamedQuery("getMenuByPlace");
		sql.setParameter("place", place);
		List<Menu> result = sql.getResultList();
		if (result.size() > 0) {
			return createMenuToDto(result.get(0));
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	Config findConfig(long company, String key) {
		Query sql = em.createNamedQuery("getConfigValue");
		sql.setParameter("company", company);
		sql.setParameter("key", key);
		List<Config> result = sql.getResultList();
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public void saveConfig(long companyId, ConfigDto configDto) {
		Config oldConfig = findConfig(companyId, configDto.getKey());
		Config config = null;
		if (oldConfig != null) {
			config = oldConfig;
			config.setStrValue(configDto.getStrValue());
			config.setIntValue(configDto.getIntValue());
			config.setLongValue(configDto.getLongValue());
			config.setDoubleValue(configDto.getDoubleValue());
			em.merge(config);
		} else {
			config = new Config();
			config.setId(configDto.getId());
			Company company = em.find(Company.class, companyId);
			config.setCompany(company);
			config.setKey(configDto.getKey());
			config.setStrValue(configDto.getStrValue());
			config.setIntValue(configDto.getIntValue());
			config.setLongValue(configDto.getLongValue());
			config.setDoubleValue(configDto.getDoubleValue());
			em.persist(config);
		}
	}

	@Override
	public ConfigDto getConfig(long company, String key) {
		Config config = findConfig(company, key);
		if (config != null) {
			ConfigDto configDto = new ConfigDto();
			configDto.setCompany(company);
			configDto.setKey(key);
			configDto.setStrValue(config.getStrValue());
			configDto.setIntValue(config.getIntValue());
			configDto.setLongValue(config.getLongValue());
			configDto.setDoubleValue(config.getDoubleValue());
			return configDto;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> listRoles(long company) {
		List<RoleDto> result = new ArrayList<RoleDto>();
		Query sql = em.createNamedQuery("listRoles");
		sql.setParameter("company", company);
		List<Role> roles = sql.getResultList();
		for (Role role : roles) {
			RoleDto dto = new RoleDto();
			dto.setId(role.getId());
			dto.setName(role.getName());
			dto.setDescription(role.getDescription());
			dto.setCompany(role.getCompany().getId());
			result.add(dto);
		}
		return result;
	}

	@Override
	public long saveRole(RoleDto dto) {
		Role role = new Role();
		role.setId(dto.getId());
		role.setName(dto.getName());
		role.setDescription(dto.getDescription());
		Company company = em.find(Company.class, dto.getCompany());
		role.setCompany(company);
		if (role.getId() == 0) {
			em.persist(role);
		} else {
			em.merge(role);
		}
		return role.getId();
	}

	@Override
	public String getDataPath(long companyId) {
		Company company = em.find(Company.class, companyId);
		return company.getDatapath();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyDto> findCompanyByCode(String code) {
		List<CompanyDto> result = new ArrayList<CompanyDto>();
		Query qry = em.createNamedQuery("findCompanyByCode");
		qry.setParameter("code", code);
		List<Company> companies = qry.getResultList();
		for (Company company : companies) {
			result.add(orgHelper.createCompanyToDto(company));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BranchDto> findBranchByCode(long companyId, String code) {
		List<BranchDto> result = new ArrayList<BranchDto>();
		Query qry = em.createNamedQuery("findBranchByCode");
		qry.setParameter("companyId", companyId);
		qry.setParameter("code", code);
		List<Branch> branches = qry.getResultList();
		for (Branch branch : branches) {
			result.add(orgHelper.createBranchToDto(branch));
		}
		return result;
	}

	@Override
	public void deleteFastMenu(long id) {
		FastMenu fm = em.find(FastMenu.class, id);
		em.remove(fm);
	}

	AuthDto createAuthToDto(Auth auth) {
		AuthDto dto = new AuthDto();
		dto.setId(auth.getId());
		dto.setCompany(auth.getCompany().getId());
		dto.setBranch(auth.getBranch().getId());
		dto.setBranchName(auth.getBranch().getName());
		dto.setDescription(auth.getDescription());
		dto.setTimestamp(auth.getTimestamp());
		dto.setUser(auth.getUser().getId());
		dto.setUserName(auth.getUser().getName() + " - "
				+ auth.getUser().getRealName());
		dto.setLevel(auth.getLevel());
		if (auth.getAuthorizer() != null) {
			dto.setAuthorizer(auth.getAuthorizer().getId());
			dto.setAuthorizerName(auth.getAuthorizer().getName() + " - "
					+ auth.getAuthorizer().getRealName());
		} else {
			dto.setAuthorizer(0);
		}
		dto.setActive(auth.getActive());
		dto.setKey(auth.getKey());
		return dto;
	}

	Auth createAuthFromDto(AuthDto dto) {
		Auth auth = new Auth();
		auth.setId(dto.getId());
		Branch branch = em.find(Branch.class, dto.getBranch());
		auth.setCompany(branch.getCompany());
		auth.setBranch(branch);
		auth.setDescription(dto.getDescription());
		auth.setTimestamp(dto.getTimestamp());
		auth.setLevel(dto.getLevel());
		User user = em.find(User.class, dto.getUser());
		auth.setUser(user);
		user = em.find(User.class, dto.getAuthorizer());
		auth.setAuthorizer(user);
		auth.setActive(dto.getActive());
		auth.setKey(dto.getKey());
		return auth;
	}

	@Override
	public long saveAuth(AuthDto dto) {
		Auth auth = createAuthFromDto(dto);
		if (auth.getId() == 0) {
			em.persist(auth);
		} else {
			em.merge(auth);
		}
		return auth.getId();
	}

	@Override
	public AuthDto getAuth(long id) {
		Auth auth = em.find(Auth.class, id);
		return createAuthToDto(auth);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AuthDto> listAuth(long company, long branch, int level) {
		List<AuthDto> result = new ArrayList<AuthDto>();
		Query qry = em.createNamedQuery(branch == 0 ? "listAuthByCompany"
				: "listAuthByBranch");
		qry.setParameter("level", level);
		if (branch == 0) {
			qry.setParameter("company", company);
		} else {
			qry.setParameter("branch", branch);
		}
		List<Auth> auths = qry.getResultList();
		for (Auth auth : auths) {
			result.add(createAuthToDto(auth));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AuthDto getAuthByKey(String key) {
		Query qry = em.createNamedQuery("getAuthByKey");
		qry.setParameter("key", key);
		List<Auth> result = qry.getResultList();
		if (result.size() > 0) {
			return createAuthToDto(result.get(0));
		}
		return null;
	}

	@Override
	public void disposeAuth(long id) {
		Auth auth = em.find(Auth.class, id);
		auth.setActive(0);
		em.persist(auth);
	}

	@Override
	public void authorize(long id, long authorizer) {
		Auth auth = em.find(Auth.class, id);
		User user = em.find(User.class, authorizer);
		auth.setAuthorizer(user);
		em.persist(auth);
	}

	@Override
	public long saveBranch(BranchDto dto) {
		Branch branch = orgHelper.createBranchFromDto(em, dto);
		if (dto.getId() == 0) {
			em.persist(branch);
		} else {
			em.merge(branch);
		}
		return branch.getId();
	}

	@Override
	public long saveSubBranch(SubBranchDto dto) {
		SubBranch subBranch = orgHelper.createSubBranchFromDto(em, dto);
		if (dto.getId() == 0) {
			em.persist(subBranch);
		} else {
			em.merge(subBranch);
		}
		return subBranch.getId();
	}

	@Override
	public SubBranchDto getSubBranch(long id) {
		SubBranch sb = em.find(SubBranch.class, id);
		return orgHelper.createSubBranchToDto(sb);
	}

}
