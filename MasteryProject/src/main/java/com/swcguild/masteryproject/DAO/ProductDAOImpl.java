/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.DAO;

import com.swcguild.masteryproject.DTO.OrderDTO;
import com.swcguild.masteryproject.DTO.ProductDTO;
import com.swcguild.masteryproject.service.PersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kaminahar
 */
public class ProductDAOImpl implements ProductDAO {
private Map<String, ProductDTO> products = new HashMap<>(); 

    public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = "::";
    
    private void loadProducts() throws PersistenceException{
        Scanner scanner;
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        }catch(FileNotFoundException e){
            throw new PersistenceException("Error: Could not load product data into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;
      
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            String productName = currentTokens[0];
            BigDecimal costPerSqft = new BigDecimal(currentTokens[1]);
            BigDecimal laborPerSqft = new BigDecimal(currentTokens[2]);
  
        ProductDTO currentProductDTO = new ProductDTO(productName, costPerSqft, laborPerSqft);
        products.put(currentProductDTO.getProductName(), currentProductDTO);
        }
       
        
        scanner.close();
    }
 
    @Override
    public List<ProductDTO> getAllProducts()  throws PersistenceException {
    loadProducts();
    return new ArrayList<ProductDTO>(products.values());
    }

    @Override
    public ProductDTO getProductByName(String getProductName) throws PersistenceException {
        //makes sure just the first letter of the word is capital, the 0 is what lets the program 
        //know its the first word (think of how things are numbered in an array list, 
        //so first letter of the word is 0, second letter is 1, third letter is 2, etc.
        String firstLetter = getProductName.substring(0,1);
        firstLetter = firstLetter.toUpperCase();
        //makes sure the rest of the word is lowercase.
        String restOfWord = getProductName.substring(1);
        restOfWord = restOfWord.toLowerCase();
        //puts the two letters together and 
        //makes sure the first is capital and the rest is lowercase 
        String wholeWord = firstLetter + restOfWord;
        ProductDTO productNameIgnoreCasing = products.get(wholeWord);
        return productNameIgnoreCasing;
        
        
   //return products.get(getName); //gets the product by name from the hashmap
    }

}


