package com.example.demo.dummy;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface DummyRepository extends CrudRepository<Dummy,Long> {}
