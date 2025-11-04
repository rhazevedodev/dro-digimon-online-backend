ALTER TABLE digimon
    ADD COLUMN species_id BIGINT,
    ADD CONSTRAINT fk_digimon_species
        FOREIGN KEY (species_id)
        REFERENCES digimon_species(id)
        ON DELETE SET NULL;