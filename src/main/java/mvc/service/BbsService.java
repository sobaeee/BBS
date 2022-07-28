package mvc.service;

import java.util.ArrayList;

import mvc.dao.BbsDAO;
import mvc.domain.Bbs;

public class BbsService {
	
	BbsDAO dao = new BbsDAO();
	
	public ArrayList<Bbs> getList(int pageNumber) {
		
		return dao.getList(pageNumber);
	}
	

	public boolean nextPage(int pageNumber) {
		
		return dao.nextPage(pageNumber);
	}


	public int write(Bbs bbs) {
		// TODO Auto-generated method stub
		return dao.write(bbs);
	}
	
}
