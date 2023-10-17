package com.example.springreferencia.services.impl;

//SERVICE PADRAO DO MVC
// maven -> spring-boot-starter-webflux (netty) ou spring-boot-starter-web (tomcat)
/*
@Service
public class PersonMongoDBServiceImpl implements PersonService {

    private final PersonMongoDBRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonMongoDBServiceImpl.class);

    public PersonMongoDBServiceImpl(PersonMongoDBRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<PersonDTO> findAll() {
        LOGGER.info("Buscando todos os recursos no Mongodb");
        List<PersonMongoDB> list = repository.findAll();
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
            ObjectId objectId = new ObjectId(id);
            Optional<PersonMongoDB> personMongoDBOptional = repository.findById(objectId);
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
