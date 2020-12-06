package com.jun.springboot.product.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jun.springboot.SpringbootApplication;
import com.jun.springboot.product.dao.ProductDao;
import com.jun.springboot.product.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired(required=false)
	private ProductDao productDao;

	@Override
	public Collection<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Integer save(Product product, String username) {
		product.setCreateBy(username);
		product.setCreateDate(new Date());
		product.setIsActive(1);
		if (product.getBuyPrice() != null && product.getSellingPrice() != null) {
			BigDecimal result = product.getSellingPrice().subtract(product.getBuyPrice());
			product.setMargin(result);
		}

		if (product.getMarketingId() != null) {
			if (product.getMarketingId() == 1) {
				product.setMarketing("OLX");
			} else if (product.getMarketingId() == 2) {
				product.setMarketing("BUKA LAPAK");
			} else if (product.getMarketingId() == 3) {
				product.setMarketing("TOKO PEDIA");
			} else {
				product.setMarketing("LAIN-LAIN");
			}

		}

		product.setModifiedBy(username);
		product.setModifiedDate(new Date());
		product.setStatus(1);

		Integer countName = this.checkName(product.getName());
		if (countName > 0) {
			product.setName(product.getName());
			return 1;
		} else {
			productDao.save(product);
			return 2;
		}
	}

	private Integer checkName(String name) {
		Collection<Product> list = productDao.search(name);
		Integer countName = 0;
		if (list == null) {
			countName = 0;
		} else {
			countName = list.size();
		}
		return countName;
	}

	@Override
	public Product update(Product product) {
		Product productBefore = productDao.findOne(product.getId());

		productBefore.setModifiedBy("developer");
		productBefore.setModifiedDate(new Date());
		productBefore.setBuyDate(product.getBuyDate());
		productBefore.setDiscription(product.getDiscription());
		productBefore.setBuyPrice(product.getBuyPrice());
		productBefore.setMarketingId(product.getMarketingId());
		productBefore.setName(product.getName());
		productBefore.setSellingDate(product.getSellingDate());
		productBefore.setSellingPrice(product.getSellingPrice());
		if (product.getMarketingId() != null) {
			if (product.getMarketingId() == 1) {
				productBefore.setMarketing("OLX");
			} else if (product.getMarketingId() == 2) {
				productBefore.setMarketing("BUKA LAPAK");
			} else if (product.getMarketingId() == 3) {
				productBefore.setMarketing("TOKO PEDIA");
			} else {
				productBefore.setMarketing("LAIN-LAIN");
			}

		}
		if (product.getBuyPrice() != null && product.getSellingPrice() != null) {
			BigDecimal result = product.getSellingPrice().subtract(product.getBuyPrice());
			productBefore.setMargin(result);
		}
		productDao.update(productBefore);
		return productBefore;
	}

	@Override
	public Product findOne(Integer id) {
		return productDao.findOne(id);
	}

	@Override
	public List<String> countMarketplace() {
		return productDao.countMarketplace();
	}

	@Override
	public Product saveTerjual(Product product) {
		Product productBefore = productDao.findOne(product.getId());
		productBefore.setStatus(2);
		productDao.update(productBefore);
		return productBefore;
	}

}
