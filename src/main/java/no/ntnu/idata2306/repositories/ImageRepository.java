package no.ntnu.idata2306.repositories;

import no.ntnu.idata2306.model.Image;
import no.ntnu.idata2306.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * repositories for images.
 *
 * @author Edvin Astad
 * @version 04.04.2023
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {
}
