package com.spring.boot.exploration.util;

import com.spring.boot.exploration.dropdown.model.AffiliationType;
import com.spring.boot.exploration.dropdown.model.Fraternity;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.spring.boot.exploration.dropdown.model.Genre;
import com.spring.boot.exploration.dropdown.repository.GenericDropdownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DropdownDataInitializationService {
    private final GenericDropdownRepository<Genre> genreRepository;
    private final GenericDropdownRepository<AffiliationType> affiliationTypeRepository;
    private final GenericDropdownRepository<Fraternity> fraternityRepository;

    @PostConstruct
    @Transactional
    public void init() {
        if (isGenreTableEmpty()) {
            loadGenreFromCsv();
        }
        if (isAffiliationTypeTableEmpty()) {
            loadAffiliationTypeFromCsv();
        }
        if (isFraternityTableEmpty()) {
            loadFraternityFromCsv();
        }
    }

    private boolean isGenreTableEmpty() {
        try {
            return !genreRepository.existsById(1L);
        } catch (Exception e) {
            System.err.println("Error checking if genre table is empty: " + e.getMessage());
            return true; // Consider it empty if there's an error
        }
    }

    private boolean isAffiliationTypeTableEmpty() {
        try {
            return !affiliationTypeRepository.existsById(1L);
        } catch (Exception e) {
            System.err.println("Error checking if affiliation type table is empty: " + e.getMessage());
            return true; // Consider it empty if there's an error
        }
    }

    private boolean isFraternityTableEmpty() {
        try {
            return !fraternityRepository.existsById(1L);
        } catch (Exception e) {
            System.err.println("Error checking if fraternity table is empty: " + e.getMessage());
            return true; // Consider it empty if there's an error
        }
    }

    @Transactional
    public void loadGenreFromCsv() {
        List<Genre> genres = new ArrayList<>();
        List<CSVRecord> records = parseCsvFile("/dump/tbl_genre.csv");
        for (CSVRecord csvRecord : records) {
            Long id = Long.parseLong(csvRecord.get("id"));
            String name = csvRecord.get("name");

            Genre genre = new Genre();
            genre.setId(id);
            genre.setName(name);

            genres.add(genre);
        }
        genreRepository.saveAll(genres);
    }

    @Transactional
    public void loadAffiliationTypeFromCsv() {
        List<AffiliationType> affiliationTypes = new ArrayList<>();
        List<CSVRecord> records = parseCsvFile("/dump/tbl_affiliation_type.csv");
        for (CSVRecord csvRecord : records) {
            Long id = Long.parseLong(csvRecord.get("id"));
            String name = csvRecord.get("name");

            AffiliationType affiliationType = new AffiliationType();
            affiliationType.setId(id);
            affiliationType.setName(name);

            affiliationTypes.add(affiliationType);
        }
        affiliationTypeRepository.saveAll(affiliationTypes);
    }

    @Transactional
    public void loadFraternityFromCsv() {
        List<Fraternity> fraternities = new ArrayList<>();
        List<CSVRecord> records = parseCsvFile("/dump/tbl_fraternity.csv");
        for (CSVRecord csvRecord : records) {
            Long id = Long.parseLong(csvRecord.get("id"));
            String name = csvRecord.get("name");

            Fraternity fraternity = new Fraternity();
            fraternity.setId(id);
            fraternity.setName(name);

            fraternities.add(fraternity);
        }
        fraternityRepository.saveAll(fraternities);
    }

    private List<CSVRecord> parseCsvFile(String filePath) {
        List<CSVRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(filePath), StandardCharsets.UTF_8))) {

            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withDelimiter(';')
                    .withFirstRecordAsHeader()
                    .withIgnoreEmptyLines()
            );

            for (CSVRecord csvRecord : csvParser) {
                records.add(csvRecord);
            }
            records.sort(Comparator.comparingLong(record -> Long.parseLong(record.get("id"))));

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        return records;
    }
}
