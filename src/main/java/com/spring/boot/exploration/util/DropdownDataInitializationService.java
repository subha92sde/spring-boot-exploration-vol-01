package com.spring.boot.exploration.util;

import com.spring.boot.exploration.dropdown.model.AffiliationType;
import com.spring.boot.exploration.dropdown.model.Fraternity;
import com.spring.boot.exploration.dropdown.model.GenericDropdownEntity;
import com.spring.boot.exploration.dropdown.service.GenericDropdownService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.spring.boot.exploration.dropdown.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DropdownDataInitializationService {
    private final GenericDropdownService<Genre> genreService;
    private final GenericDropdownService<AffiliationType> affiliationTypeService;
    private final GenericDropdownService<Fraternity> fraternityService;

    @PostConstruct
    @Transactional
    public void init() {
        if (genreService.isTableEmpty(Genre.class)) {
            loadFromCsv(Genre.class, "/dump/tbl_genre.csv", genreService);
        }
        if (affiliationTypeService.isTableEmpty(AffiliationType.class)) {
            loadFromCsv(AffiliationType.class, "/dump/tbl_affiliation_type.csv", affiliationTypeService);
        }
        if (fraternityService.isTableEmpty(Fraternity.class)) {
            loadFromCsv(Fraternity.class, "/dump/tbl_fraternity.csv", fraternityService);
        }
    }

    private <T extends GenericDropdownEntity> void loadFromCsv(Class<T> entityClass, String filePath, GenericDropdownService<T> service) {
        List<T> entities = new ArrayList<>();
        List<CSVRecord> records = parseCsvFile(filePath);
        for (CSVRecord csvRecord : records) {
            try {
                T entity = entityClass.getDeclaredConstructor().newInstance();
                Long id = Long.parseLong(csvRecord.get("id"));
                String name = csvRecord.get("name");

                entity.setId(id);
                entity.setName(name);

                entities.add(entity);
            } catch (Exception e) {
                System.err.println("Error creating entity: " + e.getMessage());
            }
        }
        service.saveAll(entities);
    }

    private List<CSVRecord> parseCsvFile(String filePath) {
        List<CSVRecord> records = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("CSV file not found: " + filePath);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withDelimiter(';')
                        .withFirstRecordAsHeader()
                        .withIgnoreEmptyLines()
                );

                for (CSVRecord csvRecord : csvParser) {
                    records.add(csvRecord);
                }
                records.sort(Comparator.comparingLong(record -> Long.parseLong(record.get("id"))));
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());  // File not found error
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return records;
    }
}
