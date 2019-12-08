package com.weile.webflux.reactor.repository;

import com.weile.webflux.reactor.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auth weile
 * @Time 2019/12/8 17:14
 * @Description TODO
 **/
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {
}
