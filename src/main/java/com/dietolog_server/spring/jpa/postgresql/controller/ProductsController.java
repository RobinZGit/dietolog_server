package com.dietolog_server.spring.jpa.postgresql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dietolog_server.spring.jpa.postgresql.model.Info;
import com.dietolog_server.spring.jpa.postgresql.model.Nutrients;
import com.dietolog_server.spring.jpa.postgresql.model.Products;
import com.dietolog_server.spring.jpa.postgresql.repository.ProductsRepository;
import com.dietolog_server.spring.jpa.postgresql.repository.InfoRepository;
import com.dietolog_server.spring.jpa.postgresql.repository.NutrientsRepository;

@CrossOrigin //(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductsController {

	@Autowired
	ProductsRepository productsRepository;

	
	@Autowired
	NutrientsRepository nutrientsRepository;

	@Autowired
	InfoRepository infoRepository;

	@GetMapping("/products")
	public ResponseEntity<List<Products>> getAllProducts(@RequestParam(required = false) String filter, @RequestParam(required = false) Integer sorting ) {
		try {
			List<Products> products = new ArrayList<Products>();
            /* dont del!!
			if (title == null)
				productsRepository.findAll().forEach(products::add);
			else
			    productsRepository.findByNameContaining(title).forEach(products::add);
            */
			productsRepository.findProductsByQuery(filter,sorting).forEach(products::add);

			
			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			//    productsRepository.findByNameContaining(title).forEach(products::add);
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/product_hint")  //TODO..
	public ResponseEntity<String> getProductHint(@RequestParam(required = false) Integer productId) {
		try {
			return new ResponseEntity<>(productsRepository.getProductHint(productId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/nutrients")
	public ResponseEntity<List<Nutrients>> getAllNutriets(@RequestParam(required = false) String par1) {
		try {
			List<Nutrients> nutrients = new ArrayList<>();

			nutrientsRepository.findNutrientsByQuery().forEach(nutrients::add);

			
			if (nutrients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			//nutrientsRepository.findByNameContaining(title).forEach(nutrients::add);
			return new ResponseEntity<>(nutrients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	//findInfoByProductId
	@GetMapping("/info")
	public ResponseEntity<List<Info>> getInfoByProductId(@RequestParam(required = false) String productList) {
		try {
			List<Info> info = new ArrayList<>();

			infoRepository.findInfoByProductList(productList).forEach(info::add);

			
			if (info.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(info, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/recommended_products")
	public ResponseEntity<List<Info>> findRecommendedProducts(@RequestParam(required = false) String nutrientList, 
	                                                          @RequestParam(required = false) String excludedProductstList, 
															  @RequestParam(required = false) Integer topCountRecommendedProducts) {
		try {
			List<Info> info = new ArrayList<>();

			infoRepository.findRecommendedProducts(nutrientList, excludedProductstList, topCountRecommendedProducts).forEach(info::add);

			
			if (info.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(info, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	//--------------------------------------------------------------------

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Products> getProductById(@PathVariable("id") long id) {
		Optional<Products> tutorialData = productsRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	@PostMapping("/products")
	public ResponseEntity<Products> createTutorial(@RequestBody Products product) {
		try {
			Products _product = productsRepository
					.save(new Products(product.getName(), product.getLowercase()));
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
*/
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Products> updateTutorial(@PathVariable("id") long id, @RequestBody Products product) {
		Optional<Products> productData = productsRepository.findById(id);

		if (productData.isPresent()) {
			Products _product = productData.get();
			_product.setName(product.getName());
			_product.setLowercase(product.getLowercase());
			return new ResponseEntity<>(productsRepository.save(_product), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
/*
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			productsRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			productsRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutorials = productsRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
*/
}
