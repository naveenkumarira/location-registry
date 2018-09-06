package com.example.astalos.locationregistry.model.repository.memory

import com.example.astalos.locationregistry.domain.entities.User
import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.OneOf
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author Tomasz Czura on 9/6/18.
 */
@RunWith(MockitoJUnitRunner::class)
class MemoryUsersRepositoryTests {

    @Test
    fun `createUser should add user`() {
        val repository = MemoryUsersRepository()
        repository.createUser(User(id = null, name = "TestUser"))
        val result = repository.users()
        val users = (result as OneOf.Success<List<User>>)
        result.isSuccess shouldEqual true
        users.data.size shouldEqual 1
        users.data[0] shouldEqual User(id = 1, name = "TestUser")
    }

    @Test
    fun `removeUser should remove user`() {
        val repository = MemoryUsersRepository()
        repository.createUser(User(id = null, name = "TestUser"))
        repository.removeUser(1)
        val result = repository.users()
        val users = (result as OneOf.Success<List<User>>)
        result.isSuccess shouldEqual true
        users.data.size shouldEqual 0
    }

    @Test
    fun `setActiveUser should set active user`() {
        val repository = MemoryUsersRepository()
        repository.createUser(User(id = null, name = "TestUser"))
        repository.setActiveUser(1)
        val result = repository.getActiveUser()
        val user = (result as OneOf.Success<User>).data

        result.isSuccess shouldEqual true
        user.id!! shouldEqual 1
    }

    @Test
    fun `setActiveUser should return Failure when no user with passed id`() {
        val repository = MemoryUsersRepository()
        val result = repository.setActiveUser(1)
        result.isError shouldEqual true

        val failure = (result as OneOf.Error<Failure>).error
        failure shouldBeInstanceOf Failure.NoUserFailure::class.java
    }

    @Test
    fun `getActiveUser should return null if no active user`() {
        val repository = MemoryUsersRepository()
        val result = repository.getActiveUser()
        val user = (result as OneOf.Success<User?>).data
        user?.shouldBeNull()
    }

    @Test
    fun `editUser should save user`() {
        val repository = MemoryUsersRepository()
        repository.createUser(User(id = null, name = "TestUser"))
        repository.editUser(User(1, "TestUser2"))
        val result = repository.users()
        result.isSuccess shouldEqual true

        val users = (result as OneOf.Success<List<User>>).data
        users.size shouldEqual 1
        users[0].name shouldEqual "TestUser2"
    }
}
