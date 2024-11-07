package com.spring.boot.exploration.dropdown.service;

import com.spring.boot.exploration.dropdown.model.Fraternity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FraternityService extends GenericDropdownService<Fraternity> {
    @PersistenceContext
    private EntityManager entityManager;

    private static final String FILE_PATH = "src/main/resources/dump/tbl_fraternity.csv";

    @Transactional
    public List<Fraternity> bulkCreate(List<Fraternity> fraternities) {
        List<Fraternity> successfullyPersisted = new ArrayList<>();
        for (Fraternity fraternity : fraternities) {
            Fraternity existingFraternity = entityManager.createQuery("SELECT f FROM Fraternity f WHERE f.name = :name", Fraternity.class)
                    .setParameter("name", fraternity.getName())
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (existingFraternity != null) {
                System.err.println("Fraternity with name '" + fraternity.getName() + "' already exists. Skipping...");
                continue;
            }
            entityManager.persist(fraternity);
            successfullyPersisted.add(fraternity);
        }
        entityManager.flush();
        entityManager.clear();
        return successfullyPersisted;
    }

    public void exportFraternityDataToCsv() {
        List<Fraternity> fraternities = entityManager.createQuery("SELECT f FROM Fraternity f", Fraternity.class)
                .getResultList();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(FILE_PATH),
                CSVFormat.DEFAULT
                        .withDelimiter(';')
                        .withQuote('"')
                        .withHeader("id", "name"))) {
            for (Fraternity fraternity : fraternities) {
                printer.printRecord(fraternity.getId(), fraternity.getName());
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
