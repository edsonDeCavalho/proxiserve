package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.model.InvalidUserException
import com.proxiserve.proximobilite.modules.connexion.domain.model.User
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserRepository
import com.proxiserve.proximobilite.modules.connexion.domain.utils.ConnexionConst
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*

/**
 * Created by dloriot on 18/11/2024.
 */
class InsertUserTest {

    private var repository = mock(UserRepository::class.java)
    private var insertUser = InsertUser(repository)

    private val userOk: User = User(
        id = "0h123456789",
        matricule = "123456789",
        login = "dloriot",
        nom = "Loriot",
        prenom = "David",
        idAdonix = "A123456",
        groupe = "TECH"
    )
    private val userWithoutId: User = User(
        id = "",
        matricule = "123456789",
        login = "dloriot",
        nom = "Loriot",
        prenom = "David",
        idAdonix = "A123456",
        groupe = "TECH"
    )
    private val userWithoutName: User = User(
        id = "0h123456789",
        matricule = "123456789",
        login = "dloriot",
        nom = "",
        prenom = "David",
        idAdonix = "A123456",
        groupe = "TECH"
    )
    private val userWithoutFirstname: User = User(
        id = "0h123456789",
        matricule = "123456789",
        login = "dloriot",
        nom = "Loriot",
        prenom = "",
        idAdonix = "A123456",
        groupe = "TECH"
    )
    private val userWithoutGroup: User = User(
        id = "0h123456789",
        matricule = "123456789",
        login = "dloriot",
        nom = "Loriot",
        prenom = "David",
        idAdonix = "A123456",
        groupe = ""
    )

    @Test
    fun `Insert User with complete infos, should call insert User`() = runTest {
        insertUser(userOk)
        verify(repository).insertUser(userOk)
    }

    @Test
    fun `Insert User with id missing, should throw InvalidUserException with id missing message`() = runTest {
        val exception = assertThrows<InvalidUserException> { insertUser(userWithoutId) }
        assertEquals(ConnexionConst.USER_ID_EMPTY, exception.message)
    }

    @Test
    fun `Insert User with name missing, should throw InvalidUserException with name missing message`() = runTest {
        val exception = assertThrows<InvalidUserException> { insertUser(userWithoutName) }
        assertEquals(ConnexionConst.USER_NAME_EMPTY, exception.message)
    }

    @Test
    fun `Insert User with firstname missing, should throw InvalidUserException with firstname missing message`() = runTest {
        val exception = assertThrows<InvalidUserException> { insertUser(userWithoutFirstname) }
        assertEquals(ConnexionConst.USER_FIRSTNAME_EMPTY, exception.message)
    }

    @Test
    fun `Insert User with group missing, should throw InvalidUserException with group missing message`() = runTest {
        val exception = assertThrows<InvalidUserException> { insertUser(userWithoutGroup) }
        assertEquals(ConnexionConst.USER_GROUP_EMPTY, exception.message)
    }
}