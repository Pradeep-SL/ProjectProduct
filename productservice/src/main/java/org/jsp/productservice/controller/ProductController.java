package org.jsp.productservice.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.jsp.productservice.entity.ProductEntity;
import org.jsp.productservice.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {
	
	
		@Autowired
		private ProductServiceImp productServiceImp;
		
		
		@PostMapping(value = "/saveproductdetail")
	    public ProductEntity saveProduct(@RequestBody ProductEntity entity)
	    {
	    	return productServiceImp.saveProduct(entity);
	    }
		
		
		@GetMapping(value = "/findproductdetailsbyid/{key}")
		public @ResponseBody ProductEntity findProductById(@PathVariable("key") Long id)
		{
			return productServiceImp.findProductById(id);
		}
		
		
		@GetMapping(value = "/findAllProduct")
		public @ResponseBody List<ProductEntity> findAllProduct()
		{
			return productServiceImp.findAllProduct();
		}
		
		
		@GetMapping(value = "/findproductquantity/{key}")
		public @ResponseBody Integer findProductQuantityById(@PathVariable("key") Long id)
		{
			return productServiceImp.findProductQuantityById(id);
		}
		
		
		@GetMapping(value = "/deleteproductbyid/{key}")
		public @ResponseBody ProductEntity deleteProductByid(@PathVariable("key") Long id)
		{
			return productServiceImp.deleteProductByid(id);
		}
		
		
		@GetMapping(value = "/FindAllProductSortedByName")
		public List<ProductEntity> FindAllProductSortedByName()
		{
			List<ProductEntity> findAllProduct = findAllProduct();
			       Comparator<ProductEntity> c1=(e1,e2)->
			       {
			    	   return e1.getName().compareToIgnoreCase(e2.getName());
			       };
			Collections.sort(findAllProduct,c1);
			return findAllProduct;
		}
		
		@GetMapping(value = "/getProductByName")
		public List<ProductEntity> getProductByName(@RequestParam("name") String name) 
		{
			List<ProductEntity> findAllProduct = findAllProduct();
			List<ProductEntity> collect = findAllProduct.stream().filter(e->{
			return	e.getName().equalsIgnoreCase(name);
			}).collect(Collectors.toList());
			return collect;
		}
		
		
		
		//we want to fetch mutilple record in the table we go for requestparam
		@GetMapping(value = "/getProductByRange")
		public List<ProductEntity> getProductByRange(@RequestParam("lower") Double lowerprice,@RequestParam("higher")Double higherprice) 
		{
			
			List<ProductEntity> findAllProduct = findAllProduct();
			List<ProductEntity> collect = findAllProduct.stream().filter(e->{
				return e.getPrice()>=lowerprice && e.getPrice()<=higherprice;
			}).collect(Collectors.toList());
			 return collect;
		}
		
		
		
	
}


