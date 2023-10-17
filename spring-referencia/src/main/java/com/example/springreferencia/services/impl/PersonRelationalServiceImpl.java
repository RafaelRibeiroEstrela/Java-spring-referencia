package com.example.springreferencia.services.impl;

//SERVICE PADRAO DO MVC
// maven -> spring-boot-starter-webflux (netty) ou spring-boot-starter-web (tomcat)
/*
@Service
public class PersonRelationalServiceImpl implements PersonService {

    private final PersonRelationalRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRelationalServiceImpl.class);

    public PersonRelationalServiceImpl(PersonRelationalRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<PersonDTO> findAll() {
        LOGGER.info("Buscando todos os recursos no Mongodb");
        List<PersonRelational> list = repository.findAll();
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        return list.stream()
                .map(PersonDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO findById(String id) {
        LOGGER.info("Buscando recurso no Mongodb com chave = {}", id);
        try {
            Long objectId = Long.valueOf(id);
            Optional<PersonRelational> personMongoDBOptional = repository.findById(objectId);
            if (personMongoDBOptional.isEmpty()) {
                LOGGER.error("Não foi encontrado um recurso com a chave = {}", id);
                throw new ResourceNotFoundException("Não foi encontrado um recurso com a chave = " + id);
            }
            return new PersonDTO(personMongoDBOptional.get());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Erro: {}", e.getMessage());
            throw new InvalidRuleException(e.getMessage());
        }
    }
}
 */
