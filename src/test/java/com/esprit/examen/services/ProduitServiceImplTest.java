package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;

public class ProduitServiceImplTest {

	@Mock
	private ProduitRepository produitRepository;

	@InjectMocks
	private ProduitServiceImpl produitService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRetrieveAllProduits() {
		List<Produit> produits = new ArrayList<>();
		produits.add(new Produit(1L, "P1", "Produit 1", 10.0f, new Date(), new Date(), null, null, null));
		produits.add(new Produit(2L, "P2", "Produit 2", 20.0f, new Date(), new Date(), null, null, null));
		when(produitRepository.findAll()).thenReturn(produits);

		List<Produit> retrievedProduits = produitService.retrieveAllProduits();
		assertEquals(2, retrievedProduits.size());
		verify(produitRepository, times(1)).findAll();
	}

	@Test
	public void testAddProduit() {
		Produit produit = new Produit(1L, "P1", "Produit 1", 10.0f, new Date(), new Date(), null, null, null);
		when(produitRepository.save(produit)).thenReturn(produit);

		Produit addedProduit = produitService.addProduit(produit);
		assertEquals(produit, addedProduit);
		verify(produitRepository, times(1)).save(produit);
	}

	@Test
	public void testDeleteProduit() {
		Long produitId = 1L;
		produitService.deleteProduit(produitId);
		verify(produitRepository, times(1)).deleteById(produitId);
	}

	@Test
	public void testUpdateProduit() {
		Produit produit = new Produit(1L, "P1", "Produit 1", 10.0f, new Date(), new Date(), null, null, null);
		when(produitRepository.save(produit)).thenReturn(produit);

		Produit updatedProduit = produitService.updateProduit(produit);
		assertEquals(produit, updatedProduit);
		verify(produitRepository, times(1)).save(produit);
	}

	@Test
	public void testRetrieveProduit() {
		Long produitId = 1L;
		Produit produit = new Produit(produitId, "P1", "Produit 1", 10.0f, new Date(), new Date(), null, null, null);
		when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));

		Produit retrievedProduit = produitService.retrieveProduit(produitId);
		assertEquals(produit, retrievedProduit);
		verify(produitRepository, times(1)).findById(produitId);
	}


}
