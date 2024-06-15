package dev.davivieira.repository;

import dev.davivieira.entity.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends CrudRepository<File, String> {

    Optional<File> findById(String Id);
}
