package com.example.backend.util.Services;

import com.example.backend.util.Class.Dokument;
import com.example.backend.util.Class.Organizacja;
import com.example.backend.util.Repositories.DocumentRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private OrganizationService organizationService;

    public DocumentService(){

    }

    public boolean saveDocument(Dokument dokument){
        try{
            documentRepository.save(dokument);
        } catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDocument(int id){
        try {
            documentRepository.deleteById(id);
        } catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public Dokument findDocumentById(int id){
        return documentRepository.findById(id).orElse(null);
    }
}
