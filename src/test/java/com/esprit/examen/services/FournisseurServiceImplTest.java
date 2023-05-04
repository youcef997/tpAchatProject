package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import com.esprit.examen.repositories.DetailFournisseurRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.repositories.FournisseurRepository;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FournisseurServiceImplTest {


    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFournisseur() {
        Fournisseur fournisseur = new Fournisseur();

        fournisseur.setCode("123");
        fournisseur.setLibelle("Test fournisseur");
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);

        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setEmail("test@test.com");
        detailFournisseur.setDateDebutCollaboration(new Date());
        detailFournisseur.setAdresse("123, rue du Test");
        detailFournisseur.setMatricule("M123");
        fournisseur.setDetailFournisseur(detailFournisseur);

        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        assertEquals(fournisseur.getCode(), result.getCode());
        assertEquals(fournisseur.getLibelle(), result.getLibelle());
        assertEquals(fournisseur.getCategorieFournisseur(), result.getCategorieFournisseur());
        assertEquals(fournisseur.getDetailFournisseur().getEmail(), result.getDetailFournisseur().getEmail());
        assertEquals(fournisseur.getDetailFournisseur().getDateDebutCollaboration(),
                result.getDetailFournisseur().getDateDebutCollaboration());
        assertEquals(fournisseur.getDetailFournisseur().getAdresse(), result.getDetailFournisseur().getAdresse());
        assertEquals(fournisseur.getDetailFournisseur().getMatricule(), result.getDetailFournisseur().getMatricule());
    }

    /////////////////////

    @Test
    public void testSaveDetailFournisseur() {
        // Create a dummy Fournisseur object
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setDetailFournisseur(new DetailFournisseur());
        fournisseur.getDetailFournisseur().setDateDebutCollaboration(new Date());
        fournisseur.getDetailFournisseur().setEmail("test@gmail.com");

        // Create a mock DetailFournisseur object to be returned by the repository
        DetailFournisseur savedDetailFournisseur = new DetailFournisseur();
        savedDetailFournisseur.setIdDetailFournisseur(1L);
        savedDetailFournisseur.setDateDebutCollaboration(new Date());
        savedDetailFournisseur.setEmail("test@gmail.com");

        // Configure the mock DetailFournisseurRepository to return the mock object
        when(detailFournisseurRepository.save(fournisseur.getDetailFournisseur()))
                .thenReturn(savedDetailFournisseur);

        // Call the method to be tested
        // DetailFournisseur result = fournisseurService.saveDetailFournisseur(fournisseur);

        // Verify that the repository's save() method was called with the expected DetailFournisseur object
        // verify(detailFournisseurRepository).save(fournisseur.getDetailFournisseur());

        // Verify that the returned DetailFournisseur object has the expected values
        // assertEquals(savedDetailFournisseur.getIdDetailFournisseur(), result.getIdDetailFournisseur());
        // assertEquals(savedDetailFournisseur.getDateDebutCollaboration(), result.getDateDebutCollaboration());
        // assertEquals(savedDetailFournisseur.getEmail(), result.getEmail());
    }

    public void testUpdateFournisseur() {
        // Create a mock Fournisseur object
        Fournisseur fournisseur = mock(Fournisseur.class);

        // Create a mock DetailFournisseur object
        DetailFournisseur detailFournisseur = mock(DetailFournisseur.class);

        // Set the DetailFournisseur object as the detailFournisseur property of the Fournisseur object
        when(fournisseur.getDetailFournisseur()).thenReturn(detailFournisseur);

        // Call the updateFournisseur method of the service
        fournisseurService.updateFournisseur(fournisseur);

        // Verify that the saveDetailFournisseur method was called with the DetailFournisseur object
        verify(detailFournisseurRepository).save(detailFournisseur);

        // Verify that the save method was called with the Fournisseur object
        verify(fournisseurRepository).save(fournisseur);
    }


    @Test
    public void testDeleteFournisseur() {
        // Create a dummy Fournisseur object with an ID of 1
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);

        // Configure the mock FournisseurRepository to return the dummy Fournisseur object
        when(fournisseurRepository.findById(fournisseur.getIdFournisseur()))
                .thenReturn(Optional.of(fournisseur));

        // Call the method to be tested
        fournisseurService.deleteFournisseur(fournisseur.getIdFournisseur());

        // Verify that the repository's deleteById() method was called with the expected ID
        verify(fournisseurRepository).deleteById(fournisseur.getIdFournisseur());
    }


    @Test
    public void testRetrieveFournisseur() {
        // Create a mock Fournisseur object
        Fournisseur fournisseur = mock(Fournisseur.class);

        // Set the id of the Fournisseur object
        long id = 1L;
        fournisseur.setIdFournisseur(id);

        // Configure the mock FournisseurRepository to return the mock object
        when(fournisseurRepository.findById(id)).thenReturn(Optional.of(fournisseur));

        // Call the method to be tested
        Fournisseur result = fournisseurService.retrieveFournisseur(id);

        // Verify that the repository's findById() method was called with the expected id
        verify(fournisseurRepository).findById(id);

        // Verify that the returned Fournisseur object is the expected one
        assertEquals(fournisseur, result);
    }



}
