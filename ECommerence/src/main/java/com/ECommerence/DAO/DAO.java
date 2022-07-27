package com.ECommerence.DAO;

import java.util.List;
import java.util.Optional;

public  interface DAO<T> { 
	
	Optional<T> get(long id) ;  
	
	List<T> getAll() ;  
	
	void Update(T t , String [] parameter) ; 
	
	void Delete(T t ) ;  
	
	void Add(T  t) ;  
	
	
}
