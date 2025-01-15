package ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ubb.model.Form;

@Repository
public interface FormRepository  extends JpaRepository<Form,Long> {
}
