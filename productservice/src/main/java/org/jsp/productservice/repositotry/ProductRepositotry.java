package org.jsp.productservice.repositotry;

import java.util.List;

import org.jsp.productservice.entity.ProductEntity;

public interface ProductRepositotry {

    public ProductEntity saveProduct(ProductEntity entity);
	
	public ProductEntity findProductById(Long id);
	
	public List<ProductEntity> findAllProduct();
	
	public Integer findProductQuantityById(Long id);
	
	public ProductEntity deleteProductByid(Long id);
	
	public ProductEntity Product(ProductEntity entity);
}
