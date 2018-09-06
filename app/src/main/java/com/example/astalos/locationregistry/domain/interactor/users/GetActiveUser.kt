package com.example.astalos.locationregistry.domain.interactor.users

import com.example.astalos.locationregistry.domain.entities.User
import com.example.astalos.locationregistry.domain.interactor.UseCase
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.IUsersRepository
import com.example.astalos.locationregistry.domain.repository.OneOf
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/6/18.
 */
class GetActiveUser @Inject constructor(private val usersRepository: IUsersRepository): UseCase<User?, UseCase.NoParams>() {
    override suspend fun run(params: NoParams): OneOf<Failure, User?> = usersRepository.getActiveUser()
}