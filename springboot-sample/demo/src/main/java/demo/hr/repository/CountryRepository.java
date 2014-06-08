package demo.hr.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import demo.hr.entity.Country;

@RepositoryRestResource(collectionResourceRel = "country", path = "country")
interface CountryRepository extends PagingAndSortingRepository<Country, String> {

}
