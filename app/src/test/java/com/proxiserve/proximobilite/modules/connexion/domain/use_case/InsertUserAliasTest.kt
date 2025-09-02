package com.proxiserve.proximobilite.modules.connexion.domain.use_case

import com.proxiserve.proximobilite.modules.connexion.domain.model.InvalidUserException
import com.proxiserve.proximobilite.modules.connexion.domain.model.UserAlias
import com.proxiserve.proximobilite.modules.connexion.domain.repository.UserAliasRepository
import com.proxiserve.proximobilite.modules.connexion.domain.utils.ConnexionConst
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*

/**
 * Created by dloriot on 20/11/2024.
 */
class InsertUserAliasTest {
    private var repository = mock(UserAliasRepository::class.java)
    private var insertUserAlias = InsertUserAlias(repository)

    private val userAliasOk: UserAlias = UserAlias(
        id = "0h123456789",
        matricule = "123456789",
        login = "dloriot",
        nom = "Loriot",
        prenom = "David",
        idAdonix = "A123456",
        groupe = "TECH"
    )
    private val userAliasWithoutId: UserAlias = UserAlias(
        id = "",
        matricule = "123456789",
        login = "dloriot",
        nom = "Loriot",
        prenom = "David",
        idAdonix = "A123456",
        groupe = "TECH"
    )
    private val userAliasWithoutName: UserAlias = UserAlias(
        id = "0h123456789",
        matricule = "123456789",
        login = "dloriot",
        nom = "",
        prenom = "David",
        idAdonix = "A123456",
        groupe = "TECH"
    )
    private val userAliasWithoutFirstname: UserAlias = UserAlias(
        id = "0h123456789",
        matricule = "123456789",
        login = "dloriot",
        nom = "Loriot",
        prenom = "",
        idAdonix = "A123456",
        groupe = "TECH"
    )
    private val userAliasWithoutGroup: UserAlias = UserAlias(
        id = "0h123456789",
        matricule = "123456789",
        login = "dloriot",
        nom = "Loriot",
        prenom = "David",
        idAdonix = "A123456",
        groupe = ""
    )

    @Test
    fun `Insert UserAlias with complete infos, should call insert UserAlias`() = runTest {
        insertUserAlias(userAliasOk)
        verify(repository).insertUserAlias(userAliasOk)
    }

    @Test
    fun `Insert UserAlias with id missing, should throw InvalidUserException with id missing message`() = runTest {
        val exception = assertThrows<InvalidUserException> { insertUserAlias(userAliasWithoutId) }
        assertEquals(ConnexionConst.USER_ALIAS_ID_EMPTY, exception.message)
    }

    @Test
    fun `Insert UserAlias with name missing, should throw InvalidUserException with name missing message`() = runTest {
        val exception = assertThrows<InvalidUserException> { insertUserAlias(userAliasWithoutName) }
        assertEquals(ConnexionConst.USER_ALIAS_NAME_EMPTY, exception.message)
    }

    @Test
    fun `Insert UserAlias with firstname missing, should throw InvalidUserException with firstname missing message`() = runTest {
        val exception = assertThrows<InvalidUserException> { insertUserAlias(userAliasWithoutFirstname) }
        assertEquals(ConnexionConst.USER_ALIAS_FIRSTNAME_EMPTY, exception.message)
    }

    @Test
    fun `Insert UserAlias with group missing, should throw InvalidUserException with group missing message`() = runTest {
        val exception = assertThrows<InvalidUserException> { insertUserAlias(userAliasWithoutGroup) }
        assertEquals(ConnexionConst.USER_ALIAS_GROUP_EMPTY, exception.message)
    }
}