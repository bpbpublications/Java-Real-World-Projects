package dev.davivieira.framework.adapters.output.repository;

import dev.davivieira.framework.adapters.output.data.NoteData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<NoteData, String> {
}
