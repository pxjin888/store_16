package cn.btttech.service.comm;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.btttech.entity.Menu;
import cn.btttech.entity.SearchBar;
import cn.btttech.service.base.AbstractService;

@Service("searchBarService")
@Transactional(readOnly = true)
public class SearchBarService extends AbstractService<SearchBar>{

	
	@Transactional(readOnly = false)
	public SearchBar getSearchBarByMenu(Menu menu) {

		SearchBar searchBar = menu.getSearchBar();
		
		return searchBar;
	}

}
